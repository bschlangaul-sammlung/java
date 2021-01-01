package org.bschlangaul.db;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.bschlangaul.antlr.RelationenSchemaBaseListener;
import org.bschlangaul.antlr.RelationenSchemaLexer;
import org.bschlangaul.antlr.RelationenSchemaParser;
import org.bschlangaul.helfer.Tex;

class AntlrListener extends RelationenSchemaBaseListener {

  private RelationenSchema schema = new RelationenSchema();

  private Relation aktuelleRelation;

  public AntlrListener(RelationenSchema schema) {
    this.schema = schema;
  }

  @Override
  public void enterRelation(RelationenSchemaParser.RelationContext ctx) {
    String relationsName = ctx.relationenName().getText();
    aktuelleRelation = new Relation(relationsName);
    aktuelleRelation.schema = schema;
  }

  public void enterAttribut(RelationenSchemaParser.AttributContext ctx) {
    Attribut attribut = new Attribut("");
    attribut.schema = schema;
    attribut.relation = aktuelleRelation;

    if (ctx.fremdSchluessel() != null) {
      attribut.name = ctx.fremdSchluessel().attributName().getText();
      attribut.fremdRelationenName = ctx.fremdSchluessel().relationenName().getText();
    } else {
      attribut.name = ctx.attributName().getText();
    }

    attribut.istPrimaer = ctx.istPrimaer() != null ? true : false;
    aktuelleRelation.setzeAttribut(attribut);
  }

  public void exitRelation(RelationenSchemaParser.RelationContext ctx) {
    schema.setzeRelation(aktuelleRelation);
  }

}

class Attribut {
  String name;
  String fremdRelationenName;
  String fremdRelationenAttribut;
  boolean istPrimaer;
  RelationenSchema schema;
  Relation relation;

  public Attribut(String name) {
    this.name = name;
  }

  private boolean nameEnthält(String... suchTexte) {
    for (String suchText : suchTexte) {
      if (name.toLowerCase().indexOf(suchText.toLowerCase()) > -1)
        return true;
    }
    return false;
  }

  public String rateSqlTypeVonName() {
    if (nameEnthält("name"))
      return "varchar(20)";
    if (istPrimaer)
      return "integer";
    if (nameEnthält("zeit", "time", "jahr", "year", "id"))
      return "integer";
    if (nameEnthält("datum", "date"))
      return "date";
    return "varchar(50)";
  }

  public String baueSqlCreate() {
    String ausgabe = "  " + name;
    ausgabe += " " + rateSqlTypeVonName();
    if (istPrimaer && relation.gibAnzahlPrimärSchlüssel() == 1)
      ausgabe += " PRIMARY KEY";

    if (fremdRelationenName != null) {
      Attribut[] primärSchlüssel = schema.gibRelation(fremdRelationenName).gibPrimärSchlüssel();
      String primär = primärSchlüssel[0].name;
      ausgabe += " REFERENCES " + fremdRelationenName + "(" + primär + ")";
    }

    return ausgabe;
  }

  public String baueTeX() {
    if (fremdRelationenName != null)
      return Tex.makro("liFremd", String.format("%s[%s]", name, fremdRelationenName));

    if (istPrimaer)
      return Tex.makro("liPrimaer", name);

    return name;
  }
}

class Relation {
  LinkedHashMap<String, Attribut> attribute;
  String name;
  RelationenSchema schema;

  public Relation(String name) {
    this.name = name;
    attribute = new LinkedHashMap<String, Attribut>();
  }

  public Attribut gibAttribut(String name) {
    return attribute.get(name);
  }

  public void setzeAttribut(String attributName) {
    attribute.put(attributName, new Attribut(attributName));
  }

  public void setzeAttribut(Attribut attribut) {
    attribute.put(attribut.name, attribut);
  }

  private String baueMehrteiligenPrimärSchlüssel() {
    String ausgabe = "";
    if (gibAnzahlPrimärSchlüssel() < 2)
      return ausgabe;
    Attribut[] primärSchlüssel = gibPrimärSchlüssel();
    for (int i = 0; i < primärSchlüssel.length; i++) {
      ausgabe += primärSchlüssel[i].name;
      if (i < primärSchlüssel.length - 1)
        ausgabe += ", ";
    }
    return String.format(",\n  PRIMARY KEY (%s)", ausgabe);
  }

  public String baueSqlCreate() {
    String[] attributeSql = RelationenSchema.sammleTextVonMethode(attribute, "baueSqlCreate");
    String ausgabe = String.format("CREATE TABLE %s (\n%s%s\n);\n", name, String.join(",\n", attributeSql),
        baueMehrteiligenPrimärSchlüssel());
    return ausgabe;
  }

  public String baueTeX() {
    return String.format("\\liRelation{%s}{%s}", name,
        String.join(", ", RelationenSchema.sammleTextVonMethode(attribute, "baueTeX")));
  }

  public String baueSqlInsert() {
    return String.format("INSERT INTO %s VALUES\n  ();\n", name);
  }

  public int gibAnzahlPrimärSchlüssel() {
    int anzahl = 0;
    for (Attribut attribut : attribute.values()) {
      if (attribut.istPrimaer)
        anzahl++;
    }
    return anzahl;
  }

  public Attribut[] gibPrimärSchlüssel() {
    Attribut[] primärSchlüssel = new Attribut[gibAnzahlPrimärSchlüssel()];
    int i = 0;
    for (Attribut attribut : attribute.values()) {
      if (attribut.istPrimaer) {
        primärSchlüssel[i] = attribut;
        i++;
      }
    }
    return primärSchlüssel;
  }
}

public class RelationenSchema {

  LinkedHashMap<String, Relation> relationen;

  public RelationenSchema() {
    relationen = new LinkedHashMap<String, Relation>();
  }

  public RelationenSchema(String format) {
    relationen = new LinkedHashMap<String, Relation>();
    leseTextFormat(format);
  }

  @SuppressWarnings("rawtypes")
  public static String[] sammleTextVonMethode(Map map, String methode) {
    return sammleText(map, methode, true);
  }

  @SuppressWarnings("rawtypes")
  public static String[] sammleTextVonAttribut(Map map, String attribut) {
    return sammleText(map, attribut, false);
  }

  @SuppressWarnings("rawtypes")
  public static String[] sammleText(Map map, String name, boolean vonMethode) {
    String[] ausgabe = new String[map.size()];
    int i = 0;
    for (Object objekt : map.values()) {
      try {
        if (vonMethode) {
          ausgabe[i] = (String) objekt.getClass().getDeclaredMethod(name).invoke(objekt);
        } else {
          ausgabe[i] = (String) objekt.getClass().getDeclaredField(name).get(objekt);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      i++;
    }
    return ausgabe;
  }

  public void setzeRelation(Relation relation) {
    relationen.put(relation.name, relation);
  }

  public Attribut gibAttribut(String relationenName, String attributName) {
    return relationen.get(relationenName).attribute.get(attributName);
  }

  public Relation gibRelation(String relationenName) {
    return relationen.get(relationenName);
  }

  private void leseTextFormat(String inhalt) {
    RelationenSchemaLexer serverGraphLexer = new RelationenSchemaLexer(CharStreams.fromString(inhalt));
    CommonTokenStream tokens = new CommonTokenStream(serverGraphLexer);
    RelationenSchemaParser parser = new RelationenSchemaParser(tokens);

    ParseTreeWalker walker = new ParseTreeWalker();
    AntlrListener antlrListener = new AntlrListener(this);
    walker.walk(antlrListener, parser.einstiegsPunkt());
  }

  public String[] gibRelationenNamen() {
    String[] namen = relationen.keySet().toArray(new String[0]);
    Arrays.sort(namen);
    return namen;
  }

  private String vereinigeRelationenMethodenAusgaben(String methode) {
    return String.join("\n", RelationenSchema.sammleTextVonMethode(relationen, methode));
  }

  public String baueSqlCreate() {
    return vereinigeRelationenMethodenAusgaben("baueSqlCreate");
  }

  public String baueSqlInsert() {
    return vereinigeRelationenMethodenAusgaben("baueSqlInsert");
  }

  public String baueÜbungsdatenbank() {
    return "% Übungsdatenbank: \n" + Tex.umgebungArgument("minted", vereinigeRelationenMethodenAusgaben("baueSqlCreate")
        + "\n" + vereinigeRelationenMethodenAusgaben("baueSqlInsert"), "sql");
  }

  public String baueTeX() {
    return Tex.umgebung("liRmodell", vereinigeRelationenMethodenAusgaben("baueTeX"));
  }

}

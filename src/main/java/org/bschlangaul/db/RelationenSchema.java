package org.bschlangaul.db;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.bschlangaul.antlr.RelationenSchemaBaseListener;
import org.bschlangaul.antlr.RelationenSchemaLexer;
import org.bschlangaul.antlr.RelationenSchemaParser;

class AntlrListener extends RelationenSchemaBaseListener {

  private RelationenSchema relationenSchema = new RelationenSchema();

  private Relation aktuelleRelation;

  public AntlrListener(RelationenSchema relationenSchema) {
    this.relationenSchema = relationenSchema;
  }

  @Override
  public void enterRelation(RelationenSchemaParser.RelationContext ctx) {
    String relationsName = ctx.relationenName().getText();
    aktuelleRelation = new Relation(relationsName);
  }

  public void enterAttribut(RelationenSchemaParser.AttributContext ctx) {
    Attribut attribut = new Attribut("");

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
    relationenSchema.setzeRelation(aktuelleRelation);
  }

}

class Attribut {
  String name;
  String fremdRelationenName;
  String fremdRelationenAttribut;
  boolean istPrimaer;

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
    if (istPrimaer) ausgabe += " PRIMARY KEY";
    return ausgabe;
  }
}

class Relation {
  HashMap<String, Attribut> attribute;
  String name;

  public Relation(String name) {
    this.name = name;
    attribute = new HashMap<String, Attribut>();
  }

  public void setzeAttribut(String attributName) {
    attribute.put(attributName, new Attribut(attributName));
  }

  public void setzeAttribut(Attribut attribut) {
    attribute.put(attribut.name, attribut);
  }
}

public class RelationenSchema {

  HashMap<String, Relation> relationen;

  public RelationenSchema() {
    relationen = new HashMap<String, Relation>();
  }

  public RelationenSchema(String format) {
    relationen = new HashMap<String, Relation>();
    leseTextFormat(format);
  }

  public void setzeRelation(Relation relation) {
    relationen.put(relation.name, relation);
  }

  public Attribut gibAttribut(String relationenName, String attributName) {
    return relationen.get(relationenName).attribute.get(attributName);
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

}

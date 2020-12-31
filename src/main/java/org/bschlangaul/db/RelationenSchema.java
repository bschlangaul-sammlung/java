package org.bschlangaul.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.bschlangaul.antlr.RelationenSchemaBaseListener;
import org.bschlangaul.antlr.RelationenSchemaLexer;
import org.bschlangaul.antlr.RelationenSchemaParser;

class AntlrListener extends RelationenSchemaBaseListener {

  private RelationenSchema relationenSchema = new RelationenSchema();

  private Relation aktuelleRelation;

  public AntlrListener(RelationenSchema relationenSchema ) {
    this.relationenSchema = relationenSchema;
  }

  @Override
  public void enterRelation(RelationenSchemaParser.RelationContext ctx) {
    String relationsName = ctx.relationenName().getText();
    aktuelleRelation = new Relation(relationsName);
  }

  public void enterAttribut(RelationenSchemaParser.AttributContext ctx) {
    aktuelleRelation.setzeAttribut(ctx.getText());
  }

  public void exitRelation(RelationenSchemaParser.RelationContext ctx) {
    relationenSchema.setzeRelation(aktuelleRelation);
  }

}

class Relation {
  List<String> attribute;
  String name;

  public Relation(String name) {
    this.name = name;
    attribute = new ArrayList<String>();
  }

  public void setzeAttribut(String name) {
    attribute.add(name);
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

package org.bschlangaul.db;

import static org.junit.Assert.assertEquals;

import org.bschlangaul.TestHelfer;

import org.junit.Test;

public class RelationenSchemaTest {
  private RelationenSchema lese(String textFormat) {
    return new RelationenSchema(textFormat);
  }

  private RelationenSchema lade(String dateiName) {
    return lese(TestHelfer.leseDatei("db/" + dateiName));
  }

  @Test
  public void einfach() {
    RelationenSchema schema = lade("einfach.txt");
    assertEquals("Relation1", schema.gibRelationenNamen()[0]);
  }

  @Test
  public void mehrere() {
    RelationenSchema schema = lade("mehrere.txt");
    assertEquals("Relation1", schema.gibRelationenNamen()[0]);
    assertEquals("Relation2", schema.gibRelationenNamen()[1]);
    assertEquals("Relation3", schema.gibRelationenNamen()[2]);
    assertEquals("Relation4", schema.gibRelationenNamen()[3]);

    Attribut a = schema.gibAttribut("Relation1", "attribut1");
    assertEquals("attribut1", a.name);
    assertEquals(true, a.istPrimaer);

    a = schema.gibAttribut("Relation1", "attribut2");
    assertEquals("attribut2", a.name);
    assertEquals(false, a.istPrimaer);

    a = schema.gibAttribut("Relation2", "Mit_Umlauten_äöüSÖÄÖÜß");
    assertEquals("Mit_Umlauten_äöüSÖÄÖÜß", a.name);
    assertEquals(true, a.istPrimaer);

    a = schema.gibAttribut("Relation2", "1234");
    assertEquals("1234", a.name);
    assertEquals(false, a.istPrimaer);
  }

  @Test
  public void fremd() {
    RelationenSchema schema = lade("fremd.txt");
    Attribut attribut = schema.gibAttribut("Relation2", "id");
    assertEquals("id", attribut.name);
    assertEquals("Relation1", attribut.fremdRelationenName);
  }

  @Test
  public void rateSqlTypeVonName() {
    RelationenSchema schema = lade("triathlon.txt");
    assertEquals("INTEGER", schema.gibAttribut("Athlet", "ID").rateSqlTypeVonName());

    assertEquals("VARCHAR(20)", schema.gibAttribut("Athlet", "Vorname").rateSqlTypeVonName());
    assertEquals("VARCHAR(20)", schema.gibAttribut("Wettbewerb", "Name").rateSqlTypeVonName());
    assertEquals("INTEGER", schema.gibAttribut("Ergebnis", "Schwimmzeit").rateSqlTypeVonName());
    assertEquals("INTEGER", schema.gibAttribut("Wettbewerb", "Jahr").rateSqlTypeVonName());
  }

  @Test
  public void primärSchlüssel() {
    RelationenSchema schema = lade("triathlon.txt");

    Relation ergebnis = schema.gibRelation("Ergebnis");
    Relation athlet = schema.gibRelation("Athlet");
    assertEquals(2, ergebnis.gibAnzahlPrimärSchlüssel());
    assertEquals(1, athlet.gibAnzahlPrimärSchlüssel());

    assertEquals("  ID INTEGER PRIMARY KEY", athlet.gibAttribut("ID").baueSqlCreate());
    assertEquals("  Athlet INTEGER REFERENCES Athlet(ID)", ergebnis.gibAttribut("Athlet").baueSqlCreate());
  }

  @Test
  public void baueSqlCreate() {
    RelationenSchema schema = lade("triathlon.txt");
    System.out.println(schema.baueSqlCreate());
  }

  @Test
  public void baueSqlInsert() {
    RelationenSchema schema = lade("triathlon.txt");
    System.out.println(schema.baueSqlInsert());
  }

  @Test
  public void baueTeX() {
    RelationenSchema schema = lade("triathlon.txt");
    System.out.println(schema.baueTeX());
  }

  @Test
  public void zusätzlicherSqlAusdruck() {
    RelationenSchema schema = lade("triathlon.txt");
    Attribut schwimmzeit = schema.gibAttribut("Ergebnis", "Schwimmzeit");
    assertEquals("NOT NULL", schwimmzeit.zusätzlicherSqlAusdruck);
  }

}

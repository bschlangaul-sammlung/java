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

}

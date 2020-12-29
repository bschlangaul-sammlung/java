package org.bschlangaul.graph;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.bschlangaul.TestHelfer;

import org.junit.Test;

public class TexGraphenFormatTest {

  private void gibGraphenFormat(String relativerPfad) throws IOException {
    TexGraphenFormat tex = new TexGraphenFormat(TestHelfer.leseDatei(relativerPfad + ".test"));
    String tatsächlich = tex.gibGraphenFormat();
    String erwartet = TestHelfer.leseDatei(relativerPfad + ".ergebnis");
    assertEquals(erwartet, tatsächlich);
  }

  @Test
  public void testDatei1() throws IOException {
    gibGraphenFormat("tex-graph-1");
  }

  @Test
  public void testDatei2() throws IOException {
    gibGraphenFormat("tex-graph-2");
  }

  @Test
  public void testDatei3() throws IOException {
    gibGraphenFormat("tex-graph-3");
  }
}

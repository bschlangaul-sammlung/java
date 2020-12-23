package org.bschlangaul.graph;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.bschlangaul.TestHelfer;

import org.junit.Test;

public class TexGraphenFormatTest {

  private TexGraphenFormat gibGraphenFormat(String relativerPfad) throws IOException {
    return new TexGraphenFormat(TestHelfer.leseDatei(relativerPfad));
  }

  @Test
  public void testDatei1() throws IOException {
    gibGraphenFormat("tex-graph-1.txt");
  }

  @Test
  public void testDatei2() throws IOException {
    gibGraphenFormat("tex-graph-2.txt");
  }

  @Test
  public void testDatei3() throws IOException {
    gibGraphenFormat("tex-graph-3.txt");
  }
}

package org.bschlangaul.db.normalisation;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;

import org.bschlangaul.TestHelfer;
import org.junit.Test;

public class TerminalAusgabeTest {

  private TerminalAusgabe lade(String dateiName) {
    Path pfad = TestHelfer.erzeugePfad("db/normalisation/" + dateiName);
    return TerminalAusgabe.sucheAbhängigkeiten(pfad.toString());
  }

  @Test
  public void minimalesBeispiel() {
    TerminalAusgabe ausgabe = lade("minimal.txt");
    assertEquals(true, ausgabe.istIn3NF());
    assertEquals(true, ausgabe.istInBCNF());
  }

  @Test
  public void kurzerMakroname() {
    TerminalAusgabe ausgabe = lade("macroname-kurz.txt");
    assertEquals(false, ausgabe.istIn3NF());
    assertEquals(false, ausgabe.istInBCNF());
  }

  @Test
  public void langerMakroname() {
    TerminalAusgabe ausgabe = lade("macroname-lang.txt");
    assertEquals(false, ausgabe.istIn3NF());
    assertEquals(false, ausgabe.istInBCNF());
  }
}

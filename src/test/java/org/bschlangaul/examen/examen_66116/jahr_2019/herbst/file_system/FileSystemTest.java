package org.bschlangaul.examen.examen_66116.jahr_2019.herbst.file_system;

import org.junit.Before;

import org.junit.Test;

public class FileSystemTest {

  Element wurzelOrdner;
  Element unterOrdner;
  Element datei;
  Element verweis;

  @Before
  public void legeZoneAn() {
    wurzelOrdner = new Directory("Wurzel", null);
    unterOrdner = new Directory("Unterordner", wurzelOrdner);
    datei = new File("Datei", wurzelOrdner);
    verweis = new Link("Verweis", wurzelOrdner, datei);
  }

  @Test
  public void löscheWurzelOrdner() {
    wurzelOrdner.delete();
  }

}

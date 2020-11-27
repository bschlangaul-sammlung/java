package org.bschlangaul.aufgaben.aud.examen_46115_2014_03.suchbaum;

/**
 * https://www.studon.fau.de/file2860860_download.html
 */
public class Test {
  private BinaererSuchbaum b;

  public Test() {
    b = new BinaererSuchbaum();

    b.einfügen(17);
    b.einfügen(7);
    b.einfügen(21);
    b.einfügen(3);
    b.einfügen(10);
    b.einfügen(13);
    b.einfügen(1);
    b.einfügen(5);
    b.ausgebenInOrder();
  }

  public void loeschen(int w) {
    b.loescheKnoten(w);
    b.ausgebenInOrder();
  }

}

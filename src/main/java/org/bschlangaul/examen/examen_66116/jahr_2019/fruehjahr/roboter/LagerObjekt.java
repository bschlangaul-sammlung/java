package org.bschlangaul.examen.examen_66116.jahr_2019.fruehjahr.roboter;

public class LagerObjekt {
  String name;
  Standort position;
  int anzahl;

  public LagerObjekt(String name, int x, int y, int anzahl) {
    this.name = name;
    this.position = new Standort(x, y);
    this.anzahl = anzahl;
  }
}

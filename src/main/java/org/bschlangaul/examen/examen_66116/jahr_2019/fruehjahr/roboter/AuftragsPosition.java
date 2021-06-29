package org.bschlangaul.examen.examen_66116.jahr_2019.fruehjahr.roboter;

public class AuftragsPosition {
  ObjektTyp typ;
  LagerObjekt lagerObjekt;
  int anzahl;

  public AuftragsPosition(LagerObjekt lagerObjekt, int anzahl) {
    if (lagerObjekt instanceof Werkzeug) {
      typ = ObjektTyp.WERKZEUG;
    } else {
      typ = ObjektTyp.MATERIAL;
    }
    this.lagerObjekt = lagerObjekt;
    this.anzahl = anzahl;
  }
}

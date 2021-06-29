package org.bschlangaul.examen.examen_66116.jahr_2019.fruehjahr.roboter;

import java.util.ArrayList;

public class Roboter {
  String name;
  Standort position;
  ArrayList<LagerObjekt> ergebnis;

  void geheZuStandort(Standort standort) {

  }

  void arbeiteAuftragAb() {

  }

  public static void main(String[] args) {
    Werkzeug bohrerB1 = new Werkzeug("Bohrer Typ B1", 1, 0, 1);
    Material schraubenM6 = new Material("Schrauben M6", 1, 1, 100);
    Material schraubenM10 = new Material("Schrauben M10", 1, 2, 10);
    Material blechB72 = new Material("Blech B72", 1, 3, 2);

    Auftrag auftrag = new Auftrag();
    auftrag.fügePositionHinzu(bohrerB1, 1);
    auftrag.fügePositionHinzu(schraubenM6, 100);
    auftrag.fügePositionHinzu(schraubenM10, 10);
    auftrag.fügePositionHinzu(blechB72, 2);
  }
}

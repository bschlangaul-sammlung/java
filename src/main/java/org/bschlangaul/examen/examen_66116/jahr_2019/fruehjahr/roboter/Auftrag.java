package org.bschlangaul.examen.examen_66116.jahr_2019.fruehjahr.roboter;

import java.util.ArrayList;

public class Auftrag {
  ArrayList<AuftragsPosition> positionen;

  public Auftrag() {
    this.positionen = new ArrayList<AuftragsPosition>();
  }

  public void fügePositionHinzu(LagerObjekt lagerObjekt, int anzahl) {
    positionen.add(new AuftragsPosition(lagerObjekt, anzahl));
  }
}

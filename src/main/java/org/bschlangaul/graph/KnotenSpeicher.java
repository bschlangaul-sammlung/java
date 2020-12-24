package org.bschlangaul.graph;

import java.util.ArrayList;
import java.util.HashMap;

public class KnotenSpeicher {

  private ArrayList<Knoten> knotenSpeicher;
  private HashMap<String, Integer> knotenIndex;

  public KnotenSpeicher() {
    knotenSpeicher = new ArrayList<Knoten>();
    knotenIndex = new HashMap<String, Integer>();
  }

  public int setzeKnoten(String knotenName) {
    int index = gibKnotenNummer(knotenName);
    if (index > -1) return index;
    knotenSpeicher.add(new Knoten(knotenName));
    index = knotenSpeicher.size() - 1;
    knotenIndex.put(knotenName, index);
    return index;
  }

  public int gibKnotenNummer(String knotenName) {
    if (knotenIndex.get(knotenName) != null) {
      return knotenIndex.get(knotenName);
    }
    return -1;
  }

  public String gibKnotenName(int knotenNummer) {
    Knoten knoten = knotenSpeicher.get(knotenNummer);
    return knoten.gibName();
  }

  public int gibKnotenAnzahl() {
    return knotenSpeicher.size();
  }

}

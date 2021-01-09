package org.bschlangaul.examen.examen_66116.jahr_2016.herbst.pki;

import java.util.ArrayList;
import java.util.List;

class MapImpl implements IMap {
  final static long serialVersionUID = 123;

  private List<MapEntry> entries;

  MapImpl() {
    entries = new ArrayList<MapEntry>();
  }

  public Object get(String key) {
    return new Object();
  }

  public void put(String key, Object value) {
    // Nicht verlangt in der Aufgabenstellung.
    entries.add(new MapEntry(key, value));
  }
}

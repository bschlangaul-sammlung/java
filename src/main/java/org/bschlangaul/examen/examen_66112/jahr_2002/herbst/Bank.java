package org.bschlangaul.examen.examen_66112.jahr_2002.herbst;

import java.util.ArrayList;

public class Bank {
  @SuppressWarnings("unused")
  private String name;
  private ArrayList<Konto> fuehrt;

  public Bank(String name) {
    this.name = name;
    fuehrt = new ArrayList<Konto>();
  }

  public void abrechnung() {
    for (int i = 0; i <= fuehrt.size(); i++) {
      fuehrt.get(i).abrechnung(); // Methode " get ( i ) " liefert i . Listenelement zurueck
    }
  }

  public void anlegen(String n, int kn, int kto, float kst, float zs) {
    Besitzer bes = schonVorhanden(n, kn);
    fuehrt.add(new Sparkonto(kto, kst, bes, zs)); // Methode " add ( element ) " haengt neues Element ans Ende der Liste
  }

  public Besitzer schonVorhanden(String name, int kunr) {
    if (!fuehrt.isEmpty()) {
      for (int i = 0; i < fuehrt.size(); i++) {
        if (fuehrt.get(i).hatBesitzer.getKundennr() == kunr) {
          // prueft , ob die Kundennr . des Kontos an der i . Stelle , des zugehoerigen
          // Kontobesitzers " hatBesitzer " gleich kunr ist
          return fuehrt.get(i).getHatBesitzer();
        }
      }
    }
    return new Besitzer(name, kunr);

  }

}

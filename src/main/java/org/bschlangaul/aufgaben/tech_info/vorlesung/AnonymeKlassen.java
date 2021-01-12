package org.bschlangaul.aufgaben.tech_info.vorlesung;

public class AnonymeKlassen {

  public static void main(String[] args) {
    new VorhandeneKlasse() { // Diese anonyme Klasse erweitere die VorhandeneKlasse
                             // Beim Initialisieren wird auch definiert

      public void hallo() { // VorhandeneKlasse wird um die Methode hallo() erweitert
        System.out.println("Hallo");
      }
    }.hallo(); // hallo() wird sofort nach dem Erzeugen aufgerufen
    // Aufgrund der fehlenden Referenz kann dieses Objekt der anonymen Klasse nicht
    // weiter benutzt werden!

    VorhandeneKlasse v = new VorhandeneKlasse() {
      @SuppressWarnings("unused")
      public void hallo() {
        System.out.println("Hallo");
      }
    };
    v.hi(); // funktioniert normal
    // v.hallo(); //Dieser Aufruf ist nicht möglich, da hallo() (hier im Code)
    // bereits nicht mehr bekannt ist.

  }
}

class VorhandeneKlasse {
  public void hi() {
    System.out.println("hi");
  }
}

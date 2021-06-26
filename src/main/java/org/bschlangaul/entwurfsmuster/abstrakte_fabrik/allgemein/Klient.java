package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.allgemein;

public class Klient {
  public static void main(String[] args) {
    // zentraler Austauschpunkt -> Implementierungsaustausch
    AbstrakteFabrik fabrik1 = new Fabrik1();
    fabrik1.erzeugeProduktA();
    fabrik1.erzeugeProduktB();
    // KonkretesProduktA1 wurde erzeugt
    // KonkretesProduktB1 wurde erzeugt

    AbstrakteFabrik fabrik2 = new Fabrik2();
    fabrik2.erzeugeProduktA();
    fabrik2.erzeugeProduktB();
    // KonkretesProduktA2 wurde erzeugt
    // KonkretesProduktB2 wurde erzeugt
  }
}

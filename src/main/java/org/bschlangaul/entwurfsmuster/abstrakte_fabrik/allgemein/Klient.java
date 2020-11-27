package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.allgemein;

public class Klient {
  public static void main(String[] args) {
    // zentraler Austauschpunkt -> Implementierungsaustausch
    AbstrakteFabrik fabrikA = new KonkreteFabrikA();
    fabrikA.erzeugeProdukt1();
    fabrikA.erzeugeProdukt2();
    fabrikA.erzeugeProdukt3();
    // KonkretesProdukt1A wurde erzeugt
    // KonkretesProdukt2A wurde erzeugt
    // KonkretesProdukt3A wurde erzeugt

    AbstrakteFabrik fabrikB = new KonkreteFabrikB();
    fabrikB.erzeugeProdukt1();
    fabrikB.erzeugeProdukt2();
    fabrikB.erzeugeProdukt3();
    // KonkretesProdukt1B wurde erzeugt
    // KonkretesProdukt2B wurde erzeugt
    // KonkretesProdukt3B wurde erzeugt
  }
}

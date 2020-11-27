package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.allgemein;

interface Produkt1 {
}

interface Produkt2 {
}

interface Produkt3 {
}

class KonkretesProdukt1A implements Produkt1 {
  public KonkretesProdukt1A() {
    System.out.println("KonkretesProdukt1A wurde erzeugt");
  }
}

class KonkretesProdukt1B implements Produkt1 {
  public KonkretesProdukt1B() {
    System.out.println("KonkretesProdukt1B wurde erzeugt");
  }
}

class KonkretesProdukt2A implements Produkt2 {
  public KonkretesProdukt2A() {
    System.out.println("KonkretesProdukt2A wurde erzeugt");
  }
}

class KonkretesProdukt2B implements Produkt2 {
  public KonkretesProdukt2B() {
    System.out.println("KonkretesProdukt2B wurde erzeugt");
  }
}

class KonkretesProdukt3A implements Produkt3 {
  public KonkretesProdukt3A() {
    System.out.println("KonkretesProdukt3A wurde erzeugt");
  }
}

class KonkretesProdukt3B implements Produkt3 {
  public KonkretesProdukt3B() {
    System.out.println("KonkretesProdukt3B wurde erzeugt");
  }
}

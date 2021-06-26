package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.allgemein;

interface AbstraktesProduktA {
}

interface AbstraktesProduktB {
}

class ProduktA1 implements AbstraktesProduktA {
  public ProduktA1() {
    System.out.println("ProduktA1 wurde erzeugt");
  }
}

class ProduktA2 implements AbstraktesProduktA {
  public ProduktA2() {
    System.out.println("ProduktA2 wurde erzeugt");
  }
}

class ProduktB1 implements AbstraktesProduktB {
  public ProduktB1() {
    System.out.println("ProduktB1 wurde erzeugt");
  }
}

class ProduktB2 implements AbstraktesProduktB {
  public ProduktB2() {
    System.out.println("ProduktB2 wurde erzeugt");
  }
}

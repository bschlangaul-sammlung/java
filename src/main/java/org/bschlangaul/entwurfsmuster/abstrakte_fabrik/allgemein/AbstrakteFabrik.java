package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.allgemein;

interface AbstrakteFabrik {
  public AbstraktesProduktA erzeugeProduktA();

  public AbstraktesProduktB erzeugeProduktB();
}

class Fabrik1 implements AbstrakteFabrik {
  @Override
  public AbstraktesProduktA erzeugeProduktA() {
    return new ProduktA1();
  }

  @Override
  public AbstraktesProduktB erzeugeProduktB() {
    return new ProduktB1();
  }

}

class Fabrik2 implements AbstrakteFabrik {
  @Override
  public AbstraktesProduktA erzeugeProduktA() {
    return new ProduktA2();
  }

  @Override
  public AbstraktesProduktB erzeugeProduktB() {
    return new ProduktB2();
  }
}

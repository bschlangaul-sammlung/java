package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.allgemein;

interface AbstrakteFabrik {
  public Produkt1 erzeugeProdukt1();

  public Produkt2 erzeugeProdukt2();

  public Produkt3 erzeugeProdukt3();
}

class KonkreteFabrikA implements AbstrakteFabrik {
  @Override
  public Produkt1 erzeugeProdukt1() {
    return new KonkretesProdukt1A();
  }

  @Override
  public Produkt2 erzeugeProdukt2() {
    return new KonkretesProdukt2A();
  }

  @Override
  public Produkt3 erzeugeProdukt3() {
    return new KonkretesProdukt3A();
  }
}

class KonkreteFabrikB implements AbstrakteFabrik {
  @Override
  public Produkt1 erzeugeProdukt1() {
    return new KonkretesProdukt1B();
  }

  @Override
  public Produkt2 erzeugeProdukt2() {
    return new KonkretesProdukt2B();
  }

  @Override
  public Produkt3 erzeugeProdukt3() {
    return new KonkretesProdukt3B();
  }
}

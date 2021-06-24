package org.bschlangaul.examen.examen_66116.jahr_2021.fruehjahr.webshop;

public class Webshop {
  public static void main(String[] args) {
    Bestellung b1 = new Bestellung();

    // ab hier soll modelliert werden
    Artikel a1 = new Artikel();
    a1.setName("Taschenrechner");
    a1.setPrice(10);

    b1.addArticle(a1);

    Bestellung b2 = new Bestellung();
    Artikel a2 = new Artikel();
    a2.setName("Lineal");
    a2.setPrice(2.5);

    Artikel a3 = new Artikel();
    a3.setName("Bleistift");
    a3.setPrice(0.7);

    b2.addArticle(a3);
    b1.addArticle(a2);

    b1.getSize();

    b2.getPrice();
  }
}

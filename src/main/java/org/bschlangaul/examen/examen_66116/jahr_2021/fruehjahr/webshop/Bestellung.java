package org.bschlangaul.examen.examen_66116.jahr_2021.fruehjahr.webshop;

import java.util.ArrayList;
import java.util.List;

public class Bestellung {
  private List<Artikel> articles;
  // Anzahl an Artikeln
  private int size = 0;
  // Gesamtpreis der Bestellung
  private double price = 0;

  public Bestellung() {
    articles = new ArrayList<>();
  }

  public void addArticle(Artikel article) {
    // muss nicht weiter aufgelöst werden, siehe Hinweise
    articles.add(article);
    size++;
    // muss nicht weiter aufgelöst werden, siehe Hinweise
    price = article.getPrice() + price;
  }

  public int getSize() {
    return size;
  }

  public double getPrice() {
    return price;
  }
}

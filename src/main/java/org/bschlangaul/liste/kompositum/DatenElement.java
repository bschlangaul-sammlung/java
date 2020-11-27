package org.bschlangaul.liste.kompositum;

public class DatenElement {
  String name;
  int alter;

  public DatenElement(String n, int a) {
    name = n;
    alter = a;
  }

  public int gibAlter() {
    return alter;
  }

  public void gibDatenAus() {
    System.out.println("Name: " + name + " Alter: " + alter);
  }
}

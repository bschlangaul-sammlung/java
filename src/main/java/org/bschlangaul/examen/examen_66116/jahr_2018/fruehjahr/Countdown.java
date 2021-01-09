package org.bschlangaul.examen.examen_66116.jahr_2018.fruehjahr;

public class Countdown extends Subject {

  private int value;

  public Countdown() {
    value = 5000;
  }

  public int getValue() {
    return value;
  }

  public void countdown() {
    if (value > 0) {
      notifyObservers();
      value--;
    }
  }
}

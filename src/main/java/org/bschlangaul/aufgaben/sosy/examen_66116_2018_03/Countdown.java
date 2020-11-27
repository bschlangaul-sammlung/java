package org.bschlangaul.aufgaben.sosy.examen_66116_2018_03;

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

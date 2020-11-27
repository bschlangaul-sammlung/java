package org.bschlangaul.aufgaben.sosy.examen_66116_2018_03;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
  private final List<Observer> observers = new ArrayList<Observer>();

  public void attach(Observer o) {
    observers.add(o);
  }

  public void notifyObservers() {
    for (Observer o : observers) {
      o.update();
    }
  }
}

package org.bschlangaul.entwurfsmuster.beobachter.hauer_neutral;

public class ConcreteObserverA implements Observer {

  public void update(int state) {
    System.out.println("Concrete Observer A is updated with " + state);
    // ggf. Modifikationen mit setState().
  }
}

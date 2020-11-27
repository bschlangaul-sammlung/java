package org.bschlangaul.entwurfsmuster.beobachter.hauer_neutral;

public class ConcreteObserverB implements Observer {

  public void update(int state) {
      System.out.println("Concrete Observer B is updated with "+state);
      //ggf. Modifikationen mit setState().
  }
}

package org.bschlangaul.entwurfsmuster.beobachter.hauer_neutral;

public class Client {

  public static void main(String[] args) {
    ConcreteSubject concreteSubject = new ConcreteSubject();
    concreteSubject.register(new ConcreteObserverA());
    concreteSubject.register(new ConcreteObserverB());

    concreteSubject.setState(77);
    // Concrete Observer A is updated with 77
    // Concrete Observer B is updated with 77
  }
}

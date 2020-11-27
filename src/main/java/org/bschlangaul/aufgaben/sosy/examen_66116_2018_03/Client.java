package org.bschlangaul.aufgaben.sosy.examen_66116_2018_03;

public class Client {
  public static void main(String[] args){
    Countdown cd = new Countdown();
    new Display(cd);
    cd.countdown();
    cd.countdown();
    cd.countdown();
  }
}

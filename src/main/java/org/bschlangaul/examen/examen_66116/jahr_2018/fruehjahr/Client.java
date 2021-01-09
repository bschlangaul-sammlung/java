package org.bschlangaul.examen.examen_66116.jahr_2018.fruehjahr;

public class Client {
  public static void main(String[] args){
    Countdown cd = new Countdown();
    new Display(cd);
    cd.countdown();
    cd.countdown();
    cd.countdown();
  }
}

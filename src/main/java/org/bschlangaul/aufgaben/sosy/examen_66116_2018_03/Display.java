package org.bschlangaul.aufgaben.sosy.examen_66116_2018_03;

public class Display extends Observer {
  Countdown myCountdown;
  public Display(Countdown cd) {
    myCountdown = cd;
    myCountdown.attach(this);
  }

  public void update() {
    System.out.println(myCountdown.getValue());
  }
}

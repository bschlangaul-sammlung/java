package org.bschlangaul.aufgaben.tech_info.philosoph;

public class PhilosophDeadlock extends Philosoph {

  public PhilosophDeadlock(Gabel ersteGabel, Gabel zweiteGabel, int id) {
    super(ersteGabel, zweiteGabel, id);
  }

  @Override
  public void essen() {
    ersteGabel.lock();
    // Das Schreiben auf die Konsole dauert so lange, dass es sich sofort verklemmt.
    // System.out.println("Philosoph "+id+" hat Gabel "+firstGabel.id+" genommen!");
    zweiteGabel.lock();
    // System.out.println("Philosoph "+id+" hat Gabel "+secondGabel.id+"
    // genommen!");
    System.out.println("Philosoph " + id + " isst gerade!");
    ersteGabel.unlock();
    zweiteGabel.unlock();
  }
}

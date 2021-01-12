package org.bschlangaul.aufgaben.tech_info.philosoph;

public abstract class Philosoph implements Runnable {

  public Gabel ersteGabel = null;
  public Gabel zweiteGabel = null;
  final int id;

  public Philosoph(Gabel ersteGabel, Gabel zweiteGabel, int id) {
    this.ersteGabel = ersteGabel;
    this.zweiteGabel = zweiteGabel;
    this.id = id;
  }

  public abstract void essen();

  public void denken() {
    System.out.println("Philosoph " + id + " denkt gerade!");
  }

  @Override
  public void run() {
    while (true) {
      denken();
      essen();
    }

  }
}

package org.bschlangaul.aufgaben.tech_info.philosoph;

public class PhilosophGlobaleOrdnung extends Philosoph {
  public PhilosophGlobaleOrdnung(Gabel ersteGabel, Gabel zweiteGabel, int id) {
    super(ersteGabel, zweiteGabel, id);
  }

  @Override
  public void essen() {
    synchronized (Object.class) {
      ersteGabel.lock();
      zweiteGabel.lock();
    }

    System.out.println("Philosoph " + id + " isst gerade!");
    ersteGabel.unlock();
    zweiteGabel.unlock();
  }
}

package org.bschlangaul.aufgaben.tech_info.philosoph;

public class PhilosophGlobaleOrdnung extends Philosoph {
  public PhilosophGlobaleOrdnung(Gabel ersteGabel, Gabel zweiteGabel, int id) {
    super(ersteGabel, zweiteGabel, id);
  }

  @Override
  public void essen() {
    if (ersteGabel.getId() < zweiteGabel.getId()) {
      ersteGabel.lock();
      zweiteGabel.lock();
    } else {
      zweiteGabel.lock();
      ersteGabel.lock();
    }

    System.out.println("Philosoph " + id + " isst gerade!");
    ersteGabel.unlock();
    zweiteGabel.unlock();
  }
}

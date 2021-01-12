package org.bschlangaul.aufgaben.tech_info.philosoph;

import java.util.concurrent.locks.ReentrantLock;

public class Gabel extends ReentrantLock {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  final int id;

  public Gabel(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

}

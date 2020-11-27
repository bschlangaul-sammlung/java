package org.bschlangaul.aufgaben.sosy.examen_66116_2018_09.beatles;

public class Music {
  private Beatle state;

  public Music() {
    state = new Paul();
  }

  public void help() {
    this.state.help(this);
  }

  public void obladi() {
    this.state.obladi(this);
  }

  public void yesterday() {
    this.state.yesterday(this);
  }

  public void setBeatle(Beatle b) {
    state = b;
  }
}

abstract class Beatle {
  public abstract void help(Music m);

  public abstract void obladi(Music m);

  public abstract void yesterday(Music m);
}

class George extends Beatle {
  public void help(Music m) {
    System.out.println("help");
    m.setBeatle(new John());
  }

  public void obladi(Music m) {
  }

  public void yesterday(Music m) {
    System.out.println("yesterday");
    m.setBeatle(new Paul());
  }
}

class John extends Beatle {
  public void help(Music m) {
    System.out.println("help");
    m.setBeatle(new Paul());
  }

  public void obladi(Music m) {
    System.out.println("obladi");
    m.setBeatle(new Ringo());
  }

  public void yesterday(Music m) {
  }
}

class Paul extends Beatle {
  public void help(Music m) {
    System.out.println("help");
    m.setBeatle(new George());
  }

  public void obladi(Music m) {
  }

  public void yesterday(Music m) {
    System.out.println("yesterday");
  }
}

class Ringo extends Beatle {
  public void help(Music m) {
    System.out.println("help");
    m.setBeatle(new John());
  }

  public void obladi(Music m) {
  }

  public void yesterday(Music m) {
    System.out.println("yesterday");
    m.setBeatle(new Paul());
  }
}

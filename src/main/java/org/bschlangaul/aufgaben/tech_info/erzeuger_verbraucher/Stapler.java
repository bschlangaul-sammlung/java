package org.bschlangaul.aufgaben.tech_info.erzeuger_verbraucher;

import java.util.Random;

public class Stapler implements Runnable {
  private char seite;
  private Bild stapler;
  private Kiste kiste;
  private Random zufall;
  private Palette palette;
  private static final int speed = 10;

  @Override
  public void run() {
    // aktives Warten
    for (int i = 0; i < 10; i++) {
      fahreHin();
      // seite=='l' == links == Erzeuger seite=='r' == rechts == Verbraucher
      // Zudem überprüft die Schleife, ob das Abladen bzw. Aufladen erfolgreich war
      // und ob man die Schleife verlassen und dann wegfahren darf
      // Es wird geprüft, ob man immer noch eine Kiste geladen hat (Erzeuger) oder
      // immer noch keine Kiste aufladen werden konnte (Verbraucher)
      while (seite == 'l' && this.kiste != null || seite == 'r' && this.kiste == null) {
        // Diese Lösung ist unschön wegen "aktivem Warten":
        // Das kostet viel Ressourcen, weil der Thread ja durchgehend aktiv ist aber
        // nicht weiterkommt, weil er nicht abladen/aufladen kann
        // Hinweis: auf keinen Fall in der while prüfen/abfragen, ob die palette.kiste
        // frei/besetzt ist, sonst Race Condition

        synchronized (palette) {// hier streiten sich beide um die Marke, falls sie der "falsche" Thread
                                // bekommt,
          // wird dieser nicht abladen/aufladen können und der Thread gibt die Marke nach
          // dem synchronized wieder frei
          // und betritt die Schleife erneut, da das Abladen/Aufladen nicht erfolgreich
          // war
          // Anschließend versucht er die Marke am synchronized wieder zu bekommen.
          // In dieser Zeit hat der andere Thread die Chance die Marke zu erwerben

          if (seite == 'l') {// l == links == Erzeuger
            if (palette.kiste == null) {
              ladeAb(); // Falls schon eine Kiste da ist, muss der Verbraucher sie erst wegnehmen
            }
          } else {
            if (palette.kiste != null) {
              ladeAuf(); // Falls keine Kiste vorhanden ist, muss der Erzeuger erst eine Kiste liefern
            }
          }
        }
      }
      fahreWeg();
    }
  }

  // @Override
  public void runPassivesWarten() {
    // passives Warten
    for (int i = 0; i < 10; i++) {
      fahreHin();

      synchronized (palette) {
        if (seite == 'l') {
          if (palette.kiste != null) {
            try {
              palette.wait(); // wait() blockiert bis ein anderer Thread auf palette notify() aufruft
              // und wait() gibt die Marke von palette zurück
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          ladeAb();
        } else {
          if (palette.kiste == null) {
            try {
              palette.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          ladeAuf();
        }
        palette.notify(); // anderer Thread wird "aufgeweckt"
      }
      fahreWeg();
    }
  }

  public Stapler(int x, int y, char seite, Palette p) {
    this.seite = seite;

    if (seite == 'r') {
      this.stapler = new Bild(x, y, Bild.gibPfad("stapler_R.gif"));
    } else if (seite == 'l') {
      this.stapler = new Bild(x, y, Bild.gibPfad("stapler_L.gif"));
    }

    this.kiste = null;
    this.palette = p;

    this.zufall = new Random();
  }

  public void nimmKiste(Kiste k) {
    this.kiste = k;
  }

  public void gibKisteAn(Palette p) {
    p.nimmKiste(this.kiste);
    this.kiste = null;
  }

  public void neueKiste() {
    this.kiste = new Kiste(this.stapler.nenneMx() + 106, this.stapler.nenneMy() - 21);
  }

  private void warte(long milliSekunden) {
    try {
      Thread.sleep(milliSekunden);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void fahreHin() {
    if (this.seite == 'l') {
      while (this.stapler.nenneMx() < 250) {
        this.stapler.verschiebenUm(speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(speed, 0);
        }

        warte(10);
      }
    } else {
      while (this.stapler.nenneMx() > 750) {
        this.stapler.verschiebenUm(-speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(-speed, 0);
        }
        warte(10);
      }
    }
  }

  public void fahreWeg() {
    int n = this.zufall.nextInt(1000);

    if (this.seite == 'l') {
      while (this.stapler.nenneMx() > -200 - n) {
        this.stapler.verschiebenUm(-speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(-speed, 0);
        }
        warte(10);
      }
    } else {
      while (this.stapler.nenneMx() < 1200 + n) {
        this.stapler.verschiebenUm(speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(speed, 0);
        }
        warte(10);
      }
    }
    if (seite == 'l') { // Code fuer Mehrfachverwendung eingefuegt
      this.neueKiste();
    }
    if (seite == 'r') {
      kiste = null;
    }
  }

  public void ladeAb() {
    if (this.seite == 'l') {
      while (this.stapler.nenneMx() < 394) {
        this.stapler.verschiebenUm(speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(speed, 0);
        }
        warte(10);
      }

      this.gibKisteAn(this.palette);

      warte(300);

      while (this.stapler.nenneMx() > 250) {
        this.stapler.verschiebenUm(-speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(-speed, 0);
        }
        warte(10);
      }
    } else {
      while (this.stapler.nenneMx() > 606) {
        this.stapler.verschiebenUm(-speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(-speed, 0);
        }

        warte(10);
      }

      this.gibKisteAn(this.palette);

      warte(300);

      while (this.stapler.nenneMx() < 750) {
        this.stapler.verschiebenUm(speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(speed, 0);
        }

        warte(10);
      }
    }
  }

  public void ladeAuf() {
    if (this.seite == 'l') {
      while (this.stapler.nenneMx() < 394) {
        this.stapler.verschiebenUm(speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(speed, 0);
        }
        warte(10);
      }

      this.palette.gibKisteAn(this);

      warte(300);

      while (this.stapler.nenneMx() > 250) {
        this.stapler.verschiebenUm(-speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(-speed, 0);
        }

        warte(10);
      }
    } else {
      while (this.stapler.nenneMx() > 606) {
        this.stapler.verschiebenUm(-speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(-speed, 0);
        }

        warte(10);
      }

      this.palette.gibKisteAn(this);

      warte(300);

      while (this.stapler.nenneMx() < 750) {
        this.stapler.verschiebenUm(speed, 0);

        if (this.kiste != null) {
          this.kiste.verschiebenUm(speed, 0);
        }

        warte(10);
      }
    }
  }
}

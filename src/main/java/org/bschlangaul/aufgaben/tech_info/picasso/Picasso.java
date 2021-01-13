package org.bschlangaul.aufgaben.tech_info.picasso;

import java.util.Random;

public class Picasso {

  Canvas canvas;
  Random zufall = new Random();

  public class HilfsMaler extends Thread {
    @Override
    public void run() {
      while (!canvas.finished()) {
        int x = zufall.nextInt(canvas.getX());
        int y = zufall.nextInt(canvas.getY());
        synchronized (Picasso.class) {
          if (!canvas.hasColor(x, y)) {
            canvas.colorize(x, y, (int) getId());
            System.out.println(String.format("Hilfsmaler Nr. %s das Pixel %s (x) %s (y) aus.", getId(), x, y));
          }
        }
        machEineKleinePause();
      }
      System.out.println(String.format("Alles ausgemalt! Hilfsmaler Nr. %s kann jetzt endlich Feierabend machen.", getId()));
    }

    /**
     * Auch ein Hilfsmaler braucht mal eine kleine Pause. Außerdem sieht man so
     * besser, wie das Feld langsam ausgemalt wird.
     */
    private void machEineKleinePause() {
      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public Picasso() {
    zufall = new Random();
  }

  public void paint(int width, int height, int anzahlThreads) {
    canvas = new Canvas(width, height);
    for (int i = 0; i < anzahlThreads; i++) {
      HilfsMaler m = new HilfsMaler();
      System.out.println("Hilfsmaler mit der Nr. " + m.getId() + " fängt an zu malen.");
      m.start();
    }
  }

  public static void main(String[] args) {
    int width = 50;
    int height = 50;
    int anzahlThreads = 10;
    new Picasso().paint(width, height, anzahlThreads);
  }

}

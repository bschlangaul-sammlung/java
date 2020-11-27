package org.bschlangaul.aufgaben.oomup.ab_3.bier;

import greenfoot.GreenfootSound;
import greenfoot.Greenfoot;
import greenfoot.World;
import java.util.List;

public class BierWorld extends World {
  public BierWorld() {
    super(360, 520, 1);
    prepare();
  }

  static int gibZufallsBereich(int untereGrenze, int obereGrenze) {
    int distanz = obereGrenze - untereGrenze;
    return Greenfoot.getRandomNumber(distanz + 1) + untereGrenze;
  }

  static boolean gibZufallsWahrFalsch() {
    int zahl = Greenfoot.getRandomNumber(2);
    if (zahl == 0) {
      return false;
    }
    return true;
  }

  public void act() {
    if (Greenfoot.getRandomNumber(100) == 99) {
      Kuh kuh = new Kuh();
      addObject(kuh, kuh.xPosition, kuh.yPosition);
    }

    if (Greenfoot.getRandomNumber(100) == 99) {
      Traktor traktor = new Traktor();
      addObject(traktor, traktor.xPosition, traktor.yPosition);
    }

    List<Gegner> gegner = getObjects(Gegner.class);

    if (gegner.size() > 0) {
      for (int i = 0; i < gegner.size(); i++) {
        Gegner g = gegner.get(i);
        if (g.istDurchgelaufen()) {
          removeObject(g);
        }
      }
    }
  }

  private void prepare() {
    Sepp sepp = new Sepp();
    addObject(sepp, 176, 448);
    GreenfootSound titelmusik = new GreenfootSound("HeimatGames-Titelmusik.mp3");
    titelmusik.playLoop();
  }
}

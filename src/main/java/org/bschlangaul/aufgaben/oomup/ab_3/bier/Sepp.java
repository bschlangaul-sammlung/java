package org.bschlangaul.aufgaben.oomup.ab_3.bier;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Sepp extends Akteur {
  private GreenfootImage[] aufBilder = new GreenfootImage[3];
  private GreenfootImage[] abBilder = new GreenfootImage[3];
  private int aktuellerBildIndex;

  public Sepp() {
    aufBilder[0] = new GreenfootImage("character1_up1.png");
    aufBilder[1] = new GreenfootImage("character1_up2.png");
    aufBilder[2] = new GreenfootImage("character1_up3.png");
    abBilder[0] = new GreenfootImage("character1_down1.png");
    abBilder[1] = new GreenfootImage("character1_down2.png");
    abBilder[2] = new GreenfootImage("character1_down3.png");
    aktuellerBildIndex = 0;
  };

  private GreenfootImage gibNächstesBild(String richtung) {
    if (aktuellerBildIndex == 2) {
      aktuellerBildIndex = 0;
    } else {
      aktuellerBildIndex++;
    }

    if (richtung == "aufwärts") {
      return aufBilder[aktuellerBildIndex];
    } else {
      return abBilder[aktuellerBildIndex];
    }
  }

  private void gehe(String richtung) {
    int y = 0;
    int schrittWeite = 3;
    if (richtung == "aufwärts") {
      y = getY() - schrittWeite;
    } else if (richtung == "abwärts") {
      y = getY() + schrittWeite;
    }
    setLocation(getX(), y);
    setImage(gibNächstesBild(richtung));
  }

  public void act() {
    if (Greenfoot.isKeyDown("up")) {
      gehe("aufwärts");
    }

    if (Greenfoot.isKeyDown("down")) {
      gehe("abwärts");
    }
  }
}

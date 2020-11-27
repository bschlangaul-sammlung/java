package org.bschlangaul.aufgaben.oomup.ab_3.bier;

import greenfoot.GreenfootImage;

public class Gegner extends Akteur
{
  protected GreenfootImage[] bilder;
  protected int aktuellerBildIndex;
  protected String richtung;
  public int yPosition;
  public int xPosition;
  protected int geschwindigkeit;
  protected int bilderAnzahl;
  protected boolean nachLinks;
  protected String bilderName;

  public Gegner () {
    yPosition = BierWorld.gibZufallsBereich(150, 350);
    geschwindigkeit = BierWorld.gibZufallsBereich(1, 3);
    nachLinks = BierWorld.gibZufallsWahrFalsch();

    if (nachLinks) {
      xPosition = 360;
      geschwindigkeit = geschwindigkeit * -1;
    } else {
      xPosition = 0;
    }
  }

  public boolean istDurchgelaufen() {
    if ((nachLinks && getX() <= 1) || (!nachLinks && getX() >= 359)) {
      return true;
    }
    return false;
  }

  protected void sammleBilder () {
    bilder = new GreenfootImage[bilderAnzahl];
    for (int i = 1; i <= bilderAnzahl; i++) {
      bilder[i - 1] = new GreenfootImage(bilderName + i + ".png");
    }
  }

  private GreenfootImage gibNächstesBild() {
    if (aktuellerBildIndex == bilderAnzahl - 1) {
      aktuellerBildIndex = 0;
    } else {
      aktuellerBildIndex++;
    }
    return bilder[aktuellerBildIndex];
  }

  private void gehe() {
    setLocation(getX() + geschwindigkeit, yPosition);
    setImage(gibNächstesBild());
  }

  public void act() {
    gehe();
  }
}

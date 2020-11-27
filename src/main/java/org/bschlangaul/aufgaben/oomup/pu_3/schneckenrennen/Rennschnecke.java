package org.bschlangaul.aufgaben.oomup.pu_3.schneckenrennen;

public class Rennschnecke {
  private String name;
  @SuppressWarnings("unused")
  private String rasse;
  private int maxSpeed;
  private double strecke;

  public Rennschnecke(String name, String r, int m) {
    this.name = name;
    this.rasse = r;
    maxSpeed = m;
    strecke = 0;
  }

  public void kriechen() {
    strecke = strecke + (1 - Math.random()) * maxSpeed;
  }

  public String gibName() {
    return name;
  }

  public double gibStrecke() {
    return strecke;
  }

}

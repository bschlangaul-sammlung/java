package org.bschlangaul.aufgaben.oomup.ab_5;

public class Katze extends Kleintier {
  @SuppressWarnings("unused")
  private boolean istHauskatze;

  public Katze(String name, int alter, float gewicht, boolean istHauskatze) {
    super(name, alter, gewicht);
    this.istHauskatze = istHauskatze;
    narkoseGrundGebuehr = 1f;
  }
}

package org.bschlangaul.aufgaben.aud.ab_5;

public class WortPaar extends WoerterbuchEintrag {
  private final String deutsch;

  private final String englisch;

  public WortPaar(String deutsch, String englisch) {
    this.deutsch = deutsch;
    this.englisch = englisch;
  }

  public String gibDeutschesWort() {
    return deutsch;
  }

  public String gibEnglischesWort() {
    return englisch;
  }
}

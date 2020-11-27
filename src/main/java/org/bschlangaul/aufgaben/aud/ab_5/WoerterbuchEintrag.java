package org.bschlangaul.aufgaben.aud.ab_5;

public abstract class WoerterbuchEintrag {
  protected WortPaar nächstes;

  protected WortPaar gibNächstes () {
    return nächstes;
  }

  protected void setzeNächstes (WortPaar wortPaar) {
    nächstes = wortPaar;
  }
}

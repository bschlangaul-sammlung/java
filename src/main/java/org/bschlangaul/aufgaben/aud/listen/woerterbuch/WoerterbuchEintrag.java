package org.bschlangaul.aufgaben.aud.listen.woerterbuch;

public abstract class WoerterbuchEintrag {
  protected WortPaar nächstes;

  protected WortPaar gibNächstes () {
    return nächstes;
  }

  protected void setzeNächstes (WortPaar wortPaar) {
    nächstes = wortPaar;
  }
}

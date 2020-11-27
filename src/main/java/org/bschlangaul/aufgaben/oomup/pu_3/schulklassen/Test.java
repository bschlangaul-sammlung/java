package org.bschlangaul.aufgaben.oomup.pu_3.schulklassen;

public class Test {
  Schulklasse sk;

  public Test() {
    sk = new Schulklasse("10a");
  }

  public void testen() {
    Schueler s1 = new Schueler("Bert", "Müller", "Amselweg", "2a", 90444, "Nürnberg");
    Schueler s2 = new Schueler("Lisa", "Meier", "Ludwigstrasse", "5", 92345, "Erlangen");
    Schueler s3 = new Schueler("Michael", "Schmidt", "Buchgasse", "3", 81234, "Infohausen");

    sk.schuelerAufnehmen(s1);
    sk.schuelerAufnehmen(s2);
    sk.schuelerAufnehmen(s3);
  }

  public void aufnehmen(Schueler s) {
    sk.schuelerAufnehmen(s);
  }

  public void klassenAusgeben() {
    sk.klasseAusgeben();
  }
}

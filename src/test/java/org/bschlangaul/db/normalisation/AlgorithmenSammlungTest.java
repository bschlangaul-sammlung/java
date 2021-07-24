package org.bschlangaul.db.normalisation;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

public class AlgorithmenSammlungTest {
  @Test
  public void findeKandidatenSchlüssel() {
    Set<Abhaengigkeit> abhängigkeiten = Abhaengigkeit.getSet("a -> b; b -> c;");
    Set<Attribut> attribute = Abhaengigkeit.extrahiereAttribute(abhängigkeiten);
    Set<Set<Attribut>> ergebnis = AlgorithmenSammlung.findeKandidatenSchlüssel(attribute, abhängigkeiten);
    for (Set<Attribut> attributMenge : ergebnis) {
      for (Attribut attribut : attributMenge) {
        assertEquals(attribut.getName(), "a");
      }
    }
  }
}

package org.bschlangaul.db.normalisation;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

public class AbhaengigkeitTest {
  @Test
  public void staticMethodeExtractAttributs() {
    Set<Abhaengigkeit> fds = Abhaengigkeit.getSet("a -> b; b -> c; a, b -> c; c, b-> a;");
    Set<Attribut> actual = Abhaengigkeit.extrahiereAttribute(fds);
    Set<Attribut> expected = Attribut.getSet("a, b, c");
    assertEquals(expected, actual);
  }

  @Test
  public void methodeGetAllAttributs() {
    Abhaengigkeit fd = Abhaengigkeit.of("c, b-> a");
    Set<Attribut> expected = Attribut.getSet("a, b, c");
    assertEquals(expected, fd.getAllAttributes());
  }
}

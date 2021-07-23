package io.bretty.solver.normalization;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

public class FuncDepTest {
  @Test
  public void staticMethodeExtractAttributes() {
    Set<FunctionalDependency> fds = FunctionalDependency.getSet("a -> b; b -> c; a, b -> c; c, b-> a;");
    Set<Attribute> actual = FunctionalDependency.extractAttributes(fds);
    Set<Attribute> expected = Attribute.getSet("a, b, c");
    assertEquals(expected, actual);
  }

  @Test
  public void methodeGetAllAttributes() {
    FunctionalDependency fd = FunctionalDependency.of("c, b-> a");
    Set<Attribute> expected = Attribute.getSet("a, b, c");
    assertEquals(expected, fd.getAllAttributes());
  }
}

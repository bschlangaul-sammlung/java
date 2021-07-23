package io.bretty.solver.normalization;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

public class FuncDepTest {
  @Test
  public void staticMethodeExtractAttributes() {
    Set<FuncDep> fds = FuncDep.getSet("a -> b; b -> c; a, b -> c; c, b-> a;");
    Set<Attribute> actual = FuncDep.extractAttributes(fds);
    Set<Attribute> expected = Attribute.getSet("a, b, c");
    assertEquals(expected, actual);
  }

  @Test
  public void methodeGetAllAttributes() {
    FuncDep fd = FuncDep.of("c, b-> a");
    Set<Attribute> expected = Attribute.getSet("a, b, c");
    assertEquals(expected, fd.getAllAttributes());
  }
}

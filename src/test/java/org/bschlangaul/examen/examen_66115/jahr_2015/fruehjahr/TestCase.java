package org.bschlangaul.examen.examen_66115.jahr_2015.fruehjahr;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestCase {

  @Test
  public void testeStapel() {
    Stapel s = new Stapel();
    s.push(1);
    s.push(2);
    s.push(3);

    assertEquals(false, s.isEmpty());

    assertEquals(3, s.top());
    assertEquals(3, s.pop());

    assertEquals(2, s.top());
    assertEquals(2, s.pop());

    assertEquals(1, s.top());
    assertEquals(1, s.pop());
    assertEquals(true, s.isEmpty());
  }

  @Test
  public void testeSchlange() {
    Schlange s = new Schlange();
    s.enqueue(1);
    s.enqueue(2);
    s.enqueue(3);

    assertEquals(false, s.isEmpty());

    assertEquals(1, s.head());
    assertEquals(1, s.dequeue());

    assertEquals(2, s.head());
    assertEquals(2, s.dequeue());

    assertEquals(3, s.head());
    assertEquals(3, s.dequeue());
    assertEquals(true, s.isEmpty());
  }


}

package org.bschlangaul.examen.examen_66115.jahr_2007.fruehjahr.queue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QueueTest {

  @Test
  public void methodAppend() {
    Queue queue = new Queue();
    assertEquals(true, queue.isEmpty());
    queue.append(1);
    assertEquals(false, queue.isEmpty());
  }

  @Test
  public void methodRemove() {
    Queue queue = new Queue();
    queue.append(1);
    queue.append(2);
    queue.append(3);

    assertEquals(1, queue.remove());
    assertEquals(2, queue.remove());
    assertEquals(3, queue.remove());
    assertEquals(null, queue.remove());
  }

  @Test
  public void methodIsEmpty() {
    Queue queue = new Queue();
    assertEquals(true, queue.isEmpty());
  }

}

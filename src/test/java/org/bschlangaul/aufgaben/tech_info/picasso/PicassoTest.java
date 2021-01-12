package org.bschlangaul.aufgaben.tech_info.picasso;

import static org.junit.Assert.assertEquals;
import javax.swing.JOptionPane;

import org.junit.Ignore;
import org.junit.Test;

public class PicassoTest {

  @Test
  @Ignore
  public void testOneThread() {
    Picasso picasso = new Picasso();
    int width = 10;
    int height = 10;
    int threads = 1;
    boolean failed = false;
    try {
      picasso.paint(width, height, threads);
      JOptionPane.showMessageDialog(null, "Finished Test1", "Test1", JOptionPane.INFORMATION_MESSAGE, null);
    } catch (Exception e) {
      failed = true;
    }
    assertEquals(failed, false);
  }

  @Test
  @Ignore
  public void testFiveThreads() {
    Picasso picasso = new Picasso();
    int width = 50;
    int height = 50;
    int threads = 5;
    boolean failed = false;
    try {
      picasso.paint(width, height, threads);
      JOptionPane.showMessageDialog(null, "Finished Test2", "Test2", JOptionPane.INFORMATION_MESSAGE, null);
    } catch (Exception e) {
      failed = true;
    }
    assertEquals(failed, false);
  }

  @Test
  @Ignore
  public void testTenThreads() {
    Picasso picasso = new Picasso();
    int width = 100;
    int height = 100;
    int threads = 10;
    boolean failed = false;
    try {
      picasso.paint(width, height, threads);
      JOptionPane.showMessageDialog(null, "Finished Test3", "Test3", JOptionPane.INFORMATION_MESSAGE, null);
    } catch (Exception e) {
      failed = true;
    }
    assertEquals(failed, false);
  }
}

package org.bschlangaul.examen.examen_66115.jahr_2013.fruehjahr;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class ZoneTest {

  Zone zone;

  @Before
  public void legeZoneAn() {
    zone = new Zone();
    zone.addUrl("google.de", "173.194.69.9");
    zone.addUrl("bayern.de", "195.200.71.1");
    zone.addUrl("facebook.com", "69.171.229.1");
    zone.addUrl("gmx.net", "213.165.64.7");
  }

  @Test
  public void methodExists() {
    assertEquals(true, zone.exists(0));
    assertEquals(true, zone.exists(1));
    assertEquals(false, zone.exists(2));
    assertEquals(true, zone.exists(45));
    assertEquals(false, zone.exists(46));
  }

  @Test
  public void methodGetIndex() {
    ArrayList<String> urlCollisionList = zone.getUrlCollisionList(45);
    assertEquals(0, zone.getIndex("facebook.com", urlCollisionList));
    assertEquals(1, zone.getIndex("gmx.net", urlCollisionList));
    assertEquals(-1, zone.getIndex("bschlangaul.org", urlCollisionList));
  }

  @Test
  public void methodLookup() {
    assertEquals("173.194.69.9", zone.lookup("google.de"));
    assertEquals("195.200.71.1", zone.lookup("bayern.de"));
    assertEquals("69.171.229.1", zone.lookup("facebook.com"));
    assertEquals("213.165.64.7", zone.lookup("gmx.net"));
    assertEquals("Die URL kannte nicht in der Tabelle gefunden werden", zone.lookup("bschlangaul.org"));
  }
}

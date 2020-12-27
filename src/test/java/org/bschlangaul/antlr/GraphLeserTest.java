package org.bschlangaul.antlr;

import static org.junit.Assert.assertEquals;

import org.bschlangaul.graph.EinfachesGraphenFormat;
import org.junit.Test;


public class GraphLeserTest {

    @Test
    public void ungerichteteKanten() throws Exception {
        String graphLines = "a -- b\r\n" +
                "c -- d\r\n";
        EinfachesGraphenFormat einfach = GraphLeser.lese(graphLines);
        assertEquals(einfach.gibAnzahlKanten(), 2);
        //assertEquals(einfach.gibAnzahlKnoten(), 3);



    }
}

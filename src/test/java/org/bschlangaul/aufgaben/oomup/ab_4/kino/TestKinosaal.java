package org.bschlangaul.aufgaben.oomup.ab_4.kino;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;

public class TestKinosaal
{

    // https://stackoverflow.com/a/1119559/10193818
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void erfolgreicheReservierung()
    {
        Kinosaal kinosaal = new Kinosaal(1);
        assertTrue(kinosaal.sitzplatzReservieren(1, 1));
    }

    @Test
    public void falscheSitzplanNummer()
    {
        Kinosaal kinosaal = new Kinosaal(1);

        assertFalse(kinosaal.sitzplatzReservieren(0, 1));
        assertEquals("Leider besitzt der Kinosaal 1 keinen Sitzplatz mit der Nummer 0." + System.lineSeparator(), outContent.toString());

        outContent.reset();

        assertFalse(kinosaal.sitzplatzReservieren(181, 1));
        assertEquals("Leider besitzt der Kinosaal 1 keinen Sitzplatz mit der Nummer 181." + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void sitzplatzBereitsReserviert()
    {
        Kinosaal kinosaal = new Kinosaal(1);
        kinosaal.sitzplatzReservieren(1, 1);
        assertFalse(kinosaal.sitzplatzReservieren(1, 1));
    }
}

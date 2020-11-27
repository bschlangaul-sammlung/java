package org.bschlangaul.aufgaben.oomup.ab_4.kino;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestSitzplatz
{
    @Test
    public void erfolgreicheReservierung()
    {
        Sitzplatz sitzplatz = new Sitzplatz(1);
        assertTrue(sitzplatz.reservieren(1));
    }

    @Test
    public void sitzplatzBereitsReserviert()
    {
        Sitzplatz sitzplatz = new Sitzplatz(1);
        sitzplatz.reservieren(1);
        assertFalse(sitzplatz.reservieren(2));
    }
}

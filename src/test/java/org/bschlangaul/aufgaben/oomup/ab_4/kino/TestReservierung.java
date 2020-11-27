package org.bschlangaul.aufgaben.oomup.ab_4.kino;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestReservierung
{
    @Test
    public void attribute()
    {
        Sitzplatz sitzplatz = new Sitzplatz(1);
        Kinosaal kinosaal = new Kinosaal(1);
        Reservierung reservierung = new Reservierung(kinosaal, sitzplatz, 1);
        assertEquals(reservierung.nummer, 1);
        assertEquals(reservierung.sitzplatz.nummer, 1);
        assertEquals(reservierung.kinosaal.nummer, 1);
    }
}

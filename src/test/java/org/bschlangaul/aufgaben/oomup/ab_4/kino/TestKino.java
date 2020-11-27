package org.bschlangaul.aufgaben.oomup.ab_4.kino;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;

public class TestKino
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
        Kino kino = new Kino();
        kino.sitzplatzReservieren(1, 1);
        assertEquals("Ihre Reservierungsnummer lautet: 1" + System.lineSeparator(), outContent.toString());
        outContent.reset();
        kino.sitzplatzReservieren(1, 2);
        assertEquals("Ihre Reservierungsnummer lautet: 2" + System.lineSeparator(), outContent.toString());

    }

    @Test
    public void fehlgeschlageneReservierung()
    {
        Kino kino = new Kino();
        kino.sitzplatzReservieren(1, 1);
        outContent.reset();
        kino.sitzplatzReservieren(1, 1);
        assertEquals("Der gewünschte Sitzplatz mit der Nummer 1 im Kino 1 ist bereits vergeben." + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void ticketAbholenErfolgreich()
    {
        Kino kino = new Kino();
        kino.sitzplatzReservieren(1, 1);
        outContent.reset();
        kino.ticketAbholen(1);
        assertEquals("Genießen Sie Ihren Film im Kino 1 auf dem Sitzplatz 1." + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void ticketAbholenUngueltigeBuchungsnummer()
    {
        Kino kino = new Kino();
        kino.sitzplatzReservieren(1, 1);
        outContent.reset();
        kino.ticketAbholen(2);
        assertEquals("Uns liegt keine Reservierung mit der Nummer 2 vor." + System.lineSeparator(), outContent.toString());
    }
}

package org.bschlangaul.aufgaben.oomup.pu_2.schildkroete;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)


public class Zeichenhintergrund extends World
{

    /**
     * Konstruktor für Objekte der Klasse Zeichenhintergrund
     *
     */
    public Zeichenhintergrund()
    {
        // Erstellt eine neue Welt mit 900x600 Zellen und einer Zell-Größe von 1x1 Pixeln.
        super(900, 600, 1);
        prepare();
    }

    /**
     * Bereite die Welt für den Programmstart vor.
     * Das heißt: Erzeuge die Anfangs-Objekte und füge sie der Welt hinzu.
     */
    private void prepare()
    {
        meineSchildkroete meineSchildkroete = new meineSchildkroete();
        addObject(meineSchildkroete,450,350);
    }
}

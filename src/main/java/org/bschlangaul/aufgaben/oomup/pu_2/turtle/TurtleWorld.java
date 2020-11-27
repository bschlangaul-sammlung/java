package org.bschlangaul.aufgaben.oomup.pu_2.turtle;

import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

public class TurtleWorld extends World
{
    /**
     * Create the turtle world. Our world has a size
     * of 560x460 cells, where every cell is just 1 pixel.
     */
    public TurtleWorld()
    {
        super(600, 480, 1);
        prepare();
        showText("Punkte: 0", 75, 75);
    }

    /**
     * Bereite die Welt für den Programmstart vor.
     * Das heißt: Erzeuge die Anfangs-Objekte und füge sie der Welt hinzu.
     */
    private void prepare()
    {
        Turtle turtle = new Turtle();
        addObject(turtle,155,252);
        Salat salat = new Salat();
        addObject(salat,240,122);
        Salat salat2 = new Salat();
        addObject(salat2,440,125);
        Salat salat3 = new Salat();
        addObject(salat3,488,323);
        Salat salat4 = new Salat();
        addObject(salat4,259,396);
        Salat salat5 = new Salat();
        addObject(salat5,73,385);
        Salat salat6 = new Salat();
        addObject(salat6,72,204);
        Salat salat7 = new Salat();
        addObject(salat7,299,248);
        Snake snake = new Snake();
        addObject(snake,343,96);
        Snake snake2 = new Snake();
        addObject(snake2,413,427);
        Snake snake3 = new Snake();
        addObject(snake3,69,109);
        Salat salat8 = new Salat();
        addObject(salat8,566,219);
        Salat salat9 = new Salat();
        addObject(salat9,547,115);
        Salat salat10 = new Salat();
        addObject(salat10,410,265);
        Salat salat11 = new Salat();
        addObject(salat11,496,418);
        Salat salat12 = new Salat();
        addObject(salat12,136,48);
        Salat salat13 = new Salat();
        addObject(salat13,152,416);
        Bug bug = new Bug();
        addObject(bug,329,322);
        removeObject(bug);
        Bug bug2 = new Bug();
        addObject(bug2,346,341);
        Bug bug3 = new Bug();
        addObject(bug3,461,70);
    }
}

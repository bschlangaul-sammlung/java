package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

// -- Im Order Samples von Mehr-Adress-Simulator.zip

// wurzel:         SEG

// init:           MOVE W I 15, R0  -- in R0 steht der Wert,
//                 -- dessen Wurzel berechnet wird

//                 MOVE W I 1, R1  -- Untere Grenze
//                 MOVE W R0, R2  -- Obere Grenze

// berechne:       SUB W R1, R2, R3 -- Delta berechnen
//                 SH I -1, R3, R3  -- Delta / 2 berechnen
//                 ADD W R1, R3, R3 -- Mitte berechnen
//                 MOVE W R3, R4  -- Mitte sichern
//                 MULT W R3, R3  -- Quadrat Mitte berechnen

// entscheide:     CMP W R3, R0  -- Vergleich mit Eingabe
//                 JEQ fertig  -- Wurzel gefunden
//                 JLT zuklein  -- Wert war zu klein

// zugross:        MOVE W R4, R2  -- Mitte wird zu Obergrenze
//                 JUMP istende  -- Sprung zum Abbruchstest

// zuklein:        MOVE W R4, R1  -- Mitte wird zu Untergrenze

// istende:        SUB W R1, R2, R3 -- Differenz Ober-/Untergrenze
//                 CMP W I 1, R3
//                 JEQ fertig
//                 JUMP berechne

// fertig:         MOVE W R1, R5  -- Untergrenze als Ergebnis nach R5

//                 HALT
//                 END

class QuadratWurzel {


  public static void main(String[] args) {
    double quadratZahl = 16;
    double untereGrenze = 1;
    double obereGrenze = quadratZahl;
    double quadratSchaetzung = obereGrenze;
    double schaetzung = untereGrenze;

    while (Math.abs(quadratZahl - quadratSchaetzung) < 0.001) {
      double delta = obereGrenze - untereGrenze;
      delta = delta / 2;
      schaetzung = untereGrenze + delta;
      quadratSchaetzung = schaetzung * schaetzung;
      obereGrenze = quadratSchaetzung;
      System.out.println(obereGrenze);
    }
    System.out.println(schaetzung);

  }


  // https://de.wikipedia.org/wiki/Heron-Verfahren

  // http://gailer-net.de/tutorials/java/Notes/chap19/ch19_15.html
  public static void quadratWurzelZiehen(String[] args) {
    final double FASTNULL = 1.0E-14;
    double n = 3.0;
    double schaetzung = 1.0;

    while (Math.abs(n / (schaetzung * schaetzung) - 1.0) > FASTNULL) {
      // einen neuen Wert für schaetzung berechnen
      schaetzung = n / (2 * schaetzung) + schaetzung / 2;

    }

    System.out.println("Die Quadratwurzel von " + n + " ist " + schaetzung);
  }

}

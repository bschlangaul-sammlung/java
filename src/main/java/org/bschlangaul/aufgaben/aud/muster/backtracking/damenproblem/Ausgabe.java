package org.bschlangaul.aufgaben.aud.muster.backtracking.damenproblem;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;

public class Ausgabe extends JPanel {
  JFrame f;
  int[][] feld;
  int feldGroese;
  final int fensterRand = 50;
  final int fensterGroesse = 500;
  Color feldColor = Color.DARK_GRAY;
  int felderAnz;

  public Ausgabe(int felderAnz, int[][] feld) {
    feldGroese = fensterGroesse / felderAnz;
    this.felderAnz = felderAnz;
    this.feld = feld;
    f = new JFrame("Damenproblem");
    f.setSize(fensterGroesse + 2 * fensterRand, fensterGroesse + 2 * fensterRand);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
    f.add(this);
  }

  @Override
  public void paint(Graphics g) {
    int xPos = fensterRand;
    int yPos = fensterRand;

    if (felderAnz % 2 != 0) {
      for (int i = 0; i < feld.length; i++) {
        for (int j = 0; j < feld[i].length; j++) {
          g.setColor(switchColor());
          if (feld[i][j] == 1) {
            // Dame
            g.setColor(Color.ORANGE);
            g.fillRect(xPos, yPos, feldGroese, feldGroese);
          } else {
            // Normalfeld
            g.fillRect(xPos, yPos, feldGroese, feldGroese);
          }
          xPos += feldGroese;
        }
        xPos = fensterRand;
        yPos += feldGroese;
      }
    } else {
      for (int i = 0; i < feld.length; i++) {
        for (int j = 0; j < feld[i].length; j++) {
          if (feld[i][j] == 1) {
            // Dame
            g.setColor(Color.ORANGE);
            g.fillRect(xPos, yPos, feldGroese, feldGroese);
          } else {
            // Normalfeld
            g.fillRect(xPos, yPos, feldGroese, feldGroese);
          }

          xPos += feldGroese;
          g.setColor(switchColor());
        }
        g.setColor(switchColor());
        xPos = fensterRand;
        yPos += feldGroese;
      }
    }
  }

  private Color switchColor() {
    if (feldColor == Color.DARK_GRAY) {
      feldColor = Color.GRAY;
      return Color.GRAY;
    } else if (feldColor == Color.GRAY) {
      feldColor = Color.DARK_GRAY;
      return Color.DARK_GRAY;
    } else {
      System.err.println("Fehler switchColor!");
      return null;
    }
  }

}

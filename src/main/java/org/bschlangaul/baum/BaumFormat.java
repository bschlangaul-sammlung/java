package org.bschlangaul.baum;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.bschlangaul.antlr.BaumBaseListener;
import org.bschlangaul.antlr.BaumLexer;
import org.bschlangaul.antlr.BaumParser;
import org.bschlangaul.baum.visualisierung.BaumReporter;
import org.bschlangaul.baum.visualisierung.TexBaumReporter;

class AntlrListener extends BaumBaseListener {

  BaumFormat baumFormat;

  List<BaumFormat.BaumArt> bäume = new ArrayList<BaumFormat.BaumArt>();

  BaumFormat.BaumArt baum;

  List<BaumFormat.BaumAktion> aktionen = new ArrayList<BaumFormat.BaumAktion>();

  BaumFormat.BaumAktion aktion;

  public AntlrListener(BaumFormat baumFormat) {
    this.baumFormat = baumFormat;
  }

  @Override
  public void enterBaum(BaumParser.BaumContext ctx) {
    baum = new BaumFormat.BaumArt();
    baum.art = ctx.baumArt().getText();
  }

  @Override
  public void exitBaum(BaumParser.BaumContext ctx) {
    baum.aktionen = aktionen.toArray(new BaumFormat.BaumAktion[0]);
    aktionen = new ArrayList<BaumFormat.BaumAktion>();
    bäume.add(baum);
  }

  @Override
  public void enterAktion(BaumParser.AktionContext ctx) {
    aktion = new BaumFormat.BaumAktion();
    aktion.befehl = ctx.befehl().getText();
    aktion.werte = new int[ctx.wert().size()];
    for (int i = 0; i < ctx.wert().size(); i++) {
      aktion.werte[i] = Integer.parseInt(ctx.wert().get(i).getText());
    }
  }

  @Override
  public void exitAktion(BaumParser.AktionContext ctx) {
    aktionen.add(aktion);
  }

  @Override
  public void exitEinstiegsPunkt(BaumParser.EinstiegsPunktContext ctx) {
    baumFormat.bäume = bäume.toArray(new BaumFormat.BaumArt[0]);
  }

}

public class BaumFormat {

  public static class BaumAktion {
    public String befehl;
    public int[] werte;
  }

  public static class BaumArt {
    public String art;
    public BaumAktion[] aktionen;
  }

  public BaumArt[] bäume;

  public BaumFormat(String format) {
    leseTextFormat(format);
  }

  private void leseTextFormat(String inhalt) {
    BaumLexer serverGraphLexer = new BaumLexer(CharStreams.fromString(inhalt));
    CommonTokenStream tokens = new CommonTokenStream(serverGraphLexer);
    BaumParser parser = new BaumParser(tokens);

    ParseTreeWalker walker = new ParseTreeWalker();
    AntlrListener antlrListener = new AntlrListener(this);
    walker.walk(antlrListener, parser.einstiegsPunkt());
  }

  public static String gibAusFürEinbettung(String formatText) {
    String ausgabe = "";
    BaumReporter reporter = new TexBaumReporter();
    BaumFormat baumFormat = new BaumFormat(formatText);
    for (BaumFormat.BaumArt baumArt : baumFormat.bäume) {
      System.out.println(baumArt.art);
      BinaerBaum baum;
      if (baumArt.art.equals("avl")) {
        baum = new AVLBaum();
      } else {
        baum = new BinaererSuchBaum();
      }

      for (BaumFormat.BaumAktion aktion : baumArt.aktionen) {
        switch (aktion.befehl) {
          case "setze":
            for (int wert : aktion.werte) {
              baum.fügeEin(wert);
            }
            break;

          case "drucke":
            ausgabe += reporter.erzeugeBaum(baum);
            break;

          case "lösche":
            if (baum instanceof AVLBaum) {
              for (int wert : aktion.werte) {
                AVLBaum avl = (AVLBaum) baum;
                avl.entferne(wert);
              }
            }
            break;

          default:
            break;
        }

      }
    }
    return ausgabe;
  }

  public static void gibAusFürProjektSprachen(String formatText) {
    System.out.println(gibAusFürEinbettung(formatText));
  }
}

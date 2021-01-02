package org.bschlangaul.baum;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.bschlangaul.antlr.BaumBaseListener;
import org.bschlangaul.antlr.BaumLexer;
import org.bschlangaul.antlr.BaumParser;

class AntlrListener extends BaumBaseListener {

  BaumFormat baumFormat;

  List<BaumArt> bäume = new ArrayList<BaumArt>();

  BaumArt baum;

  List<BaumAktion> aktionen = new ArrayList<BaumAktion>();

  BaumAktion aktion;

  public AntlrListener(BaumFormat baumFormat) {
    this.baumFormat = baumFormat;
  }

  @Override
  public void enterBaum(BaumParser.BaumContext ctx) {
    baum = new BaumArt();
    baum.art = ctx.baumArt().getText();
  }

  @Override
  public void exitBaum(BaumParser.BaumContext ctx) {
    baum.aktionen = aktionen.toArray(new BaumAktion[0]);
    aktionen = new ArrayList<BaumAktion>();
    bäume.add(baum);
  }

  @Override
  public void enterAktion(BaumParser.AktionContext ctx) {
    aktion = new BaumAktion();
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
    baumFormat.bäume = bäume.toArray(new BaumArt[0]);
  }

}

class BaumAktion {
  String befehl;
  int[] werte;
}

class BaumArt {
  String art;
  BaumAktion[] aktionen;
}

public class BaumFormat {

  BaumArt[] bäume;

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

}

package org.bschlangaul.antlr;

import org.bschlangaul.antlr.graph.GraphListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;


public class GraphParserUnitTest {

    @Test
    public void whenGraphContainsOneErrorGraphEntry_thenOneErrorIsReturned() throws Exception {
        String graphLines = "a -- b\r\n" +
                "c -- d\r\n";
        GraphLexer serverGraphLexer = new GraphLexer(CharStreams.fromString(graphLines));
        CommonTokenStream tokens = new CommonTokenStream( serverGraphLexer );
        GraphParser graphParser = new GraphParser(tokens);
        ParseTreeWalker walker = new ParseTreeWalker();
        GraphListener graph = new GraphListener();
        walker.walk(graph, graphParser.kanten());

        System.out.println(graph.gibErsteKante().von);

    }
}

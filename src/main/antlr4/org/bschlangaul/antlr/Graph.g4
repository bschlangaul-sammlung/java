grammar Graph;

graph: zeile+ EOF;

zeile: (knoten | kante) TRENNER;

knoten: name markiert? (':' x y)?;
name: knoten_name;
x: GLEITZAHL;
y: GLEITZAHL;

kante: von (gerichtet | ungerichtet)? nach markiert? (':' gewicht)?;
von: knoten_name;
nach: knoten_name;
gerichtet: '->';
ungerichtet: '--';
gewicht: GLEITZAHL;

knoten_name: (GLEITZAHL | UNZITIERTER_TEXT | ZITIERTER_TEXT_DOPPELT | ZITIERTER_TEXT_EINFACH );
markiert: '*';

fragment ZAHL: [0-9];
fragment DEZIMALZAHL: '-'? GANZZAHL '.' GANZZAHL;
fragment GANZZAHL: '-'? ZAHL+;

TRENNER: [;\n\r]+;

GLEITZAHL: DEZIMALZAHL | GANZZAHL;
ZITIERTER_TEXT_DOPPELT: '"' ('\\"' | ~[\n])*? '"';
ZITIERTER_TEXT_EINFACH: '\'' ('\\\'' | ~[\n])*? '\'';
UNZITIERTER_TEXT: (~(' ' | '\n' | '\r' | '\t' | ';' | ':' | '-' | '>' | '*'))+;

LEERZEICHEN: [ \t]+ -> skip;
ZEILEN_ENDE: '\n' -> skip;
KOMMENTAR: [ \t]* '%' ~[\r\n;]* TRENNER -> skip;

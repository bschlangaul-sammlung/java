grammar Graph;

graph: zeile+ EOF;

zeile: (knoten | kante) ZEILEN_ENDE;

kante : von ' '* (gerichtet | ungerichtet) ' '* nach;
gerichtet: '--';
ungerichtet: '->';
von : KNOTENNAME;
nach : KNOTENNAME;

knoten : name ' '* ':' ' '* x ' '+ y;
name : KNOTENNAME;
x : GLEITZAHL;
y : GLEITZAHL;

fragment BUCHSTABE : [A-Za-z];
fragment ZAHL : [0-9];
fragment OPTIONALES_LEERZEICHEN: LEERZEICHEN+;

GLEITZAHL : DEZIMALZAHL | GANZZAHL;
DEZIMALZAHL : '-'? GANZZAHL '.' GANZZAHL;
GANZZAHL : '-'? ZAHL+;

KNOTENNAME : BUCHSTABE+;
ZEILEN_ENDE : '\r'? '\n' | '\r' | ';';
LEERZEICHEN: [ \t]+;

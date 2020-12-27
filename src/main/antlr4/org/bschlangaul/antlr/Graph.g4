grammar Graph;

graph: alleKnoten? alleKanten?;

alleKanten : kante+;
kante : von ' -- ' nach CRLF;
von : KNOTENNAME;
nach : KNOTENNAME;

alleKnoten : knoten+;
knoten : name ' ' x ' ' y CRLF;
name : KNOTENNAME;
x : NUMERISCH;
y : NUMERISCH;

fragment LETTER : [A-Za-z];
fragment ZAHL : [0-9];

NUMERISCH : ZAHL+;

KNOTENNAME : LETTER+;
CRLF : '\r'? '\n' | '\r';

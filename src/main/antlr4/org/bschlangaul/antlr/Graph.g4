grammar Graph;

kanten : kante+;
kante : von ' -- ' nach CRLF;
von : KNOTENNAME;
nach : KNOTENNAME;

fragment LETTER : [A-Za-z];

KNOTENNAME : LETTER+;
CRLF : '\r'? '\n' | '\r';

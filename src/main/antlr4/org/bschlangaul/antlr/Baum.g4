grammar Baum;
wald: baum+ EOF;
baum: 'baum' baumArt '(' anweisung+ ')';
anweisung: befehl (':' argument+)? ';';
befehl: (SETZE | DRUCKE | LÖSCHE);
argument: ZAHL;
baumArt: ('avl' | 'binär');

ZAHL: [0-9]+;
SETZE: 'setze';
DRUCKE: 'drucke';
LÖSCHE: 'lösche';

ZEILEN_ENDE: '\n' -> skip;
LEERZEICHEN: [ \t]+ -> skip;

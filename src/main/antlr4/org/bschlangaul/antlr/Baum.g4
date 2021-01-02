grammar Baum;
einstiegsPunkt: baum+ EOF;
baum: 'baum' baumArt '(' aktion+ ')';
aktion: befehl (':' wert+)? ';';
befehl: (SETZE | DRUCKE | LÖSCHE);
wert: ZAHL;
baumArt: ('avl' | 'binär');

ZAHL: [0-9]+;
SETZE: 'setze';
DRUCKE: 'drucke';
LÖSCHE: 'lösche';

ZEILEN_ENDE: '\n' -> skip;
LEERZEICHEN: [ \t]+ -> skip;

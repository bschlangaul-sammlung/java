grammar TexEinbettung;

einstiegsPunkt: (
		anweisungMitEinbettung
		| anweisung
		| tex
	)+ EOF;


anweisungMitEinbettung: umgebungProjektSprache tex umgebungEinbettung;
anweisung: umgebungProjektSprache;

umgebungProjektSprache:
	'\\begin{liProjektSprache}' inhalt '\\end{liProjektSprache}';
umgebungEinbettung:
	'\\begin{liEinbettung}' inhalt '\\end{liEinbettung}';

tex: ALLES+;
inhalt: ALLES+;

ALLES: .;

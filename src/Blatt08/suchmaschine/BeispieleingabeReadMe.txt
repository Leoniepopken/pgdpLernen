Man kann die Beispieleingabe in das TestIt entweder pipen mit
cat Beispieleingabe | java TestIt
(Möglicherweise muss man vorher nochmal extra kompilieren mit javac TestIt.java)

Oder man kann mit Strg+C, Strg+V in die Eclipse-Eingabe pasten. Die erwartete Ausgabe ist dann:
> a.txt
> > a.txt
e.txt
b.txt
> 

(Das Layout der > wird zerschossen, das stört aber nicht.)

Das ist nur ein minimaler Test, aber besser als nichts finde ich.
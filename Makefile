# KIN MAX PIAMOLINI GUSMÃO - 16104046-4 - kin.gusmao@edu.pucrs.br
# PEDRO FRATINI CHEM       - 18109228   - pedro.chem@edu.pucrs.br
# YAGO DOS ANJOS VIEIRA    - 11203949-0 - yago.vieira@edu.pucrs.br
# only works with the Java extension of yacc: 
# byacc/j from http://troi.lincom-asg.com/~rjamison/byacc/

JFLEX  = java -jar JFlex.jar 
BYACCJ = ./yacc.linux -tv -J
JAVAC  = javac

# targets:

all: Parser.class

run: Parser.class
	java Parser

build: clean Parser.class

clean:
	rm -f *~ *.class *.o *.s Yylex.java Parser.java y.output

Parser.class: TS_entry.java TabSimb.java Yylex.java Parser.java
	$(JAVAC) Parser.java

Yylex.java: AJava.flex
	$(JFLEX) AJava.flex

Parser.java: AJava.y
	$(BYACCJ) AJava.y

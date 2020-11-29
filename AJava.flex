%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
    yyline = 1;
  }


  public int getLine() {
      return yyline;
  }

%}

NUM = [0-9]+
NUMDOUBLE = {NUM}+ ("." {NUM}+)
NL  = \n | \r | \r\n

%%


"$TRACE_ON"  { yyparser.setDebug(true);  }
"$TRACE_OFF" { yyparser.setDebug(false); }
"$MOSTRA_TS" { yyparser.listarTS(); }


{NL}   {yyline++;}
[ \t]+ { }

/* operadores */

"+" |
"-" |
"*" | 
"/" | 
"%" | 
">" |
"<" |
"=" |
"!" |
";" |
":" |
"(" | 
")" |
"{" |
"}" |
"," |
"." |
"\[" | 
"\]"    { return (int) yycharat(0); }

{NUM}  { yyparser.yylval = new ParserVal(yytext()); 
         return Parser.NUM; }
{NUMDOUBLE}  { yyparser.yylval = new ParserVal(yytext()); 
         return Parser.NUMDOUBLE; }

"=="   {  return Parser.EQ; }
"<="   {  return Parser.LEQ; }
">="   {  return Parser.GEQ; }
"!="   {  return Parser.NEQ; }
// "+="   {  return Parser.INCREMENT; }
// "-="   {  return Parser.DECREMENT; }
// "++"   {  return Parser.PLUSPLUS; }
// "--"   {  return Parser.MINUSMINUS; }

"&&"   { return Parser.AND; }
"||"   {  return Parser.OR; }

int    { return Parser.INT;     }
double  { return Parser.DOUBLE;   }
boolean   { return Parser.BOOLEAN; }
string   { return Parser.STRING; }
void   { return Parser.VOID; }
break   { return Parser.BREAK; }
new   { return Parser.NEW; }
private { return Parser.PRIVATE; }
public { return Parser.PUBLIC; }
class { return Parser.CLASS; }
while   { return Parser.WHILE; }
endWhile   { return Parser.ENDWHILE; }
for   { return Parser.FOR; }
endFor   { return Parser.ENDFOR; }
if   { return Parser.IF; }
else   { return Parser.ELSE; }
endIf   { return Parser.ENDIF; }
true   { return Parser.TRUE; }
false   { return Parser.FALSE; }
Escreva { return Parser.ESCREVA; }
Leia { return Parser.LEIA; }
return { return Parser.RETURN; }
extends {return Parser.EXTENDS;}


[a-zA-Z]+([a-zA-Z0-9]+)? { yyparser.yylval = new ParserVal(yytext());
            return Parser.ID; }

\"[^\n]+\" { yyparser.yylval = new ParserVal(yytext().substring(1, yylength() -1));
	     return Parser.LIT; }

"//".*                                    { /* DO NOTHING */ } // to remove comments

[/][*][^*]*[*]+([^*/][^*]*[*]+)*[/]       { /* DO NOTHING */ }
[/][*]                                    { System.err.println("Error: unterminated comment"); return -1; }

[^]    { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }


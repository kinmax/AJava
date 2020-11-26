//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "AJava.y"
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
//#line 21 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ID=257;
public final static short INT=258;
public final static short FLOAT=259;
public final static short BOOL=260;
public final static short NUM=261;
public final static short LIT=262;
public final static short VOID=263;
public final static short MAIN=264;
public final static short READ=265;
public final static short WRITE=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short WHILE=269;
public final static short TRUE=270;
public final static short FALSE=271;
public final static short EQ=272;
public final static short LEQ=273;
public final static short GEQ=274;
public final static short NEQ=275;
public final static short AND=276;
public final static short OR=277;
public final static short type=278;
public final static short CLASS=279;
public final static short PRIVATE=280;
public final static short PUBLIC=281;
public final static short RETURN=282;
public final static short INCREMENT=283;
public final static short DECREMENT=284;
public final static short NUMDOUBLE=285;
public final static short NEW=286;
public final static short ESCREVA=287;
public final static short LEIA=288;
public final static short ENDIF=289;
public final static short BREAK=290;
public final static short ENDWHILE=291;
public final static short FOR=292;
public final static short ENDFOR=293;
public final static short DOUBLE=294;
public final static short STRING=295;
public final static short BOOLEAN=296;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    2,    3,    4,    4,    6,    8,
    8,    5,    5,    9,    9,    9,   10,   11,   12,   16,
   13,   13,   17,   18,   18,   15,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   20,   20,
   20,   20,   20,   20,   20,   20,   20,   20,   20,   20,
   14,   22,   22,   23,   23,   23,   23,   23,   23,   23,
   24,   25,   30,   30,   26,   27,   31,   31,   32,   33,
   33,   33,   28,   34,   29,   35,   21,    7,    7,    7,
    7,    7,
};
final static short yylen[] = {                            2,
    1,    0,    2,    0,    5,    6,    2,    0,    4,    2,
    0,    2,    0,    1,    1,    1,    8,    8,   10,    9,
    2,    0,    2,    3,    0,    3,    3,    1,    1,    2,
    2,    3,    1,    1,    1,    1,    5,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    2,    0,    1,    1,    1,    1,    1,    1,    0,
    4,    4,    2,    0,    3,    6,    3,    0,    1,    2,
    1,    0,    5,    1,   11,    1,    6,    1,    1,    1,
    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    3,    0,    0,    0,    0,
    5,   82,   78,   79,   80,   81,    0,    0,    0,    0,
    7,    0,    0,    0,    0,    0,    0,    6,    0,    0,
   14,   15,   16,   10,    9,    0,    0,    0,   12,    0,
    0,    0,    0,    0,   23,    0,    0,   21,    0,    0,
    0,    0,    0,    0,    0,   24,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   51,    0,   54,   55,   56,
   57,   58,   59,    0,    0,    0,    0,   33,   35,   28,
   29,    0,   34,    0,    0,   36,    0,    0,    0,    0,
   17,   52,   18,    0,    0,   30,   31,    0,    0,    0,
   49,   47,   48,   50,   45,   46,   43,   44,   39,   41,
   40,   42,    0,    0,    0,    0,    0,   65,    0,    0,
    0,   61,    0,   32,    0,    0,    0,   71,    0,    0,
    0,   62,    0,    0,   19,    0,    0,    0,    0,   70,
   74,   73,    0,   26,    0,   37,    0,   69,   66,    0,
   77,   67,    0,    0,    0,    0,   76,   75,
};
final static short yydgoto[] = {                          2,
    3,    4,    9,   17,   28,   18,   19,   25,   30,   31,
   32,   33,   41,   65,  121,    0,   42,   48,   85,  114,
   86,   66,   67,   68,   69,   70,   71,   72,   73,  117,
  139,  149,  130,  142,  158,
};
final static short yysindex[] = {                      -271,
 -246,    0,    0, -271, -111,    0, -267,  -44, -110, -138,
    0,    0,    0,    0,    0,    0, -262, -138, -225,  -22,
    0,    5, -117, -210,   -8,   28, -191,    0, -183, -117,
    0,    0,    0,    0,    0, -138,   35,   42,    0, -167,
   55,   58,   64, -138,    0, -138, -138,    0, -138,   70,
   -1,   58,    7, -138,  -97,    0,  -97,   13,   67,  -40,
  -40, -136, -119, -118,   12,    0,  -97,    0,    0,    0,
    0,    0,    0,   18,  -97,  -40,  -45,    0,    0,    0,
    0,  -40,    0, -109,   -4,    0,    3,  106,   92,   93,
    0,    0,    0, -127,   10,    0,    0,  -93,  -18,  128,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -97,  -40, -125,  -40,  110,    0,  -40,  -40,
   46,    0,  134,    0, -138,  -88,   71,    0, -125, -116,
   71,    0,   17,   38,    0, -138,  140,  124, -106,    0,
    0,    0,  -40,    0,  144,    0,  -97,    0,    0,   44,
    0,    0,  -40,   65, -125, -105,    0,    0,
};
final static short yyrindex[] = {                       187,
    0,    0,    0,  189,    0,    0,    0,    0,    0,  -87,
    0,    0,    0,    0,    0,    0,    0, -120,    0,    0,
    0,  133,   68,    0,    0,  -61,    0,    0,    0,   68,
    0,    0,    0,    0,    0,  156,    0,    0,    0,    0,
    0,  157,    0,  156,    0,   77,    0,    0,   77,    0,
    0,  157,    0,   77,   74,    0,   74,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -123,    0,    0,    0,
    0,    0,    0,    0,  -81,    0,  -25,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  143,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -259,    0,  -86,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  156,  -85,   76,    0, -287,    0,
  147,    0,    0,    0,    0,  156,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -82,    0,    0,    0,
    0,    0,    0,    0,  -90,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  204,    0,    0,   75,  179,    0,   48,    0,    0,    0,
    0,    0,  -37,  -47,    0,    0,  163,  159,   33,    0,
    0,  -46,  -89,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -124,    0,    0,
};
final static int YYTABLESIZE=348;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         82,
   98,   53,    8,   60,  140,   60,   50,    1,   53,   74,
    5,    7,    8,   10,   11,   38,   38,   38,   20,   38,
   92,   38,  124,  111,  109,  129,  110,   94,  112,   53,
  156,   22,   38,   38,   38,   23,   38,  111,  109,  129,
  110,  108,  112,  107,  111,  109,   34,  110,   24,  112,
   35,  111,  109,  113,  110,  108,  112,  107,  111,  109,
  115,  110,  108,  112,  107,  129,  126,   36,  122,  108,
   29,  107,   37,   38,   43,  143,  108,   29,  107,  111,
  109,   44,  110,   40,  112,  111,  109,  137,  110,   45,
  112,   40,   21,   87,   40,   46,  144,  108,  145,  107,
  152,   47,  153,  108,   49,  107,  111,  109,   95,  110,
   54,  112,  111,  109,   99,  110,   27,  112,   12,   13,
   51,   55,  155,   53,  108,   88,  107,   76,   58,   57,
  108,   59,  107,   27,   27,   75,   91,   89,   90,   26,
   13,   60,   93,   61,   53,   27,  127,  100,  131,  116,
  118,  133,  134,  119,  120,   14,   15,   16,   53,   59,
    8,   62,   63,  123,  128,   53,   64,  125,  132,   60,
  135,   61,   40,  136,  141,  150,   14,   15,   16,  138,
  146,  147,  148,   40,  151,  154,    2,  157,    4,   62,
   63,   11,   13,    8,   64,   82,   22,   25,   53,    8,
   53,   64,   60,   68,   60,   63,   53,    6,   39,   52,
   56,    0,    0,    0,    0,    0,   77,    0,    0,    0,
   78,   79,    0,    0,    0,    0,    0,    0,    0,   80,
   81,    0,    0,    0,    0,    0,    0,   96,   97,    0,
    0,    0,    0,    0,   83,   84,   38,   38,   38,   38,
   38,   38,    0,  101,  102,  103,  104,  105,  106,    0,
    0,    0,    0,    0,    0,    0,    0,  101,  102,  103,
  104,  105,  106,    0,  101,  102,  103,  104,  105,  106,
    0,  101,  102,  103,  104,  105,  106,    0,  101,  102,
  103,  104,  105,  106,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  101,
  102,  103,  104,  105,  106,  101,  102,  103,  104,  105,
  106,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  101,  102,  103,  104,
  105,  106,  101,  102,  103,  104,  105,  106,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   46,  125,  123,  291,  129,  293,   44,  279,  268,   57,
  257,  123,  280,   58,  125,   41,   42,   43,  281,   45,
   67,   47,   41,   42,   43,  115,   45,   75,   47,  289,
  155,  257,   58,   59,   60,   58,   62,   42,   43,  129,
   45,   60,   47,   62,   42,   43,  257,   45,   44,   47,
   59,   42,   43,   58,   45,   60,   47,   62,   42,   43,
   58,   45,   60,   47,   62,  155,  113,   40,   59,   60,
   23,   62,  264,  257,   40,   59,   60,   30,   62,   42,
   43,   40,   45,   36,   47,   42,   43,  125,   45,  257,
   47,   44,   18,   61,   47,   41,   59,   60,  136,   62,
  147,   44,   59,   60,   41,   62,   42,   43,   76,   45,
   41,   47,   42,   43,   82,   45,   41,   47,  257,  258,
   46,  123,   58,   49,   60,  262,   62,   61,   54,  123,
   60,  257,   62,   58,   59,  123,  125,  257,  257,  257,
  258,  267,  125,  269,  268,  263,  114,  257,  116,   44,
   59,  119,  120,   61,  282,  294,  295,  296,  282,  257,
  281,  287,  288,  257,  290,  289,  292,   40,   59,  267,
  125,  269,  125,   40,  291,  143,  294,  295,  296,  268,
   41,   58,  289,  136,   41,  153,    0,  293,    0,  287,
  288,   59,  125,  281,  292,  257,   41,   41,  125,  123,
  282,   59,  293,  289,  291,   59,  289,    4,   30,   47,
   52,   -1,   -1,   -1,   -1,   -1,  257,   -1,   -1,   -1,
  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  270,
  271,   -1,   -1,   -1,   -1,   -1,   -1,  283,  284,   -1,
   -1,   -1,   -1,   -1,  285,  286,  272,  273,  274,  275,
  276,  277,   -1,  272,  273,  274,  275,  276,  277,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,  274,
  275,  276,  277,   -1,  272,  273,  274,  275,  276,  277,
   -1,  272,  273,  274,  275,  276,  277,   -1,  272,  273,
  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,
  273,  274,  275,  276,  277,  272,  273,  274,  275,  276,
  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,  274,  275,
  276,  277,  272,  273,  274,  275,  276,  277,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=296;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"ID","INT","FLOAT","BOOL","NUM","LIT",
"VOID","MAIN","READ","WRITE","IF","ELSE","WHILE","TRUE","FALSE","EQ","LEQ",
"GEQ","NEQ","AND","OR","type","CLASS","PRIVATE","PUBLIC","RETURN","INCREMENT",
"DECREMENT","NUMDOUBLE","NEW","ESCREVA","LEIA","ENDIF","BREAK","ENDWHILE","FOR",
"ENDFOR","DOUBLE","STRING","BOOLEAN",
};
final static String yyrule[] = {
"$accept : prog",
"prog : lclasse",
"prog :",
"lclasse : classe lclasse",
"lclasse :",
"classe : CLASS ID '{' corpoclasse '}'",
"corpoclasse : PRIVATE ':' latri PUBLIC ':' lmet",
"latri : atri latri",
"latri :",
"atri : tipo ID lid ';'",
"lid : ',' ID",
"lid :",
"lmet : met lmet",
"lmet :",
"met : metconst",
"met : metmain",
"met : metnormal",
"metconst : ID '(' lparam ')' latri '{' corpomet '}'",
"metmain : VOID MAIN '(' ')' latri '{' corpomet '}'",
"metnormal : tipo ID '(' lparam ')' latri '{' corpomet return '}'",
"metvoid : VOID ID '(' lparam ')' latri '{' corpomet '}'",
"lparam : param sublparam",
"lparam :",
"param : tipo ID",
"sublparam : ',' param sublparam",
"sublparam :",
"return : RETURN exp ';'",
"exp : exp ob exp",
"exp : TRUE",
"exp : FALSE",
"exp : ID INCREMENT",
"exp : ID DECREMENT",
"exp : '(' exp ')'",
"exp : NUM",
"exp : NUMDOUBLE",
"exp : LIT",
"exp : chamaMetodo",
"exp : NEW ID '(' lparam ')'",
"exp : ID",
"ob : '+'",
"ob : '*'",
"ob : '-'",
"ob : '/'",
"ob : '>'",
"ob : '<'",
"ob : AND",
"ob : OR",
"ob : LEQ",
"ob : GEQ",
"ob : EQ",
"ob : NEQ",
"corpomet : lcmd",
"lcmd : cmd lcmd",
"lcmd :",
"cmd : atrib",
"cmd : escrita",
"cmd : leia",
"cmd : if",
"cmd : while",
"cmd : for",
"cmd :",
"atrib : ID '=' exp ';'",
"escrita : ESCREVA LIT contescrita ';'",
"contescrita : ',' exp",
"contescrita :",
"leia : LEIA ID ';'",
"if : IF exp ':' lcmd else endif",
"else : ELSE ':' lcmd",
"else :",
"endif : ENDIF",
"lcmdloop : cmd lcmdloop",
"lcmdloop : BREAK",
"lcmdloop :",
"while : WHILE exp ':' lcmdloop endwhile",
"endwhile : ENDWHILE",
"for : FOR ID '=' exp ';' exp ';' exp ':' lcmdloop endfor",
"endfor : ENDFOR",
"chamaMetodo : ID '.' ID '(' lparam ')'",
"tipo : INT",
"tipo : DOUBLE",
"tipo : STRING",
"tipo : BOOLEAN",
"tipo : ID",
};

//#line 169 "AJava.y"

  private Yylex lexer;

  private TabSimb ts = new TabSimb();

  private int strCount = 0;
  private ArrayList<String> strTab = new ArrayList<String>();

  private Stack<Integer> pRot = new Stack<Integer>();
  private int proxRot = 1;


  public static int ARRAY = 100;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error + "  linha: " + lexer.getLine());
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
      yyparser.yyparse();
      // yyparser.listarTS();

    }
    else {
      // interactive mode
      System.out.println("\n\tFormato: java Parser entrada.cmm >entrada.s\n");
    }

  }

							
		void gcExpArit(int oparit) {
 				System.out.println("\tPOPL %EBX");
   			System.out.println("\tPOPL %EAX");

   		switch (oparit) {
     		case '+' : System.out.println("\tADDL %EBX, %EAX" ); break;
     		case '-' : System.out.println("\tSUBL %EBX, %EAX" ); break;
     		case '*' : System.out.println("\tIMULL %EBX, %EAX" ); break;

    		case '/': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     break;
     		case '%': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     System.out.println("\tMOVL %EDX, %EAX");
           		     break;
    		}
   		System.out.println("\tPUSHL %EAX");
		}

	public void gcExpRel(int oprel) {

    System.out.println("\tPOPL %EAX");
    System.out.println("\tPOPL %EDX");
    System.out.println("\tCMPL %EAX, %EDX");
    System.out.println("\tMOVL $0, %EAX");
    
    switch (oprel) {
       case '<':  			System.out.println("\tSETL  %AL"); break;
       case '>':  			System.out.println("\tSETG  %AL"); break;
       case Parser.EQ:  System.out.println("\tSETE  %AL"); break;
       case Parser.GEQ: System.out.println("\tSETGE %AL"); break;
       case Parser.LEQ: System.out.println("\tSETLE %AL"); break;
       case Parser.NEQ: System.out.println("\tSETNE %AL"); break;
       }
    
    System.out.println("\tPUSHL %EAX");

	}


	public void gcExpLog(int oplog) {

	   	System.out.println("\tPOPL %EDX");
 		 	System.out.println("\tPOPL %EAX");

  	 	System.out.println("\tCMPL $0, %EAX");
 		  System.out.println("\tMOVL $0, %EAX");
   		System.out.println("\tSETNE %AL");
   		System.out.println("\tCMPL $0, %EDX");
   		System.out.println("\tMOVL $0, %EDX");
   		System.out.println("\tSETNE %DL");

   		switch (oplog) {
    			case Parser.OR:  System.out.println("\tORL  %EDX, %EAX");  break;
    			case Parser.AND: System.out.println("\tANDL  %EDX, %EAX"); break;
       }

    	System.out.println("\tPUSHL %EAX");
	}

	public void gcExpNot(){

  	 System.out.println("\tPOPL %EAX" );
 	   System.out.println("	\tNEGL %EAX" );
  	 System.out.println("	\tPUSHL %EAX");
	}

   private void geraInicio() {
			System.out.println(".text\n\n#\t nome COMPLETO e matricula dos componentes do grupo...\n#\n"); 
			System.out.println(".GLOBL _start\n\n");  
   }

   private void geraFinal(){
	
			System.out.println("\n\n");
			System.out.println("#");
			System.out.println("# devolve o controle para o SO (final da main)");
			System.out.println("#");
			System.out.println("\tmov $0, %ebx");
			System.out.println("\tmov $1, %eax");
			System.out.println("\tint $0x80");
	
			System.out.println("\n");
			System.out.println("#");
			System.out.println("# Funcoes da biblioteca (IO)");
			System.out.println("#");
			System.out.println("\n");
			System.out.println("_writeln:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $10, (%ECX)");
			System.out.println("\tMOVL $1, %EDX");
			System.out.println("\tJMP _writeLit");
			System.out.println("_write:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJGE _write3");
			System.out.println("\tNEGL %EAX");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("_write3:");
			System.out.println("\tPUSHL %EBX");
			System.out.println("\tMOVL $10, %EBX");
			System.out.println("_divide:");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tIDIVL %EBX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tADD $48, %DL");
			System.out.println("\tMOVB %DL, (%ECX)");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJNE _divide");
			System.out.println("\tPOPL %EBX");
			System.out.println("\tCMPL $0, %EBX");
			System.out.println("\tJE _print");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $'-', (%ECX)");
			System.out.println("_print:");
			System.out.println("\tMOVL $__fim_msg, %EDX");
			System.out.println("\tSUBL %ECX, %EDX");
			System.out.println("_writeLit:");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("\tMOVL $4, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tRET");
			System.out.println("_read:");
			System.out.println("\tMOVL $15, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $3, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tMOVL $0, %EAX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tCMPB $'-', (%ECX)");
			System.out.println("\tJNE _reading");
			System.out.println("\tINCL %ECX");
			System.out.println("\tINC %BL");
			System.out.println("_reading:");
			System.out.println("\tMOVB (%ECX), %DL");
			System.out.println("\tCMP $10, %DL");
			System.out.println("\tJE _fimread");
			System.out.println("\tSUB $48, %DL");
			System.out.println("\tIMULL $10, %EAX");
			System.out.println("\tADDL %EDX, %EAX");
			System.out.println("\tINCL %ECX");
			System.out.println("\tJMP _reading");
			System.out.println("_fimread:");
			System.out.println("\tCMPB $1, %BL");
			System.out.println("\tJNE _fimread2");
			System.out.println("\tNEGL %EAX");
			System.out.println("_fimread2:");
			System.out.println("\tRET");
			System.out.println("\n");
     }

     private void geraAreaDados(){
			System.out.println("");		
			System.out.println("#");
			System.out.println("# area de dados");
			System.out.println("#");
			System.out.println(".data");
			System.out.println("#");
			System.out.println("# variaveis globais");
			System.out.println("#");
			ts.geraGlobais();	
			System.out.println("");
	
    }

     private void geraAreaLiterais() { 

         System.out.println("#\n# area de literais\n#");
         System.out.println("__msg:");
	       System.out.println("\t.zero 30");
	       System.out.println("__fim_msg:");
	       System.out.println("\t.byte 0");
	       System.out.println("\n");

         for (int i = 0; i<strTab.size(); i++ ) {
             System.out.println("_str_"+i+":");
             System.out.println("\t .ascii \""+strTab.get(i)+"\""); 
	           System.out.println("_str_"+i+"Len = . - _str_"+i);  
	      }		
   }
   
//#line 664 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################

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
public final static short PUBLIC=269;
public final static short RETURN=270;
public final static short ESCREVA=271;
public final static short LEIA=272;
public final static short ENDIF=273;
public final static short BREAK=274;
public final static short EXTENDS=275;
public final static short WHILE=276;
public final static short TRUE=277;
public final static short FALSE=278;
public final static short CLASS=279;
public final static short PRIVATE=280;
public final static short ENDWHILE=281;
public final static short FOR=282;
public final static short ENDFOR=283;
public final static short DOUBLE=284;
public final static short STRING=285;
public final static short BOOLEAN=286;
public final static short NEW=287;
public final static short NUMDOUBLE=288;
public final static short EQ=289;
public final static short LEQ=290;
public final static short GEQ=291;
public final static short NEQ=292;
public final static short AND=293;
public final static short OR=294;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    6,    0,    5,    5,    9,    7,    8,    8,   10,   11,
   11,   15,   13,   16,   14,   14,   12,   12,   17,   17,
   17,   21,   18,   24,   19,   26,   20,   22,   22,   27,
   28,   28,   31,   29,   29,   32,   30,   30,   33,   25,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,   23,   35,   35,   35,   35,   35,   35,   36,
   37,   42,   43,   43,   44,   38,   45,   39,   46,   46,
   47,   48,   40,   49,   50,   41,   51,   34,   34,   34,
   52,    3,   53,    4,    1,    1,    1,    1,    1,
};
final static short yylen[] = {                            2,
    0,    2,    2,    0,    0,    7,    2,    0,    6,    2,
    0,    0,    5,    0,    4,    0,    2,    0,    1,    1,
    1,    0,    9,    0,   11,    0,   10,    2,    0,    2,
    3,    0,    0,    3,    0,    0,    4,    0,    0,    4,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    1,    1,    1,    1,    1,    3,    1,
    1,    2,    1,    2,    1,    1,    1,    1,    1,    3,
    2,    3,    2,    0,    0,    4,    0,    6,    4,    1,
    0,    0,    7,    0,    0,   11,    2,    2,    2,    0,
    0,    7,    0,    5,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    2,    0,    5,    3,    0,    0,    0,
    7,    0,    0,    0,    0,    6,   99,   95,   96,   97,
   98,    0,    0,    0,   12,    0,   10,    0,    0,    0,
    0,    0,    0,    0,    9,    0,   19,   20,   21,   14,
   13,    0,   26,   24,   17,    0,    0,    0,    0,   15,
    0,    0,    0,    0,    0,   30,    0,    0,   28,    0,
    0,    0,    0,    0,    0,    0,   31,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   63,    0,    0,
   65,   66,   67,   68,   69,    0,    0,    0,    0,    0,
   54,   58,   56,   57,    0,   55,    0,    0,   61,    0,
   71,   75,   87,    0,    0,   23,   88,   64,   89,    0,
    0,    0,    0,   93,   62,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   27,    0,    0,   91,    0,
   59,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   42,   44,   47,    0,    0,   72,   76,   82,    0,
    0,   25,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   80,   78,    0,    0,   40,    0,    0,   94,
    0,   83,    0,   92,    0,   34,    0,   85,    0,   79,
    0,    0,    0,   37,   86,
};
final static short yydgoto[] = {                          1,
   22,  170,   99,  115,    4,    2,    5,   10,    8,   14,
   23,   35,   24,   31,   28,   46,   36,   37,   38,   39,
   42,   52,   77,   49,  138,   48,   53,   59,  171,  186,
  179,  192,  168,   78,   79,   80,   81,   82,   83,   84,
   85,  101,  132,  133,  130,  174,  134,  166,  167,  191,
   86,  163,  140,
};
final static short yysindex[] = {                         0,
    0, -267, -239,    0, -267,    0,    0, -255, -217,  -81,
    0, -233,   -9,  -51,  -77,    0,    0,    0,    0,    0,
    0, -174, -178,  -77,    0,   38,    0,   54,  -95, -153,
   65,    0, -128, -126,    0,  -95,    0,    0,    0,    0,
    0,   97,    0,    0,    0,   54,  -77,  104,  111,    0,
 -103,  116,  123,  -77,  -77,    0,  -77,  -77,    0,  135,
  138,   62,  123,  -77,  -77,  -46,    0,   64,   82,  121,
  -39,  -39,  -44,  155,  -39,  -42,   91,    0,  -46,  158,
    0,    0,    0,    0,    0,  -46,  -46,  -46,  -39,  174,
    0,    0,    0,    0,  -33,    0,  -39,    1,    0,  -28,
    0,    0,    0,    1,  168,    0,    0,    0,    0,  106,
  -41,    1,  -25,    0,    0,   -6,  -39,  -39,  -39,  -39,
  -39,  -39,  -39,  -39,  -39,  -39,  -39,  -39,  -39,  175,
  -39,  176,  178,  182,  -39,    0,  -39,  109,    0,  201,
    0,  141,  141,  141,  141,   15,    8,  141,  141,  128,
  128,    0,    0,    0,  -46,    1,    0,    0,    0,    1,
    1,    0,  202,  -39, -244,  -46,  184,  185,  -39,    1,
  204,  188,    0,    0,  -34,  -42,    0,  209,  207,    0,
  -46,    0,  200,    0,  -39,    0,   -5,    0,    1,    0,
  -46,  207,  -24,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,  267,    0,    0,  267,    0,    0,  146,    0,    0,
    0,    0,    0,    0,    3,    0,    0,    0,    0,    0,
    0,    0,    0, -120,    0,    0,    0,  211,  148,    0,
    0,  -38,    0,    0,    0,  148,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  211,  230,    0,    0,    0,
    0,    0,  233,  230,  230,    0,  152,    0,    0,    0,
    0,    0,  233,  152,  152,  151,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -112,    0,
    0,    0,    0,    0,    0, -112,  151,    7,    0,  -37,
    0,    0,    0,    0,    0,    0,    0,  220,    0,  221,
    0,    0,    0,  224,    0,    0,    0,    0,    0,    0,
    0,    6,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   41,   48,   61,   68,   94,  101,   74,   81,   28,
   35,    0,    0,    0, -240,  222,    0,    0,    0,  237,
  244,    0,    0,  238,    0,   27,    0,    0,  238,  -14,
    0,    0,    0,    0,    0,    0,    0,    0,  248,    0,
   37,    0,    0,    0,    0,    0,    0,    0,   40,    0,
   30,  248,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  119,   75,    0,    0,  306,    0,    0,    0,    0,    0,
    2,  276,    0,  268,    0,    0,    0,    0,    0,    0,
    0,   59,   29,    0,    0,    0,  257,  253,  154,  144,
    0,    0,    0,  223,    0,  -65,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static int YYTABLESIZE=414;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         60,
   97,   22,   11,   60,   60,   60,   60,   60,  129,   60,
  105,    3,   90,  127,  125,  131,  126,    6,  128,    9,
   60,   60,   60,  172,   60,   27,   33,   90,  173,   33,
  129,  124,   90,  123,  141,  127,  125,  129,  126,   11,
  128,   12,  127,  125,  129,  126,   13,  128,   15,  127,
  125,  129,  126,  124,  128,  123,  127,  125,   62,  126,
  124,  128,  123,   70,   70,   68,   69,  124,   41,  123,
   41,   41,   41,   16,  124,   43,  123,   43,   43,   43,
   36,   52,   25,   36,   52,   41,   41,   41,   50,   41,
   26,   50,   43,   43,   43,   29,   43,   30,   52,   52,
   52,   51,   52,   40,   51,   50,   50,   50,   53,   50,
  183,   53,   60,   61,   45,  110,  111,   45,   51,   51,
   51,   46,   51,   41,   46,   53,   53,   53,   43,   53,
   44,   45,   45,   45,   48,   45,   47,   48,   46,   46,
   46,   49,   46,   54,   49,   98,  100,   34,   11,  104,
   55,   48,   48,   56,   34,   90,   57,   90,   49,   49,
   90,   32,   18,  112,  129,   51,   58,   33,   90,  127,
   90,  116,   51,   51,  128,   64,   51,  129,   65,   17,
   18,   89,  127,  125,   66,  126,   87,  128,   19,   20,
   21,  142,  143,  144,  145,  146,  147,  148,  149,  150,
  151,  152,  153,  154,   88,  156,   19,   20,   21,  160,
   70,  161,  102,  103,   70,  106,  108,   90,   99,  113,
   71,   91,   92,  114,   72,   73,  135,   74,  137,   75,
  136,  139,  155,  162,  157,   76,  158,   93,   94,  159,
  164,  169,  176,  177,  180,  181,  182,   95,   96,  184,
  185,   60,   60,   60,   60,   60,   60,  188,  195,  189,
  117,  118,  119,  120,  121,  122,    4,  190,    8,   16,
   29,   11,   18,   32,   11,   90,   90,   77,   35,   74,
   73,   81,  117,  118,  119,  120,  121,  122,   38,  117,
  118,  119,  120,  121,  122,   84,  117,  118,  119,  120,
  121,  107,   39,  117,  118,  119,  120,   90,  109,   90,
    7,   45,   90,   50,   63,   67,   41,   41,   41,   41,
   41,   41,  178,   43,   43,   43,   43,   43,   43,   52,
   52,   52,   52,   52,   52,  194,   50,   50,   50,   50,
   50,   50,    0,    0,    0,    0,    0,    0,    0,   51,
   51,   51,   51,   51,   51,    0,   53,   53,   53,   53,
   53,   53,   45,   45,   45,   45,   45,   45,    0,   46,
   46,   46,   46,   46,   46,    0,    0,  165,    0,    0,
    0,    0,    0,    0,    0,    0,   48,   48,  175,    0,
    0,    0,    0,    0,   49,    0,    0,    0,    0,    0,
    0,    0,    0,  187,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  193,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   40,   40,  123,   41,   42,   43,   44,   45,   37,   47,
   76,  279,  125,   42,   43,   44,   45,  257,   47,  275,
   58,   59,   60,  268,   62,   24,   41,  268,  273,   44,
   37,   60,  273,   62,   41,   42,   43,   37,   45,  257,
   47,  123,   42,   43,   37,   45,  280,   47,   58,   42,
   43,   37,   45,   60,   47,   62,   42,   43,   57,   45,
   60,   47,   62,   58,   59,   64,   65,   60,   41,   62,
   43,   44,   45,  125,   60,   41,   62,   43,   44,   45,
   41,   41,  257,   44,   44,   58,   59,   60,   41,   62,
  269,   44,   58,   59,   60,   58,   62,   44,   58,   59,
   60,   41,   62,  257,   44,   58,   59,   60,   41,   62,
  176,   44,   54,   55,   41,   87,   88,   44,   58,   59,
   60,   41,   62,   59,   44,   58,   59,   60,  257,   62,
  257,   58,   59,   60,   41,   62,   40,   44,   58,   59,
   60,   41,   62,   40,   44,   71,   72,   29,  269,   75,
   40,   58,   59,  257,   36,  268,   41,  270,   58,   59,
  273,  257,  258,   89,   37,   47,   44,  263,  281,   42,
  283,   97,   54,   55,   47,   41,   58,   37,   41,  257,
  258,   61,   42,   43,  123,   45,  123,   47,  284,  285,
  286,  117,  118,  119,  120,  121,  122,  123,  124,  125,
  126,  127,  128,  129,  123,  131,  284,  285,  286,  135,
  257,  137,  257,   59,  257,  125,   59,  257,  257,   46,
  267,  261,  262,  257,  271,  272,   59,  274,  270,  276,
  125,  257,   58,  125,   59,  282,   59,  277,  278,   58,
   40,   40,   59,   59,   41,   58,  281,  287,  288,   41,
   44,  289,  290,  291,  292,  293,  294,   58,  283,  185,
  289,  290,  291,  292,  293,  294,    0,  273,  123,   59,
   41,  269,  125,   41,  123,  125,  270,   58,   41,   59,
   59,   58,  289,  290,  291,  292,  293,  294,   41,  289,
  290,  291,  292,  293,  294,   59,  289,  290,  291,  292,
  293,   79,   59,  289,  290,  291,  292,  281,   86,  273,
    5,   36,  283,   46,   58,   63,  289,  290,  291,  292,
  293,  294,  169,  289,  290,  291,  292,  293,  294,  289,
  290,  291,  292,  293,  294,  192,  289,  290,  291,  292,
  293,  294,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  289,
  290,  291,  292,  293,  294,   -1,  289,  290,  291,  292,
  293,  294,  289,  290,  291,  292,  293,  294,   -1,  289,
  290,  291,  292,  293,  294,   -1,   -1,  155,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  293,  294,  166,   -1,
   -1,   -1,   -1,   -1,  294,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  181,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  191,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=294;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"ID","INT","FLOAT","BOOL","NUM",
"LIT","VOID","MAIN","READ","WRITE","IF","ELSE","PUBLIC","RETURN","ESCREVA",
"LEIA","ENDIF","BREAK","EXTENDS","WHILE","TRUE","FALSE","CLASS","PRIVATE",
"ENDWHILE","FOR","ENDFOR","DOUBLE","STRING","BOOLEAN","NEW","NUMDOUBLE","EQ",
"LEQ","GEQ","NEQ","AND","OR",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 lclasse",
"lclasse : classe lclasse",
"lclasse :",
"$$2 :",
"classe : CLASS ID $$2 extends '{' corpoclasse '}'",
"extends : EXTENDS ID",
"extends :",
"corpoclasse : PRIVATE ':' ldecl PUBLIC ':' lmet",
"ldecl : decl ldecl",
"ldecl :",
"$$3 :",
"decl : tipo ID $$3 lid ';'",
"$$4 :",
"lid : ',' ID $$4 lid",
"lid :",
"lmet : met lmet",
"lmet :",
"met : metconst",
"met : metnormal",
"met : metvoid",
"$$5 :",
"metconst : ID $$5 '(' lparam ')' ldecl '{' corpomet '}'",
"$$6 :",
"metnormal : tipo ID $$6 '(' lparam ')' ldecl '{' corpomet return '}'",
"$$7 :",
"metvoid : VOID ID $$7 '(' lparam ')' ldecl '{' corpomet '}'",
"lparam : param sublparam",
"lparam :",
"param : tipo ID",
"sublparam : ',' param sublparam",
"sublparam :",
"$$8 :",
"lparamchamada : exp $$8 sublparamchamada",
"lparamchamada :",
"$$9 :",
"sublparamchamada : ',' exp $$9 sublparamchamada",
"sublparamchamada :",
"$$10 :",
"return : RETURN exp $$10 ';'",
"exp : exp '+' exp",
"exp : exp '*' exp",
"exp : exp '-' exp",
"exp : exp '/' exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
"exp : exp '%' exp",
"exp : exp AND exp",
"exp : exp OR exp",
"exp : exp LEQ exp",
"exp : exp GEQ exp",
"exp : exp EQ exp",
"exp : exp NEQ exp",
"exp : NUM",
"exp : NUMDOUBLE",
"exp : TRUE",
"exp : FALSE",
"exp : LIT",
"exp : '(' exp ')'",
"exp : ID",
"exp : chamaMetodo",
"exp : NEW chamaConstrutor",
"corpomet : lcmdloop",
"cmd : atrib ';'",
"cmd : escrita",
"cmd : leia",
"cmd : if",
"cmd : while",
"cmd : for",
"atrib : ID '=' exp",
"escrita : ESCREVA restoEscrita",
"restoEscrita : exp contescrita ';'",
"contescrita : ',' exp",
"contescrita :",
"$$11 :",
"leia : LEIA ID $$11 ';'",
"$$12 :",
"if : IF exp $$12 ':' lcmdloop else",
"else : ELSE ':' lcmdloop ENDIF",
"else : ENDIF",
"$$13 :",
"$$14 :",
"while : WHILE exp $$13 ':' $$14 lcmdloop ENDWHILE",
"$$15 :",
"$$16 :",
"for : FOR atrib ';' exp $$15 ';' atrib ':' $$16 lcmdloop ENDFOR",
"break : BREAK ';'",
"lcmdloop : cmd lcmdloop",
"lcmdloop : break lcmdloop",
"lcmdloop :",
"$$17 :",
"chamaMetodo : ID '.' ID $$17 '(' lparamchamada ')'",
"$$18 :",
"chamaConstrutor : ID $$18 '(' lparamchamada ')'",
"tipo : INT",
"tipo : DOUBLE",
"tipo : STRING",
"tipo : BOOLEAN",
"tipo : ID",
};

//#line 421 "AJava.y"

  private Yylex lexer;

  private TabSimb ts;

  private String currEscopo;
  private ClasseID currClasse;
  private TS_entry classeAtual;
  private TS_entry metodoAtual;
  private TS_entry tipoAtual;
  private boolean metodoMesmoNome = false;
  private String assinaturaAtual;
  private TS_entry classeConstrutor = null;
  int loopLevel = 0;
  





  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_DOUBLE = new TS_entry("double", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOLEAN = new TS_entry("boolean", null,  ClasseID.TipoBase);
  public static TS_entry Tp_LITERAL = new TS_entry("literal", null,  ClasseID.TipoBase);
  public static TS_entry Tp_OBJETO = new TS_entry("objeto", null,  ClasseID.TipoBase);
  public static TS_entry Tp_VOID = new TS_entry("void", null,  ClasseID.TipoBase);
  public static TS_entry Tp_CONSTRUTOR = new TS_entry("construtor", null,  ClasseID.TipoBase);

  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null,  ClasseID.TipoBase);

  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;

  private TS_entry currentType;

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
    //System.err.println("Erro (linha: "+ lexer.getLine() + ")\tMensagem: "+error);
    System.err.printf("Erro (linha: %2d) \tMensagem: %s\n", lexer.getLine(), error);
  }


  public Parser(Reader r) {
    metodoMesmoNome = false;
    lexer = new Yylex(r, this);

    ts = new TabSimb();

  
    ts.insert(Tp_ERRO);
    ts.insert(Tp_INT);
    ts.insert(Tp_DOUBLE);
    ts.insert(Tp_BOOLEAN);
    ts.insert(Tp_LITERAL);
    ts.insert(Tp_OBJETO);
    ts.insert(Tp_VOID);
    ts.insert(Tp_CONSTRUTOR);
    

  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {
    System.out.println("\n\nVerificador semantico simples\n");
    

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Programa de entrada:\n");
        yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();

      yyparser.listarTS();

      System.out.print("\n\nFeito!\n");
    
  }


   TS_entry validaTipo(int operador, TS_entry A, TS_entry B) {
       
         switch ( operador ) {
              case '=':
                    if ( (A == Tp_INT && B == Tp_INT)                        ||
                         ((A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE))) ||
                         (A == B))
                         return A;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

              case '+' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE))) 
                         return Tp_DOUBLE;     
                    else if (A == Tp_LITERAL || B == Tp_LITERAL)
                        return Tp_LITERAL;
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

            case '-' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE))) 
                         return Tp_DOUBLE;
                    else
                        yyerror("(sem) tipos incomp. para subtracao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

            case '/' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE))) 
                         return Tp_DOUBLE;
                    else
                        yyerror("(sem) tipos incomp. para divisao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

            case '*' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE))) 
                         return Tp_DOUBLE;
                    else
                        yyerror("(sem) tipos incomp. para multiplicacao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '%' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else
                        yyerror("(sem) tipos incomp. para multiplicacao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '>' :
                     if (((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE)) || (A == Tp_LITERAL && B == Tp_LITERAL))
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
                      break;

            case '<' :
                     if (((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE)) || (A == Tp_LITERAL && B == Tp_LITERAL))
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " < "+B.getTipoStr());
                      break;

             case AND:
                     if (A == Tp_BOOLEAN && B == Tp_BOOLEAN)
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());

              case OR:
                     if (A == Tp_BOOLEAN && B == Tp_BOOLEAN)
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " || "+B.getTipoStr());

               case EQ:
                     if (((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE)) || (A == Tp_LITERAL && B == Tp_LITERAL) || (A == Tp_BOOLEAN && B == Tp_BOOLEAN))
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " == "+B.getTipoStr());

                case NEQ:
                     if (((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE)) || (A == Tp_LITERAL && B == Tp_LITERAL) || (A == Tp_BOOLEAN && B == Tp_BOOLEAN))
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " != "+B.getTipoStr());

                case LEQ:
                     if (((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE)) || (A == Tp_LITERAL && B == Tp_LITERAL) || (A == Tp_BOOLEAN && B == Tp_BOOLEAN))
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " <= "+B.getTipoStr());

                case GEQ:
                     if (((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE)) || (A == Tp_LITERAL && B == Tp_LITERAL) || (A == Tp_BOOLEAN && B == Tp_BOOLEAN))
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " >= "+B.getTipoStr());
                 break;
            }
            

            return Tp_ERRO;
           
     }
//#line 682 "Parser.java"
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
case 1:
//#line 34 "AJava.y"
{ currEscopo = "Global"; currClasse = ClasseID.VarGlobal; classeAtual = null; metodoAtual = null; }
break;
case 5:
//#line 41 "AJava.y"
{    
                       TS_entry simb = ts.pesquisa((String)val_peek(0).sval);
                      if(simb != null) {
                        yyerror("(sem) classe <" + val_peek(0).sval + "> jah declarada");
                      } else {
                        TS_entry classe = new TS_entry((String)val_peek(0).sval, Tp_OBJETO, ClasseID.Classe);
                        currClasse = ClasseID.Classe;
                        classeAtual = classe;
                        ts.insert(classe);
                      }}
break;
case 6:
//#line 50 "AJava.y"
{currClasse = ClasseID.VarGlobal; classeAtual = null;}
break;
case 7:
//#line 52 "AJava.y"
{
                      TS_entry simb = ts.pesquisa((String)val_peek(0).sval);
                      if(simb == null) {
                        yyerror("(sem) classe <" + val_peek(0).sval + "> nao declarada");
                      } else {
                        classeAtual.setPai(simb);
                      }
}
break;
case 12:
//#line 74 "AJava.y"
{      TS_entry dec;
                      if(metodoAtual == null) {
                          dec = new TS_entry((String)val_peek(0).sval, (TS_entry)val_peek(1).obj, ClasseID.Atributo);
                          if(classeAtual.pesquisaAtributo(dec.getId()) == null) {
                            classeAtual.addAtributo(dec);
                          } else {
                            yyerror("(sem) atributo <" + val_peek(0).sval + "> jah declarado no escopo");
                          }
                      }
                      else {
                          dec = new TS_entry((String)val_peek(0).sval, (TS_entry)val_peek(1).obj, ClasseID.VarLocal);
                          if(classeAtual.pesquisaAtributo(dec.getId()) == null && metodoAtual.pesquisaVarLocal(dec.getId()) == null) {
                            metodoAtual.addVarLocal(dec);
                          } else {
                            yyerror("(sem) variavel <" + val_peek(0).sval + "> jah declarada no escopo");
                          }
                      }
                      tipoAtual = (TS_entry)val_peek(1).obj;
                      
                }
break;
case 14:
//#line 95 "AJava.y"
{        TS_entry dec;
                      if(metodoAtual == null) {
                          dec = new TS_entry((String)val_peek(0).sval, tipoAtual, ClasseID.Atributo);

                          if(classeAtual.pesquisaAtributo(dec.getId()) == null) {
                            classeAtual.addAtributo(dec);
                          } else {
                            yyerror("(sem) atributo <" + val_peek(0).sval + "> jah declarado no escopo");
                          }
                      }
                      else {
                          dec = new TS_entry((String)val_peek(0).sval, tipoAtual, ClasseID.VarLocal);
                          if(classeAtual.pesquisaAtributo(dec.getId()) == null && metodoAtual.pesquisaVarLocal(dec.getId()) == null && metodoAtual.pesquisaParametro(dec.getId()) == null) {
                            metodoAtual.addVarLocal(dec);
                          } else {
                            yyerror("(sem) variavel <" + val_peek(0).sval + "> jah declarada no escopo");
                          }
                      }}
break;
case 22:
//#line 127 "AJava.y"
{
                if(!(((String)val_peek(0).sval).equals(classeAtual.getId()))) {
                      yyerror("(sem) metodo nao construtor <" + val_peek(0).sval + "> sem modificador de tipo");
                } else {
                  metodoAtual = new TS_entry((String)val_peek(0).sval, Tp_CONSTRUTOR, ClasseID.Metodo);
                  currClasse = ClasseID.Metodo;

                }}
break;
case 23:
//#line 134 "AJava.y"
{currClasse = ClasseID.Classe;
                                                          if(classeAtual.pesquisaMetodo(metodoAtual.getAssinatura()) == null) {
                                                            classeAtual.addMetodo(metodoAtual);
                                                          } else {
                                                            yyerror("(sem) metodo <" + val_peek(8).sval + "> com assinatura repetida na classe");
                                                          }
                                                          metodoAtual = null;}
break;
case 24:
//#line 142 "AJava.y"
{
                  if(((String)val_peek(0).sval).equals("main")) {
                    yyerror("(sem) metodo main deve ser void");
                  } else {
                    metodoAtual = new TS_entry((String)val_peek(0).sval, (TS_entry)val_peek(1).obj, ClasseID.Metodo);
                    currClasse = ClasseID.Metodo;
                  }

            }
break;
case 25:
//#line 150 "AJava.y"
{currClasse = ClasseID.Classe;
                                                          if(metodoAtual != null) {
                                                            if(classeAtual.pesquisaMetodo(metodoAtual.getAssinatura()) == null) {
                                                              classeAtual.addMetodo(metodoAtual);
                                                            } else {
                                                              yyerror("(sem) metodo <" + val_peek(9).sval + "> com assinatura repetida na classe");
                                                            }
                                                          }                                                          
                                                          metodoAtual = null;}
break;
case 26:
//#line 160 "AJava.y"
{
                  metodoAtual = new TS_entry((String)val_peek(0).sval, Tp_VOID, ClasseID.Metodo);
                  currClasse = ClasseID.Metodo;

            }
break;
case 27:
//#line 164 "AJava.y"
{currClasse = ClasseID.Classe;
                                                          if(classeAtual.pesquisaMetodo(metodoAtual.getAssinatura()) == null) {
                                                            if(metodoAtual.getAssinatura().contains("main") && metodoAtual.getAssinatura().length() > 4) {
                                                              yyerror("(sem) metodo main nao pode ter parametros");
                                                            } else {
                                                              classeAtual.addMetodo(metodoAtual);
                                                            }                                                            
                                                          } else {
                                                            yyerror("(sem) metodo <" + val_peek(8).sval + "> com assinatura repetida na classe");
                                                          }
                                                          metodoAtual = null;}
break;
case 30:
//#line 180 "AJava.y"
{
          if(metodoAtual.parametroRepetido((String)val_peek(0).sval))
          {
              yyerror("(sem) parametro repetido <" + val_peek(0).sval + "> no metodo <" + metodoAtual.getId() + ">");
          }
          else
          {
            assinaturaAtual += " " + ((TS_entry)val_peek(1).obj).getTipoStr();
            metodoAtual.addParametro(new TS_entry((String)val_peek(0).sval, (TS_entry)val_peek(1).obj, ClasseID.Parametro));
          }
        }
break;
case 33:
//#line 196 "AJava.y"
{ assinaturaAtual += " " + ((TS_entry)val_peek(0).obj).getTipoStr();
  }
break;
case 36:
//#line 201 "AJava.y"
{ assinaturaAtual += " " + ((TS_entry)val_peek(0).obj).getTipoStr();
  }
break;
case 39:
//#line 206 "AJava.y"
{
            if((TS_entry)val_peek(0).obj != metodoAtual.getTipo())
            {
              yyerror("(sem) tipo de retorno <" + ((TS_entry)val_peek(0).obj).getTipoStr() + "> incompativel com metodo <" + metodoAtual.getId() + ">");
            } 
         }
break;
case 41:
//#line 215 "AJava.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 42:
//#line 216 "AJava.y"
{ yyval.obj = validaTipo('*', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 43:
//#line 217 "AJava.y"
{ yyval.obj = validaTipo('-', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 44:
//#line 218 "AJava.y"
{ yyval.obj = validaTipo('/', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 45:
//#line 219 "AJava.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 46:
//#line 220 "AJava.y"
{ yyval.obj = validaTipo('<', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 47:
//#line 221 "AJava.y"
{ yyval.obj = validaTipo('%', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 48:
//#line 222 "AJava.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 49:
//#line 223 "AJava.y"
{ yyval.obj = validaTipo(OR, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 50:
//#line 224 "AJava.y"
{ yyval.obj = validaTipo(LEQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 51:
//#line 225 "AJava.y"
{ yyval.obj = validaTipo(GEQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 52:
//#line 226 "AJava.y"
{ yyval.obj = validaTipo(EQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 53:
//#line 227 "AJava.y"
{ yyval.obj = validaTipo(NEQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 54:
//#line 228 "AJava.y"
{ yyval.obj = Tp_INT; }
break;
case 55:
//#line 229 "AJava.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 56:
//#line 230 "AJava.y"
{ yyval.obj = Tp_BOOLEAN; }
break;
case 57:
//#line 231 "AJava.y"
{ yyval.obj = Tp_BOOLEAN; }
break;
case 58:
//#line 232 "AJava.y"
{ yyval.obj = Tp_LITERAL; }
break;
case 59:
//#line 233 "AJava.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 60:
//#line 234 "AJava.y"
{ TS_entry nodo;
                  nodo = classeAtual.pesquisaAtributo((String)val_peek(0).sval);
                  if(nodo == null && metodoAtual != null)
                  {
                      nodo = metodoAtual.pesquisaVarLocal((String)val_peek(0).sval);
                      if(nodo == null)
                      {
                          nodo = metodoAtual.pesquisaParametro((String)val_peek(0).sval);
                          if(nodo == null) {
                            yyerror("(sem) var ou atributo <" + val_peek(0).sval + "> nao declarado ou fora do escopo");
                            yyval.obj = Tp_ERRO;
                          }
                      }
                  }
                  if(nodo != null) {
                    yyval.obj = nodo.getTipo();
                  }
                }
break;
case 61:
//#line 252 "AJava.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 62:
//#line 253 "AJava.y"
{if(val_peek(0).obj != Tp_CONSTRUTOR) {
        yyerror("(sem) somente construtores podem ser chamados com new");
          yyval.obj = Tp_ERRO;
     } else {
       yyval.obj = classeConstrutor;
       classeConstrutor = null;
     }}
break;
case 70:
//#line 272 "AJava.y"
{
         TS_entry nodo;
          nodo = classeAtual.pesquisaAtributo((String)val_peek(2).sval);
          if(nodo == null && metodoAtual != null)
          {
              nodo = metodoAtual.pesquisaVarLocal((String)val_peek(2).sval);
              if(nodo == null)
              {
                  nodo = metodoAtual.pesquisaParametro((String)val_peek(2).sval);
                  if(nodo == null) {
                    yyerror("(sem) var ou atributo <" + val_peek(2).sval + "> nao declarado ou fora do escopo");
                  }
              }
          }

          if(nodo != null) {
            validaTipo('=', nodo.getTipo(), (TS_entry)val_peek(0).obj);
          }
        }
break;
case 72:
//#line 295 "AJava.y"
{ if(val_peek(2).obj != Tp_LITERAL) yyerror("(sem) primeiro operador da escrita precisa ser do tipo string "); }
break;
case 73:
//#line 297 "AJava.y"
{
   if (val_peek(0).obj != Tp_BOOLEAN && val_peek(0).obj != Tp_INT && val_peek(0).obj != Tp_DOUBLE && val_peek(0).obj != Tp_LITERAL ){
      yyerror("tipo da expressao deve ser um tipo base: int, double, boolean ou string");}
    }
break;
case 75:
//#line 304 "AJava.y"
{
          TS_entry nodo;
          nodo = classeAtual.pesquisaAtributo((String)val_peek(0).sval);
          if(nodo == null && metodoAtual != null)
          {
              nodo = metodoAtual.pesquisaVarLocal((String)val_peek(0).sval);
              if(nodo == null)
              {
                  nodo = metodoAtual.pesquisaParametro((String)val_peek(0).sval);
                  if(nodo == null) {
                    yyerror("(sem) var ou atributo <" + val_peek(0).sval + "> nao declarado ou fora do escopo");
                  }
              }
          }
        }
break;
case 77:
//#line 320 "AJava.y"
{
          if((TS_entry)val_peek(0).obj != Tp_BOOLEAN) {
              yyerror("(sem) expressao nao booleana em condicao de IF");
          }
         }
break;
case 81:
//#line 330 "AJava.y"
{
          if((TS_entry)val_peek(0).obj != Tp_BOOLEAN) {
              yyerror("(sem) expressao nao booleana em condicao de WHILE");
          }
         }
break;
case 82:
//#line 334 "AJava.y"
{loopLevel++;}
break;
case 83:
//#line 334 "AJava.y"
{loopLevel--;}
break;
case 84:
//#line 336 "AJava.y"
{
          if((TS_entry)val_peek(0).obj != Tp_BOOLEAN) {
              yyerror("(sem) expressao nao booleana em condicao de FOR");
          }
         }
break;
case 85:
//#line 340 "AJava.y"
{loopLevel++;}
break;
case 86:
//#line 340 "AJava.y"
{loopLevel--;}
break;
case 89:
//#line 346 "AJava.y"
{if(loopLevel == 0) yyerror("(sem) break fora de loop");}
break;
case 91:
//#line 350 "AJava.y"
{assinaturaAtual = (String)val_peek(0).sval;}
break;
case 92:
//#line 350 "AJava.y"
{
          TS_entry nodo;
          nodo = classeAtual.pesquisaAtributo((String)val_peek(6).sval);
          if(nodo == null && metodoAtual != null)
          {
              nodo = metodoAtual.pesquisaVarLocal((String)val_peek(6).sval);
              if(nodo == null)
              {
                  nodo = metodoAtual.pesquisaParametro((String)val_peek(6).sval);
                  if(nodo == null) {
                    yyerror("(sem) objeto <" + val_peek(6).sval + "> nao declarado ou fora do escopo");
                    yyval.obj = Tp_ERRO;
                  }
              }
          }

          if(nodo != null) {
            if(nodo.getTipo().getTipo() == Tp_OBJETO){
              if (nodo.getTipo().pesquisaMetodoHeranca(assinaturaAtual) == null) {
                yyerror("(sem) a classe do objeto <" + val_peek(6).sval + "> nao possui o metodo <" + val_peek(4).sval + "> com esses parametros");
                yyval.obj = Tp_ERRO;
              } else {
                yyval.obj = nodo.getTipo().pesquisaMetodoHeranca(assinaturaAtual).getTipo();
              }
            }
            else
            {
              yyerror("(sem) objeto <" + val_peek(6).sval + "> nao eh um objeto");
              yyval.obj = Tp_ERRO;
            }
          } else {
            yyval.obj = Tp_ERRO;
          }
   }
break;
case 93:
//#line 385 "AJava.y"
{assinaturaAtual = (String)val_peek(0).sval;}
break;
case 94:
//#line 385 "AJava.y"
{
   TS_entry classe = ts.pesquisa(val_peek(4).sval);
   if(classe == null) {
        yyerror("(sem) chamada de construtor <" + val_peek(4).sval + "> para classe nao definida");
        yyval.obj = Tp_ERRO;
   } else {
     TS_entry met = classe.pesquisaMetodoHeranca(assinaturaAtual);
     if(met == null) {
       yyerror("(sem) construtor <" + val_peek(4).sval + "> com assinatura nao definida");
        yyval.obj = Tp_ERRO;
     } else if(met.getTipo() != Tp_CONSTRUTOR){
       yyerror("(sem) construtor <" + val_peek(4).sval + "> com assinatura nao definida");
        yyval.obj = Tp_ERRO;
     } else {
       yyval.obj = Tp_CONSTRUTOR;
       classeConstrutor = classe;
     }
   }
 }
break;
case 95:
//#line 405 "AJava.y"
{yyval.obj = Tp_INT;}
break;
case 96:
//#line 406 "AJava.y"
{yyval.obj = Tp_DOUBLE;}
break;
case 97:
//#line 407 "AJava.y"
{yyval.obj = Tp_LITERAL;}
break;
case 98:
//#line 408 "AJava.y"
{yyval.obj = Tp_BOOLEAN;}
break;
case 99:
//#line 409 "AJava.y"
{
     TS_entry nodo = ts.pesquisa((String)val_peek(0).sval);
     if(nodo == null) {
       yyerror("(sem) tipo <" + val_peek(0).sval + "> nao definido");
       yyval.obj = Tp_ERRO;
     }
     else {
       yyval.obj = nodo;
     }
   }
break;
//#line 1309 "Parser.java"
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

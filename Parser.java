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
public final static short CLASS=272;
public final static short PRIVATE=273;
public final static short EQ=274;
public final static short LEQ=275;
public final static short GEQ=276;
public final static short NEQ=277;
public final static short AND=278;
public final static short OR=279;
public final static short PUBLIC=280;
public final static short RETURN=281;
public final static short INCREMENT=282;
public final static short DECREMENT=283;
public final static short NUMDOUBLE=284;
public final static short NEW=285;
public final static short ESCREVA=286;
public final static short LEIA=287;
public final static short ENDIF=288;
public final static short BREAK=289;
public final static short ENDWHILE=290;
public final static short FOR=291;
public final static short ENDFOR=292;
public final static short DOUBLE=293;
public final static short STRING=294;
public final static short BOOLEAN=295;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    3,    3,    4,    5,    6,    6,    8,    9,
    9,    7,    7,   10,   10,   10,   11,   12,   13,   17,
   14,   14,   18,   19,   19,   16,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,   20,   20,   20,   20,
   20,   20,   20,   20,   20,   20,   20,   20,   15,   22,
   22,   23,   23,   23,   23,   23,   23,   23,   24,   25,
   30,   30,   26,   27,   31,   31,   32,   33,   33,   33,
   28,   34,   29,   35,   21,    1,    1,    1,    1,    1,
};
final static short yylen[] = {                            2,
    1,    0,    2,    0,    5,    6,    2,    0,    4,    2,
    0,    2,    0,    1,    1,    1,    8,    8,   10,    9,
    2,    0,    2,    3,    0,    3,    3,    3,    3,    1,
    3,    1,    3,    4,    3,    1,    1,    2,    2,    3,
    1,    1,    1,    1,    5,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    2,
    0,    1,    1,    1,    1,    1,    1,    0,    4,    4,
    2,    0,    3,    6,    3,    0,    1,    2,    1,    0,
    5,    1,   11,    1,    6,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    3,    0,    0,    0,    0,
    5,   90,   86,   87,   88,   89,    0,    0,    0,    0,
    0,    7,    0,    0,    0,   10,    9,    0,    0,    0,
    6,    0,   14,   15,   16,    0,    0,    0,   12,    0,
    0,    0,    0,    0,   23,    0,    0,   21,    0,    0,
    0,    0,    0,    0,    0,   24,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   59,    0,   62,   63,   64,
   65,   66,   67,    0,    0,    0,    0,   30,   43,   36,
   37,    0,   42,    0,    0,   44,    0,    0,    0,    0,
   17,   60,   18,    0,    0,   38,   39,    0,    0,    0,
   57,   55,   56,   58,    0,   54,    0,    0,   52,    0,
   49,   48,   50,    0,    0,    0,    0,    0,    0,   73,
    0,    0,    0,   69,    0,   31,    0,    0,    0,    0,
    0,    0,    0,    0,   79,    0,    0,    0,   70,    0,
    0,   19,    0,    0,    0,    0,   34,   78,   82,   81,
    0,   26,    0,   45,    0,   77,   74,    0,   85,   75,
    0,    0,    0,    0,   84,   83,
};
final static short yydgoto[] = {                          2,
   17,   85,    3,    4,    9,   18,   31,   19,   24,   32,
   33,   34,   35,   41,   65,  123,    0,   42,   48,  116,
   86,   66,   67,   68,   69,   70,   71,   72,   73,  119,
  146,  157,  137,  150,  166,
};
final static short yysindex[] = {                      -267,
 -245,    0,    0, -267,  -84,    0, -233,  -16,  -77,   12,
    0,    0,    0,    0,    0,    0, -216, -229,   12,   20,
   15,    0, -182,   23,  -66,    0,    0,   37, -172, -159,
    0,  -66,    0,    0,    0,   12,   64,   70,    0, -146,
   74,   76,   84,   12,    0,   12,   12,    0,   12,   86,
    5,   76,    8,   12,   82,    0,   82,   10,   62,  -40,
  -40, -120, -113, -107,   26,    0,   82,    0,    0,    0,
    0,    0,    0,   28,   82,  -40,  -29,    0,    0,    0,
    0,  -40,    0,  -94,   18,    0,   25,  123,  109,  114,
    0,    0,    0,  -99,   46,    0,    0,  -74,  -17,  146,
    0,    0,    0,    0,  -40,    0,  -40,  -40,    0,  -40,
    0,    0,    0,   82,  -40,  -40,   22,  -40,  129,    0,
  -40,  -40,   65,    0,  155,    0,   12,  142,  151,  173,
  118,  -69,   52,  151,    0,   22,  -90,  151,    0,   79,
   87,    0,   12,  160,  147,  -82,    0,    0,    0,    0,
  -40,    0,  166,    0,   82,    0,    0,  112,    0,    0,
  -40,  119,   22,  -78,    0,    0,
};
final static short yyrindex[] = {                       208,
    0,    0,    0,  219,    0,    0,    0,    0,    0,  -57,
    0,    0,    0,    0,    0,    0,    0,    0, -122,  165,
    0,    0,    0,    0,  100,    0,    0, -107,    0,    0,
    0,  100,    0,    0,    0,  185,    0,    0,    0,    0,
    0,  191,    0,  185,    0,  111,    0,    0,  111,    0,
    0,  191,    0,  111,  116,    0,  116,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -112,    0,    0,    0,
    0,    0,    0,    0,  -38,    0,  -39,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  187,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -261,    0,    0,  -34,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  185,  -12,   43,   -3,
  -27,  -25,    0,   59,    0, -281,    0,  206,    0,    0,
    0,    0,  185,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -20,    0,    0,    0,    0,    0,
    0,    0,  -15,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  335,  331,  274,    0,    0,  236,  248,    0,    0,    0,
    0,    0,    0,   -8,   -4,    0,    0,  234,  231,    0,
    0,  -52,  -67,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -126,    0,    0,
};
final static int YYTABLESIZE=492;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         82,
    8,   32,   32,   32,    1,   32,   61,   32,   68,  148,
   68,    5,   61,   27,   92,   27,   98,   27,   32,   32,
   32,   32,   32,  126,  112,  110,   61,  111,   29,  113,
   27,   27,   27,   27,   27,   50,  164,   28,    7,    8,
   20,   10,  109,  107,  108,   29,   29,   11,   29,  136,
   21,   32,   74,   32,   28,   28,   28,   28,   28,  112,
  110,  132,  111,   23,  113,   27,  112,  110,  136,  111,
   94,  113,   25,  115,   26,  114,   36,  109,  107,  108,
   29,   27,  117,   33,  109,  107,  108,  112,  110,   28,
  111,   37,  113,  112,  110,  136,  111,   38,  113,   35,
   33,   33,  160,   43,  124,  109,  107,  108,  115,   44,
   45,  109,  107,  108,   46,  115,   35,   35,  144,   47,
  112,  110,   76,  111,   49,  113,   54,   55,  112,  110,
   57,  111,   75,  113,  153,   33,  115,  151,  109,  107,
  108,   88,  115,   89,  147,  152,  109,  107,  108,   90,
   91,   35,   93,  112,  110,   61,  111,    8,  113,  112,
  112,  110,  100,  111,  113,  113,  118,  120,   61,  115,
  161,  109,  107,  108,  121,   61,  163,  115,  109,  107,
  108,  122,  125,  112,  110,  127,  111,  139,  113,  142,
   28,   13,  112,  110,  143,  111,   29,  113,  145,  149,
  154,  109,  115,  108,  155,  156,  159,    2,  115,  115,
  109,  107,  108,  165,  112,  110,   77,  111,    4,  113,
   78,   79,    8,   11,   13,   22,   14,   15,   16,   80,
   81,   25,  115,    8,   32,   32,   32,   32,   32,   32,
   61,  115,   61,   83,   84,   72,   27,   27,   27,   27,
   27,   27,   96,   97,   22,   68,  101,  102,  103,  104,
  105,  106,   76,  115,   71,   29,   29,   61,   12,   13,
   28,   28,   28,   28,   28,   28,   68,    6,   59,   39,
   52,   51,   56,    0,   53,    0,    0,    0,   60,   58,
   61,  101,  102,  103,  104,  105,  106,    0,  101,  102,
  103,  104,  105,  106,   14,   15,   16,   62,   63,    0,
  135,    0,   64,    0,    0,    0,    0,    0,    0,  101,
  102,  103,  104,  105,  106,  101,  102,  103,  104,  105,
  106,    0,    0,    0,    0,    0,    0,    0,   59,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   60,    0,
   61,    0,  101,  102,  103,  104,  105,  106,    0,   30,
  101,  102,  103,  104,  105,  106,   30,   62,   63,    0,
   40,    0,   64,    0,    0,    0,    0,    0,   40,    0,
    0,   40,    0,    0,    0,  101,  102,  103,  104,  105,
  106,   87,  101,  102,  103,  104,  105,  106,    0,    0,
    0,    0,    0,    0,    0,    0,   95,    0,    0,    0,
    0,    0,   99,    0,    0,  101,  102,  103,  104,    0,
    0,    0,    0,    0,  101,  102,  103,  104,  105,  106,
    0,    0,    0,    0,    0,  128,    0,  129,  130,    0,
  131,    0,    0,    0,    0,  133,  134,    0,  138,    0,
    0,  140,  141,    0,    0,    0,    0,    0,    0,    0,
    0,   40,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   40,    0,    0,
    0,  158,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  162,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
  123,   41,   42,   43,  272,   45,  268,   47,  290,  136,
  292,  257,  125,   41,   67,   43,   46,   45,   58,   59,
   60,   61,   62,   41,   42,   43,  288,   45,   41,   47,
   58,   59,   60,   61,   62,   44,  163,   41,  123,  273,
  257,   58,   60,   61,   62,   58,   59,  125,   61,  117,
  280,   91,   57,   93,   58,   59,   60,   61,   62,   42,
   43,  114,   45,   44,   47,   93,   42,   43,  136,   45,
   75,   47,   58,   91,  257,   58,   40,   60,   61,   62,
   93,   59,   58,   41,   60,   61,   62,   42,   43,   93,
   45,  264,   47,   42,   43,  163,   45,  257,   47,   41,
   58,   59,  155,   40,   59,   60,   61,   62,   91,   40,
  257,   60,   61,   62,   41,   91,   58,   59,  127,   44,
   42,   43,   61,   45,   41,   47,   41,  123,   42,   43,
  123,   45,  123,   47,  143,   93,   91,   59,   60,   61,
   62,  262,   91,  257,   93,   59,   60,   61,   62,  257,
  125,   93,  125,   42,   43,  268,   45,  280,   47,   42,
   42,   43,  257,   45,   47,   47,   44,   59,  281,   91,
   59,   60,   61,   62,   61,  288,   58,   91,   60,   61,
   62,  281,  257,   42,   43,   40,   45,   59,   47,  125,
  257,  258,   42,   43,   40,   45,  263,   47,  268,  290,
   41,   60,   91,   62,   58,  288,   41,    0,   91,   91,
   60,   61,   62,  292,   42,   43,  257,   45,    0,   47,
  261,  262,  280,   59,  125,   41,  293,  294,  295,  270,
  271,   41,   91,  123,  274,  275,  276,  277,  278,  279,
  125,   91,  281,  284,  285,   59,  274,  275,  276,  277,
  278,  279,  282,  283,   19,  290,  274,  275,  276,  277,
  278,  279,  288,   91,   59,  278,  279,  288,  257,  258,
  274,  275,  276,  277,  278,  279,  292,    4,  257,   32,
   47,   46,   52,   -1,   49,   -1,   -1,   -1,  267,   54,
  269,  274,  275,  276,  277,  278,  279,   -1,  274,  275,
  276,  277,  278,  279,  293,  294,  295,  286,  287,   -1,
  289,   -1,  291,   -1,   -1,   -1,   -1,   -1,   -1,  274,
  275,  276,  277,  278,  279,  274,  275,  276,  277,  278,
  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  267,   -1,
  269,   -1,  274,  275,  276,  277,  278,  279,   -1,   25,
  274,  275,  276,  277,  278,  279,   32,  286,  287,   -1,
   36,   -1,  291,   -1,   -1,   -1,   -1,   -1,   44,   -1,
   -1,   47,   -1,   -1,   -1,  274,  275,  276,  277,  278,
  279,   61,  274,  275,  276,  277,  278,  279,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   76,   -1,   -1,   -1,
   -1,   -1,   82,   -1,   -1,  274,  275,  276,  277,   -1,
   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
   -1,   -1,   -1,   -1,   -1,  105,   -1,  107,  108,   -1,
  110,   -1,   -1,   -1,   -1,  115,  116,   -1,  118,   -1,
   -1,  121,  122,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  127,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  143,   -1,   -1,
   -1,  151,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  161,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=295;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
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
"LIT","VOID","MAIN","READ","WRITE","IF","ELSE","WHILE","TRUE","FALSE","CLASS",
"PRIVATE","EQ","LEQ","GEQ","NEQ","AND","OR","PUBLIC","RETURN","INCREMENT",
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
"exp : exp '+' exp",
"exp : exp '>' exp",
"exp : exp AND exp",
"exp : NUM",
"exp : '(' exp ')'",
"exp : ID",
"exp : exp '=' exp",
"exp : exp '[' exp ']'",
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

//#line 195 "AJava.y"

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
   
//#line 704 "Parser.java"
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
case 27:
//#line 84 "AJava.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 28:
//#line 85 "AJava.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 29:
//#line 86 "AJava.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 30:
//#line 87 "AJava.y"
{ yyval.obj = Tp_INT; }
break;
case 31:
//#line 88 "AJava.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 32:
//#line 89 "AJava.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
                    if (nodo == null) {
                       yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada"); 
                       yyval.obj = Tp_ERRO;    
                       }           
                    else
                        yyval.obj = nodo.getTipo();
                  }
break;
case 33:
//#line 97 "AJava.y"
{  yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);  }
break;
case 34:
//#line 98 "AJava.y"
{  if ((TS_entry)val_peek(1).obj != Tp_INT) 
                              yyerror("(sem) indexador não é numérico ");
                           else 
                               if (((TS_entry)val_peek(3).obj).getTipo() != Tp_ARRAY)
                                  yyerror("(sem) elemento não indexado ");
                               else 
                                  yyval.obj = ((TS_entry)val_peek(3).obj).getTipoBase();
                         }
break;
//#line 899 "Parser.java"
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

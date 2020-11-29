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
   11,   15,   13,   14,   14,   17,   16,   16,   12,   12,
   18,   18,   18,   22,   19,   26,   27,   20,   28,   21,
   25,   25,   23,   23,   29,   30,   30,   33,   31,   31,
   34,   32,   32,   36,   35,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,   24,   38,
   38,   38,   38,   38,   38,   38,   45,   39,   40,   46,
   47,   47,   48,   41,   49,   42,   50,   50,   51,   52,
   43,   53,   54,   44,   55,   37,   37,   37,   56,    3,
   57,    4,    1,    1,    1,    1,    1,
};
final static short yylen[] = {                            2,
    0,    2,    2,    0,    0,    7,    2,    0,    6,    2,
    0,    0,    6,    3,    0,    0,    4,    0,    2,    0,
    1,    1,    1,    0,    9,    0,    0,   12,    0,   10,
    2,    0,    2,    0,    2,    3,    0,    0,    3,    0,
    0,    4,    0,    0,    4,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    1,    1,
    1,    1,    1,    3,    1,    4,    1,    2,    1,    2,
    1,    1,    1,    1,    1,    1,    0,    5,    2,    3,
    2,    0,    0,    4,    0,    6,    4,    1,    0,    0,
    7,    0,    0,   11,    2,    2,    2,    0,    0,    7,
    0,    5,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    2,    0,    5,    3,    0,    0,    0,
    7,    0,    0,    0,    0,    6,  107,  103,  104,  105,
  106,    0,    0,    0,   12,    0,   10,    0,    0,    0,
    0,    0,    0,   26,    9,    0,   21,   22,   23,    0,
    0,    0,    0,   29,    0,   19,   14,   16,   13,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   31,   27,
   17,   35,    0,    0,   33,    0,    0,    0,    0,    0,
    0,    0,   36,    0,    0,   77,    0,    0,    0,    0,
    0,    0,    0,    0,   76,   69,    0,    0,   71,   72,
   73,   74,   75,    0,    0,    0,    0,    0,   59,   63,
   61,   62,    0,   60,    0,    0,   67,    0,    0,   79,
   83,   95,    0,    0,   25,   96,   70,   97,    0,    0,
    0,    0,  101,   68,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   30,    0,    0,   99,
    0,   64,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   45,    0,   80,
   84,   90,    0,    0,    0,    0,    0,   66,    0,    0,
    0,   28,    0,    0,    0,    0,   88,   86,    0,    0,
    0,    0,  102,    0,   91,    0,  100,    0,   39,    0,
   93,    0,   87,    0,    0,    0,   42,   94,
};
final static short yydgoto[] = {                          1,
   22,  184,  107,  124,    4,    2,    5,   10,    8,   14,
   23,   35,   24,   31,   28,   42,   54,   36,   37,   38,
   39,   43,   56,   84,   53,   45,   67,   51,   57,   65,
  185,  199,  192,  205,   85,  141,   86,   87,   88,   89,
   90,   91,   92,   93,   97,  110,  143,  144,  140,  188,
  145,  180,  181,  204,   94,  176,  151,
};
final static short yysindex[] = {                         0,
    0, -276, -222,    0, -276,    0,    0, -236, -195,  -60,
    0, -207,   16,  -42,  -74,    0,    0,    0,    0,    0,
    0, -170, -180,  -74,    0,   33,    0,    6,   87, -163,
   56,    0, -150,    0,    0,   87,    0,    0,    0,   18,
 -131,   69,   91,    0,   44,    0,    0,    0,    0,  -74,
  101,   49, -114,   56, -107,  112,  111,  -74,    0,    0,
    0,    0,  -74,  -74,    0,  116,  119,   38,  111,  -74,
  -74,  107,    0,   42,  125,    0,   53,   53,   53,  -75,
  126,   53,  -71,   50,    0,    0,  107,  131,    0,    0,
    0,    0,    0,  107,  107,  -74,    6,  146,    0,    0,
    0,    0,  -56,    0,   53,   76,    0,   76,   34,    0,
    0,    0,   76,  138,    0,    0,    0,    0,   78,   81,
  144,  -50,    0,    0,   43,   53,   53,   53,   53,   53,
   53,   53,   53,   53,   53,   53,   53,   53,   53,  150,
  155,   53,  156,  160,  163,   53,    0,  107,   53,    0,
  190,    0,  231,  231,  231,  231,  109,  102,  231,  231,
   -9,   -9,  141,  141,  141,   67,  107,    0,   76,    0,
    0,    0,   76,  108,   76,  194,   53,    0, -153,  107,
  177,    0,   53,   76,  196,  180,    0,    0,  -40,  -71,
  198,  199,    0,  107,    0,  184,    0,   53,    0,  -29,
    0,   76,    0,  107,  199,  -35,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,  259,    0,    0,  259,    0,    0,  137,    0,    0,
    0,    0,    0,    0,    2,    0,    0,    0,    0,    0,
    0,    0,    0, -121,    0,    0,    0,  -32,  152,    0,
  216,  -22,    0,    0,    0,  152,    0,    0,    0,    0,
    0,    0,    0,    0,   22,    0,    0,    0,    0,  240,
    0,    0,    0,  216,    0,    0,  241,  240,    0,    0,
    0,    0,  171,    0,    0,    0,    0,    0,  241,  171,
  240,  170,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -105,    0,    0,    0,
    0,    0,    0, -105,  170,  171,  222,  -37,    0,    0,
    0,    0,    0,    0,    0,  238,    0,  244,  245,    0,
    0,    0,  247,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  170,    0,    0,
    0,    0,  158,  165,  187,  344,  226,  228,  373,  402,
  129,  136,  -28,   -1,    8,    0, -151,    0,  248,    0,
    0,    0,  249,    0,  -11,    0,  265,    0,    0,   28,
    0,    0,  265,  -15,    0,    0,    0,    0,    0,    0,
    0,  270,    0,   39,    0,    0,    0,    0,    0,    0,
    0,   31,    0,   30,  270,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   66,  405,    0,    0,  311,    0,    0,    0,    0,    0,
   36,  281,    0,  221,    0,  266,    0,    0,    0,    0,
    0,    0,  -34,  -84,    0,    0,    0,    0,  274,  260,
  159,  134,    0,    0,    0,    0,   46,    0,  -82,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=696;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         65,
  114,   11,    3,   65,   65,   65,   65,   65,   47,   65,
  119,   15,   47,   47,   47,   47,   47,   24,   47,   98,
   65,   65,   65,   66,   65,   38,   15,  138,   38,   47,
   47,   47,  136,   47,    6,   49,   75,  137,    9,   49,
   49,   49,   49,   49,   52,   49,   78,   78,   52,   52,
   52,   52,   52,   65,   52,   65,   49,   49,   49,   27,
   49,   11,   12,  174,   47,   52,   52,   52,  107,   52,
  138,   41,   13,   15,   41,  136,  134,  142,  135,  138,
  137,  139,   16,  152,  136,  134,   25,  135,   26,  137,
   29,   49,  105,  133,   34,  132,   30,   40,   68,   41,
   52,   34,  133,  138,  132,   74,   44,  196,  136,  134,
   47,  135,  138,  137,  186,   55,   98,  136,  134,  187,
  135,   98,  137,   55,  139,   48,  133,   49,  132,   55,
   50,  120,  116,  139,   52,  133,   55,  132,  138,  118,
   58,   59,   60,  136,  134,  138,  135,   11,  137,   62,
  136,  134,   63,  135,   64,  137,   70,  139,   71,  178,
   72,  133,   98,  132,   95,   96,  139,   98,  133,   46,
  132,   46,   46,   46,  115,   98,   48,   98,   48,   48,
   48,  111,   17,   18,  112,   76,   46,   46,   46,  117,
   46,  122,  139,   48,   48,   48,  146,   48,   57,  139,
  123,   57,  147,  148,  149,   55,  150,  167,   55,   19,
   20,   21,  179,  168,  170,   57,   57,   57,  171,   57,
  172,   46,   55,   55,   55,  189,   55,   56,   48,  177,
   56,  139,  182,  183,  107,  190,  193,  194,  197,  200,
  195,  201,  198,  203,   56,   56,   56,  208,   56,  206,
   57,   65,   65,   65,   65,   65,   65,   55,    4,    8,
   47,   47,   47,   47,   47,   47,   53,  138,   54,   53,
   11,   54,  136,  134,   18,  135,   20,  137,   32,   56,
   34,   37,   15,   53,   53,   54,   54,   49,   49,   49,
   49,   49,   49,   11,   98,   85,   52,   52,   52,   52,
   52,   52,   44,   82,   89,   40,   81,   92,   98,   98,
   43,   98,   98,   99,  100,    7,   46,  121,   53,   61,
   54,  139,  126,  127,  128,  129,  130,  131,   73,  101,
  102,  126,  127,  128,  129,  130,  131,   69,  207,  103,
  104,  191,    0,   32,   18,    0,    0,    0,    0,   33,
    0,    0,    0,    0,    0,  126,  127,  128,  129,  130,
  131,    0,    0,   76,  126,  127,  128,  129,  130,  131,
   19,   20,   21,   77,    0,    0,   78,   79,   80,    0,
   81,    0,   82,    0,   58,    0,    0,   58,   83,    0,
  126,  127,  128,  129,  130,    0,    0,  126,  127,  128,
  129,   58,   58,   58,    0,   58,    0,    0,    0,    0,
    0,    0,    0,   50,    0,    0,   50,   46,   46,   46,
   46,   46,   46,    0,   48,   48,   48,   48,   48,   48,
   50,   50,   50,    0,   50,    0,   58,    0,    0,    0,
    0,    0,   51,    0,    0,   51,   57,   57,   57,   57,
   57,   57,    0,   55,   55,   55,   55,   55,   55,   51,
   51,   51,    0,   51,    0,   50,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   56,   56,   56,   56,   56,
   56,  106,  108,  109,    0,    0,  113,    0,    0,    0,
    0,    0,    0,    0,   51,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  125,
    0,    0,    0,    0,    0,    0,    0,    0,   53,   53,
    0,   54,    0,    0,    0,    0,    0,    0,    0,    0,
  153,  154,  155,  156,  157,  158,  159,  160,  161,  162,
  163,  164,  165,  166,    0,    0,  169,    0,    0,    0,
  173,    0,    0,  175,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  202,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   58,   58,   58,   58,   58,   58,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   50,   50,   50,   50,   50,   50,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   51,   51,   51,   51,   51,   51,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   83,  123,  279,   41,   42,   43,   44,   45,   37,   47,
   95,   44,   41,   42,   43,   44,   45,   40,   47,  125,
   58,   59,   60,   58,   62,   41,   59,   37,   44,   58,
   59,   60,   42,   62,  257,   37,   71,   47,  275,   41,
   42,   43,   44,   45,   37,   47,   58,   59,   41,   42,
   43,   44,   45,   91,   47,   93,   58,   59,   60,   24,
   62,  257,  123,  148,   93,   58,   59,   60,   91,   62,
   37,   41,  280,   58,   44,   42,   43,   44,   45,   37,
   47,   91,  125,   41,   42,   43,  257,   45,  269,   47,
   58,   93,   40,   60,   29,   62,   91,  261,   63,   44,
   93,   36,   60,   37,   62,   70,  257,  190,   42,   43,
   93,   45,   37,   47,  268,   50,  268,   42,   43,  273,
   45,  273,   47,   58,   91,  257,   60,   59,   62,   64,
   40,   96,   87,   91,   91,   60,   71,   62,   37,   94,
   40,   93,  257,   42,   43,   37,   45,  269,   47,  257,
   42,   43,   41,   45,   44,   47,   41,   91,   40,   93,
  123,   60,  268,   62,  123,   41,   91,  273,   60,   41,
   62,   43,   44,   45,  125,  281,   41,  283,   43,   44,
   45,  257,  257,  258,   59,  257,   58,   59,   60,   59,
   62,   46,   91,   58,   59,   60,   59,   62,   41,   91,
  257,   44,  125,  123,   61,   41,  257,   58,   44,  284,
  285,  286,  167,   59,   59,   58,   59,   60,   59,   62,
   58,   93,   58,   59,   60,  180,   62,   41,   93,   40,
   44,   91,  125,   40,  257,   59,   41,   58,   41,  194,
  281,   58,   44,  273,   58,   59,   60,  283,   62,  204,
   93,  289,  290,  291,  292,  293,  294,   93,    0,  123,
  289,  290,  291,  292,  293,  294,   41,   37,   41,   44,
  269,   44,   42,   43,   59,   45,  125,   47,  257,   93,
   41,   41,   61,   58,   59,   58,   59,  289,  290,  291,
  292,  293,  294,  123,  125,   58,  289,  290,  291,  292,
  293,  294,   59,   59,   58,   41,   59,   59,  281,  257,
   41,  273,  283,  261,  262,    5,   36,   97,   93,   54,
   93,   91,  289,  290,  291,  292,  293,  294,   69,  277,
  278,  289,  290,  291,  292,  293,  294,   64,  205,  287,
  288,  183,   -1,  257,  258,   -1,   -1,   -1,   -1,  263,
   -1,   -1,   -1,   -1,   -1,  289,  290,  291,  292,  293,
  294,   -1,   -1,  257,  289,  290,  291,  292,  293,  294,
  284,  285,  286,  267,   -1,   -1,  270,  271,  272,   -1,
  274,   -1,  276,   -1,   41,   -1,   -1,   44,  282,   -1,
  289,  290,  291,  292,  293,   -1,   -1,  289,  290,  291,
  292,   58,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   41,   -1,   -1,   44,  289,  290,  291,
  292,  293,  294,   -1,  289,  290,  291,  292,  293,  294,
   58,   59,   60,   -1,   62,   -1,   93,   -1,   -1,   -1,
   -1,   -1,   41,   -1,   -1,   44,  289,  290,  291,  292,
  293,  294,   -1,  289,  290,  291,  292,  293,  294,   58,
   59,   60,   -1,   62,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  289,  290,  291,  292,  293,
  294,   77,   78,   79,   -1,   -1,   82,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  105,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  293,  294,
   -1,  294,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  126,  127,  128,  129,  130,  131,  132,  133,  134,  135,
  136,  137,  138,  139,   -1,   -1,  142,   -1,   -1,   -1,
  146,   -1,   -1,  149,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  198,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  289,  290,  291,  292,  293,  294,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  289,  290,  291,  292,  293,  294,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  289,  290,  291,  292,  293,  294,
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
"decl : tipo ID $$3 arrayOuNao lid ';'",
"arrayOuNao : '[' NUM ']'",
"arrayOuNao :",
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
"$$7 :",
"metnormal : tipo $$6 arrayMetodoOuNao ID $$7 '(' lparam ')' ldecl '{' corpomet '}'",
"$$8 :",
"metvoid : VOID ID $$8 '(' lparam ')' ldecl '{' corpomet '}'",
"arrayMetodoOuNao : '[' ']'",
"arrayMetodoOuNao :",
"lparam : param sublparam",
"lparam :",
"param : tipo ID",
"sublparam : ',' param sublparam",
"sublparam :",
"$$9 :",
"lparamchamada : exp $$9 sublparamchamada",
"lparamchamada :",
"$$10 :",
"sublparamchamada : ',' exp $$10 sublparamchamada",
"sublparamchamada :",
"$$11 :",
"return : RETURN exp $$11 ';'",
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
"exp : exp '[' exp ']'",
"exp : chamaMetodo",
"exp : NEW chamaConstrutor",
"corpomet : lcmdloop",
"cmd : atrib ';'",
"cmd : escrita",
"cmd : leia",
"cmd : if",
"cmd : while",
"cmd : for",
"cmd : return",
"$$12 :",
"atrib : ID $$12 arrayOuNao '=' exp",
"escrita : ESCREVA restoEscrita",
"restoEscrita : exp contescrita ';'",
"contescrita : ',' exp",
"contescrita :",
"$$13 :",
"leia : LEIA ID $$13 ';'",
"$$14 :",
"if : IF exp $$14 ':' lcmdloop else",
"else : ELSE ':' lcmdloop ENDIF",
"else : ENDIF",
"$$15 :",
"$$16 :",
"while : WHILE exp $$15 ':' $$16 lcmdloop ENDWHILE",
"$$17 :",
"$$18 :",
"for : FOR atrib ';' exp $$17 ';' atrib ':' $$18 lcmdloop ENDFOR",
"break : BREAK ';'",
"lcmdloop : cmd lcmdloop",
"lcmdloop : break lcmdloop",
"lcmdloop :",
"$$19 :",
"chamaMetodo : ID '.' ID $$19 '(' lparamchamada ')'",
"$$20 :",
"chamaConstrutor : ID $$20 '(' lparamchamada ')'",
"tipo : INT",
"tipo : DOUBLE",
"tipo : STRING",
"tipo : BOOLEAN",
"tipo : ID",
};

//#line 470 "AJava.y"

  private Yylex lexer;

  private TabSimb ts;
  private ClasseID currClasse;
  private TS_entry classeAtual;
  private TS_entry metodoAtual;
  private TS_entry tipoAtual;
  private TS_entry tipoBaseAtual;
  private boolean metodoMesmoNome = false;
  private String assinaturaAtual;
  private TS_entry classeConstrutor = null;
  int loopLevel = 0;
  private TS_entry declAtual = null;
  private boolean atribuendoehArray = false;
  private TS_entry arrayAtual = null;
  private int nReturns = 0;
  private boolean foiArray = false;
  





  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_DOUBLE = new TS_entry("double", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOLEAN = new TS_entry("boolean", null,  ClasseID.TipoBase);
  public static TS_entry Tp_LITERAL = new TS_entry("literal", null,  ClasseID.TipoBase);
  public static TS_entry Tp_OBJETO = new TS_entry("objeto", null,  ClasseID.TipoBase);
  public static TS_entry Tp_VOID = new TS_entry("void", null,  ClasseID.TipoBase);
  public static TS_entry Tp_CONSTRUTOR = new TS_entry("construtor", null,  ClasseID.TipoBase);
  public static TS_entry Tp_ARRAY = new TS_entry("array", null,  ClasseID.TipoBase);

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

    //ts.setListar(true);
  
    ts.insert(Tp_ERRO);
    ts.insert(Tp_INT);
    ts.insert(Tp_DOUBLE);
    ts.insert(Tp_BOOLEAN);
    ts.insert(Tp_LITERAL);
    ts.insert(Tp_OBJETO);
    ts.insert(Tp_VOID);
    ts.insert(Tp_CONSTRUTOR);
    ts.insert(Tp_ARRAY);
    

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
//#line 758 "Parser.java"
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
{currClasse = ClasseID.VarGlobal; classeAtual = null; metodoAtual = null; }
break;
case 5:
//#line 41 "AJava.y"
{    
                       TS_entry simb = ts.pesquisa((String)val_peek(0).sval);
                      if(simb != null) {
                        yyerror("(sem) classe <" + val_peek(0).sval + "> já declarada");
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
                        yyerror("(sem) classe <" + val_peek(0).sval + "> não declarada");
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
                            yyerror("(sem) atributo <" + val_peek(0).sval + "> já declarado no escopo");
                          }
                      }
                      else {
                          dec = new TS_entry((String)val_peek(0).sval, (TS_entry)val_peek(1).obj, ClasseID.VarLocal);
                          if(classeAtual.pesquisaAtributo(dec.getId()) == null && metodoAtual.pesquisaVarLocal(dec.getId()) == null) {
                            metodoAtual.addVarLocal(dec);
                          } else {
                            yyerror("(sem) variável <" + val_peek(0).sval + "> já declarada no escopo");
                          }
                      }
                      declAtual = dec;
                      tipoAtual = (TS_entry)val_peek(1).obj;
                      
                }
break;
case 13:
//#line 94 "AJava.y"
{declAtual = null;}
break;
case 14:
//#line 96 "AJava.y"
{
    atribuendoehArray = true;
    if(declAtual != null) {
      declAtual.setTipoBase(declAtual.getTipo());
      declAtual.setTipo(Tp_ARRAY);
      declAtual.setCapacidade(val_peek(1).ival);
    }
    
  }
break;
case 16:
//#line 108 "AJava.y"
{        TS_entry dec;
                      if(metodoAtual == null) {
                          dec = new TS_entry((String)val_peek(0).sval, tipoAtual, ClasseID.Atributo);

                          if(classeAtual.pesquisaAtributo(dec.getId()) == null) {
                            classeAtual.addAtributo(dec);
                          } else {
                            yyerror("(sem) atributo <" + val_peek(0).sval + "> já declarado no escopo");
                          }
                      }
                      else {
                          dec = new TS_entry((String)val_peek(0).sval, tipoAtual, ClasseID.VarLocal);
                          if(classeAtual.pesquisaAtributo(dec.getId()) == null && metodoAtual.pesquisaVarLocal(dec.getId()) == null && metodoAtual.pesquisaParametro(dec.getId()) == null) {
                            metodoAtual.addVarLocal(dec);
                          } else {
                            yyerror("(sem) variável <" + val_peek(0).sval + "> já declarada no escopo");
                          }
                      }}
break;
case 24:
//#line 140 "AJava.y"
{
                if(!(((String)val_peek(0).sval).equals(classeAtual.getId()))) {
                      yyerror("(sem) metodo nao construtor <" + val_peek(0).sval + "> sem modificador de tipo");
                } else {
                  metodoAtual = new TS_entry((String)val_peek(0).sval, Tp_CONSTRUTOR, ClasseID.Metodo);
                  currClasse = ClasseID.Metodo;

                }}
break;
case 25:
//#line 147 "AJava.y"
{currClasse = ClasseID.Classe;
                                                          if(classeAtual.pesquisaMetodo(metodoAtual.getAssinatura()) == null) {
                                                            classeAtual.addMetodo(metodoAtual);
                                                          } else {
                                                            yyerror("(sem) método <" + val_peek(8).sval + "> com assinatura repetida na classe");
                                                          }  
                                                          metodoAtual = null;}
break;
case 26:
//#line 155 "AJava.y"
{tipoAtual = (TS_entry)val_peek(0).obj;}
break;
case 27:
//#line 155 "AJava.y"
{
                  if(((String)val_peek(0).sval).equals("main")) {
                    yyerror("(sem) método main deve ser void");
                  } else {
                    metodoAtual = new TS_entry((String)val_peek(0).sval, tipoAtual, ClasseID.Metodo);
                    metodoAtual.setTipoBase(tipoBaseAtual);
                    currClasse = ClasseID.Metodo;
                    nReturns = 0;
                  }

            }
break;
case 28:
//#line 165 "AJava.y"
{currClasse = ClasseID.Classe;
                                                          if(metodoAtual != null) {
                                                            if(classeAtual.pesquisaMetodo(metodoAtual.getAssinatura()) == null) {
                                                              classeAtual.addMetodo(metodoAtual);
                                                            } else {
                                                              yyerror("(sem) método <" + val_peek(8).sval + "> com assinatura repetida na classe");
                                                            }
                                                            if(nReturns < 1) {
                                                              yyerror("(sem) método <" + val_peek(8).sval + "> sem comando de retorno");
                                                            }
                                                          }
                                                                                                                  
                                                          metodoAtual = null; nReturns = 0;}
break;
case 29:
//#line 179 "AJava.y"
{
                  metodoAtual = new TS_entry((String)val_peek(0).sval, Tp_VOID, ClasseID.Metodo);
                  currClasse = ClasseID.Metodo;

            }
break;
case 30:
//#line 183 "AJava.y"
{currClasse = ClasseID.Classe;
                                                          if(classeAtual.pesquisaMetodo(metodoAtual.getAssinatura()) == null) {
                                                            if(metodoAtual.getAssinatura().contains("main") && metodoAtual.getAssinatura().length() > 4) {
                                                              yyerror("(sem) método main nao pode ter parâmetros");
                                                            } else {
                                                              classeAtual.addMetodo(metodoAtual);
                                                            }                                                            
                                                          } else {
                                                            yyerror("(sem) método <" + val_peek(8).sval + "> com assinatura repetida na classe");
                                                          }
                                                          metodoAtual = null;}
break;
case 31:
//#line 195 "AJava.y"
{tipoBaseAtual = tipoAtual; tipoAtual = Tp_ARRAY;}
break;
case 35:
//#line 203 "AJava.y"
{
          if(metodoAtual.parametroRepetido((String)val_peek(0).sval))
          {
              yyerror("(sem) parâmetro repetido <" + val_peek(0).sval + "> no método <" + metodoAtual.getId() + ">");
          }
          else
          {
            assinaturaAtual += " " + ((TS_entry)val_peek(1).obj).getTipoStr();
            metodoAtual.addParametro(new TS_entry((String)val_peek(0).sval, (TS_entry)val_peek(1).obj, ClasseID.Parametro));
          }
        }
break;
case 38:
//#line 219 "AJava.y"
{ assinaturaAtual += " " + ((TS_entry)val_peek(0).obj).getTipoStr();
  }
break;
case 41:
//#line 224 "AJava.y"
{ assinaturaAtual += " " + ((TS_entry)val_peek(0).obj).getTipoStr();
  }
break;
case 44:
//#line 229 "AJava.y"
{
            nReturns++;
            if(validaTipo('=', metodoAtual.getTipo(), (TS_entry)val_peek(0).obj) == Tp_ERRO)
            {
              yyerror("(sem) tipo de retorno <" + ((TS_entry)val_peek(0).obj).getTipoStr() + "> incompatível com metodo <" + metodoAtual.getId() + ">");
            }
            if(metodoAtual.getTipo() == Tp_CONSTRUTOR) {
              yyerror("(sem) método construtor não possui return");
            }
            if(metodoAtual.getTipo() == Tp_VOID) {
              yyerror("(sem) método void não possui return");
            } 
         }
break;
case 46:
//#line 245 "AJava.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 47:
//#line 246 "AJava.y"
{ yyval.obj = validaTipo('*', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 48:
//#line 247 "AJava.y"
{ yyval.obj = validaTipo('-', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 49:
//#line 248 "AJava.y"
{ yyval.obj = validaTipo('/', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 50:
//#line 249 "AJava.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 51:
//#line 250 "AJava.y"
{ yyval.obj = validaTipo('<', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 52:
//#line 251 "AJava.y"
{ yyval.obj = validaTipo('%', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 53:
//#line 252 "AJava.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 54:
//#line 253 "AJava.y"
{ yyval.obj = validaTipo(OR, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 55:
//#line 254 "AJava.y"
{ yyval.obj = validaTipo(LEQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 56:
//#line 255 "AJava.y"
{ yyval.obj = validaTipo(GEQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 57:
//#line 256 "AJava.y"
{ yyval.obj = validaTipo(EQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 58:
//#line 257 "AJava.y"
{ yyval.obj = validaTipo(NEQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 59:
//#line 258 "AJava.y"
{ yyval.obj = Tp_INT; }
break;
case 60:
//#line 259 "AJava.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 61:
//#line 260 "AJava.y"
{ yyval.obj = Tp_BOOLEAN; }
break;
case 62:
//#line 261 "AJava.y"
{ yyval.obj = Tp_BOOLEAN; }
break;
case 63:
//#line 262 "AJava.y"
{ yyval.obj = Tp_LITERAL; }
break;
case 64:
//#line 263 "AJava.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 65:
//#line 264 "AJava.y"
{ TS_entry nodo;
                  nodo = classeAtual.pesquisaAtributo((String)val_peek(0).sval);
                  if(nodo == null && metodoAtual != null)
                  {
                      nodo = metodoAtual.pesquisaVarLocal((String)val_peek(0).sval);
                      if(nodo == null)
                      {
                          nodo = metodoAtual.pesquisaParametro((String)val_peek(0).sval);
                          if(nodo == null) {
                            yyerror("(sem) variável ou atributo <" + val_peek(0).sval + "> não declarado(a) ou fora do escopo");
                            yyval.obj = Tp_ERRO;
                          }
                      }
                  }
                  if(nodo != null) {
                    yyval.obj = nodo.getTipo();
                    if(nodo.getTipo() == Tp_ARRAY) {
                      arrayAtual = nodo;
                    }
                  }
                }
break;
case 66:
//#line 285 "AJava.y"
{  if ((TS_entry)val_peek(1).obj != Tp_INT) 
                              yyerror("(sem) indexador não é numérico ");
                           else 
                               if (arrayAtual.getTipo() != Tp_ARRAY) {
                                  System.out.println((TS_entry)val_peek(3).obj);
                                  yyerror("(sem) elemento não indexado "); }
                               else 
                                  yyval.obj = arrayAtual.getTipoBase();
                         }
break;
case 67:
//#line 294 "AJava.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 68:
//#line 295 "AJava.y"
{if(val_peek(0).obj != Tp_CONSTRUTOR) {
        yyerror("(sem) somente construtores podem ser chamados com new");
          yyval.obj = Tp_ERRO;
     } else {
       yyval.obj = classeConstrutor;
       classeConstrutor = null;
     }}
break;
case 77:
//#line 315 "AJava.y"
{atribuendoehArray = false;}
break;
case 78:
//#line 315 "AJava.y"
{
         TS_entry nodo;
          nodo = classeAtual.pesquisaAtributo((String)val_peek(4).sval);
          if(nodo == null && metodoAtual != null)
          {
              nodo = metodoAtual.pesquisaVarLocal((String)val_peek(4).sval);
              if(nodo == null)
              {
                  nodo = metodoAtual.pesquisaParametro((String)val_peek(4).sval);
                  if(nodo == null) {
                    yyerror("(sem) variável ou atributo <" + val_peek(4).sval + "> não declarado(a) ou fora do escopo");
                  }
              }
          }
          if(nodo != null && atribuendoehArray && nodo.getTipo() == Tp_ARRAY) {
            validaTipo('=', nodo.getTipoBase(), (TS_entry)val_peek(0).obj);
          }
          else if(nodo != null) {
            validaTipo('=', nodo.getTipo(), (TS_entry)val_peek(0).obj);
          }
        }
break;
case 80:
//#line 340 "AJava.y"
{ if(val_peek(2).obj != Tp_LITERAL) yyerror("(sem) primeiro operador da escrita precisa ser do tipo string "); }
break;
case 81:
//#line 342 "AJava.y"
{
   if (val_peek(0).obj != Tp_BOOLEAN && val_peek(0).obj != Tp_INT && val_peek(0).obj != Tp_DOUBLE && val_peek(0).obj != Tp_LITERAL ){
      yyerror("tipo da expressão deve ser um tipo base: int, double, boolean ou string");}
    }
break;
case 83:
//#line 349 "AJava.y"
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
                    yyerror("(sem) variável ou atributo <" + val_peek(0).sval + "> não declarado(a) ou fora do escopo");
                  }
              }
          }
        }
break;
case 85:
//#line 365 "AJava.y"
{
          if((TS_entry)val_peek(0).obj != Tp_BOOLEAN) {
              yyerror("(sem) expressão não booleana em condição de IF");
          }
         }
break;
case 89:
//#line 375 "AJava.y"
{
          if((TS_entry)val_peek(0).obj != Tp_BOOLEAN) {
              yyerror("(sem) expressão não booleana em condição de WHILE");
          }
         }
break;
case 90:
//#line 379 "AJava.y"
{loopLevel++;}
break;
case 91:
//#line 379 "AJava.y"
{loopLevel--;}
break;
case 92:
//#line 381 "AJava.y"
{
          if((TS_entry)val_peek(0).obj != Tp_BOOLEAN) {
              yyerror("(sem) expressão não booleana em condição de FOR");
          }
         }
break;
case 93:
//#line 385 "AJava.y"
{loopLevel++;}
break;
case 94:
//#line 385 "AJava.y"
{loopLevel--;}
break;
case 97:
//#line 391 "AJava.y"
{if(loopLevel == 0) yyerror("(sem) break fora de loop");}
break;
case 99:
//#line 395 "AJava.y"
{assinaturaAtual = (String)val_peek(0).sval;}
break;
case 100:
//#line 395 "AJava.y"
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
                    yyerror("(sem) objeto <" + val_peek(6).sval + "> não declarado ou fora do escopo");
                    yyval.obj = Tp_ERRO;
                  }
              }
          }

          if(nodo != null) {
            if(nodo.getTipo().getTipo() == Tp_OBJETO){
              if (nodo.getTipo().pesquisaMetodoHeranca(assinaturaAtual) == null) {
                yyerror("(sem) a classe do objeto <" + val_peek(6).sval + "> não possui o método <" + val_peek(4).sval + "> com esses parametros");
                yyval.obj = Tp_ERRO;
              } else {
                TS_entry tipoRet = nodo.getTipo().pesquisaMetodoHeranca(assinaturaAtual).getTipo();
                if(tipoRet == Tp_ARRAY) {
                  arrayAtual = nodo.getTipo().pesquisaMetodoHeranca(assinaturaAtual);
                }
                yyval.obj = nodo.getTipo().pesquisaMetodoHeranca(assinaturaAtual).getTipo();
              }
            }
            else
            {
              yyerror("(sem) identificador <" + val_peek(6).sval + "> não é um objeto");
              yyval.obj = Tp_ERRO;
            }
          } else {
            yyval.obj = Tp_ERRO;
          }
   }
break;
case 101:
//#line 434 "AJava.y"
{assinaturaAtual = (String)val_peek(0).sval;}
break;
case 102:
//#line 434 "AJava.y"
{
   TS_entry classe = ts.pesquisa(val_peek(4).sval);
   if(classe == null) {
        yyerror("(sem) chamada de construtor <" + val_peek(4).sval + "> para classe não definida");
        yyval.obj = Tp_ERRO;
   } else {
     TS_entry met = classe.pesquisaMetodoHeranca(assinaturaAtual);
     if(met == null) {
       yyerror("(sem) construtor <" + val_peek(4).sval + "> com assinatura não definida");
        yyval.obj = Tp_ERRO;
     } else if(met.getTipo() != Tp_CONSTRUTOR){
       yyerror("(sem) construtor <" + val_peek(4).sval + "> com assinatura não definida");
        yyval.obj = Tp_ERRO;
     } else {
       yyval.obj = Tp_CONSTRUTOR;
       classeConstrutor = classe;
     }
   }
 }
break;
case 103:
//#line 454 "AJava.y"
{yyval.obj = Tp_INT;}
break;
case 104:
//#line 455 "AJava.y"
{yyval.obj = Tp_DOUBLE;}
break;
case 105:
//#line 456 "AJava.y"
{yyval.obj = Tp_LITERAL;}
break;
case 106:
//#line 457 "AJava.y"
{yyval.obj = Tp_BOOLEAN;}
break;
case 107:
//#line 458 "AJava.y"
{
     TS_entry nodo = ts.pesquisa((String)val_peek(0).sval);
     if(nodo == null) {
       yyerror("(sem) tipo <" + val_peek(0).sval + "> não definido");
       yyval.obj = Tp_ERRO;
     }
     else {
       yyval.obj = nodo;
     }
   }
break;
//#line 1448 "Parser.java"
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

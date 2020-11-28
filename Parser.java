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
public final static short WHILE=275;
public final static short TRUE=276;
public final static short FALSE=277;
public final static short CLASS=278;
public final static short PRIVATE=279;
public final static short ENDWHILE=280;
public final static short FOR=281;
public final static short ENDFOR=282;
public final static short DOUBLE=283;
public final static short STRING=284;
public final static short BOOLEAN=285;
public final static short NEW=286;
public final static short NUMDOUBLE=287;
public final static short EQ=288;
public final static short LEQ=289;
public final static short GEQ=290;
public final static short NEQ=291;
public final static short AND=292;
public final static short OR=293;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    4,    4,    5,    6,    7,    7,    9,   10,   10,
    8,    8,   11,   11,   11,   11,   12,   13,   14,   15,
   16,   16,   19,   20,   20,   18,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    3,    3,   17,
   22,   22,   23,   23,   23,   23,   23,   23,   24,   25,
   30,   30,   26,   27,   31,   31,   32,   33,   33,   33,
   28,   34,   29,   35,   21,    1,    1,    1,    1,    1,
};
final static short yylen[] = {                            2,
    1,    2,    0,    5,    6,    2,    0,    4,    2,    0,
    2,    0,    1,    1,    1,    1,    8,    8,   10,    9,
    2,    0,    2,    3,    0,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    1,    1,
    1,    1,    1,    3,    1,    3,    1,    1,    3,    1,
    2,    0,    1,    1,    1,    1,    1,    1,    4,    4,
    2,    0,    3,    6,    3,    0,    1,    2,    1,    0,
    5,    1,   11,    1,    6,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    2,    0,    0,    0,    0,
    4,   80,   76,   77,   78,   79,    0,    0,    0,    0,
    0,    6,    0,    0,    0,    9,    8,    0,    0,    0,
    5,    0,   13,   14,   15,   16,    0,    0,    0,    0,
   11,    0,    0,    0,    0,    0,    0,   23,    0,    0,
   21,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   24,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   50,    0,   53,   54,   55,   56,   57,   58,    0,
    0,    0,    0,    0,   39,   43,   41,   42,   40,    0,
    0,    0,   47,    0,    0,    0,    0,   17,   51,    0,
   18,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   63,    0,    0,   20,    0,    0,   59,    0,   44,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   28,   30,    0,    0,    0,   60,   69,    0,    0,    0,
    0,   19,    0,    0,    0,   68,   72,   71,    0,   26,
    0,    0,   67,   64,    0,   75,   65,    0,    0,    0,
    0,   74,   73,
};
final static short yydgoto[] = {                          2,
   17,   91,   92,    3,    4,    9,   18,   31,   19,   24,
   32,   33,   34,   35,   36,   43,   71,  127,   44,   51,
   93,   72,   73,   74,   75,   76,   77,   78,   79,  121,
  155,  164,  149,  158,  173,
};
final static short yysindex[] = {                      -248,
 -218,    0,    0, -248,  -78,    0, -228,    1,  -67,  -47,
    0,    0,    0,    0,    0,    0, -192, -191,  -47,   41,
   31,    0, -166,   39,  -23,    0,    0,   64, -254, -157,
    0,  -23,    0,    0,    0,    0,  -47,   77,   79,   81,
    0, -126,   92,   99,  -47,   96,  -47,    0,  -47,  -47,
    0,  104,  -47,  107,   27,   99,  -47,   37,  -47,  -49,
    0,   43,  -49,   45,  103,  111, -108,  -88,  111,  -87,
   47,    0,  -49,    0,    0,    0,    0,    0,    0,  -49,
   52,  -49,  111,  139,    0,    0,    0,    0,    0,  111,
   54,  126,    0,  149,  132,   60,  136,    0,    0,   74,
    0,  -68,   66,  -53,  -34,  111,  111,  111,  111,  111,
  111,  111,  111,  111,  111,  111,  111,  -49,  111,  111,
  161,    0,  218,  111,    0,  111,  116,    0,  166,    0,
  186,  186,  186,  186,  153,  147,  186,  186,   68,   68,
    0,    0,  -44,  141,  141,    0,    0,  218,  -59,   87,
   93,    0,  -47,  181,  -31,    0,    0,    0,  111,    0,
  202,  -49,    0,    0,  114,    0,    0,  111,  120,  218,
  -38,    0,    0,
};
final static short yyrindex[] = {                       245,
    0,    0,    0,  245,    0,    0,    0,    0,    0,  -16,
    0,    0,    0,    0,    0,    0,    0,    0, -111,  187,
    0,    0,    0,    0,  138,    0,    0,    7,    0,    0,
    0,  138,    0,    0,    0,    0,  224,    0,    0,    0,
    0,    0,    0,  225,  224,    0,  224,    0,  -78,    0,
    0,    0,  -78,    0,    0,  225,  -78,    0,  -78,  142,
    0,    0,  142,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -54,    0,    0,    0,    0,    0,    0,  142,
    0,   -2,    0,  -41,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  210,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -129,    0,    0,
    0,    0,    9,    0,    0,    0,    0,    0,  227,    0,
   -6,    2,    8,   14,   34,   36,   22,   28,  -18,  -12,
    0,    0,   35,   65,  250,    0,    0, -266,    0,    0,
    0,    0,  224,    0,    0,    0,    0,    0,    0,    0,
    0,   49,    0,    0,    0,    0,    0,    0,    0,   42,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
  180,  357,    0,  319,    0,    0,  122,  293,    0,    0,
    0,    0,    0,    0,    0,  -11,  -58,    0,  278,  274,
    0,  -35,  -91,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -133,    0,    0,
};
final static int YYTABLESIZE=525;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         45,
   45,   45,   38,   45,   81,   45,  130,  116,  114,   39,
  115,    7,  117,   70,  156,   70,   45,   45,   45,   48,
   45,  100,   27,  102,   27,  113,   27,  112,   29,    1,
   29,  148,   29,   52,   37,   54,  171,   99,    5,   27,
   27,   27,   35,   27,    7,   29,   29,   29,   36,   29,
    8,   37,   37,   37,   38,   37,  148,   11,   10,   35,
   35,   35,   31,   35,   20,   36,   36,   36,   32,   36,
   52,   38,   38,   38,   33,   38,   34,   21,  148,   31,
   31,   31,  143,   31,   23,   32,   32,   32,   25,   32,
   26,   33,   33,   34,   34,  116,  114,   27,  115,   40,
  117,  116,  114,   37,  115,   46,  117,  116,  114,  116,
  115,  118,  117,  113,  117,  112,   45,  123,   46,  113,
   47,  112,   46,   46,  128,  113,  167,  112,  116,  114,
   48,  115,   49,  117,  116,  114,   53,  115,   52,  117,
   22,  161,   50,   52,   57,  159,  113,   59,  112,   60,
   90,  160,  113,   94,  112,  116,  114,    7,  115,   63,
  117,  116,  114,   83,  115,   80,  117,   82,   95,   97,
   55,   98,  168,  113,   58,  112,  101,  170,   62,  113,
   64,  112,  116,  114,  104,  115,  119,  117,  116,  114,
  122,  115,  120,  117,  116,  114,  124,  115,  125,  117,
  113,  126,  112,  129,   30,  153,  113,   65,  112,   12,
   13,   30,  113,   52,  112,   52,   42,   66,   52,  146,
  157,   67,   68,  154,   42,   69,   42,  116,  114,   42,
  115,   70,  117,   28,   13,   14,   15,   16,  162,   29,
  152,  163,  166,  172,    3,   10,   45,   45,   45,   45,
   45,   45,    7,  106,  107,  108,  109,  110,  111,   14,
   15,   16,   12,   80,   22,   25,   52,   52,   62,   27,
   27,   27,   27,   27,   27,   29,   29,   29,   29,   29,
   29,   37,   37,   37,   37,   37,   37,   49,   70,   35,
   35,   35,   35,   35,   35,   36,   36,   36,   36,   36,
   36,   38,   38,   38,   38,   38,   38,   66,   61,   31,
   31,   31,   31,   31,   31,   32,   32,   32,   32,   32,
   32,   52,    6,   70,   41,   33,   33,   56,   34,   61,
    0,    0,   42,    0,    0,    0,    0,    0,    0,    0,
    0,  106,  107,  108,  109,  110,  111,  106,  107,  108,
  109,  110,  111,  106,  107,  108,  109,  110,  111,    0,
    0,    0,    0,    0,    0,    0,    0,   84,    0,    0,
    0,   85,   86,    0,  106,  107,  108,  109,  110,  111,
  106,  107,  108,  109,  110,  111,   87,   88,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   89,    0,    0,
    0,  106,  107,  108,  109,  110,  111,  106,  107,  108,
  109,  110,  111,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   96,    0,    0,  106,  107,
  108,  109,  110,  111,  106,  107,  108,  109,  110,  103,
  106,  107,  108,  109,    0,    0,  105,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  131,  132,  133,  134,  135,  136,  137,  138,
  139,  140,  141,  142,   65,  144,  145,    0,    0,    0,
  150,    0,  151,    0,   66,    0,    0,    0,   67,   68,
    0,  147,   69,    0,    0,    0,    0,    0,   70,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  165,    0,    0,    0,    0,
    0,    0,    0,    0,  169,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   42,   43,  257,   45,   63,   47,   41,   42,   43,  264,
   45,  123,   47,  280,  148,  282,   58,   59,   60,   61,
   62,   80,   41,   82,   43,   60,   45,   62,   41,  278,
   43,  123,   45,   45,   41,   47,  170,   73,  257,   58,
   59,   60,   41,   62,  123,   58,   59,   60,   41,   62,
  279,   58,   59,   60,   41,   62,  148,  125,   58,   58,
   59,   60,   41,   62,  257,   58,   59,   60,   41,   62,
  125,   58,   59,   60,   41,   62,   41,  269,  170,   58,
   59,   60,  118,   62,   44,   58,   59,   60,   58,   62,
  257,   58,   59,   58,   59,   42,   43,   59,   45,  257,
   47,   42,   43,   40,   45,   41,   47,   42,   43,   42,
   45,   58,   47,   60,   47,   62,   40,   58,   40,   60,
   40,   62,   58,   59,   59,   60,  162,   62,   42,   43,
  257,   45,   41,   47,   42,   43,   41,   45,  268,   47,
   19,  153,   44,  273,   41,   59,   60,   41,   62,  123,
   40,   59,   60,  262,   62,   42,   43,  269,   45,  123,
   47,   42,   43,   61,   45,  123,   47,  123,  257,  257,
   49,  125,   59,   60,   53,   62,  125,   58,   57,   60,
   59,   62,   42,   43,   46,   45,   61,   47,   42,   43,
   59,   45,   44,   47,   42,   43,   61,   45,  125,   47,
   60,  270,   62,  257,   25,   40,   60,  257,   62,  257,
  258,   32,   60,  268,   62,  270,   37,  267,  273,   59,
  280,  271,  272,  268,   45,  275,   47,   42,   43,   50,
   45,  281,   47,  257,  258,  283,  284,  285,   58,  263,
  125,  273,   41,  282,    0,   59,  288,  289,  290,  291,
  292,  293,  269,  288,  289,  290,  291,  292,  293,  283,
  284,  285,  125,  257,   41,   41,  125,  270,   59,  288,
  289,  290,  291,  292,  293,  288,  289,  290,  291,  292,
  293,  288,  289,  290,  291,  292,  293,   61,  280,  288,
  289,  290,  291,  292,  293,  288,  289,  290,  291,  292,
  293,  288,  289,  290,  291,  292,  293,  273,   59,  288,
  289,  290,  291,  292,  293,  288,  289,  290,  291,  292,
  293,  273,    4,  282,   32,  292,  293,   50,  293,   56,
   -1,   -1,  153,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  288,  289,  290,  291,  292,  293,  288,  289,  290,
  291,  292,  293,  288,  289,  290,  291,  292,  293,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,   -1,
   -1,  261,  262,   -1,  288,  289,  290,  291,  292,  293,
  288,  289,  290,  291,  292,  293,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  287,   -1,   -1,
   -1,  288,  289,  290,  291,  292,  293,  288,  289,  290,
  291,  292,  293,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   69,   -1,   -1,  288,  289,
  290,  291,  292,  293,  288,  289,  290,  291,  292,   83,
  288,  289,  290,  291,   -1,   -1,   90,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  106,  107,  108,  109,  110,  111,  112,  113,
  114,  115,  116,  117,  257,  119,  120,   -1,   -1,   -1,
  124,   -1,  126,   -1,  267,   -1,   -1,   -1,  271,  272,
   -1,  274,  275,   -1,   -1,   -1,   -1,   -1,  281,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  159,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  168,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=293;
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
"LEIA","ENDIF","BREAK","WHILE","TRUE","FALSE","CLASS","PRIVATE","ENDWHILE",
"FOR","ENDFOR","DOUBLE","STRING","BOOLEAN","NEW","NUMDOUBLE","EQ","LEQ","GEQ",
"NEQ","AND","OR",
};
final static String yyrule[] = {
"$accept : prog",
"prog : lclasse",
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
"met : metvoid",
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
"exp : exp '*' exp",
"exp : exp '-' exp",
"exp : exp '/' exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
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
"exp : lvalue '=' exp",
"exp : chamaMetodo",
"lvalue : ID",
"lvalue : ID '.' ID",
"corpomet : lcmd",
"lcmd : cmd lcmd",
"lcmd :",
"cmd : atrib",
"cmd : escrita",
"cmd : leia",
"cmd : if",
"cmd : while",
"cmd : for",
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

//#line 176 "AJava.y"

  private Yylex lexer;

  private TabSimb ts;

  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_DOUBLE = new TS_entry("double", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOLEAN = new TS_entry("boolean", null,  ClasseID.TipoBase);
  public static TS_entry Tp_LITERAL = new TS_entry("literal", null,  ClasseID.TipoBase);
  public static TS_entry Tp_OBJETO = new TS_entry("objeto", null,  ClasseID.TipoBase);
  public static TS_entry Tp_VOID = new TS_entry("void", null,  ClasseID.TipoBase);

  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null,  ClasseID.TipoBase);

  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;

  private String currEscopo;
  private ClasseID currClass;

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
    lexer = new Yylex(r, this);

    ts = new TabSimb();

    //
    // não me parece que necessitem estar na TS
    // já que criei todas como public static...
    //
    ts.insert(Tp_ERRO);
    ts.insert(Tp_INT);
    ts.insert(Tp_DOUBLE);
    ts.insert(Tp_BOOLEAN);
    ts.insert(Tp_LITERAL);
    ts.insert(Tp_OBJETO);
    ts.insert(Tp_VOID);
    

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
              case ATRIB:
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
                     if (A == B)
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " == "+B.getTipoStr());

                case NEQ:
                     if (A == B)
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " != "+B.getTipoStr());

                case LEQ:
                     if (A == B)
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " <= "+B.getTipoStr());

                case GEQ:
                     if (A == B)
                         return Tp_BOOLEAN;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " >= "+B.getTipoStr());
                 break;
            }
            

            return Tp_ERRO;
           
     }
//#line 651 "Parser.java"
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
//#line 88 "AJava.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 28:
//#line 89 "AJava.y"
{ yyval.obj = validaTipo('*', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 29:
//#line 90 "AJava.y"
{ yyval.obj = validaTipo('-', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 30:
//#line 91 "AJava.y"
{ yyval.obj = validaTipo('/', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 31:
//#line 92 "AJava.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 32:
//#line 93 "AJava.y"
{ yyval.obj = validaTipo('<', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 33:
//#line 94 "AJava.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 34:
//#line 95 "AJava.y"
{ yyval.obj = validaTipo(OR, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 35:
//#line 96 "AJava.y"
{ yyval.obj = validaTipo(LEQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 36:
//#line 97 "AJava.y"
{ yyval.obj = validaTipo(GEQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 37:
//#line 98 "AJava.y"
{ yyval.obj = validaTipo(EQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 38:
//#line 99 "AJava.y"
{ yyval.obj = validaTipo(NEQ, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 39:
//#line 100 "AJava.y"
{ yyval.obj = Tp_INT; }
break;
case 40:
//#line 101 "AJava.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 41:
//#line 102 "AJava.y"
{ yyval.obj = Tp_BOOLEAN; }
break;
case 42:
//#line 103 "AJava.y"
{ yyval.obj = Tp_BOOLEAN; }
break;
case 43:
//#line 104 "AJava.y"
{ yyval.obj = Tp_LITERAL; }
break;
case 44:
//#line 105 "AJava.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 45:
//#line 106 "AJava.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
                    if (nodo == null) {
                       yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada"); 
                       yyval.obj = Tp_ERRO;    
                       }           
                    else
                        yyval.obj = nodo.getTipo();
                  }
break;
case 46:
//#line 114 "AJava.y"
{  yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);  }
break;
//#line 887 "Parser.java"
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

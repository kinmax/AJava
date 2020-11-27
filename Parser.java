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
    8,    8,   11,   11,   11,   12,   13,   14,   18,   15,
   15,   19,   20,   20,   17,    2,    2,    2,    2,    2,
    2,    2,    2,    3,    3,   16,   21,   21,   22,   22,
   22,   22,   22,   22,   23,   24,   29,   29,   25,   26,
   30,   30,   31,   32,   32,   32,   27,   33,   28,   34,
   35,    1,    1,    1,    1,    1,
};
final static short yylen[] = {                            2,
    1,    2,    0,    5,    6,    2,    0,    4,    2,    0,
    2,    0,    1,    1,    1,    8,    8,   10,    9,    2,
    0,    2,    3,    0,    3,    3,    3,    3,    1,    3,
    1,    3,    4,    1,    3,    1,    2,    0,    1,    1,
    1,    1,    1,    1,    4,    4,    2,    0,    3,    6,
    3,    0,    1,    2,    1,    0,    5,    1,   11,    1,
    6,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    2,    0,    0,    0,    0,
    4,   66,   62,   63,   64,   65,    0,    0,    0,    0,
    0,    6,    0,    0,    0,    9,    8,    0,    0,    0,
    5,    0,   13,   14,   15,    0,    0,    0,   11,    0,
    0,    0,    0,    0,   22,    0,    0,   20,    0,    0,
    0,    0,    0,    0,    0,   23,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   36,    0,   39,   40,   41,
   42,   43,   44,    0,    0,    0,    0,   29,    0,    0,
    0,    0,    0,    0,    0,   16,   37,   17,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   49,    0,    0,    0,    0,   45,   35,   30,    0,    0,
    0,    0,    0,    0,    0,   46,   55,    0,    0,    0,
    0,   18,   33,    0,    0,   54,   58,   57,    0,   25,
    0,   53,   50,    0,   51,    0,    0,    0,    0,   60,
   59,
};
final static short yydgoto[] = {                          2,
   17,   80,   81,    3,    4,    9,   18,   31,   19,   24,
   32,   33,   34,   35,   41,   65,  105,    0,   42,   48,
   66,   67,   68,   69,   70,   71,   72,   73,  100,  125,
  133,  119,  128,  141,    0,
};
final static short yysindex[] = {                      -265,
 -241,    0,    0, -265,  -83,    0, -234,   -9,  -57, -149,
    0,    0,    0,    0,    0,    0, -188, -196, -149,   56,
   53,    0, -145,   61, -181,    0,    0,   86, -137, -129,
    0, -181,    0,    0,    0, -149,   90,   91,    0, -124,
   96,   94,   98, -149,    0, -149, -149,    0, -149,   99,
   18,   94,   19, -149, -102,    0, -102,   21,   84,  -39,
 -112, -110,  -39, -105,   34,    0, -102,    0,    0,    0,
    0,    0,    0,   37, -102,  -39,  117,    0,  -39,  -24,
  103,  122,  109,  -19,  110,    0,    0,    0,  -98,  -11,
  -82,  -37,  -39,  -39,  -39,  -39, -102,  -39,  -39,  115,
    0, -114,  -39,  -39,   51,    0,    0,    0,    4,  -10,
   87,   -7,  -91,    3,    3,    0,    0, -114, -100,   -6,
   -1,    0,    0,  123,  -90,    0,    0,    0,  -39,    0,
 -102,    0,    0,    1,    0,  -39,   16, -114,  -97,    0,
    0,
};
final static short yyrindex[] = {                       182,
    0,    0,    0,  182,    0,    0,    0,    0,    0,  -85,
    0,    0,    0,    0,    0,    0,    0,    0, -115,  127,
    0,    0,    0,    0,   62,    0,    0,  -69,    0,    0,
    0,   62,    0,    0,    0,  148,    0,    0,    0,    0,
    0,  149,    0,  148,    0,  -83,    0,    0,  -83,    0,
    0,  149,    0,  -83,   66,    0,   66,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -122,    0,    0,    0,
    0,    0,    0,    0,  -78,    0,  -41,    0,    0,    0,
    0,  134,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -259,    0,    0,    0,
    0,  -86,    0,    0,    0,    0,    0,    0,  -29,  -31,
  -36,    0,  -77,   29,  136,    0,    0, -189,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -76,    0,    0,    0,    0,    0,    0,  -84,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
   85,   20,    0,  195,    0,    0,   52,  168,    0,    0,
    0,    0,    0,    0,  157,   22,    0,    0,  155,  151,
  -56,  -13,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -103,    0,    0,    0,
};
final static int YYTABLESIZE=308;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         31,
   79,   31,   38,  108,   26,   95,   26,    7,   38,   27,
   87,   28,    1,   38,  126,    5,   31,   31,   95,   34,
   31,   26,   26,   95,   94,   26,   27,   27,   28,   28,
   27,   95,   95,   97,  139,   95,   95,   94,  102,    7,
  113,   95,   94,   95,    8,   95,   95,  106,   10,   31,
   94,   31,  129,   96,   94,   94,   26,  130,   95,  136,
   94,   27,   94,   28,   94,   94,   96,   11,   20,   32,
   22,   96,   21,  138,  135,   28,   13,   94,   74,   96,
   96,   29,   84,   96,   96,  123,   32,   32,  118,   96,
   56,   96,   56,   96,   96,   90,   89,   51,   92,   23,
   53,   14,   15,   16,  118,   58,   96,   12,   13,   30,
   25,   26,  109,  110,  111,  112,   30,  114,  115,   27,
   40,   32,  120,  121,  118,   36,   37,   38,   40,   43,
   44,   40,   45,   14,   15,   16,   46,   47,   49,   54,
   55,   57,   59,   75,   76,   38,   83,   38,  134,   82,
   38,   85,   60,    7,   59,  137,   61,   62,   86,  117,
   63,   88,   91,   98,   60,   99,   64,  101,   61,   62,
  103,  104,   63,  116,  107,  122,  124,   96,   64,  127,
  131,    3,  132,    7,  140,   10,   12,   66,   21,   24,
   38,   38,   48,   56,   47,   52,   38,   56,    6,   39,
   50,   52,   56,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   77,    0,    0,
    0,   78,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   31,    0,    0,    0,   93,   26,    0,    0,    0,    0,
   27,    0,   28,    0,    0,    0,    0,   93,    0,    0,
    0,    0,   93,    0,    0,    0,    0,    0,    0,    0,
   93,    0,    0,    0,   93,   93,    0,    0,    0,    0,
   93,    0,   93,    0,   93,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   93,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   40,   43,  125,   41,   41,   43,   43,  123,  268,   41,
   67,   41,  278,  273,  118,  257,   58,   59,   43,   61,
   62,   58,   59,   43,   62,   62,   58,   59,   58,   59,
   62,   43,   43,   58,  138,   43,   43,   62,   58,  123,
   97,   43,   62,   43,  279,   43,   43,   59,   58,   91,
   62,   93,   59,   91,   62,   62,   93,   59,   43,   59,
   62,   93,   62,   93,   62,   62,   91,  125,  257,   41,
   19,   91,  269,   58,  131,  257,  258,   62,   57,   91,
   91,  263,   63,   91,   91,   93,   58,   59,  102,   91,
  280,   91,  282,   91,   91,   76,   75,   46,   79,   44,
   49,  283,  284,  285,  118,   54,   91,  257,  258,   25,
   58,  257,   93,   94,   95,   96,   32,   98,   99,   59,
   36,   93,  103,  104,  138,   40,  264,  257,   44,   40,
   40,   47,  257,  283,  284,  285,   41,   44,   41,   41,
  123,  123,  257,  123,   61,  268,  257,  270,  129,  262,
  273,  257,  267,  269,  257,  136,  271,  272,  125,  274,
  275,  125,   46,   61,  267,   44,  281,   59,  271,  272,
   61,  270,  275,   59,  257,  125,  268,   91,  281,  280,
   58,    0,  273,  269,  282,   59,  125,  257,   41,   41,
  125,  270,   59,  280,   59,  273,  273,  282,    4,   32,
   44,   47,   52,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,   -1,
   -1,  261,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  292,   -1,   -1,   -1,  292,  292,   -1,   -1,   -1,   -1,
  292,   -1,  292,   -1,   -1,   -1,   -1,  292,   -1,   -1,
   -1,   -1,  292,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  292,   -1,   -1,   -1,  292,  292,   -1,   -1,   -1,   -1,
  292,   -1,  292,   -1,  292,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  292,
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
"exp : lvalue '=' exp",
"exp : exp '[' exp ']'",
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

//#line 167 "AJava.y"

  private Yylex lexer;

  private TabSimb ts;

  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_DOUBLE = new TS_entry("double", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("bool", null,  ClasseID.TipoBase);

  public static TS_entry Tp_ARRAY = new TS_entry("array", null,  ClasseID.TipoBase);

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
    ts.insert(Tp_BOOL);
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
              case ATRIB:
                    if ( (A == Tp_INT && B == Tp_INT)                        ||
                         ((A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE))) ||
                         (A == B) )
                         return A;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

              case '+' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) ) 
                         return Tp_DOUBLE;     
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '>' :
                     if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
                      break;

             case AND:
                     if (A == Tp_BOOL && B == Tp_BOOL)
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());
                 break;
            }

            return Tp_ERRO;
           
     }
//#line 509 "Parser.java"
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
case 26:
//#line 85 "AJava.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 27:
//#line 86 "AJava.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 28:
//#line 87 "AJava.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 29:
//#line 88 "AJava.y"
{ yyval.obj = Tp_INT; }
break;
case 30:
//#line 89 "AJava.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 31:
//#line 90 "AJava.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
                    if (nodo == null) {
                       yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada"); 
                       yyval.obj = Tp_ERRO;    
                       }           
                    else
                        yyval.obj = nodo.getTipo();
                  }
break;
case 32:
//#line 98 "AJava.y"
{  yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);  }
break;
case 33:
//#line 99 "AJava.y"
{  if ((TS_entry)val_peek(1).obj != Tp_INT) 
                              yyerror("(sem) indexador não é numérico ");
                           else 
                               if (((TS_entry)val_peek(3).obj).getTipo() != Tp_ARRAY)
                                  yyerror("(sem) elemento não indexado ");
                               else 
                                  yyval.obj = ((TS_entry)val_peek(3).obj).getTipoBase();
                         }
break;
//#line 704 "Parser.java"
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

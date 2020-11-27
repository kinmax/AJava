	
%{
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
%}
 

%token ID, INT, FLOAT, BOOL, NUM, LIT, VOID, MAIN, READ, WRITE, IF, ELSE, PUBLIC, RETURN, ESCREVA, LEIA, ENDIF, BREAK
%token WHILE,TRUE, FALSE, CLASS, PRIVATE, ENDWHILE, FOR, ENDFOR, DOUBLE, STRING, BOOLEAN, NEW, NUMDOUBLE
%token EQ, LEQ, GEQ, NEQ 
%token AND, OR

%right '=' 
%left OR
%left AND
%left  '>' '<' EQ LEQ GEQ NEQ 
%left '+' '-'
%left '*' '/' '%'
%left '!' 
%left '['

%type <sval> ID
%type <ival> NUM
%type <obj> tipo
%type <obj> exp
%type <obj> lvalue



%%

 prog: lclasse
	;
 
 lclasse: classe lclasse
 	| 
	;
 
 classe: CLASS ID '{' corpoclasse '}' ;
			
 
 
 corpoclasse: PRIVATE ':' latri PUBLIC ':' lmet ;
 
 latri: atri latri 
 	|
	;
 	
 atri: tipo ID lid ';' ;
 
 lid: ',' ID
      | 
	  ;
 	
 lmet: met lmet
 	| 
	;
 	
 met: metconst
 	| metmain
 	| metnormal
	; 
 
 metconst: ID '('lparam')' latri '{' corpomet '}' ;
 
 metmain: VOID MAIN'('')' latri '{' corpomet '}' ;
  
 metnormal: tipo ID '('lparam')' latri '{' corpomet return '}' ;
 
 metvoid: VOID ID '('lparam')' latri '{' corpomet '}' ;
 
 lparam: param sublparam
 	|
	;

 param: tipo ID ;
 
 sublparam: ',' param sublparam
	|
	;
	
 return: RETURN exp ';' ;
 
exp: exp '+' exp { $$ = validaTipo('+', (TS_entry)$1, (TS_entry)$3); }
    | exp '>' exp { $$ = validaTipo('>', (TS_entry)$1, (TS_entry)$3); }
    | exp AND exp { $$ = validaTipo(AND, (TS_entry)$1, (TS_entry)$3); } 
    | NUM         { $$ = Tp_INT; }      
    | '(' exp ')' { $$ = $2; }
    | ID       { TS_entry nodo = ts.pesquisa($1);
                    if (nodo == null) {
                       yyerror("(sem) var <" + $1 + "> nao declarada"); 
                       $$ = Tp_ERRO;    
                       }           
                    else
                        $$ = nodo.getTipo();
                  }                   
     | lvalue '=' exp  {  $$ = validaTipo(ATRIB, (TS_entry)$1, (TS_entry)$3);  } 
     | exp '[' exp ']'  {  if ((TS_entry)$3 != Tp_INT) 
                              yyerror("(sem) indexador não é numérico ");
                           else 
                               if (((TS_entry)$1).getTipo() != Tp_ARRAY)
                                  yyerror("(sem) elemento não indexado ");
                               else 
                                  $$ = ((TS_entry)$1).getTipoBase();
                         } 
    ;

lvalue: ID | ID '.' ID
;  
 
 corpomet: lcmd ;
 	
 	
 lcmd: cmd lcmd ;
 	| 
 
 cmd: atrib
 	| escrita
 	| leia
 	| if
 	| while
 	| for
	;
 	
 atrib: ID '=' exp ';' ;
 
 escrita: ESCREVA LIT contescrita ';' ;
 
 contescrita: ',' exp
	|
	;

 leia: LEIA ID ';' ;
 
 if: IF exp ':' lcmd else endif ;
 
 else: ELSE ':' lcmd
 	|
	;

 endif: ENDIF ;
 
 lcmdloop: cmd lcmdloop
 	| BREAK
 	|
	;
   

 while: WHILE exp ':' lcmdloop endwhile ;
 
 endwhile: ENDWHILE ;
 
 for: FOR ID '=' exp ';' exp ';' exp ':' lcmdloop endfor ;
 
 endfor: ENDFOR ;
 
 chamaMetodo: ID '.' ID '(' lparam')' ;
 
 tipo: INT
 	| DOUBLE
 	| STRING
 	| BOOLEAN
 	| ID
	;
%%

  private Yylex lexer;

  private TabSimb ts;

  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_DOUBLE = new TS_entry("double", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("boolean", null,  ClasseID.TipoBase);

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

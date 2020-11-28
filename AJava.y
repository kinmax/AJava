	
%{
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
%}
 

%token ID, INT, FLOAT, BOOL, NUM, LIT, VOID, MAIN, READ, WRITE, IF, ELSE, PUBLIC, RETURN, ESCREVA, LEIA, ENDIF, BREAK, EXTENDS
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

 prog: { currEscopo = "Global"; currClasse = ClasseID.VarGlobal; } lclasse 
	;
 
 lclasse: classe lclasse
 	| 
	;
 
 classe: CLASS ID {    
                       TS_entry simb = ts.pesquisa((String)$2, ClasseID.Classe, "Global");
                      if(simb != null) {
                        yyerror("(sem) classe <" + $2 + "> jah declarada"); 
                        $$ = Tp_ERRO;
                      } else {
                        ts.insert(new TS_entry((String)$2, Tp_OBJETO, ClasseID.Classe, currEscopo));
                        currEscopo = (String)$2;
                        currClasse = ClasseID.Classe;
                        classeAtual = ts.pesquisa((String)$2, ClasseID.Classe, "Global");
                      }} extends '{' corpoclasse '}' {currEscopo = "Global"; currClasse = ClasseID.VarGlobal; classeAtual = null;} ;

extends: EXTENDS ID {
                      TS_entry simb = ts.pesquisa((String)$2, ClasseID.Classe, "Global");
                      if(simb == null) {
                        yyerror("(sem) classe <" + $2 + "> nao declarada"); 
                        $$ = Tp_ERRO;
                      } else {
                        classeAtual.setPai(simb);
                      }
}
         |
         ;
			
 
 
 corpoclasse: PRIVATE ':' ldecl PUBLIC ':' lmet ;
 
 ldecl: decl ldecl 
 	|
	;
 	
 decl: tipo ID {  TS_entry simb = ts.pesquisa((String)$2, ClasseID.Atributo, currEscopo);
                      if(simb != null) {
                        yyerror("(sem) variável ou atributo <" + $2 + "> jah declarado no escopo"); 
                        $$ = Tp_ERRO;
                      } else {
                        tipoAtual = $1;
                        if(currClasse == ClasseID.Classe) {
                          ts.insert(new TS_entry((String)$2, $1, ClasseID.Atributo, currEscopo));
                          classeAtual.addAtributo(simb);
                        } else if(currClasse == ClasseID.Metodo) {
                          ts.insert(new TS_entry((String)$2, $1, ClasseID.VarLocal, currEscopo));
                          metodoAtual.addVarLocal(simb);
                        }
                        
                      }} lid ';' ;
 
 lid: ',' ID {  TS_entry simb = ts.pesquisa((String)$2, ClasseID.Atributo, currEscopo);
                      if(simb != null) {
                        yyerror("(sem) variável ou atributo <" + $2 + "> jah declarado no escopo"); 
                        $$ = Tp_ERRO;
                      } else {
                        if(currClasse == ClasseID.Classe) {
                          ts.insert(new TS_entry((String)$2, tipoAtual, ClasseID.Atributo, currEscopo));
                          classeAtual.addAtributo(simb);
                        } else if(currClasse == ClasseID.Metodo) {
                          ts.insert(new TS_entry((String)$2, tipoAtual, ClasseID.VarLocal, currEscopo));
                          metodoAtual.addVarLocal(simb);
                        }
                        
                      }} lid
      | 
	  ;

 	
 lmet: met lmet
 	| 
	;


 met: metconst
 	| metnormal
  | metvoid
	; 
 
 metconst: ID {
                if(!(((String)$2).equals(classeAtual.getId()))) {
                      yyerror("(sem) metodo nao construtor <" + $2 + "> sem modificador de tipo"); 
                      $$ = Tp_ERRO;
                } else {
                  metodoAtual = new TS_entry(String($2), Tp_CONSTRUTOR, ClasseID.Metodo, currEscopo);
                  currClasse = ClasseID.Metodo;
                  currEscopo = metodoAtual.getId();

                }} '(' lparam ')' ldecl '{' corpomet '}' {currEscopo = classeAtual.getId(); currClasse = ClasseID.Classe; metodoAtual = null;} ;
  
 metnormal: tipo ID {
                  metodoAtual = new TS_entry((String)$2, (TS_entry)$1, ClasseID.Metodo, currEscopo);
                  currClasse = ClasseID.Metodo;
                  currEscopo = metodoAtual.getId();

            } '('lparam')' ldecl '{' corpomet return '}' {currEscopo = classeAtual.getId(); currClasse = ClasseID.Classe; metodoAtual = null;} ;
 
 metvoid: VOID ID {
                  metodoAtual = new TS_entry((String)$2, Tp_VOID, ClasseID.Metodo, currEscopo);
                  currClasse = ClasseID.Metodo;
                  currEscopo = metodoAtual.getId();

            } '(' lparam ')' ldecl '{' corpomet '}' {currEscopo = classeAtual.getId(); currClasse = ClasseID.Classe; metodoAtual = null;} ;
 
 lparam: param sublparam
 	|
	;

 param: tipo ID {
          if(metodoAtual.parametroRepetido((String)$2))
          {
              yyerror("(sem) parametro repetido <" + $2 + "> no metodo <" + metodoAtual.getId() + ">"); 
              $$ = Tp_ERRO;
          }
          else
          {
            metodoAtual.addParametro(new TS_entry((String)$2, (TS_entry)$1, ClasseID.Parametro, currEscopo));
            currEscopo = metodoAtual.getEscopo();
          }
        } ;
 
 sublparam: ',' param sublparam
	|
	;
	
 return: RETURN exp {
            if(exp != metodoAtual.getTipo())
            {
              yyerror("(sem) tipo de retorno <" + ((TS_entry)$1).getTipoStr() + "> incompativel com metodo <" + metodoAtual.getId() + ">"); 
              $$ = Tp_ERRO;
            } 
         }  ';' ;

 
 
exp: exp '+' exp { $$ = validaTipo('+', (TS_entry)$1, (TS_entry)$3); }
    | exp '*' exp { $$ = validaTipo('*', (TS_entry)$1, (TS_entry)$3); }
    | exp '-' exp { $$ = validaTipo('-', (TS_entry)$1, (TS_entry)$3); }
    | exp '/' exp { $$ = validaTipo('/', (TS_entry)$1, (TS_entry)$3); }
   	| exp '>' exp { $$ = validaTipo('>', (TS_entry)$1, (TS_entry)$3);}
    | exp '<' exp { $$ = validaTipo('<', (TS_entry)$1, (TS_entry)$3);}
 	  | exp AND exp { $$ = validaTipo(AND, (TS_entry)$1, (TS_entry)$3); }
    | exp OR exp  { $$ = validaTipo(OR, (TS_entry)$1, (TS_entry)$3); }
    | exp LEQ exp { $$ = validaTipo(LEQ, (TS_entry)$1, (TS_entry)$3); }
    | exp GEQ exp { $$ = validaTipo(GEQ, (TS_entry)$1, (TS_entry)$3); }
    | exp EQ exp { $$ = validaTipo(EQ, (TS_entry)$1, (TS_entry)$3); }
    | exp NEQ exp { $$ = validaTipo(NEQ, (TS_entry)$1, (TS_entry)$3); }
    | NUM         { $$ = Tp_INT; }
    | NUMDOUBLE         { $$ = Tp_DOUBLE; } 
    | TRUE     { $$ = Tp_BOOLEAN; }  
    | FALSE     { $$ = Tp_BOOLEAN; } 
    | LIT     { $$ = Tp_LITERAL; } 
    | '(' exp ')' { $$ = $2; }
    | ID       { TS_entry nodo = ts.pesquisa($1, ClasseID.Atributo, classeAtual.getEscopo());
                    if (nodo == null) {
                       nodo = ts.pesquisa($1, ClasseID.Parametro, metodoAtual.getEscopo());
                       if (nodo == null) {
                         nodo = ts.pesquisa($1, ClasseID.VarLocal, metodoAtual.getEscopo());
                         if (nodo == null) {
                          yyerror("(sem) variavel ou atributo <" + $1 + "> nao declarado ou fora de escopo"); 
                          $$ = Tp_ERRO;    
                         } else {
                           $$ = nodo.getTipo();
                         }
                       } else {
                         $$ = nodo.getTipo();
                       }
                       
                    }           
                    else
                        $$ = nodo.getTipo();
                  }                   
     | lvalue '=' exp  {  $$ = validaTipo('=', (TS_entry)$1, (TS_entry)$3);  } 
     | chamaMetodo
    ;

lvalue: ID {TS_entry simb = ts.pesquisa($1);
            if(simb == null)
            {
                yyerror("(sem) var <" + $1 + "> nao declarada"); 
                $$ = Tp_ERRO; 
            } else{
              if(currEscopo.equals(simb.getEscopo()) {
                return simb.getTipo();
              } else {
                yyerror("(sem) var <" + $1 + "> fora do escopo"); 
                $$ = Tp_ERRO;
              })
            }}
;  
 
 corpomet: lcmd ;
 	
 	
 lcmd: cmd lcmd 
 	| 
  ;
 
 cmd: atrib
 	| escrita
 	| leia
 	| if
 	| while
 	| for
  | break
	;
 	
 atrib: ID {
          if(ts.pesquisa((String)$1, ClasseID.Atributo, classeAtual.getEscopo()) == null || ts.pesquisa((String)$1, ClasseID.VarLocal, metodoAtual.getEscopo()) == null)
          {
              yyerror("(sem) var ou atributo <" + $1 + "> nao declarado ou fora do escopo"); 
              $$ = Tp_ERRO;
          }
        } '=' exp {
          TS_entry nodo;
          nodo = ts.pesquisa((String)$1, ClasseID.Atributo, classeAtual.getEscopo());
          if(nodo == null) {
            ts.pesquisa((String)$1, ClasseID.VarLocal, metodoAtual.getEscopo())
            if(nodo != null) {
              if(nodo.getTipo() != (TS_entry)$3) {
                yyerror("(sem) Tipos incompativeis para atribuição: " + getTipoStr($3) + " e " + getTipoStr(nodo.getTipo())); 
                $$ = Tp_ERRO;
              }
            }
          } else {
            if(nodo.getTipo() != (TS_entry)$3) {
                yyerror("(sem) Tipos incompativeis para atribuição: " + getTipoStr($3) + " e " + getTipoStr(nodo.getTipo())); 
                $$ = Tp_ERRO;
            }
          }
        } ';' ;
 
 escrita: ESCREVA LIT contescrita ';' ;
 
 contescrita: ',' exp
	|
	;

 leia: LEIA ID {
          TS_entry nodo;
          nodo = ts.pesquisa((String)$1, ClasseID.VarGlobal, "Global");
          if(nodo == null) {
            ts.pesquisa((String)$1, ClasseID.Atributo, classeAtual.getEscopo())
            if(nodo == null) {
              nodo = ts.pesquisa((String)$1, ClasseID.VarLocal, metodoAtual.getEscopo());
              if(nodo == null) {
                yyerror("(sem) var <" + $1 + "> nao declarada ou fora do escopo"); 
                $$ = Tp_ERRO;
              }              
            }
          }
        } ';' ;
 
 if: IF exp {
          if((TS_entry)$2 != Tp_BOOLEAN) {
              yyerror("(sem) expressao nao booleana em condicao de IF"); 
              $$ = Tp_ERRO;
          }
         } ':' lcmd else ENDIF ;
 
 else: ELSE ':' lcmd
 	|
	;

 while: WHILE exp {
          if((TS_entry)$2 != Tp_BOOLEAN) {
              yyerror("(sem) expressao nao booleana em condicao de WHILE"); 
              $$ = Tp_ERRO;
          }
         } ':' lcmd endwhile ENDWHILE ;
 
 for: FOR ID '=' atrib ';' exp {
          if((TS_entry)$2 != Tp_BOOLEAN) {
              yyerror("(sem) expressao nao booleana em condicao de WHILE"); 
              $$ = Tp_ERRO;
          }
         } ';' atrib ':' lcmd ENDFOR ;

 break: BREAK ';' ;
 
 chamaMetodo: ID '.' ID '(' lparam')' ;
 
 tipo: INT
 	| DOUBLE
 	| STRING
 	| BOOLEAN
 	| ID
	;
%%

import java.util.ArrayList;
import java.util.HashMap;

  private Yylex lexer;

  private TabSimb ts;

  private String currEscopo;
  private ClasseID currClasse;
  private TabSimb ts;
  private TS_entry classeAtual;
  private TS_entry metodoAtual;
  private TS_entry tipoAtual;
  private boolean metodoMesmoNome = false;
  





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

    classes = new ArrayList<String>();

    herancas = new HashMap<String, String>();

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

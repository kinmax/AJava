	
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
%type <obj> chamaMetodo
%type <obj> chamaConstrutor



%%

 prog: { currEscopo = "Global"; currClasse = ClasseID.VarGlobal; classeAtual = null; metodoAtual = null; } lclasse 
	;
 
 lclasse: classe lclasse
 	| 
	;
 
 classe: CLASS ID {    
                       TS_entry simb = ts.pesquisa((String)$2);
                      if(simb != null) {
                        yyerror("(sem) classe <" + $2 + "> jah declarada");
                      } else {
                        TS_entry classe = new TS_entry((String)$2, Tp_OBJETO, ClasseID.Classe);
                        currClasse = ClasseID.Classe;
                        classeAtual = classe;
                        ts.insert(classe);
                      }} extends '{' corpoclasse '}' {currClasse = ClasseID.VarGlobal; classeAtual = null;} ;

extends: EXTENDS ID {
                      TS_entry simb = ts.pesquisa((String)$2);
                      if(simb == null) {
                        yyerror("(sem) classe <" + $2 + "> nao declarada");
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
 	



 decl: tipo ID {      TS_entry dec;
                      if(metodoAtual == null) {
                          dec = new TS_entry((String)$2, (TS_entry)$1, ClasseID.Atributo);
                          if(classeAtual.pesquisaAtributo(dec.getId()) == null) {
                            classeAtual.addAtributo(dec);
                          } else {
                            yyerror("(sem) atributo <" + $2 + "> jah declarado no escopo");
                          }
                      }
                      else {
                          dec = new TS_entry((String)$2, (TS_entry)$1, ClasseID.VarLocal);
                          if(classeAtual.pesquisaAtributo(dec.getId()) == null && metodoAtual.pesquisaVarLocal(dec.getId()) == null) {
                            metodoAtual.addVarLocal(dec);
                          } else {
                            yyerror("(sem) variavel <" + $2 + "> jah declarada no escopo");
                          }
                      }
                      tipoAtual = (TS_entry)$1;
                      
                } lid ';' ;
 
 lid: ',' ID {        TS_entry dec;
                      if(metodoAtual == null) {
                          dec = new TS_entry((String)$2, tipoAtual, ClasseID.Atributo);

                          if(classeAtual.pesquisaAtributo(dec.getId()) == null) {
                            classeAtual.addAtributo(dec);
                          } else {
                            yyerror("(sem) atributo <" + $2 + "> jah declarado no escopo");
                          }
                      }
                      else {
                          dec = new TS_entry((String)$2, tipoAtual, ClasseID.VarLocal);
                          if(classeAtual.pesquisaAtributo(dec.getId()) == null && metodoAtual.pesquisaVarLocal(dec.getId()) == null && metodoAtual.pesquisaParametro(dec.getId()) == null) {
                            metodoAtual.addVarLocal(dec);
                          } else {
                            yyerror("(sem) variavel <" + $2 + "> jah declarada no escopo");
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
                if(!(((String)$1).equals(classeAtual.getId()))) {
                      yyerror("(sem) metodo nao construtor <" + $1 + "> sem modificador de tipo");
                } else {
                  metodoAtual = new TS_entry((String)$1, Tp_CONSTRUTOR, ClasseID.Metodo);
                  currClasse = ClasseID.Metodo;

                }} '(' lparam ')' ldecl '{' corpomet '}' {currClasse = ClasseID.Classe;
                                                          if(classeAtual.pesquisaMetodo(metodoAtual.getAssinatura()) == null) {
                                                            classeAtual.addMetodo(metodoAtual);
                                                          } else {
                                                            yyerror("(sem) metodo <" + $1 + "> com assinatura repetida na classe");
                                                          }
                                                          metodoAtual = null;} ;
  
 metnormal: tipo ID {
                  if(((String)$2).equals("main")) {
                    yyerror("(sem) metodo main deve ser void");
                  } else {
                    metodoAtual = new TS_entry((String)$2, (TS_entry)$1, ClasseID.Metodo);
                    currClasse = ClasseID.Metodo;
                  }

            } '('lparam')' ldecl '{' corpomet return '}' {currClasse = ClasseID.Classe;
                                                          if(metodoAtual != null) {
                                                            if(classeAtual.pesquisaMetodo(metodoAtual.getAssinatura()) == null) {
                                                              classeAtual.addMetodo(metodoAtual);
                                                            } else {
                                                              yyerror("(sem) metodo <" + $2 + "> com assinatura repetida na classe");
                                                            }
                                                          }                                                          
                                                          metodoAtual = null;} ;
 
 metvoid: VOID ID {
                  metodoAtual = new TS_entry((String)$2, Tp_VOID, ClasseID.Metodo);
                  currClasse = ClasseID.Metodo;

            } '(' lparam ')' ldecl '{' corpomet '}' {currClasse = ClasseID.Classe;
                                                          if(classeAtual.pesquisaMetodo(metodoAtual.getAssinatura()) == null) {
                                                            if(metodoAtual.getAssinatura().contains("main") && metodoAtual.getAssinatura().length() > 4) {
                                                              yyerror("(sem) metodo main nao pode ter parametros");
                                                            } else {
                                                              classeAtual.addMetodo(metodoAtual);
                                                            }                                                            
                                                          } else {
                                                            yyerror("(sem) metodo <" + $2 + "> com assinatura repetida na classe");
                                                          }
                                                          metodoAtual = null;} ;
 
 lparam: param sublparam
 	|
	;

 param: tipo ID {
          if(metodoAtual.parametroRepetido((String)$2))
          {
              yyerror("(sem) parametro repetido <" + $2 + "> no metodo <" + metodoAtual.getId() + ">");
          }
          else
          {
            assinaturaAtual += " " + ((TS_entry)$1).getTipoStr();
            metodoAtual.addParametro(new TS_entry((String)$2, (TS_entry)$1, ClasseID.Parametro));
          }
        } ;
 
 sublparam: ',' param sublparam
	|
	;

  lparamchamada: exp { assinaturaAtual += " " + ((TS_entry)$1).getTipoStr();
  } sublparamchamada
 	|
	;
 
 sublparamchamada: ',' exp { assinaturaAtual += " " + ((TS_entry)$2).getTipoStr();
  } sublparamchamada
	|
	;
	
 return: RETURN exp {
            if((TS_entry)$2 != metodoAtual.getTipo())
            {
              yyerror("(sem) tipo de retorno <" + ((TS_entry)$2).getTipoStr() + "> incompativel com metodo <" + metodoAtual.getId() + ">");
            } 
         }  ';' ;

 
 
exp: exp '+' exp { $$ = validaTipo('+', (TS_entry)$1, (TS_entry)$3); }
    | exp '*' exp { $$ = validaTipo('*', (TS_entry)$1, (TS_entry)$3); }
    | exp '-' exp { $$ = validaTipo('-', (TS_entry)$1, (TS_entry)$3); }
    | exp '/' exp { $$ = validaTipo('/', (TS_entry)$1, (TS_entry)$3); }
   	| exp '>' exp { $$ = validaTipo('>', (TS_entry)$1, (TS_entry)$3);}
    | exp '<' exp { $$ = validaTipo('<', (TS_entry)$1, (TS_entry)$3);}
    | exp '%' exp { $$ = validaTipo('%', (TS_entry)$1, (TS_entry)$3);}
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
    | ID       { TS_entry nodo;
                  nodo = classeAtual.pesquisaAtributo((String)$1);
                  if(nodo == null && metodoAtual != null)
                  {
                      nodo = metodoAtual.pesquisaVarLocal((String)$1);
                      if(nodo == null)
                      {
                          nodo = metodoAtual.pesquisaParametro((String)$1);
                          if(nodo == null) {
                            yyerror("(sem) var ou atributo <" + $1 + "> nao declarado ou fora do escopo");
                            $$ = Tp_ERRO;
                          }
                      }
                  }
                  if(nodo != null) {
                    $$ = nodo.getTipo();
                  }
                }     
     | chamaMetodo { $$ = $1; }
     | NEW chamaConstrutor {if($2 != Tp_CONSTRUTOR) {
        yyerror("(sem) somente construtores podem ser chamados com new");
          $$ = Tp_ERRO;
     } else {
       $$ = classeConstrutor;
       classeConstrutor = null;
     }}
    ;
 
 corpomet: lcmdloop ;
 
 cmd: atrib ';'
 	| escrita
 	| leia
 	| if
 	| while
 	| for
	;
 	
 atrib: ID  '=' exp {
         TS_entry nodo;
          nodo = classeAtual.pesquisaAtributo((String)$1);
          if(nodo == null && metodoAtual != null)
          {
              nodo = metodoAtual.pesquisaVarLocal((String)$1);
              if(nodo == null)
              {
                  nodo = metodoAtual.pesquisaParametro((String)$1);
                  if(nodo == null) {
                    yyerror("(sem) var ou atributo <" + $1 + "> nao declarado ou fora do escopo");
                  }
              }
          }

          if(nodo != null) {
            validaTipo('=', nodo.getTipo(), (TS_entry)$3);
          }
        } ;

 escrita: ESCREVA restoEscrita ;

 restoEscrita: 
               exp contescrita ';' { if($1 != Tp_LITERAL) yyerror("(sem) primeiro operador da escrita precisa ser do tipo string "); }
 
 contescrita: ',' exp {
   if ($2 != Tp_BOOLEAN && $2 != Tp_INT && $2 != Tp_DOUBLE && $2 != Tp_LITERAL ){
      yyerror("tipo da expressao deve ser um tipo base: int, double, boolean ou string");}
    }
	|
	;

 leia: LEIA ID {
          TS_entry nodo;
          nodo = classeAtual.pesquisaAtributo((String)$2);
          if(nodo == null && metodoAtual != null)
          {
              nodo = metodoAtual.pesquisaVarLocal((String)$2);
              if(nodo == null)
              {
                  nodo = metodoAtual.pesquisaParametro((String)$2);
                  if(nodo == null) {
                    yyerror("(sem) var ou atributo <" + $2 + "> nao declarado ou fora do escopo");
                  }
              }
          }
        } ';' ;
 
 if: IF exp {
          if((TS_entry)$2 != Tp_BOOLEAN) {
              yyerror("(sem) expressao nao booleana em condicao de IF");
          }
         } ':' lcmdloop else ;
 
 else: ELSE ':' lcmdloop ENDIF ;
 	| ENDIF ;
	;

 while: WHILE exp {
          if((TS_entry)$2 != Tp_BOOLEAN) {
              yyerror("(sem) expressao nao booleana em condicao de WHILE");
          }
         } ':' {loopLevel++;} lcmdloop ENDWHILE {loopLevel--;} ;
 
 for: FOR atrib  ';' exp {
          if((TS_entry)$4 != Tp_BOOLEAN) {
              yyerror("(sem) expressao nao booleana em condicao de FOR");
          }
         } ';' atrib ':' {loopLevel++;} lcmdloop ENDFOR {loopLevel--;} ;


 break: BREAK ';' ;
 
 lcmdloop: cmd lcmdloop
          | break lcmdloop; {if(loopLevel == 0) yyerror("(sem) break fora de loop");}
          | 
          ;
 
 chamaMetodo: ID '.' ID {assinaturaAtual = (String)$3;} '(' lparamchamada')' {
          TS_entry nodo;
          nodo = classeAtual.pesquisaAtributo((String)$1);
          if(nodo == null && metodoAtual != null)
          {
              nodo = metodoAtual.pesquisaVarLocal((String)$1);
              if(nodo == null)
              {
                  nodo = metodoAtual.pesquisaParametro((String)$1);
                  if(nodo == null) {
                    yyerror("(sem) objeto <" + $1 + "> nao declarado ou fora do escopo");
                    $$ = Tp_ERRO;
                  }
              }
          }

          if(nodo != null) {
            if(nodo.getTipo().getTipo() == Tp_OBJETO){
              if (nodo.getTipo().pesquisaMetodoHeranca(assinaturaAtual) == null) {
                yyerror("(sem) a classe do objeto <" + $1 + "> nao possui o metodo <" + $3 + "> com esses parametros");
                $$ = Tp_ERRO;
              } else {
                $$ = nodo.getTipo().pesquisaMetodoHeranca(assinaturaAtual).getTipo();
              }
            }
            else
            {
              yyerror("(sem) objeto <" + $1 + "> nao eh um objeto");
              $$ = Tp_ERRO;
            }
          } else {
            $$ = Tp_ERRO;
          }
   };

 chamaConstrutor: ID {assinaturaAtual = (String)$1;} '(' lparamchamada ')' {
   TS_entry classe = ts.pesquisa($1);
   if(classe == null) {
        yyerror("(sem) chamada de construtor <" + $1 + "> para classe nao definida");
        $$ = Tp_ERRO;
   } else {
     TS_entry met = classe.pesquisaMetodoHeranca(assinaturaAtual);
     if(met == null) {
       yyerror("(sem) construtor <" + $1 + "> com assinatura nao definida");
        $$ = Tp_ERRO;
     } else if(met.getTipo() != Tp_CONSTRUTOR){
       yyerror("(sem) construtor <" + $1 + "> com assinatura nao definida");
        $$ = Tp_ERRO;
     } else {
       $$ = Tp_CONSTRUTOR;
       classeConstrutor = classe;
     }
   }
 };
 
 tipo: INT {$$ = Tp_INT;}
 	| DOUBLE {$$ = Tp_DOUBLE;}
 	| STRING {$$ = Tp_LITERAL;}
 	| BOOLEAN {$$ = Tp_BOOLEAN;}
 	| ID {
     TS_entry nodo = ts.pesquisa((String)$1);
     if(nodo == null) {
       yyerror("(sem) tipo <" + $1 + "> nao definido");
       $$ = Tp_ERRO;
     }
     else {
       $$ = nodo;
     }
   }
	;
%%

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

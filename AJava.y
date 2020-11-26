	
%{
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
%}
 

%token ID, INT, FLOAT, BOOL, NUM, LIT, VOID, MAIN, READ, WRITE, IF, ELSE
%token WHILE,TRUE, FALSE, IF, ELSE
%token EQ, LEQ, GEQ, NEQ 
%token AND, OR

%right '='
%left OR
%left AND
%left  '>' '<' EQ LEQ GEQ NEQ
%left '+' '-'
%left '*' '/' '%'
%left '!' 

%type <sval> ID
%type <sval> LIT
%type <sval> NUM
%type <ival> type


%%

 prog: lclasse 
 	|
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
 
 exp: exp ob exp
 	| TRUE
	| FALSE
	| ID INCREMENT
	| ID DECREMENT
	| '(' exp ')'
	| NUM
	| NUMDOUBLE
	| LIT
	| chamaMetodo
	| NEW ID '(' lparam ')'
	| ID
	;
	
 
 ob: '+'
    |'*'
    |'-'
    |'/'
    |'>'
    |'<'
    |AND
    |OR
    |LEQ
    |GEQ
    |EQ
    |NEQ
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
 	| 
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
   

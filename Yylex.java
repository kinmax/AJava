/* The following code was generated by JFlex 1.4.3 on 29/11/20 14:22 */

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 29/11/20 14:22 from the specification file
 * <tt>AJava.flex</tt>
 */
class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\21\1\3\2\0\1\4\22\0\1\21\1\30\1\64\1\0"+
    "\1\5\1\22\1\31\1\0\1\22\1\22\1\23\1\22\1\22\1\22"+
    "\1\2\1\24\12\1\1\22\1\22\1\26\1\27\1\25\2\0\1\10"+
    "\1\63\1\11\1\63\1\12\1\16\2\63\1\60\2\63\1\61\1\17"+
    "\1\15\1\14\2\63\1\7\1\20\1\6\2\63\1\56\3\63\1\22"+
    "\1\0\1\22\1\0\1\13\1\0\1\44\1\41\1\54\1\36\1\43"+
    "\1\57\1\47\1\55\1\33\1\63\1\51\1\42\1\63\1\34\1\37"+
    "\1\53\1\63\1\46\1\45\1\35\1\40\1\50\1\52\1\62\2\63"+
    "\1\22\1\32\1\22\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\2\4\1\1\2\5\1\6"+
    "\5\3\2\1\16\5\1\1\3\0\1\5\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\5\1\17"+
    "\22\5\1\0\1\20\2\0\1\5\2\0\1\21\1\22"+
    "\16\5\1\23\2\5\1\24\2\0\1\5\1\10\1\25"+
    "\6\5\1\26\3\5\1\27\5\5\1\30\2\0\3\5"+
    "\1\31\2\5\1\32\3\5\1\33\2\5\1\34\1\35"+
    "\2\0\1\5\1\36\1\5\1\37\2\5\1\40\1\41"+
    "\1\42\1\5\2\0\1\43\1\44\1\5\1\45\1\46"+
    "\2\0\1\47\1\50\2\0\1\51\1\52";

  private static int [] zzUnpackAction() {
    int [] result = new int[157];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\65\0\152\0\65\0\65\0\237\0\324\0\u0109"+
    "\0\u013e\0\u0173\0\u01a8\0\u01dd\0\u0212\0\u0247\0\u027c\0\u02b1"+
    "\0\u02e6\0\u031b\0\u0350\0\u0385\0\u03ba\0\u03ef\0\u0424\0\u0459"+
    "\0\u048e\0\u04c3\0\u04f8\0\u052d\0\u0562\0\u0597\0\u05cc\0\u0601"+
    "\0\u0636\0\u066b\0\u06a0\0\u06d5\0\u070a\0\u073f\0\65\0\65"+
    "\0\65\0\65\0\65\0\65\0\u0774\0\u0109\0\u07a9\0\u07de"+
    "\0\u0813\0\u0848\0\u087d\0\u08b2\0\u08e7\0\u091c\0\u0951\0\u0986"+
    "\0\u09bb\0\u09f0\0\u0a25\0\u0a5a\0\u0a8f\0\u0ac4\0\u0af9\0\u0b2e"+
    "\0\u0b63\0\u0636\0\u0b98\0\u0bcd\0\u0c02\0\u070a\0\u0c37\0\u0109"+
    "\0\u0109\0\u0c6c\0\u0ca1\0\u0cd6\0\u0d0b\0\u0d40\0\u0d75\0\u0daa"+
    "\0\u0ddf\0\u0e14\0\u0e49\0\u0e7e\0\u0eb3\0\u0ee8\0\u0f1d\0\u0109"+
    "\0\u0f52\0\u0f87\0\u0b63\0\u0fbc\0\u0ff1\0\u1026\0\65\0\u0109"+
    "\0\u105b\0\u1090\0\u10c5\0\u10fa\0\u112f\0\u1164\0\u0109\0\u1199"+
    "\0\u11ce\0\u1203\0\u0109\0\u1238\0\u126d\0\u12a2\0\u12d7\0\u130c"+
    "\0\u0109\0\u1341\0\u1376\0\u13ab\0\u13e0\0\u1415\0\u0109\0\u144a"+
    "\0\u147f\0\u0109\0\u14b4\0\u14e9\0\u151e\0\u0109\0\u1553\0\u1588"+
    "\0\u0109\0\u0109\0\u15bd\0\u15f2\0\u1627\0\u0109\0\u165c\0\u0109"+
    "\0\u1691\0\u16c6\0\u0109\0\u0109\0\u0109\0\u16fb\0\u1730\0\u1765"+
    "\0\u0109\0\u0109\0\u179a\0\u0109\0\u0109\0\u17cf\0\u1804\0\u0109"+
    "\0\65\0\u1839\0\u186e\0\65\0\65";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[157];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\4\10\1\11"+
    "\1\2\5\10\1\12\2\4\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\2\10"+
    "\1\26\1\10\1\27\1\10\1\30\1\31\1\10\1\32"+
    "\1\10\1\33\1\34\1\35\2\10\1\36\1\10\1\37"+
    "\2\10\1\40\66\0\1\3\1\41\65\0\1\5\67\0"+
    "\1\42\10\0\1\43\46\0\1\10\4\0\5\10\1\0"+
    "\5\10\12\0\31\10\2\0\1\10\4\0\5\10\1\0"+
    "\5\10\12\0\12\10\1\44\16\10\22\0\1\12\66\0"+
    "\1\45\1\46\67\0\1\47\64\0\1\50\64\0\1\51"+
    "\64\0\1\52\66\0\1\53\65\0\1\54\33\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\1\10\1\55\22\10"+
    "\1\56\4\10\2\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\10\10\1\57\20\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\13\10\1\60\15\10\2\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\4\10\1\61\24\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\4\10"+
    "\1\62\6\10\1\63\15\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\1\10\1\64\5\10\1\65\17\10"+
    "\1\66\1\10\2\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\2\10\1\67\26\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\10\10\1\70\20\10\2\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\4\10\1\71\24\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\22\10"+
    "\1\72\6\10\2\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\5\10\1\73\5\10\1\74\15\10\2\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\7\10\1\75\21\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\4\10"+
    "\1\76\4\10\1\77\17\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\10\10\1\100\20\10\1\0\3\101"+
    "\1\0\61\101\1\0\1\102\72\0\1\103\71\0\1\104"+
    "\51\0\1\10\4\0\5\10\1\0\5\10\12\0\21\10"+
    "\1\105\7\10\1\0\23\106\1\107\41\106\3\46\1\0"+
    "\61\46\1\0\1\10\4\0\5\10\1\0\5\10\12\0"+
    "\2\10\1\110\26\10\2\0\1\10\4\0\5\10\1\0"+
    "\5\10\12\0\17\10\1\111\11\10\2\0\1\10\4\0"+
    "\5\10\1\0\5\10\12\0\5\10\1\112\23\10\2\0"+
    "\1\10\4\0\5\10\1\0\5\10\12\0\5\10\1\113"+
    "\23\10\2\0\1\10\4\0\5\10\1\0\5\10\12\0"+
    "\4\10\1\114\24\10\2\0\1\10\4\0\5\10\1\0"+
    "\5\10\12\0\10\10\1\115\20\10\2\0\1\10\4\0"+
    "\5\10\1\0\5\10\12\0\3\10\1\116\25\10\2\0"+
    "\1\10\4\0\5\10\1\0\5\10\12\0\12\10\1\117"+
    "\16\10\2\0\1\10\4\0\5\10\1\0\5\10\12\0"+
    "\2\10\1\120\26\10\2\0\1\10\4\0\5\10\1\0"+
    "\5\10\12\0\13\10\1\121\15\10\2\0\1\10\4\0"+
    "\5\10\1\0\5\10\12\0\2\10\1\122\26\10\2\0"+
    "\1\10\4\0\5\10\1\0\5\10\12\0\1\123\30\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\1\124"+
    "\30\10\2\0\1\10\4\0\5\10\1\0\5\10\12\0"+
    "\6\10\1\125\22\10\2\0\1\10\4\0\5\10\1\0"+
    "\5\10\12\0\1\126\30\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\11\10\1\127\17\10\2\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\13\10\1\130\15\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\7\10"+
    "\1\131\21\10\2\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\1\132\30\10\1\0\3\101\1\0\60\101\1\133"+
    "\10\0\1\134\74\0\1\135\45\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\13\10\1\136\15\10\1\0\23\106"+
    "\1\107\1\137\40\106\1\0\1\10\4\0\5\10\1\0"+
    "\5\10\12\0\10\10\1\140\20\10\2\0\1\10\4\0"+
    "\5\10\1\0\5\10\12\0\6\10\1\141\22\10\2\0"+
    "\1\10\4\0\5\10\1\0\5\10\12\0\7\10\1\142"+
    "\21\10\2\0\1\10\4\0\5\10\1\0\5\10\12\0"+
    "\11\10\1\143\17\10\2\0\1\10\4\0\5\10\1\0"+
    "\2\10\1\144\2\10\12\0\23\10\1\145\1\10\1\146"+
    "\3\10\2\0\1\10\4\0\5\10\1\0\5\10\12\0"+
    "\10\10\1\147\20\10\2\0\1\10\4\0\5\10\1\0"+
    "\5\10\12\0\10\10\1\150\20\10\2\0\1\10\4\0"+
    "\5\10\1\0\5\10\12\0\1\151\30\10\2\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\5\10\1\152\23\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\3\10"+
    "\1\153\25\10\2\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\7\10\1\154\21\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\7\10\1\155\21\10\2\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\15\10\1\156\13\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\12\10"+
    "\1\157\16\10\2\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\12\10\1\160\16\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\11\10\1\161\17\10\12\0\1\162"+
    "\61\0\1\163\57\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\10\10\1\164\20\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\7\10\1\165\21\10\2\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\10\10\1\166\20\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\16\10"+
    "\1\167\12\10\2\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\4\10\1\170\24\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\22\10\1\171\6\10\2\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\24\10\1\172\4\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\1\10"+
    "\1\173\27\10\2\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\1\10\1\174\27\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\13\10\1\175\15\10\2\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\10\10\1\176\20\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\1\177"+
    "\30\10\2\0\1\10\4\0\5\10\1\0\5\10\12\0"+
    "\11\10\1\200\17\10\2\0\1\10\4\0\5\10\1\0"+
    "\5\10\12\0\12\10\1\201\16\10\2\0\1\10\4\0"+
    "\5\10\1\0\5\10\12\0\10\10\1\202\20\10\13\0"+
    "\1\203\61\0\1\204\56\0\1\10\4\0\5\10\1\0"+
    "\5\10\12\0\15\10\1\205\13\10\2\0\1\10\4\0"+
    "\5\10\1\0\5\10\12\0\10\10\1\206\20\10\2\0"+
    "\1\10\4\0\5\10\1\0\5\10\12\0\11\10\1\207"+
    "\17\10\2\0\1\10\4\0\5\10\1\0\5\10\12\0"+
    "\13\10\1\210\15\10\2\0\1\10\4\0\5\10\1\0"+
    "\5\10\12\0\1\211\30\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\3\10\1\212\25\10\2\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\14\10\1\213\14\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\1\10"+
    "\1\214\27\10\2\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\21\10\1\215\7\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\2\10\1\216\26\10\14\0\1\217"+
    "\61\0\1\220\55\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\11\10\1\221\17\10\2\0\1\10\4\0\5\10"+
    "\1\0\5\10\12\0\1\10\1\222\27\10\2\0\1\10"+
    "\4\0\5\10\1\0\5\10\12\0\7\10\1\223\21\10"+
    "\2\0\1\10\4\0\5\10\1\0\5\10\12\0\12\10"+
    "\1\224\16\10\2\0\1\10\4\0\5\10\1\0\5\10"+
    "\12\0\10\10\1\225\20\10\15\0\1\226\63\0\1\227"+
    "\52\0\1\10\4\0\5\10\1\0\5\10\12\0\10\10"+
    "\1\230\20\10\16\0\1\231\1\232\54\0\1\233\74\0"+
    "\1\234\66\0\1\235\44\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6307];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\2\11\33\1\3\0\3\1\6\11"+
    "\24\1\1\0\1\1\2\0\1\1\2\0\24\1\2\0"+
    "\1\1\1\11\22\1\2\0\17\1\2\0\12\1\2\0"+
    "\5\1\2\0\1\1\1\11\2\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[157];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
    yyline = 1;
  }


  public int getLine() {
      return yyline;
  }



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 168) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 28: 
          { return Parser.CLASS;
          }
        case 43: break;
        case 21: 
          { return Parser.TRUE;
          }
        case 44: break;
        case 5: 
          { yyparser.yylval = new ParserVal(yytext());
            return Parser.ID;
          }
        case 45: break;
        case 39: 
          { return Parser.ENDWHILE;
          }
        case 46: break;
        case 13: 
          { return Parser.AND;
          }
        case 47: break;
        case 27: 
          { return Parser.WHILE;
          }
        case 48: break;
        case 33: 
          { return Parser.RETURN;
          }
        case 49: break;
        case 26: 
          { return Parser.ENDIF;
          }
        case 50: break;
        case 17: 
          { return Parser.INT;
          }
        case 51: break;
        case 3: 
          { return (int) yycharat(0);
          }
        case 52: break;
        case 40: 
          { yyparser.setDebug(true);
          }
        case 53: break;
        case 35: 
          { return Parser.ESCREVA;
          }
        case 54: break;
        case 25: 
          { return Parser.BREAK;
          }
        case 55: break;
        case 12: 
          { return Parser.NEQ;
          }
        case 56: break;
        case 4: 
          { yyline++;
          }
        case 57: break;
        case 32: 
          { return Parser.STRING;
          }
        case 58: break;
        case 34: 
          { return Parser.PUBLIC;
          }
        case 59: break;
        case 18: 
          { return Parser.NEW;
          }
        case 60: break;
        case 11: 
          { return Parser.EQ;
          }
        case 61: break;
        case 38: 
          { return Parser.PRIVATE;
          }
        case 62: break;
        case 9: 
          { return Parser.GEQ;
          }
        case 63: break;
        case 16: 
          { yyparser.yylval = new ParserVal(yytext()); 
         return Parser.NUMDOUBLE;
          }
        case 64: break;
        case 7: 
          { System.err.println("Error: unterminated comment"); return -1;
          }
        case 65: break;
        case 1: 
          { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1;
          }
        case 66: break;
        case 10: 
          { return Parser.LEQ;
          }
        case 67: break;
        case 15: 
          { return Parser.IF;
          }
        case 68: break;
        case 37: 
          { return Parser.EXTENDS;
          }
        case 69: break;
        case 19: 
          { return Parser.FOR;
          }
        case 70: break;
        case 41: 
          { yyparser.setDebug(false);
          }
        case 71: break;
        case 8: 
          { /* DO NOTHING */
          }
        case 72: break;
        case 24: 
          { return Parser.LEIA;
          }
        case 73: break;
        case 20: 
          { yyparser.yylval = new ParserVal(yytext().substring(1, yylength() -1));
	     return Parser.LIT;
          }
        case 74: break;
        case 29: 
          { return Parser.FALSE;
          }
        case 75: break;
        case 2: 
          { yyparser.yylval = new ParserVal(yytext()); 
         return Parser.NUM;
          }
        case 76: break;
        case 42: 
          { yyparser.listarTS();
          }
        case 77: break;
        case 14: 
          { return Parser.OR;
          }
        case 78: break;
        case 30: 
          { return Parser.DOUBLE;
          }
        case 79: break;
        case 22: 
          { return Parser.ELSE;
          }
        case 80: break;
        case 36: 
          { return Parser.BOOLEAN;
          }
        case 81: break;
        case 23: 
          { return Parser.VOID;
          }
        case 82: break;
        case 31: 
          { return Parser.ENDFOR;
          }
        case 83: break;
        case 6: 
          { 
          }
        case 84: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}

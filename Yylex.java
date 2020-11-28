/* The following code was generated by JFlex 1.4.3 on 28/11/20 17:32 */

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 28/11/20 17:32 from the specification file
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
    "\11\0\1\21\1\3\2\0\1\4\22\0\1\21\1\27\1\64\1\0"+
    "\1\5\1\22\1\30\1\0\1\22\1\22\1\22\1\22\1\22\1\22"+
    "\1\2\1\23\12\1\1\22\1\22\1\25\1\26\1\24\2\0\1\10"+
    "\1\63\1\11\1\63\1\12\1\16\2\63\1\60\2\63\1\61\1\17"+
    "\1\15\1\14\2\63\1\7\1\20\1\6\2\63\1\56\3\63\1\22"+
    "\1\0\1\22\1\0\1\13\1\0\1\43\1\40\1\54\1\35\1\42"+
    "\1\57\1\46\1\55\1\32\1\63\1\51\1\41\1\50\1\33\1\36"+
    "\1\53\1\63\1\45\1\44\1\34\1\37\1\47\1\52\1\62\2\63"+
    "\1\22\1\31\1\22\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\2\3\1\0\2\4\1\5\5\2"+
    "\2\0\17\4\4\0\1\4\1\0\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\4\1\14\23\4\1\0\1\15"+
    "\2\0\1\4\2\16\1\17\1\20\17\4\1\21\2\4"+
    "\1\22\2\0\1\4\1\23\6\4\1\24\3\4\1\25"+
    "\1\26\5\4\1\27\2\0\3\4\1\30\2\4\1\31"+
    "\3\4\1\32\2\4\1\33\1\34\2\0\1\4\1\35"+
    "\1\4\1\36\2\4\1\37\1\40\1\41\1\4\2\0"+
    "\1\42\1\43\1\4\1\44\1\45\2\0\1\46\1\47"+
    "\2\0\1\50\1\51";

  private static int [] zzUnpackAction() {
    int [] result = new int[158];
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
    "\0\0\0\65\0\152\0\152\0\237\0\324\0\u0109\0\u013e"+
    "\0\u0173\0\u01a8\0\u01dd\0\u0212\0\u0247\0\u027c\0\u02b1\0\u02e6"+
    "\0\u031b\0\u0350\0\u0385\0\u03ba\0\u03ef\0\u0424\0\u0459\0\u048e"+
    "\0\u04c3\0\u04f8\0\u052d\0\u0562\0\u0597\0\u05cc\0\u0601\0\u0636"+
    "\0\u066b\0\u06a0\0\u06d5\0\u070a\0\u073f\0\152\0\152\0\152"+
    "\0\152\0\152\0\152\0\u0774\0\u0109\0\u07a9\0\u07de\0\u0813"+
    "\0\u0848\0\u087d\0\u08b2\0\u08e7\0\u091c\0\u0951\0\u0986\0\u09bb"+
    "\0\u09f0\0\u0a25\0\u0a5a\0\u0a8f\0\u0ac4\0\u0af9\0\u0b2e\0\u0b63"+
    "\0\u0b98\0\u066b\0\u0bcd\0\u0c02\0\u0c37\0\u073f\0\152\0\u0109"+
    "\0\u0109\0\u0c6c\0\u0ca1\0\u0cd6\0\u0d0b\0\u0d40\0\u0d75\0\u0daa"+
    "\0\u0ddf\0\u0e14\0\u0e49\0\u0e7e\0\u0eb3\0\u0ee8\0\u0f1d\0\u0f52"+
    "\0\u0109\0\u0f87\0\u0fbc\0\u0b98\0\u0ff1\0\u1026\0\u105b\0\u0109"+
    "\0\u1090\0\u10c5\0\u10fa\0\u112f\0\u1164\0\u1199\0\u0109\0\u11ce"+
    "\0\u1203\0\u1238\0\u0109\0\u0109\0\u126d\0\u12a2\0\u12d7\0\u130c"+
    "\0\u1341\0\u0109\0\u1376\0\u13ab\0\u13e0\0\u1415\0\u144a\0\u0109"+
    "\0\u147f\0\u14b4\0\u0109\0\u14e9\0\u151e\0\u1553\0\u0109\0\u1588"+
    "\0\u15bd\0\u0109\0\u0109\0\u15f2\0\u1627\0\u165c\0\u0109\0\u1691"+
    "\0\u0109\0\u16c6\0\u16fb\0\u0109\0\u0109\0\u0109\0\u1730\0\u1765"+
    "\0\u179a\0\u0109\0\u0109\0\u17cf\0\u0109\0\u0109\0\u1804\0\u1839"+
    "\0\u0109\0\152\0\u186e\0\u18a3\0\152\0\152";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[158];
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
    "\1\0\1\2\1\3\1\4\1\5\1\6\4\7\1\10"+
    "\1\0\5\7\1\11\1\3\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\2\7"+
    "\1\25\1\7\1\26\1\7\1\27\1\30\1\7\1\31"+
    "\1\32\1\7\1\33\1\34\1\35\2\7\1\36\1\7"+
    "\1\37\2\7\1\40\1\0\1\2\1\41\152\0\1\4"+
    "\67\0\1\42\10\0\1\43\46\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\32\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\12\7\1\44\17\7\22\0\1\11"+
    "\66\0\1\45\67\0\1\46\64\0\1\47\64\0\1\50"+
    "\64\0\1\51\66\0\1\52\65\0\1\53\34\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\1\7\1\54\23\7"+
    "\1\55\4\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\10\7\1\56\21\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\13\7\1\57\16\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\4\7\1\60\25\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\4\7"+
    "\1\61\6\7\1\62\16\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\1\7\1\63\5\7\1\64\20\7"+
    "\1\65\1\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\2\7\1\66\27\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\10\7\1\67\21\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\4\7\1\70\25\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\11\7"+
    "\1\71\20\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\23\7\1\72\6\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\5\7\1\73\5\7\1\74\16\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\7\7"+
    "\1\75\22\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\4\7\1\76\4\7\1\77\20\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\10\7\1\100\21\7"+
    "\1\0\3\101\1\0\61\101\1\0\1\102\72\0\1\103"+
    "\71\0\1\104\51\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\22\7\1\105\7\7\1\0\3\106\1\107\61\106"+
    "\1\0\1\7\4\0\5\7\1\0\5\7\11\0\2\7"+
    "\1\110\27\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\20\7\1\111\11\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\5\7\1\112\24\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\5\7\1\113\24\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\4\7"+
    "\1\114\25\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\10\7\1\115\21\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\3\7\1\116\26\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\12\7\1\117\17\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\2\7"+
    "\1\120\27\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\13\7\1\121\16\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\2\7\1\122\27\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\1\123\31\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\1\124\31\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\1\125"+
    "\31\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\6\7\1\126\23\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\1\127\31\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\11\7\1\130\20\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\13\7\1\131\16\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\7\7"+
    "\1\132\22\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\1\133\31\7\1\0\3\101\1\0\60\101\1\134"+
    "\10\0\1\135\74\0\1\136\45\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\13\7\1\137\16\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\10\7\1\140\21\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\6\7"+
    "\1\141\23\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\7\7\1\142\22\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\11\7\1\143\20\7\2\0\1\7"+
    "\4\0\5\7\1\0\2\7\1\144\2\7\11\0\24\7"+
    "\1\145\1\7\1\146\3\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\10\7\1\147\21\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\10\7\1\150\21\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\1\151"+
    "\31\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\5\7\1\152\24\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\3\7\1\153\26\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\1\7\1\154\30\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\7\7\1\155"+
    "\22\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\7\7\1\156\22\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\15\7\1\157\14\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\12\7\1\160\17\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\12\7\1\161"+
    "\17\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\11\7\1\162\20\7\12\0\1\163\61\0\1\164\57\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\10\7\1\165"+
    "\21\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\7\7\1\166\22\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\10\7\1\167\21\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\17\7\1\170\12\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\4\7\1\171"+
    "\25\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\23\7\1\172\6\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\25\7\1\173\4\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\1\7\1\174\30\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\1\7\1\175"+
    "\30\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\13\7\1\176\16\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\10\7\1\177\21\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\1\200\31\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\11\7\1\201\20\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\12\7"+
    "\1\202\17\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\10\7\1\203\21\7\13\0\1\204\61\0\1\205"+
    "\56\0\1\7\4\0\5\7\1\0\5\7\11\0\15\7"+
    "\1\206\14\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\10\7\1\207\21\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\11\7\1\210\20\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\13\7\1\211\16\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\1\212"+
    "\31\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\3\7\1\213\26\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\14\7\1\214\15\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\1\7\1\215\30\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\22\7\1\216"+
    "\7\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\2\7\1\217\27\7\14\0\1\220\61\0\1\221\55\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\11\7\1\222"+
    "\20\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\1\7\1\223\30\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\7\7\1\224\22\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\12\7\1\225\17\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\10\7\1\226"+
    "\21\7\15\0\1\227\63\0\1\230\52\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\10\7\1\231\21\7\16\0"+
    "\1\232\1\233\54\0\1\234\74\0\1\235\66\0\1\236"+
    "\44\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6360];
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
    "\1\0\1\1\2\11\1\1\1\0\10\1\2\0\17\1"+
    "\4\0\1\1\1\0\6\11\25\1\1\0\1\1\2\0"+
    "\2\1\1\11\25\1\2\0\24\1\2\0\17\1\2\0"+
    "\12\1\2\0\5\1\2\0\1\1\1\11\2\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[158];
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
        case 27: 
          { return Parser.CLASS;
          }
        case 42: break;
        case 19: 
          { return Parser.TRUE;
          }
        case 43: break;
        case 4: 
          { yyparser.yylval = new ParserVal(yytext());
            return Parser.ID;
          }
        case 44: break;
        case 38: 
          { return Parser.ENDWHILE;
          }
        case 45: break;
        case 10: 
          { return Parser.AND;
          }
        case 46: break;
        case 26: 
          { return Parser.WHILE;
          }
        case 47: break;
        case 32: 
          { return Parser.RETURN;
          }
        case 48: break;
        case 25: 
          { return Parser.ENDIF;
          }
        case 49: break;
        case 15: 
          { return Parser.INT;
          }
        case 50: break;
        case 2: 
          { return (int) yycharat(0);
          }
        case 51: break;
        case 39: 
          { yyparser.setDebug(true);
          }
        case 52: break;
        case 34: 
          { return Parser.ESCREVA;
          }
        case 53: break;
        case 24: 
          { return Parser.BREAK;
          }
        case 54: break;
        case 9: 
          { return Parser.NEQ;
          }
        case 55: break;
        case 3: 
          { yyline++;
          }
        case 56: break;
        case 31: 
          { return Parser.STRING;
          }
        case 57: break;
        case 33: 
          { return Parser.PUBLIC;
          }
        case 58: break;
        case 16: 
          { return Parser.NEW;
          }
        case 59: break;
        case 8: 
          { return Parser.EQ;
          }
        case 60: break;
        case 37: 
          { return Parser.PRIVATE;
          }
        case 61: break;
        case 6: 
          { return Parser.GEQ;
          }
        case 62: break;
        case 13: 
          { yyparser.yylval = new ParserVal(yytext()); 
         return Parser.NUMDOUBLE;
          }
        case 63: break;
        case 14: 
          { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1;
          }
        case 64: break;
        case 7: 
          { return Parser.LEQ;
          }
        case 65: break;
        case 12: 
          { return Parser.IF;
          }
        case 66: break;
        case 22: 
          { return Parser.MAIN;
          }
        case 67: break;
        case 17: 
          { return Parser.FOR;
          }
        case 68: break;
        case 40: 
          { yyparser.setDebug(false);
          }
        case 69: break;
        case 23: 
          { return Parser.LEIA;
          }
        case 70: break;
        case 18: 
          { yyparser.yylval = new ParserVal(yytext().substring(1, yylength() -1));
	     return Parser.LIT;
          }
        case 71: break;
        case 28: 
          { return Parser.FALSE;
          }
        case 72: break;
        case 1: 
          { yyparser.yylval = new ParserVal(yytext()); 
         return Parser.NUM;
          }
        case 73: break;
        case 41: 
          { yyparser.listarTS();
          }
        case 74: break;
        case 36: 
          { return Parser.EXTENDS
          }
        case 75: break;
        case 11: 
          { return Parser.OR;
          }
        case 76: break;
        case 29: 
          { return Parser.DOUBLE;
          }
        case 77: break;
        case 20: 
          { return Parser.ELSE;
          }
        case 78: break;
        case 35: 
          { return Parser.BOOLEAN;
          }
        case 79: break;
        case 21: 
          { return Parser.VOID;
          }
        case 80: break;
        case 30: 
          { return Parser.ENDFOR;
          }
        case 81: break;
        case 5: 
          { 
          }
        case 82: break;
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

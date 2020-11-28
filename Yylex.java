/* The following code was generated by JFlex 1.4.3 on 27/11/2020 19:44 */

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 27/11/2020 19:44 from the specification file
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
    "\11\0\1\21\1\3\2\0\1\4\22\0\1\21\1\27\1\63\1\0"+
    "\1\5\1\22\1\30\1\0\1\22\1\22\1\22\1\22\1\22\1\22"+
    "\1\2\1\23\12\1\1\22\1\22\1\25\1\26\1\24\2\0\1\10"+
    "\1\62\1\11\1\62\1\12\1\16\2\62\1\60\2\62\1\61\1\17"+
    "\1\15\1\14\2\62\1\7\1\20\1\6\2\62\1\56\3\62\1\22"+
    "\1\0\1\22\1\0\1\13\1\0\1\43\1\40\1\54\1\35\1\42"+
    "\1\57\1\46\1\55\1\32\1\62\1\51\1\41\1\50\1\33\1\36"+
    "\1\53\1\62\1\45\1\44\1\34\1\37\1\47\1\52\3\62\1\22"+
    "\1\31\1\22\uff82\0";

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
    "\1\11\1\12\1\13\1\4\1\14\22\4\1\0\1\15"+
    "\2\0\1\4\2\16\1\17\1\20\16\4\1\21\2\4"+
    "\1\22\2\0\1\4\1\23\6\4\1\24\2\4\1\25"+
    "\1\26\5\4\1\27\2\0\3\4\1\30\2\4\1\31"+
    "\2\4\1\32\2\4\1\33\1\34\2\0\1\4\1\35"+
    "\1\4\1\36\1\4\1\37\1\40\1\41\1\4\2\0"+
    "\1\42\1\43\1\4\1\44\2\0\1\45\1\46\2\0"+
    "\1\47\1\50";

  private static int [] zzUnpackAction() {
    int [] result = new int[152];
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
    "\0\0\0\64\0\150\0\150\0\234\0\320\0\u0104\0\u0138"+
    "\0\u016c\0\u01a0\0\u01d4\0\u0208\0\u023c\0\u0270\0\u02a4\0\u02d8"+
    "\0\u030c\0\u0340\0\u0374\0\u03a8\0\u03dc\0\u0410\0\u0444\0\u0478"+
    "\0\u04ac\0\u04e0\0\u0514\0\u0548\0\u057c\0\u05b0\0\u05e4\0\u0618"+
    "\0\u064c\0\u0680\0\u06b4\0\u06e8\0\u071c\0\150\0\150\0\150"+
    "\0\150\0\150\0\150\0\u0750\0\u0104\0\u0784\0\u07b8\0\u07ec"+
    "\0\u0820\0\u0854\0\u0888\0\u08bc\0\u08f0\0\u0924\0\u0958\0\u098c"+
    "\0\u09c0\0\u09f4\0\u0a28\0\u0a5c\0\u0a90\0\u0ac4\0\u0af8\0\u0b2c"+
    "\0\u064c\0\u0b60\0\u0b94\0\u0bc8\0\u071c\0\150\0\u0104\0\u0104"+
    "\0\u0bfc\0\u0c30\0\u0c64\0\u0c98\0\u0ccc\0\u0d00\0\u0d34\0\u0d68"+
    "\0\u0d9c\0\u0dd0\0\u0e04\0\u0e38\0\u0e6c\0\u0ea0\0\u0104\0\u0ed4"+
    "\0\u0f08\0\u0b2c\0\u0f3c\0\u0f70\0\u0fa4\0\u0104\0\u0fd8\0\u100c"+
    "\0\u1040\0\u1074\0\u10a8\0\u10dc\0\u0104\0\u1110\0\u1144\0\u0104"+
    "\0\u0104\0\u1178\0\u11ac\0\u11e0\0\u1214\0\u1248\0\u0104\0\u127c"+
    "\0\u12b0\0\u12e4\0\u1318\0\u134c\0\u0104\0\u1380\0\u13b4\0\u0104"+
    "\0\u13e8\0\u141c\0\u0104\0\u1450\0\u1484\0\u0104\0\u0104\0\u14b8"+
    "\0\u14ec\0\u1520\0\u0104\0\u1554\0\u0104\0\u1588\0\u0104\0\u0104"+
    "\0\u0104\0\u15bc\0\u15f0\0\u1624\0\u0104\0\u0104\0\u1658\0\u0104"+
    "\0\u168c\0\u16c0\0\u0104\0\150\0\u16f4\0\u1728\0\150\0\150";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[152];
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
    "\1\37\1\7\1\40\1\0\1\2\1\41\150\0\1\4"+
    "\66\0\1\42\10\0\1\43\45\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\31\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\12\7\1\44\16\7\22\0\1\11"+
    "\65\0\1\45\66\0\1\46\63\0\1\47\63\0\1\50"+
    "\63\0\1\51\65\0\1\52\64\0\1\53\33\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\1\7\1\54\23\7"+
    "\1\55\3\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\10\7\1\56\20\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\13\7\1\57\15\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\4\7\1\60\24\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\4\7"+
    "\1\61\6\7\1\62\15\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\1\7\1\63\5\7\1\64\21\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\2\7"+
    "\1\65\26\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\10\7\1\66\20\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\4\7\1\67\24\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\11\7\1\70\17\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\23\7"+
    "\1\71\5\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\5\7\1\72\5\7\1\73\15\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\7\7\1\74\21\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\4\7"+
    "\1\75\4\7\1\76\17\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\10\7\1\77\20\7\1\0\3\100"+
    "\1\0\60\100\1\0\1\101\71\0\1\102\70\0\1\103"+
    "\50\0\1\7\4\0\5\7\1\0\5\7\11\0\22\7"+
    "\1\104\6\7\1\0\3\105\1\106\60\105\1\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\2\7\1\107\26\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\20\7"+
    "\1\110\10\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\5\7\1\111\23\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\5\7\1\112\23\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\4\7\1\113\24\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\10\7"+
    "\1\114\20\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\3\7\1\115\25\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\12\7\1\116\16\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\13\7\1\117\15\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\2\7"+
    "\1\120\26\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\1\121\30\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\1\122\30\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\1\123\30\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\6\7\1\124\22\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\1\125\30\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\11\7"+
    "\1\126\17\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\13\7\1\127\15\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\7\7\1\130\21\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\1\131\30\7\1\0"+
    "\3\100\1\0\57\100\1\132\10\0\1\133\73\0\1\134"+
    "\44\0\1\7\4\0\5\7\1\0\5\7\11\0\13\7"+
    "\1\135\15\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\10\7\1\136\20\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\6\7\1\137\22\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\7\7\1\140\21\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\11\7"+
    "\1\141\17\7\2\0\1\7\4\0\5\7\1\0\2\7"+
    "\1\142\2\7\11\0\24\7\1\143\1\7\1\144\2\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\10\7"+
    "\1\145\20\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\1\146\30\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\5\7\1\147\23\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\3\7\1\150\25\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\1\7\1\151"+
    "\27\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\7\7\1\152\21\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\7\7\1\153\21\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\15\7\1\154\13\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\12\7\1\155"+
    "\16\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\12\7\1\156\16\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\11\7\1\157\17\7\12\0\1\160\60\0"+
    "\1\161\56\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\10\7\1\162\20\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\7\7\1\163\21\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\10\7\1\164\20\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\17\7\1\165"+
    "\11\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\4\7\1\166\24\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\23\7\1\167\5\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\25\7\1\170\3\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\1\7\1\171"+
    "\27\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\13\7\1\172\15\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\10\7\1\173\20\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\1\174\30\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\11\7\1\175\17\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\12\7"+
    "\1\176\16\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\10\7\1\177\20\7\13\0\1\200\60\0\1\201"+
    "\55\0\1\7\4\0\5\7\1\0\5\7\11\0\15\7"+
    "\1\202\13\7\2\0\1\7\4\0\5\7\1\0\5\7"+
    "\11\0\10\7\1\203\20\7\2\0\1\7\4\0\5\7"+
    "\1\0\5\7\11\0\11\7\1\204\17\7\2\0\1\7"+
    "\4\0\5\7\1\0\5\7\11\0\13\7\1\205\15\7"+
    "\2\0\1\7\4\0\5\7\1\0\5\7\11\0\1\206"+
    "\30\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\14\7\1\207\14\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\1\7\1\210\27\7\2\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\22\7\1\211\6\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\2\7\1\212"+
    "\26\7\14\0\1\213\60\0\1\214\54\0\1\7\4\0"+
    "\5\7\1\0\5\7\11\0\11\7\1\215\17\7\2\0"+
    "\1\7\4\0\5\7\1\0\5\7\11\0\1\7\1\216"+
    "\27\7\2\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\7\7\1\217\21\7\2\0\1\7\4\0\5\7\1\0"+
    "\5\7\11\0\10\7\1\220\20\7\15\0\1\221\62\0"+
    "\1\222\51\0\1\7\4\0\5\7\1\0\5\7\11\0"+
    "\10\7\1\223\20\7\16\0\1\224\1\225\53\0\1\226"+
    "\73\0\1\227\65\0\1\230\43\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5980];
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
    "\4\0\1\1\1\0\6\11\24\1\1\0\1\1\2\0"+
    "\2\1\1\11\24\1\2\0\23\1\2\0\16\1\2\0"+
    "\11\1\2\0\4\1\2\0\1\1\1\11\2\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[152];
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
    while (i < 166) {
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
        case 41: break;
        case 19: 
          { return Parser.TRUE;
          }
        case 42: break;
        case 4: 
          { yyparser.yylval = new ParserVal(yytext());
            return Parser.ID;
          }
        case 43: break;
        case 37: 
          { return Parser.ENDWHILE;
          }
        case 44: break;
        case 10: 
          { return Parser.AND;
          }
        case 45: break;
        case 26: 
          { return Parser.WHILE;
          }
        case 46: break;
        case 32: 
          { return Parser.RETURN;
          }
        case 47: break;
        case 25: 
          { return Parser.ENDIF;
          }
        case 48: break;
        case 15: 
          { return Parser.INT;
          }
        case 49: break;
        case 2: 
          { return (int) yycharat(0);
          }
        case 50: break;
        case 38: 
          { yyparser.setDebug(true);
          }
        case 51: break;
        case 34: 
          { return Parser.ESCREVA;
          }
        case 52: break;
        case 24: 
          { return Parser.BREAK;
          }
        case 53: break;
        case 9: 
          { return Parser.NEQ;
          }
        case 54: break;
        case 3: 
          { yyline++;
          }
        case 55: break;
        case 31: 
          { return Parser.STRING;
          }
        case 56: break;
        case 33: 
          { return Parser.PUBLIC;
          }
        case 57: break;
        case 16: 
          { return Parser.NEW;
          }
        case 58: break;
        case 8: 
          { return Parser.EQ;
          }
        case 59: break;
        case 36: 
          { return Parser.PRIVATE;
          }
        case 60: break;
        case 6: 
          { return Parser.GEQ;
          }
        case 61: break;
        case 13: 
          { yyparser.yylval = new ParserVal(yytext()); 
         return Parser.NUMDOUBLE;
          }
        case 62: break;
        case 14: 
          { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1;
          }
        case 63: break;
        case 7: 
          { return Parser.LEQ;
          }
        case 64: break;
        case 12: 
          { return Parser.IF;
          }
        case 65: break;
        case 22: 
          { return Parser.MAIN;
          }
        case 66: break;
        case 17: 
          { return Parser.FOR;
          }
        case 67: break;
        case 39: 
          { yyparser.setDebug(false);
          }
        case 68: break;
        case 23: 
          { return Parser.LEIA;
          }
        case 69: break;
        case 18: 
          { yyparser.yylval = new ParserVal(yytext().substring(1, yylength() -1));
	     return Parser.LIT;
          }
        case 70: break;
        case 28: 
          { return Parser.FALSE;
          }
        case 71: break;
        case 1: 
          { yyparser.yylval = new ParserVal(yytext()); 
         return Parser.NUM;
          }
        case 72: break;
        case 40: 
          { yyparser.listarTS();
          }
        case 73: break;
        case 11: 
          { return Parser.OR;
          }
        case 74: break;
        case 29: 
          { return Parser.DOUBLE;
          }
        case 75: break;
        case 20: 
          { return Parser.ELSE;
          }
        case 76: break;
        case 35: 
          { return Parser.BOOLEAN;
          }
        case 77: break;
        case 21: 
          { return Parser.VOID;
          }
        case 78: break;
        case 30: 
          { return Parser.ENDFOR;
          }
        case 79: break;
        case 5: 
          { 
          }
        case 80: break;
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

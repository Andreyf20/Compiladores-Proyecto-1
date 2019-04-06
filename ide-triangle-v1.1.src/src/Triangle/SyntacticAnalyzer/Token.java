/*
 * @(#)Token.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.SyntacticAnalyzer;


final class Token extends Object {

  protected int kind;
  protected String spelling;
  protected SourcePosition position;

  public Token(int kind, String spelling, SourcePosition position) {

    if (kind == Token.IDENTIFIER) {
      int currentKind = firstReservedWord;
      boolean searching = true;

      while (searching) {
        int comparison = tokenTable[currentKind].compareTo(spelling);
        if (comparison == 0) {
          this.kind = currentKind;
          searching = false;
        } else if (comparison > 0 || currentKind == lastReservedWord) {
          this.kind = Token.IDENTIFIER;
          searching = false;
        } else {
          currentKind ++;
        }
      }
    } else
      this.kind = kind;

    this.spelling = spelling;
    this.position = position;

  }

  public static String spell (int kind) {
    return tokenTable[kind];
  }

  public String toString() {
    return "Kind=" + kind + ", spelling=" + spelling +
      ", position=" + position;
  }

  // Token classes...
  //Cambio: se agrego los token necesarios para los cambios sintacticos
  public static final int

    // literals, identifiers, operators...
    INTLITERAL          = 0,
    CHARLITERAL         = 1,
    IDENTIFIER          = 2,
    OPERATOR            = 3,
    OR                  = 4,

    // reserved words - must be in alphabetical order...
    ARRAY		= 5,
    CHOOSE              = 6,
    CONST		= 7,
    DO			= 8,
    ELSE		= 9,
    END			= 10,
    FOR                 = 11,
    FROM                = 12,
    FUNC		= 13,
    IF			= 14,
    IN			= 15,
    LET			= 16,
    LOOP                = 17,
    OF			= 18,
    PACKAGE             = 19,
    PAR                 = 20,
    PASS                = 21,
    PRIVATE             = 22,
    PROC		= 23,
    RECORD		= 24,
    RECURSIVE           = 25,
    THEN		= 26,
    TO                  = 27,
    TYPE		= 28,
    UNTIL               = 29,
    VAR			= 30,
    WHEN                = 31,
    WHILE		= 32,

    // punctuation...
    DOT			= 33,
    COLON		= 34,
    SEMICOLON           = 35,
    COMMA		= 36,
    BECOMES		= 37,
    DOUBLEBECOMES       = 38,
    IS			= 39,
    DOUBLEDOT           = 40,
    DOLLAR              = 41,

    // brackets...
    LPAREN		= 42,
    RPAREN		= 43,
    LBRACKET            = 44,
    RBRACKET            = 45,
    LCURLY		= 46,
    RCURLY		= 47,
         
    // special tokens...
    EOT			= 48,
    COMMENTARY          = 49,
    ERROR		= 50;


  private static String[] tokenTable = new String[] {
    "<int>",
    "<char>",
    "<identifier>",
    "<operator>",
    "|",
    "array",
    "choose",
    "const",
    "do",
    "else",
    "end",
    "for",
    "from",
    "func",
    "if",
    "in",
    "let",
    "loop",
    "of",
    "package",
    "par",
    "pass",
    "private",
    "proc",
    "record",
    "recursive",
    "then",
    "to",
    "type",
    "until",
    "var",
    "when",
    "while",
    ".",
    ":",
    ";",
    ",",
    ":=",
    "::=",
    "~",
    "..",
    "$",
    "(",
    ")",
    "[",
    "]",
    "{",
    "}",
    "",
    "!",
    "<error>"
  };

  private final static int	firstReservedWord = Token.ARRAY,
  				lastReservedWord  = Token.WHILE;

}

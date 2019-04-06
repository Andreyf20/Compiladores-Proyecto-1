/*
 * @(#)Parser.java                        2.1 2003/10/07
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

import Triangle.ErrorReporter;
import Triangle.AbstractSyntaxTrees.ActualParameter;
import Triangle.AbstractSyntaxTrees.ActualParameterSequence;
import Triangle.AbstractSyntaxTrees.ArrayAggregate;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.Case;
import Triangle.AbstractSyntaxTrees.CaseLiteral;
import Triangle.AbstractSyntaxTrees.CaseLiterals;
import Triangle.AbstractSyntaxTrees.CaseRange;
import Triangle.AbstractSyntaxTrees.Cases;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ChooseCommand;
import Triangle.AbstractSyntaxTrees.Command;
import Triangle.AbstractSyntaxTrees.Commentary;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.Declaration;
import Triangle.AbstractSyntaxTrees.DoUntilCommand;
import Triangle.AbstractSyntaxTrees.DoWhileCommand;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.Expression;
import Triangle.AbstractSyntaxTrees.FieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.ForCtlDeclaration;
import Triangle.AbstractSyntaxTrees.ForDoCommand;
import Triangle.AbstractSyntaxTrees.ForUntilCommand;
import Triangle.AbstractSyntaxTrees.ForWhileCommand;
import Triangle.AbstractSyntaxTrees.FormalParameter;
import Triangle.AbstractSyntaxTrees.FormalParameterSequence;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
import Triangle.AbstractSyntaxTrees.IntegerExpression;
import Triangle.AbstractSyntaxTrees.IntegerLiteral;
import Triangle.AbstractSyntaxTrees.LetCommand;
import Triangle.AbstractSyntaxTrees.LetExpression;
import Triangle.AbstractSyntaxTrees.Long_Identifier;
import Triangle.AbstractSyntaxTrees.MultipleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleArrayAggregate;
import Triangle.AbstractSyntaxTrees.MultipleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.MultipleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleRecordAggregate;
import Triangle.AbstractSyntaxTrees.Operator;
import Triangle.AbstractSyntaxTrees.PackageDeclaration;
import Triangle.AbstractSyntaxTrees.ParDeclaration;
import Triangle.AbstractSyntaxTrees.PrivateDeclaration;
import Triangle.AbstractSyntaxTrees.ProcActualParameter;
import Triangle.AbstractSyntaxTrees.ProcDeclaration;
import Triangle.AbstractSyntaxTrees.ProcFormalParameter;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.AbstractSyntaxTrees.RecordAggregate;
import Triangle.AbstractSyntaxTrees.RecordExpression;
import Triangle.AbstractSyntaxTrees.RecordTypeDenoter;
import Triangle.AbstractSyntaxTrees.RecursiveDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialCase;
import Triangle.AbstractSyntaxTrees.SequentialCaseLiterals;
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialPackageDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleTypeDenoter;
import Triangle.AbstractSyntaxTrees.SimpleVname;
import Triangle.AbstractSyntaxTrees.SingleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleArrayAggregate;
import Triangle.AbstractSyntaxTrees.SingleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.SingleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleRecordAggregate;
import Triangle.AbstractSyntaxTrees.SubscriptVname;
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.TypeDenoter;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
import Triangle.AbstractSyntaxTrees.VarDeclarationInitialized;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.Vname;
import Triangle.AbstractSyntaxTrees.VnameExpression;
import Triangle.AbstractSyntaxTrees.WhileCommand;
import Triangle.AbstractSyntaxTrees.UntilCommand;
import Triangle.AbstractSyntaxTrees.PackageVname;

public class Parser {

  private Scanner lexicalAnalyser;
  private ErrorReporter errorReporter;
  private Token currentToken;
  private SourcePosition previousTokenPosition;

  public Parser(Scanner lexer, ErrorReporter reporter) {
    lexicalAnalyser = lexer;
    errorReporter = reporter;
    previousTokenPosition = new SourcePosition();
  }

// accept checks whether the current token matches tokenExpected.
// If so, fetches the next token.
// If not, reports a syntactic error.

  void accept (int tokenExpected) throws SyntaxError {
    if (currentToken.kind == tokenExpected) {
      previousTokenPosition = currentToken.position;
      currentToken = lexicalAnalyser.scan();
    } else {
      syntacticError("\"%\" expected here", Token.spell(tokenExpected));
    }
  }

  void acceptIt() {
    previousTokenPosition = currentToken.position;
    currentToken = lexicalAnalyser.scan();
  }

// start records the position of the start of a phrase.
// This is defined to be the position of the first
// character of the first token of the phrase.

  void start(SourcePosition position) {
    position.start = currentToken.position.start;
  }

// finish records the position of the end of a phrase.
// This is defined to be the position of the last
// character of the last token of the phrase.

  void finish(SourcePosition position) {
    position.finish = previousTokenPosition.finish;
  }

  void syntacticError(String messageTemplate, String tokenQuoted) throws SyntaxError {
    SourcePosition pos = currentToken.position;
    errorReporter.reportError(messageTemplate, tokenQuoted, pos);
    throw(new SyntaxError());
  }
////////////////////////////////////////////////////////////////////////////////
//
//Cambios
//
///////////////////////////////////////////////////////////////////////////////
//
// PROGRAMS
//
///////////////////////////////////////////////////////////////////////////////

  public Program parseProgram() {

    Program programAST = null;

    previousTokenPosition.start = 0;
    previousTokenPosition.finish = 0;
    currentToken = lexicalAnalyser.scan();
    
    try {
      Declaration packageDecl = null;
      if(currentToken.kind == Token.PACKAGE){
          packageDecl = parsePackageDeclaration();
          accept(Token.SEMICOLON);
      }
      while(currentToken.kind == Token.PACKAGE){
          Declaration decl2 = parsePackageDeclaration();
          accept(Token.SEMICOLON);
          packageDecl = new SequentialPackageDeclaration(packageDecl, decl2, previousTokenPosition);
      }
      Command cAST = parseCommand();
      
      //if(currentToken.kind == Token.COMMENTARY){
          //acceptIt();
          //System.out.println(lexicalAnalyser.getComment());
      //}
      
      programAST = new Program(packageDecl, cAST, previousTokenPosition, lexicalAnalyser.getComment());
      if (currentToken.kind != Token.EOT) {
        syntacticError("\"%\" not expected after end of program",
          currentToken.spelling);
      }
    }
    catch (SyntaxError s) { return null; }
    return programAST;
  }
////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
//
// LITERALS
//
///////////////////////////////////////////////////////////////////////////////

// parseIntegerLiteral parses an integer-literal, and constructs
// a leaf AST to represent it.

  IntegerLiteral parseIntegerLiteral() throws SyntaxError {
    IntegerLiteral IL = null;

    if (currentToken.kind == Token.INTLITERAL) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      IL = new IntegerLiteral(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } 
    else{
      IL = null;
      syntacticError("integer literal expected here", "");
    }
    return IL;
  }

// parseCharacterLiteral parses a character-literal, and constructs a leaf
// AST to represent it.

  CharacterLiteral parseCharacterLiteral() throws SyntaxError {
    CharacterLiteral CL = null;

    if (currentToken.kind == Token.CHARLITERAL) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      CL = new CharacterLiteral(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      CL = null;
      syntacticError("character literal expected here", "");
    }
    return CL;
  }

// parseIdentifier parses an identifier, and constructs a leaf AST to
// represent it.

  Identifier parseIdentifier() throws SyntaxError {
    Identifier I = null;

    if (currentToken.kind == Token.IDENTIFIER) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      I = new Identifier(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } 
    else {
      I = null;
      syntacticError("identifier expected here", "");
    }
    return I;
  }
  ////////////////////////////////////////////////////////////////////////////////
  //
  //Cambios
  //
  Declaration parsePackageDeclaration() throws SyntaxError{
      Identifier I = null;
      Declaration decl = null;
      Declaration result = null;
      
      SourcePosition positionPackage = new SourcePosition();
      start(positionPackage);
      if(currentToken.kind == Token.PACKAGE){
        accept(Token.PACKAGE);
        I = parsePackageIdentifier();
        accept(Token.IS);
        decl = parseDeclaration();
        accept(Token.END);
        finish(positionPackage);
        result = new PackageDeclaration(I, decl, positionPackage, currentToken.spelling);
      }
      else{
        syntacticError("expected package, found \"%\"", currentToken.spelling);
      }
      return result;
  }
  
  Identifier parsePackageIdentifier() throws SyntaxError{
      return parseIdentifier();
  }
  
  Identifier parseLongIdentifier() throws SyntaxError{
      Identifier I = null;
      SourcePosition positionLong = new SourcePosition();
      start(positionLong);
      if(currentToken.kind == Token.IDENTIFIER){
          Identifier optional = parsePackageIdentifier();
          if(currentToken.kind == Token.DOLLAR){
              acceptIt();
              Identifier second = parseIdentifier();
              finish(positionLong);
                      
              I = new Long_Identifier(optional, second, positionLong, currentToken.spelling);
          }
          else{
              finish(positionLong);
              I = new Long_Identifier(null, optional, positionLong, currentToken.spelling);
          }          
      }
      else{
          syntacticError("expected identifier, found \"%\"", currentToken.spelling);
      }
      return I;
  }
////////////////////////////////////////////////////////////////////////////////
// parseOperator parses an operator, and constructs a leaf AST to
// represent it.

  Operator parseOperator() throws SyntaxError {
    Operator O = null;

    if (currentToken.kind == Token.OPERATOR) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      O = new Operator(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      O = null;
      syntacticError("operator expected here", "");
    }
    return O;
  }

///////////////////////////////////////////////////////////////////////////////
//
// COMMANDS
//
///////////////////////////////////////////////////////////////////////////////

// parseCommand parses the command, and constructs an AST
// to represent its phrase structure.

  Command parseCommand() throws SyntaxError {
    Command commandAST = null; // in case there's a syntactic error

    SourcePosition commandPos = new SourcePosition();

    start(commandPos);
    commandAST = parseSingleCommand();
    while (currentToken.kind == Token.SEMICOLON) {
      acceptIt();
      Command c2AST = parseSingleCommand();
      finish(commandPos);
      commandAST = new SequentialCommand(commandAST, c2AST, commandPos);
    }
    return commandAST;
  }

  Command parseSingleCommand() throws SyntaxError {
    Command commandAST = null; // in case there's a syntactic error

    SourcePosition commandPos = new SourcePosition();
    start(commandPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
      {
        Identifier iAST = parseLongIdentifier();
        if (currentToken.kind == Token.LPAREN) {
          acceptIt();
          ActualParameterSequence apsAST = parseActualParameterSequence();
          accept(Token.RPAREN);
          finish(commandPos);
          commandAST = new CallCommand(iAST, apsAST, commandPos);

        } else {

          Vname vAST = parseRestOfVname(null, iAST);
          accept(Token.BECOMES);
          Expression eAST = parseExpression();
          finish(commandPos);
          commandAST = new AssignCommand(vAST, eAST, commandPos);
        }
      }
      break;
    
    //Cambio: se agrego el case de loop y sus casos
    case Token.LOOP:
      {
      acceptIt();
      switch (currentToken.kind) {
          case Token.FOR:
          {
              acceptIt();

              Declaration dAST = parseForCtlDeclaration();
              accept(Token.TO);
              Expression e2AST = parseExpression();
              switch (currentToken.kind) {
                  case Token.DO:
                  {
                    acceptIt();
                    commandAST = parseCommand();
                    accept(Token.END);
                    finish(commandPos);
                    commandAST = new ForDoCommand(dAST, e2AST, commandAST, commandPos);
                    break;
                  }
                  case Token.WHILE: 
                    acceptIt();
                    Expression e3AST = parseExpression();
                    accept(Token.DO);
                    commandAST = parseCommand();
                    accept(Token.END);
                    finish(commandPos);
                    commandAST = new ForWhileCommand(dAST, e2AST, e3AST, commandAST, commandPos);
                    break;
                  case Token.UNTIL:
                    acceptIt();
                    Expression e4AST = parseExpression();
                    accept(Token.DO);
                    commandAST = parseCommand();
                    accept(Token.END);
                    finish(commandPos);
                    commandAST = new ForUntilCommand(dAST, e2AST, e4AST, commandAST, commandPos);
                    break;
                  default:
                      syntacticError("expected do, while or until, found \"%\"", currentToken.spelling);
              }
              break;
          }
          case Token.WHILE:
          {
              acceptIt();
              Expression e1AST = parseExpression();
              accept(Token.DO);
              Command c1AST = parseCommand();
              accept(Token.END);
              finish(commandPos);
              commandAST = new WhileCommand(e1AST, c1AST, commandPos);
              break;
          }
          case Token.UNTIL:
          {
              acceptIt();
              Expression e1AST = parseExpression();
              accept(Token.DO);
              Command c1AST = parseCommand();
              accept(Token.END);
              finish(commandPos);
              commandAST = new UntilCommand(e1AST, c1AST, commandPos);
              break;
          }
          case Token.DO:
          {
              acceptIt();
              Command c1AST = parseCommand();
              switch(currentToken.kind)
              {
                  case Token.WHILE:
                  {
                      acceptIt();
                      Expression e1AST = parseExpression();
                      accept(Token.END);
                      finish(commandPos);
                      commandAST = new DoWhileCommand(c1AST, e1AST, commandPos); //DoWhileCommand
                      break;
                  }
                  case Token.UNTIL:
                  {
                      acceptIt();
                      Expression e1AST = parseExpression();
                      accept(Token.END);
                      finish(commandPos);
                      commandAST = new DoUntilCommand(c1AST, e1AST, commandPos); //DoUntilCommand
                      break;
                  }
                  default:
                      syntacticError("expected while or until, found \"%\"", currentToken.spelling);
                      break;
              }
              break;
          }
          default:
              syntacticError("expected for, while, until or do, found \"%\"", currentToken.spelling);
      }
    }
      break;  
    
    case Token.LET:
      {
        acceptIt();
        Declaration dAST = parseDeclaration();
        accept(Token.IN);
        Command cAST = parseCommand();
        accept(Token.END);
        finish(commandPos);
        commandAST = new LetCommand(dAST, cAST, commandPos);
      }
      break; 
    case Token.IF:
      {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.THEN);
        Command c1AST = parseCommand();
        accept(Token.ELSE);
        Command c2AST = parseCommand();
        accept(Token.END);
        finish(commandPos);
        commandAST = new IfCommand(eAST, c1AST, c2AST, commandPos);
        
      }
      break;

    case Token.CHOOSE:
      {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.FROM);
        Cases commandCaseAST = parseCasesPlural();
        accept(Token.END);
        finish(commandPos);
        commandAST = new ChooseCommand(eAST, commandCaseAST, commandPos);
      }
      break;

    //Cambio: se agrego el case de pass
    case Token.PASS:
    {
      acceptIt();
      finish(commandPos);
      commandAST = new EmptyCommand(commandPos);
      break;
    }

    default:
      syntacticError("\"%\" cannot start a command",
        currentToken.spelling);
      break;

    }

    return commandAST;
  }
  
  Command parseElseCase() throws SyntaxError{
      
      Command commandAST = null; // in case there's a syntactic error
      if(currentToken.kind == Token.ELSE){
        acceptIt();
        commandAST = parseCommand();
      }
      else{
          syntacticError("expected else, found \"%\"",
        currentToken.spelling);
      }
      return commandAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// EXPRESSIONS
//
///////////////////////////////////////////////////////////////////////////////

  Expression parseExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();

    start (expressionPos);

    switch (currentToken.kind) {

    case Token.LET:
      {
        acceptIt();
        Declaration dAST = parseDeclaration();
        accept(Token.IN);
        Expression eAST = parseExpression();
        finish(expressionPos);
        expressionAST = new LetExpression(dAST, eAST, expressionPos);
      }
      break;

    case Token.IF:
      {
        acceptIt();
        Expression e1AST = parseExpression();
        accept(Token.THEN);
        Expression e2AST = parseExpression();
        accept(Token.ELSE);
        Expression e3AST = parseExpression();
        finish(expressionPos);
        expressionAST = new IfExpression(e1AST, e2AST, e3AST, expressionPos);
      }
      break;

    default:
      expressionAST = parseSecondaryExpression();
      break;
    }
    return expressionAST;
  }

  Expression parseSecondaryExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();
    start(expressionPos);

    expressionAST = parsePrimaryExpression();
    while (currentToken.kind == Token.OPERATOR) {
      Operator opAST = parseOperator();
      Expression e2AST = parsePrimaryExpression();
      expressionAST = new BinaryExpression (expressionAST, opAST, e2AST,
        expressionPos);
    }
    return expressionAST;
  }

  Expression parsePrimaryExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();
    start(expressionPos);

    switch (currentToken.kind) {

    case Token.INTLITERAL:
      {
        IntegerLiteral ilAST = parseIntegerLiteral();
        finish(expressionPos);
        expressionAST = new IntegerExpression(ilAST, expressionPos);
      }
      break;

    case Token.CHARLITERAL:
      {
        CharacterLiteral clAST= parseCharacterLiteral();
        finish(expressionPos);
        expressionAST = new CharacterExpression(clAST, expressionPos);
      }
      break;

    case Token.LBRACKET:
      {
        acceptIt();
        ArrayAggregate aaAST = parseArrayAggregate();
        accept(Token.RBRACKET);
        finish(expressionPos);
        expressionAST = new ArrayExpression(aaAST, expressionPos);
      }
      break;

    case Token.LCURLY:
      {
        acceptIt();
        RecordAggregate raAST = parseRecordAggregate();
        accept(Token.RCURLY);
        finish(expressionPos);
        expressionAST = new RecordExpression(raAST, expressionPos);
      }
      break;

    case Token.IDENTIFIER:
      {
        Identifier iAST= parseLongIdentifier();
        if (currentToken.kind == Token.LPAREN) {
          acceptIt();
          ActualParameterSequence apsAST = parseActualParameterSequence();
          accept(Token.RPAREN);
          finish(expressionPos);
          expressionAST = new CallExpression(iAST, apsAST, expressionPos);

        } else {
          Vname vAST = parseRestOfVname(null, iAST);
          finish(expressionPos);
          expressionAST = new VnameExpression(vAST, expressionPos);
        }
      }
      break;

    case Token.OPERATOR:
      {
        Operator opAST = parseOperator();
        Expression eAST = parsePrimaryExpression();
        finish(expressionPos);
        expressionAST = new UnaryExpression(opAST, eAST, expressionPos);
      }
      break;

    case Token.LPAREN:
      acceptIt();
      expressionAST = parseExpression();
      accept(Token.RPAREN);
      break;

    default:
      syntacticError("\"%\" cannot start an expression",
        currentToken.spelling);
      break;

    }
    return expressionAST;
  }
  
  
  
  
  
  RecordAggregate parseRecordAggregate() throws SyntaxError {
    RecordAggregate aggregateAST = null; // in case there's a syntactic error

    SourcePosition aggregatePos = new SourcePosition();
    start(aggregatePos);

    Identifier iAST = parseIdentifier();
    accept(Token.IS);
    Expression eAST = parseExpression();

    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      RecordAggregate aAST = parseRecordAggregate();
      finish(aggregatePos);
      aggregateAST = new MultipleRecordAggregate(iAST, eAST, aAST, aggregatePos);
    } else {
      finish(aggregatePos);
      aggregateAST = new SingleRecordAggregate(iAST, eAST, aggregatePos);
    }
    return aggregateAST;
  }

  ArrayAggregate parseArrayAggregate() throws SyntaxError {
    ArrayAggregate aggregateAST = null; // in case there's a syntactic error

    SourcePosition aggregatePos = new SourcePosition();
    start(aggregatePos);

    Expression eAST = parseExpression();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      ArrayAggregate aAST = parseArrayAggregate();
      finish(aggregatePos);
      aggregateAST = new MultipleArrayAggregate(eAST, aAST, aggregatePos);
    } else {
      finish(aggregatePos);
      aggregateAST = new SingleArrayAggregate(eAST, aggregatePos);
    }
    return aggregateAST;
  }
////////////////////////////////////////////////////////////////////////////////
//
//Cambios
//
///////////////////////////////////////////////////////////////////////////////
//
//CASES
//
///////////////////////////////////////////////////////////////////////////////
  
  Cases parseCasesPlural() throws SyntaxError{
      Cases casesAST = null;
      SequentialCase caseAST = null;
      Command elseCommand = null;
      
      //dado que un case empieza con el token when entonces entra en el ciclo en caso
      //de existir m�s cases, si no continua con el elseCase
      SourcePosition casesPos = new SourcePosition();
      start (casesPos);
      
      if(currentToken.kind == Token.WHEN){
        while(currentToken.kind == Token.WHEN){

            Case c2AST = parseCaseSingular();
            
            finish(casesPos);
            caseAST = new SequentialCase(caseAST, c2AST, casesPos);

            if(currentToken.kind == Token.ELSE){
              break;
            }
        }
        //en caso de existir el else de un elseCase lo parsea
        if(currentToken.kind == Token.ELSE){
          elseCommand = parseElseCase();
        }
        else if(currentToken.kind != Token.END){
            syntacticError("exprected when found, \"%\"",
            currentToken.spelling);
        }
        if(currentToken.kind == Token.WHEN){
          syntacticError("\"%\" there cannot be an else before a choose case",
          currentToken.spelling);
        }
      }
      else{
          syntacticError("expected when, found \"%\"",
          currentToken.spelling);
      }
      
      casesAST = new Cases(caseAST, elseCommand, casesPos);
      return casesAST;
  }
  
  Case parseCaseSingular() throws SyntaxError{
      Case caseAST = null; // in case thre�s a syntactic error
      SourcePosition casePos = new SourcePosition();
      start (casePos);
      
      if(currentToken.kind == Token.WHEN){
        accept(Token.WHEN);
        CaseLiterals cL1 = parseCaseLiteralsPlural();

        if(currentToken.kind == Token.THEN){
            accept(Token.THEN);
        }
        else{
            syntacticError("expected then, found: \"%\"",
          currentToken.spelling);
        }
        
        Command c1 = parseCommand();
        finish(casePos);

        caseAST = new Case(cL1, c1, casePos);
      }
      else{
          syntacticError("expected when, found: \"%\"",
          currentToken.spelling);
      }
      return caseAST;
      
  }
  
  CaseLiterals parseCaseLiteralsPlural() throws SyntaxError{
      CaseLiterals caseLiteralsAST = null; // in case there's a syntactic error
      SequentialCaseLiterals sequentialCaseLiteralsAST = null;
      CaseRange cR1 = null, cR2 = null;
      
      SourcePosition caseLiteralPos = new SourcePosition();
      start(caseLiteralPos);
      
      cR1 = parseCaseRange();
      finish(caseLiteralPos);
      sequentialCaseLiteralsAST = new SequentialCaseLiterals(sequentialCaseLiteralsAST, cR1, caseLiteralPos);
      while(currentToken.kind == Token.OR){
          accept(Token.OR);
          cR2 = parseCaseRange();
          finish(caseLiteralPos);
          sequentialCaseLiteralsAST = new SequentialCaseLiterals(sequentialCaseLiteralsAST, cR2, caseLiteralPos);
      }
      caseLiteralsAST = new CaseLiterals(sequentialCaseLiteralsAST, caseLiteralPos);
      return caseLiteralsAST;
  }
  
  CaseRange parseCaseRange() throws SyntaxError{
      CaseRange caseRangeAST = null; // in case there's a syntactic error
      CaseLiteral cL1 = null, cL2 = null;
      
      cL1 = parseCaseLiteralSingular();
      
      SourcePosition rangePos = new SourcePosition();
      start(rangePos);
      if(currentToken.kind == Token.DOUBLEDOT){          
        acceptIt();
        cL2 = parseCaseLiteralSingular();
      }
      else if(currentToken.kind == Token.DOT){
        syntacticError("\"%\" found expected .. ",
        currentToken.spelling);
      }
      finish(rangePos);
      caseRangeAST = new CaseRange(cL1, cL2, rangePos);
      
      return caseRangeAST;
  }
  
  CaseLiteral parseCaseLiteralSingular() throws SyntaxError{
      CaseLiteral caseLiteralAST = null; // in case there's a syntactic error

      IntegerLiteral ilAST = null;
      CharacterLiteral clAST = null;
      
      SourcePosition expressionPos = new SourcePosition();
      start(expressionPos);
      switch (currentToken.kind) {

        case Token.INTLITERAL:
          ilAST = parseIntegerLiteral();
          finish(expressionPos);
          caseLiteralAST = new CaseLiteral(ilAST, clAST, expressionPos);
          break;

        case Token.CHARLITERAL:
          clAST= parseCharacterLiteral();
          finish(expressionPos);
          caseLiteralAST = new CaseLiteral(ilAST, clAST, expressionPos);
          break;
          
        default:
          syntacticError("expected a char (int | literal), found: \"%\"",
          currentToken.spelling);
          break;
      }
      return caseLiteralAST;
  }
////////////////////////////////////////////////////////////////////////////////
  
  
  
  
////////////////////////////////////////////////////////////////////////////////
//
//Cambios
//
///////////////////////////////////////////////////////////////////////////////
//
// VALUE-OR-VARIABLE NAMES
//
///////////////////////////////////////////////////////////////////////////////

  Vname parseVname () throws SyntaxError {
    //////////////////////////////////////////////////////////////////////////////////////////
    /////////// NUEVA REGLA DE PACKAGEIDENTIFIER OPCIONAL  ///////////////////
    Identifier packageID = null;
    if(currentToken.kind == Token.IDENTIFIER){
        packageID = parsePackageIdentifier();
        accept(Token.DOLLAR);
    }
    /////////// NUEVA REGLA DE PACKAGEIDENTIFIER OPCIONAL  ///////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
      
    Vname vnameAST = null; // in case there's a syntactic error
    Identifier iAST = parseIdentifier();
    vnameAST = parseRestOfVname(packageID, iAST);
    return vnameAST;
  }

  Vname parseRestOfVname(Identifier packageID, Identifier identifierAST) throws SyntaxError {
    SourcePosition vnamePos = new SourcePosition();
    vnamePos = identifierAST.position;
    Vname vAST = null;
    //////////////////////////////////////////////////////////////////////////////////////////
    /////////// NUEVA REGLA DE PACKAGEIDENTIFIER OPCIONAL  ///////////////////
    if(packageID == null)
        vAST = new SimpleVname(identifierAST, vnamePos);
    else
        vAST = new PackageVname(packageID, identifierAST, vnamePos);
    /////////// NUEVA REGLA DE PACKAGEIDENTIFIER OPCIONAL  ///////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    while (currentToken.kind == Token.DOT ||
           currentToken.kind == Token.LBRACKET) {

      if (currentToken.kind == Token.DOT) {
        acceptIt();
        Identifier iAST = parseIdentifier();
        vAST = new DotVname(vAST, iAST, vnamePos);
      } else {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.RBRACKET);
        finish(vnamePos);
        vAST = new SubscriptVname(vAST, eAST, vnamePos);
      }
    }
    return vAST;
  }
  
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
//
//Cambios
//
///////////////////////////////////////////////////////////////////////////////
//
// DECLARATIONS
//
///////////////////////////////////////////////////////////////////////////////

  //Cambio: se agrego Parse para ForCtlDeclaration
  Declaration parseForCtlDeclaration() throws SyntaxError
  {
      Declaration declarationAST = null; //si hay un error de sintaxis
      SourcePosition declarationPos = new SourcePosition();
      start(declarationPos);
      Identifier iAST = parseIdentifier();
      accept(Token.FROM);
      Expression eAST = parseExpression();
      declarationAST = new ForCtlDeclaration(iAST, eAST, declarationPos);
      return declarationAST;
  }
  
  Declaration parseDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);
    declarationAST = parseCompoundDeclaration();
    while (currentToken.kind == Token.SEMICOLON) {
      acceptIt();
      Declaration d2AST = parseCompoundDeclaration();
      finish(declarationPos);
      declarationAST = new SequentialDeclaration(declarationAST, d2AST,
        declarationPos);
    }
    return declarationAST;
  }
  
  Declaration parseCompoundDeclaration() throws SyntaxError{
      Declaration declarationAST = null;
      
      SourcePosition declarationPos = new SourcePosition();
      start(declarationPos);
      switch (currentToken.kind) {

            case Token.RECURSIVE:
                acceptIt();
                declarationAST = parseProcFuncs();
                accept(Token.END);
                finish(declarationPos);
                declarationAST = new RecursiveDeclaration(declarationAST, declarationPos);
                break;
            case Token.PRIVATE:
                
                acceptIt();
                Declaration dlAST1 = parseDeclaration();
                
                accept(Token.IN);
                
                Declaration dlAST2 = parseDeclaration();
                
                accept(Token.END);
                
                finish(declarationPos);
                declarationAST = new PrivateDeclaration(dlAST1, dlAST2, declarationPos);
                
                break;
                
            case Token.PAR:
                acceptIt();
                declarationAST = parseSingleDeclaration();
                if(currentToken.kind == Token.OR) {
                    while (currentToken.kind == Token.OR) {
                        acceptIt();
                        Declaration dAST2 = parseSingleDeclaration();
                        finish(declarationPos);
                        declarationAST = new SequentialDeclaration(declarationAST, dAST2, declarationPos);
                    }
                } else { 
                    syntacticError("expected | but found \"%\"",
                currentToken.spelling);
                }
                    
                declarationAST = new ParDeclaration(declarationAST, declarationPos);
                accept(Token.END);
                
                break;
            case Token.CONST:
            case Token.VAR:
            case Token.FUNC:
            case Token.TYPE:
                declarationAST = parseSingleDeclaration();

                break;
            default:
                
                syntacticError("\"%\" cannot start a compound declaration",
                currentToken.spelling);
                break;
      }
      return declarationAST;
  }
  
  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////// CAMBIOS EN PROC Y FUNC //////////////////////////////////////////////////////
  
  Declaration parseProcFunc() throws SyntaxError{
      Declaration declarationAST = null; // inicia en nulo
      
      SourcePosition declarationPos = new SourcePosition(); // posicion de la declaracion
      start(declarationPos);
      
      // Se revisa si es un proc o un func si no es ninguno entonces error
      switch(currentToken.kind) {
          // En caso de proc se necesita un id, secuencia de parametros y un comando
          case Token.PROC:
            {
                acceptIt();
                Identifier iAST = parseIdentifier();
                accept(Token.LPAREN);
                FormalParameterSequence fpsAST = parseFormalParameterSequence();
                accept(Token.RPAREN);
                accept(Token.IS);
                Command cmdAST = parseCommand();
                accept(Token.END);
                finish(declarationPos);
                declarationAST = new ProcDeclaration(iAST, fpsAST, cmdAST, declarationPos);
            }
            break;
          // En caso de func se necesita un id, secuencia de parametros, un tipo y una expresion
          case Token.FUNC:
            {
                acceptIt();
                Identifier iAST = parseIdentifier();
                accept(Token.LPAREN);
                FormalParameterSequence fpsAST = parseFormalParameterSequence();
                accept(Token.RPAREN);
                accept(Token.COLON);
                TypeDenoter tAST = parseTypeDenoter();
                accept(Token.IS);
                Expression eAST = parseExpression();
                finish(declarationPos);
                declarationAST = new FuncDeclaration(iAST, fpsAST, tAST, eAST, declarationPos);
            }
            break;
          // Si no es un proc ni un func entonces error
          default:
              syntacticError("\"%\" error parsing proc-func, unexpected token", currentToken.spelling);
              break;
      
      }
      return declarationAST;
  }
  
  ///////////////// CAMBIOS EN PROC Y FUNC //////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////// NUEVA REGLA DE PROC-FUNCS /////////////////////////////////////////////////
  SequentialDeclaration parseProcFuncs() throws SyntaxError{
      SequentialDeclaration declarationAST = null; // inicia nulo
      
      SourcePosition declarationPos = new SourcePosition(); // posicion de las declaraciones
      start(declarationPos);
      
      Declaration dAST1 = null; // inicia nulo
      
      // Se verifica que se esta recibiendo un proc o un func
      if(currentToken.kind == Token.PROC || currentToken.kind == Token.FUNC) {
          dAST1 = parseProcFunc();
          finish(declarationPos);
      } else {
          syntacticError("\"%\" error parsing proc-funcs, unexpected token", currentToken.spelling);
      }
      
      // Si se llega aqui debe haber una o mas "|" proc-func
      if(currentToken.kind == Token.OR) {
            acceptIt();
            start(declarationPos);
            Declaration dAST2 = parseProcFunc();
            finish(declarationPos);
            declarationAST = new SequentialDeclaration(dAST1, dAST2, declarationPos);
            // Aqui se verifica que se haya terminado de parsear todas las procs o funcs que hayan
            while(currentToken.kind == Token.OR){
                acceptIt();
                start(declarationPos);
                dAST1 = parseProcFunc();
                finish(declarationPos);
                declarationAST = new SequentialDeclaration(declarationAST, dAST1, declarationPos);
            }
      } else {
          syntacticError("\"%\" error parsing proc-funcs, expected |", currentToken.spelling);
      }
      
      return declarationAST;
  }
   ///////////////// NUEVA REGLA DE PROC-FUNCS /////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////////////////////////////////////////////  

  Declaration parseSingleDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);

    switch (currentToken.kind) {

    case Token.CONST:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.IS);
        Expression eAST = parseExpression();
        finish(declarationPos);
        declarationAST = new ConstDeclaration(iAST, eAST, declarationPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        //////////////////////////////////////////////////////////////////////////////
        ////////////// NUEVA VARIABLE INICIALIZADA /////////////////////////
        switch (currentToken.kind) {
            case Token.COLON:
                acceptIt();
                TypeDenoter tAST = parseTypeDenoter();
                finish(declarationPos);
                declarationAST = new VarDeclaration(iAST, tAST, declarationPos);
                break;
            case Token.DOUBLEBECOMES:
                acceptIt();
                Expression eAST = parseExpression();
                finish(declarationPos);
                // Se crea una nueva variable con la expresion en vez del tipo
                declarationAST = new VarDeclarationInitialized(iAST, eAST, declarationPos);
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                break;
        ////////////// NUEVA VARIABLE INICIALIZADA /////////////////////////
        //////////////////////////////////////////////////////////////////////////////        
            default:
                syntacticError("\"%\" unexpexted token in var", currentToken.spelling);
                break;
        }
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.IS);
        Command cAST = parseCommand();
        accept(Token.END);
        finish(declarationPos);
        declarationAST = new ProcDeclaration(iAST, fpsAST, cAST, declarationPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        accept(Token.IS);
        Expression eAST = parseExpression();
        finish(declarationPos);
        declarationAST = new FuncDeclaration(iAST, fpsAST, tAST, eAST,
          declarationPos);
      }
      break;

    case Token.TYPE:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.IS);
        TypeDenoter tAST = parseTypeDenoter();
        finish(declarationPos);
        declarationAST = new TypeDeclaration(iAST, tAST, declarationPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a declaration",
        currentToken.spelling);
      break;

    }
    return declarationAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// PARAMETERS
//
///////////////////////////////////////////////////////////////////////////////

  FormalParameterSequence parseFormalParameterSequence() throws SyntaxError {
    FormalParameterSequence formalsAST;

    SourcePosition formalsPos = new SourcePosition();

    start(formalsPos);
    if (currentToken.kind == Token.RPAREN) {
      finish(formalsPos);
      formalsAST = new EmptyFormalParameterSequence(formalsPos);

    } else {
      formalsAST = parseProperFormalParameterSequence();
    }
    return formalsAST;
  }

  FormalParameterSequence parseProperFormalParameterSequence() throws SyntaxError {
    FormalParameterSequence formalsAST = null; // in case there's a syntactic error;

    SourcePosition formalsPos = new SourcePosition();
    start(formalsPos);
    FormalParameter fpAST = parseFormalParameter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      FormalParameterSequence fpsAST = parseProperFormalParameterSequence();
      finish(formalsPos);
      formalsAST = new MultipleFormalParameterSequence(fpAST, fpsAST,
        formalsPos);

    } else {
      finish(formalsPos);
      formalsAST = new SingleFormalParameterSequence(fpAST, formalsPos);
    }
    return formalsAST;
  }

  FormalParameter parseFormalParameter() throws SyntaxError {
    FormalParameter formalAST = null; // in case there's a syntactic error;

    SourcePosition formalPos = new SourcePosition();
    start(formalPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
      {
        Identifier iAST = parseIdentifier();
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new ConstFormalParameter(iAST, tAST, formalPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new VarFormalParameter(iAST, tAST, formalPos);
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        finish(formalPos);
        formalAST = new ProcFormalParameter(iAST, fpsAST, formalPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new FuncFormalParameter(iAST, fpsAST, tAST, formalPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a formal parameter",
        currentToken.spelling);
      break;

    }
    return formalAST;
  }


  ActualParameterSequence parseActualParameterSequence() throws SyntaxError {
    ActualParameterSequence actualsAST;

    SourcePosition actualsPos = new SourcePosition();

    start(actualsPos);
    if (currentToken.kind == Token.RPAREN) {
      finish(actualsPos);
      actualsAST = new EmptyActualParameterSequence(actualsPos);

    } else {
      actualsAST = parseProperActualParameterSequence();
    }
    return actualsAST;
  }

  ActualParameterSequence parseProperActualParameterSequence() throws SyntaxError {
    ActualParameterSequence actualsAST = null; // in case there's a syntactic error

    SourcePosition actualsPos = new SourcePosition();

    start(actualsPos);
    ActualParameter apAST = parseActualParameter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      ActualParameterSequence apsAST = parseProperActualParameterSequence();
      finish(actualsPos);
      actualsAST = new MultipleActualParameterSequence(apAST, apsAST,
        actualsPos);
    } else {
      finish(actualsPos);
      actualsAST = new SingleActualParameterSequence(apAST, actualsPos);
    }
    return actualsAST;
  }

  ActualParameter parseActualParameter() throws SyntaxError {
    ActualParameter actualAST = null; // in case there's a syntactic error

    SourcePosition actualPos = new SourcePosition();

    start(actualPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
    case Token.INTLITERAL:
    case Token.CHARLITERAL:
    case Token.OPERATOR:
    case Token.LET:
    case Token.IF:
    case Token.LPAREN:
    case Token.LBRACKET:
    case Token.LCURLY:
      {
        Expression eAST = parseExpression();
        finish(actualPos);
        actualAST = new ConstActualParameter(eAST, actualPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Vname vAST = parseVname();
        finish(actualPos);
        actualAST = new VarActualParameter(vAST, actualPos);
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        finish(actualPos);
        actualAST = new ProcActualParameter(iAST, actualPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        finish(actualPos);
        actualAST = new FuncActualParameter(iAST, actualPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start an actual parameter",
        currentToken.spelling);
      break;

    }
    return actualAST;
  }
////////////////////////////////////////////////////////////////////////////////
//
//Cambios
//
///////////////////////////////////////////////////////////////////////////////
//
// TYPE-DENOTERS
//
///////////////////////////////////////////////////////////////////////////////

  TypeDenoter parseTypeDenoter() throws SyntaxError {
    TypeDenoter typeAST = null; // in case there's a syntactic error
    SourcePosition typePos = new SourcePosition();

    start(typePos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
      {
        Identifier iAST = parseLongIdentifier();//cambio
        finish(typePos);
        typeAST = new SimpleTypeDenoter(iAST, typePos);
      }
      break;

    case Token.ARRAY:
      {
        acceptIt();
        IntegerLiteral ilAST = parseIntegerLiteral();
        accept(Token.OF);
        TypeDenoter tAST = parseTypeDenoter();
        finish(typePos);
        typeAST = new ArrayTypeDenoter(ilAST, tAST, typePos);
      }
      break;

    case Token.RECORD:
      {
        acceptIt();
        FieldTypeDenoter fAST = parseFieldTypeDenoter();
        accept(Token.END);
        finish(typePos);
        typeAST = new RecordTypeDenoter(fAST, typePos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a type denoter",
        currentToken.spelling);
      break;

    }
    return typeAST;
  }
  
  //////////////////////////////////////////////////////////////////////////////

  FieldTypeDenoter parseFieldTypeDenoter() throws SyntaxError {
    FieldTypeDenoter fieldAST = null; // in case there's a syntactic error

    SourcePosition fieldPos = new SourcePosition();

    start(fieldPos);
    Identifier iAST = parseIdentifier();
    accept(Token.COLON);
    TypeDenoter tAST = parseTypeDenoter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      FieldTypeDenoter fAST = parseFieldTypeDenoter();
      finish(fieldPos);
      fieldAST = new MultipleFieldTypeDenoter(iAST, tAST, fAST, fieldPos);
    } else {
      finish(fieldPos);
      fieldAST = new SingleFieldTypeDenoter(iAST, tAST, fieldPos);
    }
    return fieldAST;
  }
}

/*
 * @(#)Case.java                        2.1 2003/10/07
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

package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class Case extends AST {

  public Case (CaseLiterals cL1, Command c1AST, SourcePosition thePosition) {
    super (thePosition);
    this.cL1 = cL1;
    this.c1 = c1AST;

  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitCase(this, o);
  }

  public Triangle.AbstractSyntaxTrees.CaseLiterals cL1;
  public Triangle.AbstractSyntaxTrees.Command c1;
}
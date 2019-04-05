/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author kduran
 */
public class PrivateDeclaration extends Declaration{
    public PrivateDeclaration (Declaration dcl1, Declaration dcl2,
                    SourcePosition thePosition) {
    super (thePosition);
    this.dcl1 = dcl1;
    this.dcl2 = dcl2;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitPrivateDeclaration(this, o);
  }

  public Declaration dcl1, dcl2;
}


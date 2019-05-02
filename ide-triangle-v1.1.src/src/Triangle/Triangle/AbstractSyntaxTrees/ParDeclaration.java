/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ParDeclaration extends Declaration{
    public ParDeclaration (Declaration sdeclAST, 
                    SourcePosition thePosition) {
    super (thePosition);
    this.sdeclAST = sdeclAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitParDeclaration(this, o);
  }

  public Declaration sdeclAST;
}


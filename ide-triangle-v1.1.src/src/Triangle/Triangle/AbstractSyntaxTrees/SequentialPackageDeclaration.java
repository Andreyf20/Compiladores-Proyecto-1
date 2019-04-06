/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialPackageDeclaration extends Declaration{
    
    public SequentialPackageDeclaration (Declaration decl1, Declaration decl2,
                    SourcePosition thePosition) {
    super (thePosition);
    this.decl1 = decl1;
    this.decl2 = decl2;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitSequentialPackageDeclaration(this, o);
  }

  public Declaration decl1 = null;
  public Declaration decl2 = null;
}


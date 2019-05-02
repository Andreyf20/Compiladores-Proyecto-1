/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class Long_Identifier extends Identifier{
    
    public Long_Identifier (Identifier optionalIdentifier1, Identifier identifier2, SourcePosition sourcePosition, String theSpelling) {
        super(theSpelling, sourcePosition);
    this.optionalIdentifier1 = optionalIdentifier1;
    this.identifier2 = identifier2;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitLong_Identifier(this, o);
  }

  public Identifier optionalIdentifier1 = null;
  public Identifier identifier2;
}


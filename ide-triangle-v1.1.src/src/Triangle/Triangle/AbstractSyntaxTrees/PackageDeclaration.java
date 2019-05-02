/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class PackageDeclaration extends Declaration{
    
    public PackageDeclaration (Identifier identifier, Declaration decl, SourcePosition sourcePosition, String theSpelling) {
        super(sourcePosition);
        this.identifier = identifier;
        this.decl = decl;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitPackageDeclaration(this, o);
  }

  public Identifier identifier;
  public Declaration decl;
}


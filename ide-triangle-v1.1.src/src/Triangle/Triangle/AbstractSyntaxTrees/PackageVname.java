/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

/**
 *
 * @author torre
 */
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.Visitor;
import Triangle.AbstractSyntaxTrees.Vname;
import Triangle.SyntacticAnalyzer.SourcePosition;

public class PackageVname extends Vname {

  public PackageVname (Identifier packageID, Identifier iAST, SourcePosition thePosition) {
    super (thePosition);
    pI = packageID;
    I = iAST;
  }

  public Object visit (Visitor v, Object o) {
    return v.visitPackageVname(this, o);
  }

  public Identifier pI;
  public Identifier I;
}

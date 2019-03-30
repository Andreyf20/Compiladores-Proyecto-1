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
public class ForWhileCommand extends Command{
    public ForWhileCommand (Triangle.AbstractSyntaxTrees.Declaration dAST, Triangle.AbstractSyntaxTrees.Expression e2AST, Triangle.AbstractSyntaxTrees.Expression e3AST, Triangle.AbstractSyntaxTrees.Command cAST,
                    SourcePosition thePosition) {
    super (thePosition);
    D = dAST;
    E2 = e2AST;
    E3 = e3AST;
    C = cAST;
  }
  
  public Object visit(Triangle.AbstractSyntaxTrees.Visitor v, Object o) {
    return v.visitForWhileCommand(this, o);
  }

  public Triangle.AbstractSyntaxTrees.Declaration D;
  public Triangle.AbstractSyntaxTrees.Expression E2;
  public Triangle.AbstractSyntaxTrees.Expression E3;
  public Triangle.AbstractSyntaxTrees.Command C;
}

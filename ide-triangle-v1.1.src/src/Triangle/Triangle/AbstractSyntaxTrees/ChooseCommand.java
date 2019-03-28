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
public class ChooseCommand extends Command{
    public ChooseCommand (Triangle.AbstractSyntaxTrees.Expression eAST, Triangle.AbstractSyntaxTrees.Command c1AST,
                    SourcePosition thePosition) {
    super (thePosition);
    E = eAST;
    C1 = c1AST;
  }
  
  public Object visit(Triangle.AbstractSyntaxTrees.Visitor v, Object o) {
    return v.visitChooseCommand(this, o);
  }

  public Triangle.AbstractSyntaxTrees.Expression E;
  public Triangle.AbstractSyntaxTrees.Command C1;
}


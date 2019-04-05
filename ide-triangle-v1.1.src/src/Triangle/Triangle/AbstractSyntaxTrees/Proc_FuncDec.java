/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class Proc_FuncDec extends Declaration{
    
    public Proc_FuncDec (FuncDeclaration func, ProcDeclaration proc,
                    SourcePosition thePosition) {
    super (thePosition);
    this.func = func;
    this.proc = proc;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitProc_FuncDec(this, o);
  }

  public FuncDeclaration func = null;
  public ProcDeclaration proc = null;
}


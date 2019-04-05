/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialProcFuncs extends Declaration{
    
    public SequentialProcFuncs (SequentialProcFuncs rootSPF, Proc_FuncDec leafProc,
                    SourcePosition thePosition) {
    super (thePosition);
    this.rootSPF = rootSPF;
    this.leafProc = leafProc;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitSequentialProcFuncs(this, o);
  }

  public SequentialProcFuncs rootSPF = null;
  public Proc_FuncDec leafProc = null;
}


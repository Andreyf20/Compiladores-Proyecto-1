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
public class Comment extends AST {
    public String spelling;
    
    public Comment (SourcePosition thePosition) {
        super (thePosition);
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitComment(this, o);
    }
}

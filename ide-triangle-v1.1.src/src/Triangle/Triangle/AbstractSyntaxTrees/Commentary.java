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
public class Commentary {
    
    public String spelling;
    public SourcePosition position;
    
    public Commentary (SourcePosition thePosition, String spelling) {
        this.spelling = spelling;
        position = thePosition;
    }
    
}

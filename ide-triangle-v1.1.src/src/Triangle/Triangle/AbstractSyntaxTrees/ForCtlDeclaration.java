/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Eduardo Jirón
 */
public abstract class ForCtlDeclaration extends Declaration
{

    public ForCtlDeclaration(SourcePosition thePosition)
    {
        super(thePosition);
        id = null;
        expression = null;
    }
    
    public Identifier id;
    public Expression expression;
}

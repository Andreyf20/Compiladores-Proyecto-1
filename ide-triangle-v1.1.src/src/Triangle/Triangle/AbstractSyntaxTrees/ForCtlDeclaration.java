/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * Cambio: se agrego la clase para el FoCtlDeclaration
 * @author Eduardo Jiron
 */
public class ForCtlDeclaration extends Declaration
{

    public ForCtlDeclaration(Identifier iAST, Expression eAST, SourcePosition thePosition)
    {
        super(thePosition);
        id = iAST;
        expression = eAST;
    }
    
    public Identifier id;
    public Expression expression;

    @Override
    public Object visit(Visitor v, Object o)
    {
        return v.visitForCtlDeclaration(this, o);
    }
}

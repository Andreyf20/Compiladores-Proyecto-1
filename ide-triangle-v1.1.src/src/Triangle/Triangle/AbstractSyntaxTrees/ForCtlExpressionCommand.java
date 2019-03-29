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
public class ForCtlExpressionCommand extends Command
{

    public ForCtlExpressionCommand(Identifier iAST, Expression eAST, SourcePosition commandPos)
    {
        super(commandPos);
    }
    
    @Override
    public Object visit(Visitor v, Object o)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

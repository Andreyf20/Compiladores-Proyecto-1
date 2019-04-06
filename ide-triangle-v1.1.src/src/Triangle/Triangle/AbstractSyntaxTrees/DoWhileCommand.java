/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Eduardo Jirn
 */
public class DoWhileCommand extends Command
{
    public DoWhileCommand(Command cAST, Expression eAST, SourcePosition commandPos)
    {
        super(commandPos);
        this.cAST = cAST;
        this.eAST = eAST;
    }
    
    @Override
    public Object visit(Visitor v, Object o)
    {
        return v.visitDoWhileCommand(this, o);
    }
    
    public Command cAST;
    public Expression eAST;
}
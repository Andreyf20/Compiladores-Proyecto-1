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
public class ForDoCommand extends Command
{
	public Identifier I;
	public Expression E1, E2;
	public Command C;

    public ForDoCommand(Identifier iAST, Expression e1AST, Expression e2AST,
            Command commandAST, SourcePosition commandPos)
    {
    	super(commandPos);
    	I = iAST;
    	E1 = e1AST;
    	E2 = e2AST;
    	C = commandAST;
    }

    @Override
    public Object visit(Visitor v, Object o)
    {
        return this;
    }
    
}

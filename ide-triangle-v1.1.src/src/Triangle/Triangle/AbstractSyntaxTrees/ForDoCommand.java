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
    public ForDoCommand(ForCtlDeclaration ctlAST, Expression e2AST,
            Command commandAST, SourcePosition commandPos)
    {
    	super(commandPos);
    	FCD = ctlAST;
    	E1 = e2AST;
    	C = commandAST;
    }

    @Override
    public Object visit(Visitor v, Object o)
    {
        return v.visitForDoCommand(this, o);
    }
    
    public ForCtlDeclaration FCD;
    public Expression E1;
    public Command C;
}

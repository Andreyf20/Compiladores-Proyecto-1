/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.TreeWriterHTML1;

import Triangle.AbstractSyntaxTrees.AnyTypeDenoter;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.BinaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.BoolTypeDenoter;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.Case;
import Triangle.AbstractSyntaxTrees.CaseLiteral;
import Triangle.AbstractSyntaxTrees.CaseLiterals;
import Triangle.AbstractSyntaxTrees.CaseRange;
import Triangle.AbstractSyntaxTrees.Cases;
import Triangle.AbstractSyntaxTrees.CharTypeDenoter;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ChooseCommand;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.DoUntilCommand;
import Triangle.AbstractSyntaxTrees.DoWhileCommand;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyExpression;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.ErrorTypeDenoter;
import Triangle.AbstractSyntaxTrees.ForCtlDeclaration;
import Triangle.AbstractSyntaxTrees.ForDoCommand;
import Triangle.AbstractSyntaxTrees.ForUntilCommand;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
import Triangle.AbstractSyntaxTrees.IntTypeDenoter;
import Triangle.AbstractSyntaxTrees.IntegerExpression;
import Triangle.AbstractSyntaxTrees.IntegerLiteral;
import Triangle.AbstractSyntaxTrees.LetCommand;
import Triangle.AbstractSyntaxTrees.LetExpression;
import Triangle.AbstractSyntaxTrees.MultipleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleArrayAggregate;
import Triangle.AbstractSyntaxTrees.MultipleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.MultipleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleRecordAggregate;
import Triangle.AbstractSyntaxTrees.Operator;
import Triangle.AbstractSyntaxTrees.ProcActualParameter;
import Triangle.AbstractSyntaxTrees.ProcDeclaration;
import Triangle.AbstractSyntaxTrees.ProcFormalParameter;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.AbstractSyntaxTrees.RecordExpression;
import Triangle.AbstractSyntaxTrees.RecordTypeDenoter;
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleTypeDenoter;
import Triangle.AbstractSyntaxTrees.SimpleVname;
import Triangle.AbstractSyntaxTrees.SingleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleArrayAggregate;
import Triangle.AbstractSyntaxTrees.SingleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.SingleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleRecordAggregate;
import Triangle.AbstractSyntaxTrees.SubscriptVname;
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.UnaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.UntilCommand;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
import Triangle.AbstractSyntaxTrees.VarDeclarationInitialized;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.Visitor;
import Triangle.AbstractSyntaxTrees.VnameExpression;
import Triangle.AbstractSyntaxTrees.WhileCommand;
import Triangle.AbstractSyntaxTrees.ForWhileCommand;
import Triangle.AbstractSyntaxTrees.Long_Identifier;
import Triangle.AbstractSyntaxTrees.PackageDeclaration;
import Triangle.AbstractSyntaxTrees.ParDeclaration;
import Triangle.AbstractSyntaxTrees.PrivateDeclaration;
import Triangle.AbstractSyntaxTrees.RecursiveDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialCase;
import Triangle.AbstractSyntaxTrees.SequentialCaseLiterals;
import Triangle.AbstractSyntaxTrees.SequentialPackageDeclaration;
import Triangle.AbstractSyntaxTrees.Comment;
import Triangle.AbstractSyntaxTrees.PackageVname;

import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author kduran
 */
public class WriterVisitor implements Visitor {
    
    //this code was modified------------------------------------------------------------------------------------
      
    private FileWriter fileWriter;

    WriterVisitor(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }
    
    // Commands
    public Object visitAssignCommand(AssignCommand ast, Object obj) {
        writeLineHTML("<div class=\"AssignCommand\"");
        ast.V.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"> := </p>");
        ast.E.visit(this, null);
        writeLineHTML("</div");
        return null;
    }

    public Object visitCallCommand(CallCommand ast, Object obj) {
        writeLineHTML("<div class=\"callCommand\">");
        ast.I.visit(this, null);
        ast.APS.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitChooseCommand(ChooseCommand ast, Object o) {
        writeLineHTML("<div class=\"ChooseCommand\">");
        writeLineHTML("<p style=\"color: #000000;\"> </br><strong>choose</strong></p>");
        ast.E.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \">  </br><strong>from</strong>   </p>");
        if(ast.C1 != null){
            ast.C1.visit(this, null);
        }
        writeLineHTML("<p style=\"color: #000000; \">  </br><strong>end</strong>   </p>");
        writeLineHTML("</div>");
        return null;
    }
    
    //Cambio: se agrego Visit de DoUntilCommand
    public Object visitDoUntilCommand(DoUntilCommand ast, Object o)
    {
        writeLineHTML("<div class=\"DoUntilCommand\">");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>loop</strong> </p>");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>do</strong> </p>");
        ast.cAST.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>until</strong> </p>");
        ast.eAST.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }
    
    //Cambio: se agrego Visit de DoWhileCommand
    public Object visitDoWhileCommand(DoWhileCommand ast, Object o)
    {
        writeLineHTML("<div class=\"DoWhileCommand\">");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>loop</strong> </p>");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>do</strong> </p>");
        ast.cAST.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>while</strong> </p>");
        ast.eAST.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }
    
    public Object visitEmptyCommand(EmptyCommand ast, Object obj) {
        writeLineHTML("<div class=\"EmptyCommand\">\n<p style=\"color: #000000; \"><strong>&ensp;pass</strong> </p>\n</div>");
        return null;
    }

    public Object visitForUntilCommand(ForUntilCommand ast, Object o) {
        writeLineHTML("<div class=\"ForUntilCommand\">");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>loop</strong></p>");
        writeLineHTML("\t<p style=\"color: #000000; \"></br><strong>for</strong></br></p>" );
        ast.D.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>to</strong></br></p>");
        ast.E2.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>until</strong></br></p>");
        ast.E3.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>do</strong></br></p>");
        ast.C.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>end</strong></br></p>");
        writeLineHTML("</div>");
        return null;
    }
    
    public Object visitForWhileCommand(ForWhileCommand ast, Object o) {
        writeLineHTML("<div class=\"ForWhileCommand\">");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>loop</strong></p>");
        writeLineHTML("\t<p style=\"color: #000000; \"></br><strong>for</strong></br></p>" );
        ast.D.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>to</strong></br></p>");
        ast.E2.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>while</strong></br></p>");
        ast.E3.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>do</strong></br></p>");
        ast.C.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>end</strong></br></p>");
        writeLineHTML("</div>");
        return null;
    }
    
    public Object visitIfCommand(IfCommand ast, Object obj) {
        writeLineHTML("<div class=\"IfCommand\"");
        writeLineHTML("\t<p style=\"color: #000000;"
                            + "\">" 
                                + "</br></br><strong>if</strong><p style=\"color: #000000;\">(</p>" );
        ast.E.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \">)</br><strong>then</strong></br></p>");
        ast.C1.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>else</strong></br></p>");
        ast.C2.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>end</strong></br></p>");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitLetCommand(LetCommand ast, Object obj) {
        writeLineHTML("<div class=\"LetCommand\"");
        writeLineHTML("<p style=\"color: #000000; \"> </br><strong>let</strong></p>");
        ast.D.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"> </br><strong>in</strong> </br></p>");
        ast.C.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"> </br><strong>end</strong></br></p>");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitSequentialCommand(SequentialCommand ast, Object obj) {
        writeLineHTML("<div class=\"SequentialCommand\"");
        ast.C1.visit(this, null);
        writeLineHTML("<p class=\"SequentialDeclaration\"style=\"color: #000000;\">;</br></p>");
        ast.C2.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitUntilCommand(UntilCommand ast, Object o) {
        writeLineHTML("<div class=\"UntilCommand\"");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>loop</strong></p>");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>until</strong></br></p>");
        ast.E.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>do</strong></br></p>");
        ast.C.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>end</strong></br></p>");
        writeLineHTML("</div>");
        return null;
    }
    
    public Object visitWhileCommand(WhileCommand ast, Object obj) {
        writeLineHTML("<div class=\"WhileCommand\"");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>loop</strong></p>");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>while</strong></br></p>");
        ast.E.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>do</strong></br></p>");
        ast.C.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>end</strong></br></p>");
        writeLineHTML("</div>");
        return null;
    }


    // Expressions
    public Object visitArrayExpression(ArrayExpression ast, Object obj) {
        writeLineHTML("<div class=\"ArrayExpression\"");
        writeLineHTML("<p style=\"color: #000000; \"> [ </p>");
        ast.AA.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"> ] </p>");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitBinaryExpression(BinaryExpression ast, Object obj) {
        writeLineHTML("<div class=\"BinaryExpression\"");
        ast.E1.visit(this, null);
        ast.O.visit(this, null);
        ast.E2.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitCallExpression(CallExpression ast, Object obj) {
        writeLineHTML("<div class=\"CallExpression\"");
        writeLineHTML("<p style=\"color: #2E6FE; \">" + ast.I.spelling + "</p>");
        ast.I.visit(this, null);
        ast.APS.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitCharacterExpression(CharacterExpression ast, Object obj) {
        writeLineHTML("<div class=\"CharacterExpression\"");
        writeLineHTML("<p style=\"color: #2E64FE; \">"+ ast.CL.spelling + "</p>");
        ast.CL.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitEmptyExpression(EmptyExpression ast, Object obj) {
        writeLineHTML("<div class=\"EmptyExpression\"");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitIfExpression(IfExpression ast, Object obj) {
        writeLineHTML("<div class=\"IfExpression\"");
        writeLineHTML("<p style=\"color: #000000; \"></br></br><strong>if</strong></p>");
        ast.E1.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>then</strong></p>");
        ast.E2.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"></br><strong>else</strong></p></p>");
        ast.E3.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitIntegerExpression(IntegerExpression ast, Object obj) {
        writeLineHTML("<div class=\"IntegerExpression\"");
        writeLineHTML("<p style=\"color: #2E64FE;\">"+ ast.IL.spelling + "</p>");
        ast.IL.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitLetExpression(LetExpression ast, Object obj) {
        writeLineHTML("<div class=\"LetExpression\"");
        writeLineHTML("<p style=\"color: #000000;\"></br><strong>let</strong></p>");
        ast.D.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"></br><strong>in</strong></p>");
        ast.E.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitRecordExpression(RecordExpression ast, Object obj) {
        writeLineHTML("<div class=\"RecordExpression\"");
        writeLineHTML("<p style=\"color: #000000;\"> {</p>");
        ast.RA.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"> }</p>");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitUnaryExpression(UnaryExpression ast, Object obj) {
        writeLineHTML("<div class=\"UnaryExpression\"");
        writeLineHTML("<p style=\"color: #000000;\">"+ast.O.spelling + "</p>");
        ast.O.visit(this, null);
        ast.E.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitVnameExpression(VnameExpression ast, Object obj) {
        writeLineHTML("<div class=\"VnameExpression\"");
        ast.V.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }


    // Declarations
    public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object obj) {
        writeLineHTML("<div class=\"BinaryOperatorDeclaration\"");
        ast.O.visit(this, null);
        ast.ARG1.visit(this, null);
        ast.ARG2.visit(this, null);
        ast.RES.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitConstDeclaration(ConstDeclaration ast, Object obj) {
        writeLineHTML("<div class=\"ConstDeclaration\"");
        writeLineHTML("<p style=\"color: #000000;\"></br><strong>&ensp;const</strong></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"><strong>~</strong></p>");
        ast.E.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitFuncDeclaration(FuncDeclaration ast, Object obj) {
        writeLineHTML("<div class=\"FuncDeclaration\"");
        writeLineHTML("<p style=\"color: #000000;\"></br><strong>func</strong></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"> ( </p>");
        ast.FPS.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"> ) </p>");
        writeLineHTML("<p style=\"color: #000000;\"> : </p>");
        ast.T.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\">~</p>");
        ast.E.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitProcDeclaration(ProcDeclaration ast, Object obj) {
        writeLineHTML("<div class=\"ProcDeclaration\"");
        writeLineHTML("<p style=\"color: #000000;\"></br><strong>proc</strong> </p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"> (</p>");
        ast.FPS.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"> )</p>");
        writeLineHTML("<p style=\"color: #000000;\">~</p>");
        ast.C.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"></br><strong>end</strong>   </p>");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitSequentialDeclaration(SequentialDeclaration ast, Object obj) {
        writeLineHTML("<div class=\"SequentialDeclaration\"");
        writeLineHTML("<p class=\"SequentialDeclaration\"style=\"color: #000000;\"></p>");
        ast.D1.visit(this, null);
        writeLineHTML("<p class=\"SequentialDeclaration\"style=\"color: #000000;\">;</p>");
        ast.D2.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitTypeDeclaration(TypeDeclaration ast, Object obj) {
        writeLineHTML("<div class=\"TypeDeclaration\"");
        writeLineHTML("<p class=\"TypeDeclaration\"style=\"color: #000000;\"></br><strong>&ensp;type</strong></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p class=\"TypeDeclaration\"style=\"color: #000000;\">~</p>");
        ast.T.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object obj) {
        writeLineHTML("<div class=\"UnaryOperatorDeclaration\"");
        ast.O.visit(this, null);
        ast.ARG.visit(this, null);
        ast.RES.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitVarDeclaration(VarDeclaration ast, Object obj) {
        writeLineHTML("<div class=\"VarDeclaration\"");
        writeLineHTML("<p style=\"color: #000000;\"></br><strong>&ensp;var</strong> </p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"> : </p>");
        ast.T.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }
    
    public Object visitVarDeclarationInitialized(VarDeclarationInitialized ast, Object obj) {
        writeLineHTML("<div class=\"DeclarationInitialized\"");
        writeLineHTML("<p style=\"color: #000000;\"></br><strong>&ensp;var</strong> </p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"> ::= </p>");
        ast.E.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }
    
    //Cases
    @Override
    public Object visitCases(Cases ast, Object o) {
        writeLineHTML("<div class=\"Cases\"");
        ast.SC1.visit(this, null);
        if(ast.command1 != null){
            writeLineHTML("<p style=\"color: #000000;\"></br><strong>else</strong></p>");
        ast.command1.visit(this, null);
        }
        writeLineHTML("</div>");
        return null;
    }
    
    @Override
    public Object visitSequentialCase(SequentialCase ast, Object o) {
        writeLineHTML("<div class=\"SequentialCase\"");
        if(ast.C1 != null){
            ast.C1.visit(this, null);
        }
        writeLineHTML("<p class=\"SequentialDeclaration\"style=\"color: #000000;\">;</p>");
        ast.C2.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    @Override
    public Object visitCase(Case ast, Object o) {
        writeLineHTML("<div class=\"Case\"");
        writeLineHTML("<p style=\"color: #000000;\"></br><strong>when</strong>  </p>");
        ast.cL1.visit(this, null);
        writeLineHTML("<p style=\"color: #000000;\"> </br><strong>then</strong>   </p>");
        ast.c1.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }
    
    @Override
    public Object visitSequentialCaseLiterals(SequentialCaseLiterals ast, Object o) {
        writeLineHTML("<div class=\"SequentialCaseLiterals\"");
        if(ast.SC1 != null){
            ast.SC1.visit(this, null);
            writeLineHTML("<p style=\"color: #000000;\">|</p>");
            ast.cR2.visit(this, null); 
        }
        else{
            ast.cR2.visit(this, null);
        }
        writeLineHTML("</div>");
        return null;
    }

    @Override
    public Object visitCaseLiterals(CaseLiterals ast, Object o) {
        writeLineHTML("<div class=\"CaseLiterals\"");
        ast.SCL1.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    @Override
    public Object visitCaseRange(CaseRange ast, Object o) {
        writeLineHTML("<div class=\"CaseRange\"");
        ast.cL1.visit(this, null);
        String data = "";
        data += "<p style=\"color: #2E64FE;\">";
        if(ast.cL1.IL1 != null)
            data += ast.cL1.IL1.spelling;
        else if(ast.cL1.CL1 != null)
            data += ast.cL1.CL1.spelling;
        
        if(ast.cL2 != null){
            ast.cL2.visit(this, null);
            data += "<p style=\"color: #000000;\">..</p>"
                    + "<p style=\"color: #2E64FE;\">";
            if(ast.cL2.IL1 != null)
                data += ast.cL2.IL1.spelling;
            else if(ast.cL2.CL1 != null)
                data += ast.cL2.CL1.spelling;
            data += "</p>";
        } 
        data += "</p>";
        writeLineHTML(data);
        writeLineHTML("</div>");
        return null;
    }

    @Override
    public Object visitCaseLiteral(CaseLiteral ast, Object o) {
        writeLineHTML("<div class=\"CaseLiteral\"");
        if(ast.CL1 == null){
            writeLineHTML("<IntLiteral>");
            ast.IL1.visit(this, null);
            writeLineHTML("</IntLiteral>"); 
        }
        else{
            writeLineHTML("<CharLiteral>");
            ast.CL1.visit(this, null);
            writeLineHTML("</CharLiteral>"); 
        }
        writeLineHTML("</div>");
        return null;
    }

    // Array Aggregates
    public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object obj) {
        writeLineHTML("<div class=\"MultipleArrayAggregate\"");
        ast.E.visit(this, null);
        ast.AA.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object obj) {
        writeLineHTML("<div class=\"SingleArrayAggregate\"");
        ast.E.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }


    // Record Aggregates
    public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object obj) {
        writeLineHTML("<div class=\"MultipleRecordAggregate\"");
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        ast.RA.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object obj) {
        writeLineHTML("<div class=\"SingleRecordAggregate\"");
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }


    // Formal Parameters
    public Object visitConstFormalParameter(ConstFormalParameter ast, Object obj) {
        writeLineHTML("<div class=\"ConstFormalParameter\">");
        writeLineHTML("<p class=\"ConstFormalParameter\" style=\"color: #000000; \"></br><strong></strong></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p class=\"ConstFormalParameter\" style=\"color: #000000; \">:</p>");
        ast.T.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitFuncFormalParameter(FuncFormalParameter ast, Object obj) {
        writeLineHTML("<div class=\"FuncFormalParameter\">");
        writeLineHTML("<p class=\"FuncFormalParameter\" style=\"color: #000000; \"></br><strong>func</strong></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p class=\"FuncFormalParameter\" style=\"color: #000000; \">(</p>");
        ast.FPS.visit(this, null);
        writeLineHTML("<p class=\"FuncFormalParameter\" style=\"color: #000000; \">)</p>");
        writeLineHTML("<p class=\"FuncFormalParameter\" style=\"color: #000000; \">:</p>");
        ast.T.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitProcFormalParameter(ProcFormalParameter ast, Object obj) {
        writeLineHTML("<div class=\"ProcFormalParameter\">");
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000; \"></br><strong>proc</strong></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000; \">(</p>");
        ast.FPS.visit(this, null);
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000; \">)</p>");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitVarFormalParameter(VarFormalParameter ast, Object obj) {
        writeLineHTML("<div class=\"VarFormalParameter\">");
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000; \"></br><strong>&ensp;var</strong></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000; \">:</p>");
        ast.T.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }


    public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object obj) {
        writeLineHTML("<div class=\"EmptyFormalParameterSequence\">");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object obj) {
        writeLineHTML("<div class=\"MultipleFormalParameterSequence\">");
        ast.FP.visit(this, null);
        ast.FPS.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object obj) {
        writeLineHTML("<div class=\"SingleFormalParameterSequence\">");
        ast.FP.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }


    // Actual Parameters
    public Object visitConstActualParameter(ConstActualParameter ast, Object obj) {
        writeLineHTML("<div class=\"ConstActualParameter\">");
        ast.E.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitFuncActualParameter(FuncActualParameter ast, Object obj) {
        writeLineHTML("<div class=\"FuncActualParameter\">");
        ast.I.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitProcActualParameter(ProcActualParameter ast, Object obj) {
        writeLineHTML("<div class=\"ProcActualParameter\">");
        ast.I.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitVarActualParameter(VarActualParameter ast, Object obj) {
        writeLineHTML("<div class=\"VarActualParameter\">");
        ast.V.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }


    public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object obj) {
        writeLineHTML("<div class=\"EmptyActualParameterSequence\">");
        writeLineHTML("<p class=\"emptyParameterSequence\" style=\"color: #000000;\">()</p>");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object obj) {
        writeLineHTML("<div class=\"MultipleActualParameterSequence\">");
        writeLineHTML("<p class=\"multipleParameterSequence\" style=\"color: #000000;\"></(");
        ast.AP.visit(this, null);
        ast.APS.visit(this, null);
        writeLineHTML(")  </p>");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object obj) {
        writeLineHTML("<div class=\"SingleActualParameterSequence\">");
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000; \">(</p>");
        ast.AP.visit(this, null);
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000; \">)  </p>");
        writeLineHTML("</div>");
        return null;
    }


    // Type Denoters
    public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object obj) {
        writeLineHTML("<div class=\"AnyTypeDenoter\">");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object obj) {
        writeLineHTML("<div class=\"ArrayTypeDenoter\">");
        ast.IL.visit(this, null);
        ast.T.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object obj) {
        writeLineHTML("<div class=\"BoolTypeDenoter\">");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitCharTypeDenoter(CharTypeDenoter ast, Object obj) {
        writeLineHTML("<div class=\"CharTypeDenoter\">");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object obj) {
        writeLineHTML("<div class=\"ErrorTypeDenoter\">");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object obj) {
        writeLineHTML("<div class=\"SimpleTypeDenoter\">");
        ast.I.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitIntTypeDenoter(IntTypeDenoter ast, Object obj) {
        writeLineHTML("<div class=\"IntTypeDenoter\">");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object obj) {
        writeLineHTML("<div class=\"RecordTypeDenoter\">");
        ast.FT.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object obj) {
        writeLineHTML("<div class=\"RecordTypeDenoter\">");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        ast.FT.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object obj) {
        writeLineHTML("<div class=\"SingleFieldTypeDenoter\">");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }


    // Literals
    public Object visitCharacterLiteral(CharacterLiteral ast, Object obj) {
        writeLineHTML("<div class=\"CharacterLiteral\">");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitIntegerLiteral(IntegerLiteral ast, Object obj) {
        writeLineHTML("<div class=\"IntegerLiteral\">");
        writeLineHTML("</div>");
        return null;
    }
    
    //Identifiers and Operators
    public Object visitIdentifier(Identifier ast, Object obj) {
        writeLineHTML("<div class=\"Identifier\">");
        writeLineHTML("\t<p style=\"color: #000000;"
                                    + "\">&ensp;" 
                                + ast.spelling + 
                        "</p>");
        writeLineHTML("</div>");
        return null;
    }

    public Object visitOperator(Operator ast, Object obj) {
        writeLineHTML("<div class=\"Operator\">");
        writeLineHTML("\t<p style=\"color: #000000;"
                                    + "\">&ensp;" 
                                + ast.spelling + 
                        "</p>");
        writeLineHTML("</div>");
        return null;
    }


    // Value-or-variable names
    public Object visitDotVname(DotVname ast, Object obj) {
        writeLineHTML("<div class=\"DotVname\">");
        ast.V.visit(this, null);
        ast.I.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }

    public Object visitSimpleVname(SimpleVname ast, Object obj) {
        writeLineHTML("<div class=\"SimpleVname\">");
        ast.I.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }
    
    public Object visitPackageVname(PackageVname ast, Object obj) {
        writeLineHTML("<PackageVname>");
        ast.pI.visit(this, null);
        ast.I.visit(this, null);
        writeLineHTML("</PackageVname>");
        return null;
    }

    public Object visitSubscriptVname(SubscriptVname ast, Object obj) {
        writeLineHTML("<div class=\"SubscriptVname\">");
        ast.V.visit(this, null);
        ast.E.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }


    // Programs
    public Object visitProgram(Program ast, Object obj) {
        writeLineHTML("<div class=\"SubscriptVname\">");
        if(ast.packageAST != null){
            ast.packageAST.visit(this, null);
        }
        ast.C.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }
    
    private void writeLineHTML(String line) {
        try {
            fileWriter.write(line);
            fileWriter.write('\n');
        } catch (IOException e) {
            System.err.println("Error while writing file for print the AST");
            e.printStackTrace();
        }
    }

    /*
     * Convert the characters "<" & "<=" to their equivalents in html
     */
    private String transformOperator(String operator) {
        if (operator.compareTo("<") == 0)
            return "&lt;";
        else if (operator.compareTo("<=") == 0)
            return "&lt;=";
        else
            return operator;
    }

    //Cambio: se agrego visit para ForDoCommand
    @Override
    public Object visitForDoCommand(ForDoCommand ast, Object o) {
        writeLineHTML("<div class=\"ForDoCommand\">");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>loop</strong></p>");
        writeLineHTML("\t<p style=\"color: #000000; \"></br><strong>for</strong></br></p>" );
        ast.FCD.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>to</strong></br></p>");
        ast.E1.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>do</strong></br></p>");
        ast.C.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>end</strong></br></p>");
        writeLineHTML("</div>");
        return null;
    }

    //Cambio: se agrego visit para ForCtlDeclaration
    @Override
    public Object visitForCtlDeclaration(ForCtlDeclaration ast, Object o) {
        writeLineHTML("<div class=\"ForCtlDeclaration\">");
        ast.id.visit(this, null);
        writeLineHTML("\t<p style=\"color: #000000; \"></br><strong>from</strong></br></p>" );
        ast.expression.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }


    @Override
    public Object visitRecursiveDeclaration(RecursiveDeclaration ast, Object o) {
        writeLineHTML("<div class=\"RecursiveDeclaration\">");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>recursive</strong></br></p>");
        ast.ProcFuncAST.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>end</strong></br></p>");
        writeLineHTML("</div>");
        return(null);
    }

    @Override
    public Object visitPrivateDeclaration(PrivateDeclaration ast, Object o) {
        writeLineHTML("<div class=\"PrivateDeclaration\">");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>private</strong></br></p>");
        ast.dcl1.visit(this, null);
        ast.dcl2.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>end</strong></br></p>");
        writeLineHTML("</div>");
        return null;
    }

    @Override
    public Object visitParDeclaration(ParDeclaration ast, Object o) {
        writeLineHTML("<div class=\"ParDeclaration\">");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>par</strong></br></p>");
        ast.sdeclAST.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>end</strong></br></p>");
        writeLineHTML("</div>");
        return null;
    }

    @Override
    public Object visitLong_Identifier(Long_Identifier ast, Object o) {
        writeLineHTML("<div class=\"Long_Identifier\">");
        writeLineHTML("<p class=\"Long_Identifier\"style=\"color: #000000; \"></p>");
            if(ast.optionalIdentifier1 == null){
            ast.identifier2.visit(this, null);
        }else{
            ast.optionalIdentifier1.visit(this, null);
            ast.identifier2.visit(this, null);
        }
        writeLineHTML("</div>");
        return null;
    }

    @Override
    public Object visitPackageDeclaration(PackageDeclaration ast, Object o) {
        writeLineHTML("<div class=\"PackageDeclaration\">");
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>package</strong></p>");
        ast.identifier.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"><strong>~</strong></br></p>");
        ast.decl.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; \"></br><strong>end</strong>;</br></p>");
        writeLineHTML("</div>");
        return null;
    }

    @Override
    public Object visitSequentialPackageDeclaration(SequentialPackageDeclaration ast, Object o) {
        writeLineHTML("<div class=\"SequentialPackageDeclaration\">");
        ast.decl1.visit(this, null);
        ast.decl2.visit(this, null);
        writeLineHTML("</div>");
        return null;
    }
    
    @Override
    public Object visitComment(Comment ast, Object o) {
        writeLineHTML("<div class=\"Comment\">");
        writeLineHTML("<p style=\"color: #04B431; \"></br>"+ ast.spelling +"</br></p>");
        writeLineHTML("</div>");
        return null;
    }
}       

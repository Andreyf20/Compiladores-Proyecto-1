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
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyExpression;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.ErrorTypeDenoter;
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

import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author kduran
 */
public class WriterVisitor implements Visitor {
    
    private FileWriter fileWriter;

    WriterVisitor(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }
    
    // Commands
    public Object visitAssignCommand(AssignCommand ast, Object obj) {
        //writeLineHTML("<AssignCommand>");
        ast.V.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> := </br> </p>");
        ast.E.visit(this, null);
        return null;
    }

    public Object visitCallCommand(CallCommand ast, Object obj) {
        //writeLineHTML("<p class=\"callCommand\">");
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> ( </br> </p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> ) </br> </p>");
        ast.APS.visit(this, null);
        writeLineHTML("</p>");
        return null;
    }

    public Object visitChooseCommand(ChooseCommand ast, Object o) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>choose</strong> </br> </p>");
        ast.E.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>from</strong> </br> </p>");
        ast.C1.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>end</strong> </br> </p>");
        return null;
    }
    
    public Object visitEmptyCommand(EmptyCommand ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>pass</strong> </br> </p>");
        return null;
    }

    public Object visitForUntilCommand(ForUntilCommand ast, Object o) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>loop</strong> </br> </p>");
        writeLineHTML("\t<p style=\"color: #000000;font-family: courier;font-size:1em;\"><strong>for</strong> </br> </p>" );
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>from</strong> </br> </p>");
        ast.E1.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>to</strong> </br> </p>");
        ast.E2.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>until</strong> </br> </p>");
        ast.E3.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>do</strong> </br> </p>");
        ast.C.visit(this, null);
        return null;
    }
    
    public Object visitForWhileCommand(ForWhileCommand ast, Object o) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>loop</strong> </br> </p>");
        writeLineHTML("\t<p style=\"color: #000000;font-family: courier;font-size:1em;\"><strong>for</strong> </br> </p>" );
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>from</strong> </br> </p>");
        ast.E1.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>to</strong> </br> </p>");
        ast.E2.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>while</strong> </br> </p>");
        ast.E3.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>do</strong> </br> </p>");
        ast.C.visit(this, null);
        return null;
    }
    
    public Object visitIfCommand(IfCommand ast, Object obj) {
        writeLineHTML("\t<p style=\"color: #000000;"
                            + "font-family: courier;"
                            + "font-size:1em;"
                            + "\">" 
                                + "<strong>if</strong><p style=\"color: #000000;\">&ensp;(</p>" );
        ast.E.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">) </br> <strong>then</strong> </br> </p>");
        ast.C1.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> </br> <strong>else</strong> </br> </p>");
        ast.C2.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> </br> <strong>end</strong> </br> </p>");
        
        return null;
    }

    public Object visitLetCommand(LetCommand ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> </br> <strong>let</strong> </br> </p>");
        ast.D.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>in</strong> </br> </p>");
        ast.C.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>end</strong> </br> </p>");
        return null;
    }

    public Object visitSequentialCommand(SequentialCommand ast, Object obj) {
        writeLineHTML("<SequentialCommand>");
        ast.C1.visit(this, null);
        ast.C2.visit(this, null);
        writeLineHTML("</SequentialCommand>");
        return null;
    }

    public Object visitUntilCommand(UntilCommand ast, Object o) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>loop</strong> </br> </p>");
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>until</strong> </br> </p>");
        ast.E.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>do</strong> </br> </p>");
        ast.C.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>end</strong> </br> </p>");
        return null;
    }
    
    public Object visitWhileCommand(WhileCommand ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"><strong>loop</strong> </br> </p>");
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>while</strong> </br> </p></p>");
        ast.E.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>do</strong> </br> </p></p>");
        ast.C.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>end</strong> </br> </p></p>");
        return null;
    }


    // Expressions
    public Object visitArrayExpression(ArrayExpression ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> [ </br> </p></p>");
        ast.AA.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> ] </br> </p></p>");
        return null;
    }

    public Object visitBinaryExpression(BinaryExpression ast, Object obj) {
        //writeLineHTML("<BinaryExpression>");
        ast.E1.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">" + ast.O.spelling + "</br> </p></p>");
        ast.O.visit(this, null);
        ast.E2.visit(this, null);
        //writeLineHTML("</BinaryExpression>");
        return null;
    }

    public Object visitCallExpression(CallExpression ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">" + ast.I.spelling + "</br> </p></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> ( </br> </p></p>");
        ast.APS.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> ) </br> </p></p>");
        return null;
    }

    public Object visitCharacterExpression(CharacterExpression ast, Object obj) {
        writeLineHTML("<p style=\"color: #2E64FE; font-family: courier; font-size:1em;\">"+ ast.CL.spelling + "</br> </p></p>");
        ast.CL.visit(this, null);
        //writeLineHTML("</CharacterExpression>");
        return null;
    }

    public Object visitEmptyExpression(EmptyExpression ast, Object obj) {
        writeLineHTML("<EmptyExpression/>");
        return null;
    }

    public Object visitIfExpression(IfExpression ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>if</strong> </br> </p></p>");
        ast.E1.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>then</strong> </br> </p></p>");
        ast.E2.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>else</strong> </br> </p></p>");
        ast.E3.visit(this, null);
        return null;
    }

    public Object visitIntegerExpression(IntegerExpression ast, Object obj) {
        writeLineHTML("<p style=\"color: #2E64FE; font-family: courier; font-size:1em;\"> "+ ast.IL.spelling + " </br> </p></p>");
        ast.IL.visit(this, null);
        //writeLineHTML("</IntegerExpression>");
        return null;
    }

    public Object visitLetExpression(LetExpression ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>let</strong> </br> </p></p>");
        ast.D.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>in</strong> </br> </p></p>");
        ast.E.visit(this, null);
        return null;
    }

    public Object visitRecordExpression(RecordExpression ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> { </br> </p></p>");
        ast.RA.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> } </br> </p></p>");
        return null;
    }

    public Object visitUnaryExpression(UnaryExpression ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> "+ast.O.spelling + " </br> </p></p>");
        ast.O.visit(this, null);
        ast.E.visit(this, null);
        return null;
    }

    public Object visitVnameExpression(VnameExpression ast, Object obj) {
        writeLineHTML("<VnameExpression>");
        ast.V.visit(this, null);
        writeLineHTML("</VnameExpression>");
        return null;
    }


    // Declarations
    public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object obj) {
        writeLineHTML("<BinaryOperatorDeclaration>");
        ast.O.visit(this, null);
        ast.ARG1.visit(this, null);
        ast.ARG2.visit(this, null);
        ast.RES.visit(this, null);
        writeLineHTML("</BinaryOperatorDeclaration>");
        return null;
    }

    public Object visitConstDeclaration(ConstDeclaration ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>const</strong> </br> </p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>~</strong> </br> </p>");
        ast.E.visit(this, null);
        return null;
    }

    public Object visitFuncDeclaration(FuncDeclaration ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>func</strong> </br> </p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  ( </br> </p>");
        ast.FPS.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  ) </br> </p>");
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  ; </br> </p>");
        ast.T.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  is </br> </p>");
        ast.E.visit(this, null);
        return null;
    }

    public Object visitProcDeclaration(ProcDeclaration ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>proc</strong> </br> </p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  ( </br> </p>");
        ast.FPS.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  ) </br> </p>");
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> is </br> </p>");
        ast.C.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>end</strong> </br> </p>");
        return null;
    }

    public Object visitSequentialDeclaration(SequentialDeclaration ast, Object obj) {
        writeLineHTML("<SequentialDeclaration>");
        ast.D1.visit(this, null);
        ast.D2.visit(this, null);
        writeLineHTML("</SequentialDeclaration>");
        return null;
    }

    public Object visitTypeDeclaration(TypeDeclaration ast, Object obj) {
        writeLineHTML("<TypeDeclaration>");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        writeLineHTML("</TypeDeclaration>");
        return null;
    }

    public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object obj) {
        writeLineHTML("<UnaryOperatorDeclaration>");
        ast.O.visit(this, null);
        ast.ARG.visit(this, null);
        ast.RES.visit(this, null);
        writeLineHTML("</UnaryOperatorDeclaration>");
        return null;
    }

    public Object visitVarDeclaration(VarDeclaration ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>var</strong> </br> </p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> ::= </br> </p>");
        ast.T.visit(this, null);
        return null;
    }
    
    public Object visitVarDeclarationInitialized(VarDeclarationInitialized ast, Object obj) {
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\">  <strong>var</strong> </br> </p>");
        ast.I.visit(this, null);
        writeLineHTML("<p style=\"color: #000000; font-family: courier; font-size:1em;\"> ::= </br> </p>");
        ast.E.visit(this, null);
        return null;
    }
    
    //Cases
    @Override
    public Object visitCases(Cases ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitCase(Case ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitCaseLiterals(CaseLiterals ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitCaseRange(CaseRange ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitCaseLiteral(CaseLiteral ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Array Aggregates
    public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object obj) {
        writeLineHTML("<MultipleArrayAggregate>");
        ast.E.visit(this, null);
        ast.AA.visit(this, null);
        writeLineHTML("</MultipleArrayAggregate>");
        return null;
    }

    public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object obj) {
        writeLineHTML("<SingleArrayAggregate>");
        ast.E.visit(this, null);
        writeLineHTML("</SingleArrayAggregate>");
        return null;
    }


    // Record Aggregates
    public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object obj) {
        writeLineHTML("<MultipleRecordAggregate>");
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        ast.RA.visit(this, null);
        writeLineHTML("</MultipleRecordAggregate>");
        return null;
    }

    public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object obj) {
        writeLineHTML("<SingleRecordAggregate>");
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        writeLineHTML("</SingleRecordAggregate>");
        return null;
    }


    // Formal Parameters
    public Object visitConstFormalParameter(ConstFormalParameter ast, Object obj) {
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\"><strong></strong></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\">:</p>");
        ast.T.visit(this, null);
        //writeLineHTML("</ConstFormalParameter>");
        return null;
    }

    public Object visitFuncFormalParameter(FuncFormalParameter ast, Object obj) {
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\"><strong>func</strong></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\">(</p>");
        ast.FPS.visit(this, null);
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\">)</p>");
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\">:</p>");
        ast.T.visit(this, null);
        writeLineHTML("<FuncFormalParameter>");
        return null;
    }

    public Object visitProcFormalParameter(ProcFormalParameter ast, Object obj) {
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\"><strong>proc</strong></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\">(</p>");
        ast.FPS.visit(this, null);
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\">)</p>");
        return null;
    }

    public Object visitVarFormalParameter(VarFormalParameter ast, Object obj) {
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\"><strong>var</strong></p>");
        ast.I.visit(this, null);
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\">:</p>");
        ast.T.visit(this, null);
        //writeLineHTML("</VarFormalParameter>");
        return null;
    }


    public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object obj) {
        writeLineHTML("<EmptyFormalParameterSequence/>");
        return null;
    }

    public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object obj) {
        writeLineHTML("<MultipleFormalParameterSequence>");
        ast.FP.visit(this, null);
        ast.FPS.visit(this, null);
        writeLineHTML("</MultipleFormalParameterSequence>");
        return null;
    }

    public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object obj) {
        writeLineHTML("<SingleFormalParameterSequence>");
        ast.FP.visit(this, null);
        writeLineHTML("</SingleFormalParameterSequence>");
        return null;
    }


    // Actual Parameters
    public Object visitConstActualParameter(ConstActualParameter ast, Object obj) {
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\"> <strong>const</strong></p>");
        ast.E.visit(this, null);
        return null;
    }

    public Object visitFuncActualParameter(FuncActualParameter ast, Object obj) {
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\"> <strong>func</strong></p>");
        ast.I.visit(this, null);
        return null;
    }

    public Object visitProcActualParameter(ProcActualParameter ast, Object obj) {
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\"> <strong>proc</strong></p>");
        ast.I.visit(this, null);
        return null;
    }

    public Object visitVarActualParameter(VarActualParameter ast, Object obj) {
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\"> <strong>var</strong></p>");
        ast.V.visit(this, null);
        return null;
    }


    public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object obj) {
        writeLineHTML("<p class=\"emptyParameterSequence\" style=\"color: #000000;"
                            + "font-family: courier;"
                            + "font-size:1em;\">()</p>");
        return null;
    }

    public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object obj) {
        writeLineHTML("<p class=\"multipleParameterSequence\" style=\"color: #000000;"
                            + "font-family: courier;"
                            + "font-size:1em;\">(");
        ast.AP.visit(this, null);
        ast.APS.visit(this, null);
        writeLineHTML(")</p>");
        return null;
    }

    public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object obj) {
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\">(</p>");
        ast.AP.visit(this, null);
        writeLineHTML("<p class=\"SingleActualParameterSequence\" style=\"color: #000000;font-family: courier;font-size:1em;\">)</p>");
        return null;
    }


    // Type Denoters
    public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object obj) {
        writeLineHTML("<AnyTypeDenoter/>");
        return null;
    }

    public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object obj) {
        writeLineHTML("<ArrayTypeDenoter>");
        ast.IL.visit(this, null);
        ast.T.visit(this, null);
        writeLineHTML("</ArrayTypeDenoter>");
        return null;
    }

    public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object obj) {
        writeLineHTML("<BoolTypeDenoter/>");
        return null;
    }

    public Object visitCharTypeDenoter(CharTypeDenoter ast, Object obj) {
        writeLineHTML("<CharTypeDenoter/>");
        return null;
    }

    public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object obj) {
        writeLineHTML("<ErrorTypeDenoter/>");
        return null;
    }

    public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object obj) {
        writeLineHTML("<SimpleTypeDenoter>");
        ast.I.visit(this, null);
        writeLineHTML("</SimpleTypeDenoter>");
        return null;
    }

    public Object visitIntTypeDenoter(IntTypeDenoter ast, Object obj) {
        writeLineHTML("<IntTypeDenoter/>");
        return null;
    }

    public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object obj) {
        writeLineHTML("<RecordTypeDenoter>");
        ast.FT.visit(this, null);
        writeLineHTML("</RecordTypeDenoter>");
        return null;
    }


    public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object obj) {
        writeLineHTML("<MultipleFieldTypeDenoter>");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        ast.FT.visit(this, null);
        writeLineHTML("</MultipleFieldTypeDenoter>");
        return null;
    }

    public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object obj) {
        writeLineHTML("<SingleFieldTypeDenoter>");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        writeLineHTML("</SingleFieldTypeDenoter>");
        return null;
    }


    // Literals
    public Object visitCharacterLiteral(CharacterLiteral ast, Object obj) {
        writeLineHTML("\t<p style=\"color: #2E64FE;"
                                    + "font-family: courier;"
                                    + "font-size:1em;"
                                    + "\">" 
                                + ast.spelling + 
                        "</p>");
        return null;
    }

    public Object visitIntegerLiteral(IntegerLiteral ast, Object obj) {
        writeLineHTML("\t<p style=\"color: #2E64FE;"
                                    + "font-family: courier;"
                                    + "font-size:1em;"
                                    + "\">" 
                                + ast.spelling + 
                        "</p>");
        return null;
    }
    
    //Identifiers and Operators
    public Object visitIdentifier(Identifier ast, Object obj) {
        writeLineHTML("\t<p style=\"color: #000000;"
                                    + "font-family: courier;"
                                    + "font-size:1em;"
                                    + "\">" 
                                + ast.spelling + 
                        "</p>");
        return null;
    }

    public Object visitOperator(Operator ast, Object obj) {
        writeLineHTML("\t<p style=\"color: #000000;"
                                    + "font-family: courier;"
                                    + "font-size:1em;"
                                    + "\">" 
                                + ast.spelling + 
                        "</p>");
        return null;
    }


    // Value-or-variable names
    public Object visitDotVname(DotVname ast, Object obj) {
        writeLineHTML("<DotVname>");
        ast.V.visit(this, null);
        ast.I.visit(this, null);
        writeLineHTML("</DotVname>");
        return null;
    }

    public Object visitSimpleVname(SimpleVname ast, Object obj) {
        writeLineHTML("<SimpleVname>");
        ast.I.visit(this, null);
        writeLineHTML("</SimpleVname>");
        return null;
    }

    public Object visitSubscriptVname(SubscriptVname ast, Object obj) {
        writeLineHTML("<SubscriptVname>");
        ast.V.visit(this, null);
        ast.E.visit(this, null);
        writeLineHTML("</SubscriptVname>");
        return null;
    }


    // Programs
    public Object visitProgram(Program ast, Object obj) {
        writeLineHTML("<Program>");
        ast.C.visit(this, null);
        writeLineHTML("</Program>");
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

    
       
}

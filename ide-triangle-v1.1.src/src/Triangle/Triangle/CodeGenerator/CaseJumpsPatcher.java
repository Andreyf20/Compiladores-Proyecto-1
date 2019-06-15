/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.CodeGenerator;

import Triangle.AbstractSyntaxTrees.Expression;

/**
 *
 * @author Jil
 */
public class CaseJumpsPatcher {
    
    CaseJumpsPatcher nextCase = null;
    Expression chooseExpression;
    Frame chooseFrame;
    
    int jmpIfCommandEx = 0,
        jumpOut = 0,//ends de execution dont match
        compareDdr = 0,//dir to compare
        jmpIfddrGreater = -1,//has to jump next compare dir
        jmpIfddrLess = 0,//jumps to the command dir
        jmp = 0;//unconditional jump dir

    public CaseJumpsPatcher(Expression exp, Frame f, int jmpIfCommandEx) {
        this.chooseExpression = exp;
        this.chooseFrame = f;
        this.jmpIfCommandEx = jmpIfCommandEx;
    }

    public int getJmpIfCommandEx() {
        return jmpIfCommandEx;
    }

    public void setJmpIfCommandEx(int jmpIfCommandEx) {
        this.jmpIfCommandEx = jmpIfCommandEx;
    }

    public Expression getChooseExpression() {
        return chooseExpression;
    }

    public void setChooseExpression(Expression chooseExpression) {
        this.chooseExpression = chooseExpression;
    }

    public Frame getChooseFrame() {
        return chooseFrame;
    }

    public void setChooseFrame(Frame chooseFrame) {
        this.chooseFrame = chooseFrame;
    }

    public int getJumpOut() {
        return jumpOut;
    }

    public void setJumpOut(int jumpOut) {
        this.jumpOut = jumpOut;
    }
    
    public CaseJumpsPatcher getNextCase() {
        return nextCase;
    }

    public void setNextCase(CaseJumpsPatcher nextCase) {
        this.nextCase = nextCase;
    }

    public int getCompareDdr() {
        return compareDdr;
    }

    public void setCompareDdr(int compareDdr) {
        this.compareDdr = compareDdr;
    }

    public int getJmpIfddrGreater() {
        return jmpIfddrGreater;
    }

    public void setJmpIfddrGreater(int jmpIfddrGreater) {
        this.jmpIfddrGreater = jmpIfddrGreater;
    }

    public int getJmpIfddrLess() {
        return jmpIfddrLess;
    }

    public void setJmpIfddrLess(int jmpIfddrLess) {
        this.jmpIfddrLess = jmpIfddrLess;
    }
    
    public int getJmp() {
        return jmp;
    }

    public void setJmp(int jmp) {
        this.jmp = jmp;
    } 

    @Override
    public String toString() {
        return "CaseJumpsPatcher{" + "jmpIfCommandEx=" + jmpIfCommandEx + ", jumpOut=" + jumpOut + ", compareDdr=" + compareDdr + ", jmpIfddrGreater=" + jmpIfddrGreater + ", jmpIfddrLess=" + jmpIfddrLess + ", jmp=" + jmp + '}';
    }
    
    
}

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
public class CaseTree {
    
    int caseDdr, endDdr, ifddr;
    CaseJumpsPatcher myRoot = null;
    //CaseTree nextCase;
    Expression exp;
    Frame frame;

    public CaseTree(Expression exp, Frame frame) {
        this.exp = exp;
        this.frame = frame;            
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }
    
    
    
    public int getCaseDdr() {
        return caseDdr;
    }

    public void setCaseDdr(int caseDdr) {
        this.caseDdr = caseDdr;
    }

    public int getEndDdr() {
        return endDdr;
    }

    public void setEndDdr(int endDdr) {
        this.endDdr = endDdr;
    }

    public int getIfddr() {
        return ifddr;
    }

    public void setIfddr(int ifddr) {
        this.ifddr = ifddr;
    }

    public CaseJumpsPatcher getMyRoot() {
        return myRoot;
    }

    public void setMyRoot(CaseJumpsPatcher myRoot) {
        this.myRoot = myRoot;
    }

    /*public CaseTree getNextCase() {
        return nextCase;
    }

    public void setNextCase(CaseTree nextCase) {
        this.nextCase = nextCase;
    }*/
    
    public void addCaseAtEnd(CaseJumpsPatcher toAdd){
        if(this.getMyRoot() == null){
            this.setMyRoot(toAdd);
            return;
        }
        CaseJumpsPatcher iterator = this.getMyRoot();
        while(iterator != null){
            if(iterator.getNextCase() == null){
                iterator.setNextCase(toAdd);
            }
            iterator = iterator.getNextCase();
        }
    }

    @Override
    public String toString() {
        return "CaseTree{" + "caseDdr=" + caseDdr + ", endDdr=" + endDdr + ", ifddr=" + ifddr + '}';
    }
    
    
    
    
}

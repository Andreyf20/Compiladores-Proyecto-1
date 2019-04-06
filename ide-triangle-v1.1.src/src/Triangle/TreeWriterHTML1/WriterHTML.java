/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
      
package Triangle.TreeWriterHTML1;

import Triangle.AbstractSyntaxTrees.Program;

import java.io.FileWriter;
import java.io.IOException;

public class WriterHTML {

    private String fileName;

    public WriterHTML(String fileName) {
        this.fileName = fileName;
    }

    // Draw the AST representing a complete program.
    public void write(Program ast) {
        // Prepare the file to write
        try {
            //this code was modified------------------------------------------------------------------------
            FileWriter fileWriter = new FileWriter(fileName + ".html");

            //HTML header
            fileWriter.write("<!DOCTYPE html>\n" +
                             "<head>\n" +
                             "  \t<meta charset=\"utf-8\">\n" +
                             "  \t<title>FileHTML</title>\n" +
                             "</head>\n"+
                             "<style type=\"text/css\">\n"
                    + "div {display: inline;" +
                             "      font-family: courier;" +
                             "      font-size:1em;" +
                             "    }"
                    + "p {display: inline;" +
                             "      font-family: courier;" +
                             "      font-size:1em;" +
                             "    }" +
                             "\n" +
                             "</style>" +
                             "<body>");
            WriterVisitor layout = new WriterVisitor(fileWriter);
            ast.visit(layout, null);
            
            //HTML footer
            fileWriter.write("</body>\n" +
                             "</html>");
            
            fileWriter.close();

        } catch (IOException e) {
            System.err.println("Error while creating file for print the AST");
            e.printStackTrace();
        }
    }

}
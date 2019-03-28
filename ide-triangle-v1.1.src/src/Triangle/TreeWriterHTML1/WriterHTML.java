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
            FileWriter fileWriter = new FileWriter(fileName + ".html");

            //HTML header
            fileWriter.write("<!DOCTYPE html>\n" +
                             "<head>\n" +
                             "  \t<meta charset=\"utf-8\">\n" +
                             "  \t<title>FileHTML</title>\n" +
                             "</head>\n" +
                             "<body>" +
                             "  \t<span style=\"color: #0000ff;\">");
                             //<p><span style="font-family: courier new; font-size: 1em;"> &ensp; &ensp; text as it would appear on a teletype. </span></p>
                             // tab --->&ensp;
                             // salto linea </br>
            WriterVisitor layout = new WriterVisitor(fileWriter);
            ast.visit(layout, null);
            
            //HTML footer
            fileWriter.write("  \t</span>\n" +
                             "</body>\n" +
                             "</html>");
            
            fileWriter.close();

        } catch (IOException e) {
            System.err.println("Error while creating file for print the AST");
            e.printStackTrace();
        }
    }

}
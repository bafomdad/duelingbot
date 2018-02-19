package com.bafomdad.duelingbot.test;

import org.luaj.vm2.ast.*;
import org.luaj.vm2.parser.LuaParser;
import org.luaj.vm2.parser.ParseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by bafomdad on 1/23/2018.
 */
public class TestLuaInterpreter {

    public static void main(String[] args) {

        try {
            final String file = "lua/mst.lua";

            // Create a LuaParser. This will fill in line and column number
            // information for most exceptions.
            LuaParser parser = new LuaParser(new FileInputStream(file));

            // Perform the parsing.
            Chunk chunk = parser.Chunk();

            // Print out line info for all function definitions.
            chunk.accept(new Visitor() {
                public void visit(Exp.AnonFuncDef exp) {
                    System.out.println("Anonymous function definition at "
                            + exp.beginLine + "." + exp.beginColumn + ","
                            + exp.endLine + "." + exp.endColumn);
                }
                public void visit(Stat.FuncDef stat) {
                    System.out.println("Function definition " + stat.name.name.name + ":");
                    List<Name> names = stat.body.parlist.names;
                    for (Name name : names) {
                        System.out.println(name.name);
                    }
//                    System.out.println("Function definition '" + stat.name.name.name + "' at "
//                            + stat.beginLine + "." + stat.beginColumn + ","
//                            + stat.endLine + "." + stat.endColumn);
//
//                    System.out.println("\tName location "
//                            + stat.name.beginLine + "." + stat.name.beginColumn + ","
//                            + stat.name.endLine + "." + stat.name.endColumn);
                }
                public void visit(Stat.LocalFuncDef stat) {
                    System.out.println("Local function definition '" + stat.name.name + "' at "
                            + stat.beginLine + "." + stat.beginColumn + ","
                            + stat.endLine + "." + stat.endColumn);
                }
            });

        } catch (ParseException e) {
            System.out.println("parse failed: " + e.getMessage() + "\n"
                    + "Token Image: '" + e.currentToken.image + "'\n"
                    + "Location: " + e.currentToken.beginLine + ":" + e.currentToken.beginColumn
                    + "-" + e.currentToken.endLine + "," + e.currentToken.endColumn);

        } catch (IOException e) {
            System.out.println( "IOException occurred: " + e );
            e.printStackTrace();
        }
    }
}

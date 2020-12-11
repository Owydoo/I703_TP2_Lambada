package fr.usmb.m1isc.compilation.tp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Assembleur {
    public static void createAssemblyfile(Arbre arbre, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter pw = new PrintWriter(fileName, "UTF-8");
        pw.println(arbre.toString());
        pw.println("DATA SEGMENT");

        // need the variables
        rightVariables(arbre,pw);

        pw.println("DATA ENDS");
        pw.println("CODE SEGMENT");

        //need the code
        rightCodes(arbre,pw);

        pw.println("CODE ENDS");
        pw.close();


    }

    public static void rightVariables(Arbre arbre, PrintWriter pw){
        if(arbre.getType() == Arbre.NodeType.SEMI) {

            if(arbre.getFg() != null){
                rightVariables(arbre.getFg(), pw);
            }
            if(arbre.getFd() != null){
                rightVariables(arbre.getFd(), pw);
            }

        }
        else{

            if(arbre.getType() == Arbre.NodeType.LET){
                pw.println("   " + arbre.getFg() + " DD");
            }
            if(arbre.getFg() != null){
                rightVariables(arbre.getFg(), pw);
            }
            if(arbre.getFd() != null){
                rightVariables(arbre.getFd(), pw);
            }

        }
    }

    public static void rightCodes(Arbre arbre, PrintWriter pw){

    }


    public static void rightLine(){

    }
}

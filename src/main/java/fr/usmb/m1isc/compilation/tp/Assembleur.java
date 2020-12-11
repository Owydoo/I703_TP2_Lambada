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
        if(arbre.getType() == Arbre.NodeType.SEMI) {
            rightCodes(arbre.getFg(), pw);
            if(!arbre.getFd().isALeaf()){
                rightCodes(arbre.getFd(), pw);
            }
        }
        else{
            if(arbre.getFg().isALeaf() && arbre.getFd().isALeaf()){
                rightLine(arbre.getFg(), arbre.getType(), arbre.getFd(), pw );
            }
            else{
                rightCodes(arbre.getFg(), pw);

            }
            /*else{

                if(arbre.getFg() != null){
                    rightCodes(arbre.getFg(), pw);
                }
                if(arbre.getFd() != null){
                    rightCodes(arbre.getFd(),pw);

                }
            }*/

        }
        //let prixHt = 200;
        //let prixTtc =  prixHt * 119 / 100.

    }


    public static void rightLine(Arbre arbreGauche, Arbre.NodeType nodeType, Arbre arbreDroit, PrintWriter pw){
        switch(nodeType) {
            case LET:
                System.err.println("let");
                pw.println("    mov eax,"+ arbreDroit);
                pw.println("    mov "+ arbreGauche +", eax");
                break;

            case PLUS:
                System.err.println("+");
                pw.println("    mov eax,"+ arbreGauche);
                pw.println("    add eax,"+ arbreDroit);
                break;

            case MOINS:
                System.err.println("-");
                pw.println("    mov eax,"+ arbreGauche);
                pw.println("    sub eax,"+ arbreDroit);
                break;

            case MUL:
                System.err.println("*");
                pw.println("    mov eax,"+ arbreGauche);
                pw.println("    mul eax,"+arbreDroit);
                break;

            case DIV:
                System.err.println("/");
                pw.println("    mov eax,"+ arbreGauche);
                pw.println("    div eax,"+arbreDroit);
                break;

        }

    }

}

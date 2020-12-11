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
        rightCode(arbre,pw);

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

    public static void rightCode(Arbre arbre, PrintWriter pw) {
        if(arbre!= null){
            switch (arbre.getType()) {
                case ENTIER:
                case IDENT:
                    pw.println("    mov eax, " + arbre.toString());
                    break;

                case SEMI:
                    rightCode(arbre.getFg(), pw);
                    rightCode(arbre.getFd(), pw);
                    break;
                case LET:
                    rightCode(arbre.getFd(), pw);

                    pw.println("    mov " + arbre.getFg() + ", eax");
                    break;
                case PLUS:
                    rightCode(arbre.getFg(), pw);
                    pw.println("    push eax");
                    rightCode(arbre.getFd(), pw);
                    pw.println("    pop ebx");
                    pw.println("    add eax, ebx");
                    break;
                case MOINS:
                    rightCode(arbre.getFg(), pw);
                    pw.println("    push eax");
                    rightCode(arbre.getFd(), pw);
                    pw.println("    pop ebx");
                    pw.println("    sub ebx, eax");
                    pw.println("    mov eax, ebx");
                    break;
                case MUL:
                    rightCode(arbre.getFg(), pw);
                    pw.println("    push eax");
                    rightCode(arbre.getFd(), pw);
                    pw.println("    pop ebx");
                    pw.println("    mul eax, ebx");
                    break;
                case DIV:
                    rightCode(arbre.getFg(), pw);
                    pw.println("    push eax");
                    rightCode(arbre.getFd(), pw);
                    pw.println("    pop ebx");
                    pw.println("    div ebx, eax");
                    pw.println("    mov eax, ebx");
                    break;
            }
        }
    }


}

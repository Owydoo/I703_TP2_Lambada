package fr.usmb.m1isc.compilation.tp;

import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception  {
		
		System.out.println("Write input : ");
		
		LexicalAnalyzer yy;
		 if (args.length > 0)
		        yy = new LexicalAnalyzer(new FileReader(args[0])) ;
		    else
		        yy = new LexicalAnalyzer(new InputStreamReader(System.in)) ;
		@SuppressWarnings("deprecation")
		parser p = new parser (yy);
		
		Arbre a = (Arbre) p.parse( ).value;
		System.out.println(a.toString());
		Assembleur.createAssemblyfile(a,"assembly.asm");
		//System.out.println(p.parse( ));
		//p.parse( );
	}

}

/*
 * Esta clase se encarga de analizar la sintaxis de la lista de Tokens 
 * generados que provienen de la clase PseudoLexer y así mismo traducir a lenguaje C,
 * ultilizando como base las reglas gramaticales de PsudoCodigo
 * */

import java.io.File;
import java.io.FileWriter;
import PseudoLexer.PseudoLexer;
import java.io.BufferedWriter;

public class PseudoParser extends Parser {
	
	public PseudoParser(PseudoLexer input) { 
		super(input); 
		programa();
	}

	private void programa(){    

		match("INICIOPROGRAMA");
		traductC += "#include<stdio.lib>\n\n"
								+ "void main(){ \n\n";
		enunciados();
		traductC += "}";
		try{
			File file = new File ("R:\\TraductorC\\src\\Traduccion.c");
			BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
			out.write(traductC);
			out.close();
		}
		catch(Exception e) {
			System.out.println("Error "+e);
		}
	}
	
	private void enunciados() {
		
		String y = lookahead.type.toString();
		while(!(lookahead.type.toString().equals("FINPROGRAMA"))){
			traductC += "\t";
			enunciado();
		}
		consume();
	}

	private void enunciado() {
		if(lookahead.type.toString().equals("VARIABLE")){
			asignacion();
			traductC += ";\n";
		}
		else if(lookahead.type.toString().equals("LEER")) {
			leer();
			traductC += ";\n";
		}
		else if(lookahead.type.toString().equals("ESCRIBIR")) {
			escribir();
			traductC += ";\n";
		}
		else if(lookahead.type.toString().equals("SI")) {
			si();
		}
		else if(lookahead.type.toString().equals("MIENTRAS")) {
			mientras();
		}
		
	}
	
	private void asignacion() {
		traductC += ""+lookahead.data;
		match("VARIABLE");
		traductC += lookahead.data;
		match("IGUAL");
		operacion();
	}
	
	
	private void operacion() {
		valor();
		if(lookahead.type.toString().equals("OPARITMETICO")) {

			traductC += lookahead.data;
			consume();
			valor();
		}	
	}
	
	private void valor() {
		if (lookahead.type.toString().equals("NUMERO")||lookahead.type.toString().equals("VARIABLE")){
			traductC += lookahead.data;
			consume();
		}
	}
	
	private void leer() {
		traductC += "scanf(";
		match("LEER");
		traductC += lookahead.data;
		match("CADENA");
		traductC += "%d";
		traductC += lookahead.data;
		match("COMA");
		traductC += lookahead.data;
		match("VARIABLE"); 
		traductC += ")";
	}
	
	private void escribir() {
		match("ESCRIBIR");
		traductC += "printf(";
		traductC += lookahead.data;
		match("CADENA");
		if(lookahead.type.toString().equals("COMA")) {
			traductC += lookahead.data;
			consume();
			traductC += lookahead.data;
			match("VARIABLE");
		}
		traductC += ")";
	}
	
	private void si() {
		match("SI");
		traductC += "\n\tif ";
		traductC += lookahead.data;
		match("PARENTESISIZQ");
		comparacion();
		traductC += lookahead.data;
		match("PARENTESISDER");
		match("ENTONCES");
		traductC += "\t{ \n";
		while(!(lookahead.type.toString().equals("FINSI"))){
			traductC += "\t\t";
			enunciado();	
		}
		traductC += "\t} \n";
		consume();
	}
	
	private void mientras() {
		traductC += "\n\twhile";
		match("MIENTRAS");
		traductC += lookahead.data;
		match("PARENTESISIZQ");
		comparacion();
		traductC += lookahead.data;
		match("PARENTESISDER");
		traductC += "{\n";
		while(!(lookahead.type.toString().equals("FINMIENTRAS"))){
			traductC += "\t\t";
			enunciado();
		}
		traductC += "\t} \n";
		consume();
	}
	
	private void comparacion() {
		valor();
		traductC += lookahead.data;
		match("OPERACIONAL");
		valor();
	}
	
}
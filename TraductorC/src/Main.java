import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import PseudoLexer.PseudoLexer;

public class Main {

	private static Scanner scanner;
	private static PseudoParser parser;

	public static void main(String[] args) throws IOException {
//		scanner = new Scanner(new File("R:\\GitHub\\MayapanTrainingPrototype\\TeoriaDeCompiladores\\TraductorC\\src\\PseudoCodigo.txt"));
//		String content = scanner.useDelimiter("\\Z").next();
//		PseudoLexer pseuLexer = new PseudoLexer(content);
//		String TokenS = "inicio-programa";
//		parser = new PseudoParser(pseuLexer);
		
		Scope currentScope;
		
		currentScope = new GlobalScope();
		
		//P.push(currentScope);
		currentScope.define(new BuiltInTypeSymbol("int"));
		currentScope.define(new BuiltInTypeSymbol("float"));
		currentScope.define(new BuiltInTypeSymbol("void"));
	
		Symbol x = currentScope.resolve("int");
		currentScope.define(new VariableSymbol("i"));
		Symbol y = currentScope.resolve("float");
		Symbol aux = new MethodSymbol("f");
		currentScope.define(aux);
		currentScope = (Scope) aux;
		//P.push(currentScope);
	}

}

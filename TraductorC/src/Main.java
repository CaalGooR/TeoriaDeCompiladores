import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import PseudoLexer.PseudoLexer;

public class Main {

	private static Scanner scanner;
	private static PseudoParser parser;

	public static void main(String[] args) throws IOException {
		scanner = new Scanner(new File("R:\\TraductorC\\src\\PseudoCodigo.txt"));
		String content = scanner.useDelimiter("\\Z").next();
		PseudoLexer pseuLexer = new PseudoLexer(content);
		parser = new PseudoParser(pseuLexer);
	}

}

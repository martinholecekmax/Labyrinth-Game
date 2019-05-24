package interpreter;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import game.commands.ICommand;
import parser.ast.ASTCode;
import parser.ast.LabyrinthGrammar;
import parser.ast.LabyrinthGrammarVisitor;
import parser.ast.ParseException;
import parser.ast.TokenMgrError;
import interpreter.Parser;

/**
 * This class initiate parsing of the source code
 * 
 * @author Martin Holecek
 *
 */
public class Interpreter {
	/**
	 * Parse the source code
	 * 
	 * @param input - string input of the source code
	 * @return - List of Commands which controls game environment
	 * @throws ParseException
	 * @throws TokenMgrError
	 */
	public static ArrayList<ICommand> parse(String input) throws ParseException, TokenMgrError {
		Reader reader = new StringReader(input);
		LabyrinthGrammar labyrinthGrammar = new LabyrinthGrammar(reader);
		ASTCode parser = labyrinthGrammar.code();
		LabyrinthGrammarVisitor nodeVisitor;
		Parser p = new Parser();
		nodeVisitor = p;
		parser.jjtAccept(nodeVisitor, null);
		return p.getCommands();
	}
}

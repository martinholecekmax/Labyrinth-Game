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

public class Interpreter {
	public static ArrayList<ICommand> parse(String input) throws ParseException, TokenMgrError {
//		ArrayList<ICommand> commands = new ArrayList<ICommand>();
		Reader reader = new StringReader(input);
//		ICommand command = new MoveCommand(Direction.LEFT);
//		ICommand command2 = new MoveCommand(Direction.LEFT);
		LabyrinthGrammar labyrinthGrammar = new LabyrinthGrammar(reader);
		ASTCode parser = labyrinthGrammar.code();
		LabyrinthGrammarVisitor nodeVisitor;
		Parser p = new Parser();
		nodeVisitor = p;
		parser.jjtAccept(nodeVisitor, null);
//		commands.add(command);
//		commands.add(command2);
		return p.getCommands();
//		return commands;
	}
}

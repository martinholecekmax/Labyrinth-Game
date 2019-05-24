package interpreter;

import java.util.ArrayList;
import java.util.Vector;

import game.commands.FreezeCommand;
import game.commands.ICommand;
import game.commands.KeyboardInputCommand;
import game.commands.MoveCommand;
import game.commands.OpenDoorCommand;
import game.commands.QuitGameCommand;
import game.commands.ShootCommand;
import game.commands.WriteCommand;
import game.enums.Direction;
import parser.ast.*;
import values.*;

/**
 * This class parses the abstract syntax tree
 * 
 * @author Martin Holecek
 *
 */
public class Parser implements LabyrinthGrammarVisitor {

	ArrayList<ICommand> commands = new ArrayList<ICommand>();
	String message = "";
	
	/**
	 * Get all commands
	 * 
	 * @return list of the commands
	 */
	public ArrayList<ICommand> getCommands() {
		if (!message.isEmpty()) {
			ICommand command = new WriteCommand(message);
			commands.add(command);
		}
		return commands;
	}

	// Array structure holds information of the arrays
	private ArrayStructure arrayStructure = new ArrayStructure();

	// Scope display handler
	private Display scope = new Display();

	// Get the ith child of a given node.
	private static SimpleNode getChild(SimpleNode node, int childIndex) {
		return (SimpleNode) node.jjtGetChild(childIndex);
	}

	// Get the token value of the ith child of a given node.
	private static String getTokenOfChild(SimpleNode node, int childIndex) {
		return getChild(node, childIndex).tokenValue;
	}

	// Execute a given child of the given node
	private Object doChild(SimpleNode node, int childIndex, Object data) {
		return node.jjtGetChild(childIndex).jjtAccept(this, data);
	}

	// Execute a given child of a given node, and return its value as a Value.
	// This is used by the expression evaluation nodes.
	Value doChild(SimpleNode node, int childIndex) {
		return (Value) doChild(node, childIndex, null);
	}

	// Execute all children of the given node
	Object doChildren(SimpleNode node, Object data) {
		return node.childrenAccept(this, data);
	}

	// Called if one of the following methods is missing...
	public Object visit(SimpleNode node, Object data) {
		System.out.println(node + ": acceptor not implemented in subclass?");
		return data;
	}

	// Execute a Sili program
	public Object visit(ASTCode node, Object data) {
		return doChildren(node, data);
	}

	// Execute a statement
	public Object visit(ASTStatement node, Object data) {
		return doChildren(node, data);
	}

	// Execute a block
	public Object visit(ASTBlock node, Object data) {
		return doChildren(node, data);
	}

	// Function definition
	public Object visit(ASTFnDef node, Object data) {
		// Already defined?
		if (node.optimised != null)
			return data;
		// Child 0 - identifier (fn name)
		String fnname = getTokenOfChild(node, 0);
		if (scope.findFunctionInCurrentLevel(fnname) != null)
			throw new ExceptionSemantic("Function " + fnname + " already exists.");
		FunctionDefinition currentFunctionDefinition = new FunctionDefinition(fnname, scope.getLevel() + 1);
		// Child 1 - function definition parameter list
		doChild(node, 1, currentFunctionDefinition);
		// Add to available functions
		scope.addFunction(currentFunctionDefinition);
		// Child 2 - function body
		currentFunctionDefinition.setFunctionBody(getChild(node, 2));
		// Child 3 - optional return expression
		if (node.fnHasReturn)
			currentFunctionDefinition.setFunctionReturnExpression(getChild(node, 3));
		// Preserve this definition for future reference, and so we don't define
		// it every time this node is processed.
		node.optimised = currentFunctionDefinition;
		return data;
	}

	// Function definition parameter list
	public Object visit(ASTParmlist node, Object data) {
		FunctionDefinition currentDefinition = (FunctionDefinition) data;
		for (int i = 0; i < node.jjtGetNumChildren(); i++)
			currentDefinition.defineParameter(getTokenOfChild(node, i));
		return data;
	}

	// Function body
	public Object visit(ASTFnBody node, Object data) {
		return doChildren(node, data);
	}

	// Function return expression
	public Object visit(ASTReturnExpression node, Object data) {
		return doChildren(node, data);
	}

	// Function call
	public Object visit(ASTCall node, Object data) {
		FunctionDefinition fndef;
		if (node.optimised == null) {
			// Child 0 - identifier (fn name)
			String fnname = getTokenOfChild(node, 0);
			fndef = scope.findFunction(fnname);
			if (fndef == null)
				throw new ExceptionSemantic("Function " + fnname + " is undefined.");
			// Save it for next time
			node.optimised = fndef;
		} else
			fndef = (FunctionDefinition) node.optimised;
		FunctionInvocation newInvocation = new FunctionInvocation(fndef);
		// Child 1 - arglist
		doChild(node, 1, newInvocation);
		// Execute
		scope.execute(newInvocation, this);
		return data;
	}

	// Function invocation in an expression
	public Object visit(ASTFnInvoke node, Object data) {
		FunctionDefinition fndef;
		if (node.optimised == null) {
			// Child 0 - identifier (fn name)
			String fnname = getTokenOfChild(node, 0);
			fndef = scope.findFunction(fnname);
			if (fndef == null)
				throw new ExceptionSemantic("Function " + fnname + " is undefined.");
			if (!fndef.hasReturn())
				throw new ExceptionSemantic(
						"Function " + fnname + " is being invoked in an expression but does not have a return value.");
			// Save it for next time
			node.optimised = fndef;
		} else
			fndef = (FunctionDefinition) node.optimised;
		FunctionInvocation newInvocation = new FunctionInvocation(fndef);
		// Child 1 - arglist
		doChild(node, 1, newInvocation);
		// Execute
		return scope.execute(newInvocation, this);
	}

	// Function invocation argument list.
	public Object visit(ASTArgList node, Object data) {
		FunctionInvocation newInvocation = (FunctionInvocation) data;
		for (int i = 0; i < node.jjtGetNumChildren(); i++)
			newInvocation.setArgument(doChild(node, i));
		newInvocation.checkArgumentCount();
		return data;
	}

	// Execute an IF
	public Object visit(ASTIfStatement node, Object data) {
		// evaluate boolean expression
		Value hopefullyValueBoolean = doChild(node, 0);
		if (!(hopefullyValueBoolean instanceof ValueBoolean))
			throw new ExceptionSemantic("The test expression of an if statement must be boolean.");
		if (((ValueBoolean) hopefullyValueBoolean).booleanValue())
			doChild(node, 1); // if(true), therefore do 'if' statement
		else if (node.ifHasElse) // does it have an else statement?
			doChild(node, 2); // if(false), therefore do 'else' statement
		return data;
	}

	// Execute a FOR loop
	public Object visit(ASTForLoop node, Object data) {
		// loop initialisation
		doChild(node, 0);
		while (true) {
			// evaluate loop test
			Value hopefullyValueBoolean = doChild(node, 1);
			if (!(hopefullyValueBoolean instanceof ValueBoolean))
				throw new ExceptionSemantic("The test expression of a for loop must be boolean.");
			if (!((ValueBoolean) hopefullyValueBoolean).booleanValue())
				break;
			// do loop statement
			doChild(node, 3);
			// assign loop increment
			doChild(node, 2);
		}
		return data;
	}

	// Execute a WHILE loop
	public Object visit(ASTWhileLoop node, Object data) {
		while (true) {
			Value expressionValue = doChild(node, 0);
			if (!(expressionValue instanceof ValueBoolean))
				throw new ExceptionSemantic("The test expression of a while loop must be boolean");
			if (!((ValueBoolean) expressionValue).booleanValue())
				break;
			doChild(node, 1);
		}
		return data;
	}

	// Process an identifier
	// This doesn't do anything, but needs to be here because we need an
	// ASTIdentifier node.
	public Object visit(ASTIdentifier node, Object data) {
		return data;
	}

	// Execute the WRITE statement
	public Object visit(ASTWrite node, Object data) {
		System.out.println(doChild(node, 0));
		message += doChild(node, 0).toString() + "\n";
		return data;
	}

	// Dereference a variable or parameter, and return its value.
	public Object visit(ASTDereference node, Object data) {
		Display.Reference reference;
		if (node.optimised == null) {
			String name = node.tokenValue;
			reference = scope.findReference(name);
			if (reference == null)
				throw new ExceptionSemantic("Variable or parameter " + name + " is undefined.");
			node.optimised = reference;
		} else
			reference = (Display.Reference) node.optimised;
		return reference.getValue();
	}

	// Execute an assignment statement.
	public Object visit(ASTAssignment node, Object data) {
		Display.Reference reference;
		if (node.optimised == null) {
			String name = getTokenOfChild(node, 0);
			reference = scope.findReference(name);
			if (reference == null)
				reference = scope.defineVariable(name);
			node.optimised = reference;
		} else
			reference = (Display.Reference) node.optimised;
		reference.setValue(doChild(node, 1));
		return data;
	}

	// OR
	public Object visit(ASTOr node, Object data) {
		return doChild(node, 0).or(doChild(node, 1));
	}

	// AND
	public Object visit(ASTAnd node, Object data) {
		return doChild(node, 0).and(doChild(node, 1));
	}

	// ==
	public Object visit(ASTCompEqual node, Object data) {
		return doChild(node, 0).eq(doChild(node, 1));
	}

	// !=
	public Object visit(ASTCompNequal node, Object data) {
		return doChild(node, 0).neq(doChild(node, 1));
	}

	// >=
	public Object visit(ASTCompGTE node, Object data) {
		return doChild(node, 0).gte(doChild(node, 1));
	}

	// <=
	public Object visit(ASTCompLTE node, Object data) {
		return doChild(node, 0).lte(doChild(node, 1));
	}

	// >
	public Object visit(ASTCompGT node, Object data) {
		return doChild(node, 0).gt(doChild(node, 1));
	}

	// <
	public Object visit(ASTCompLT node, Object data) {
		return doChild(node, 0).lt(doChild(node, 1));
	}

	// +
	public Object visit(ASTAdd node, Object data) {
		return doChild(node, 0).add(doChild(node, 1));
	}

	// -
	public Object visit(ASTSubtract node, Object data) {
		return doChild(node, 0).subtract(doChild(node, 1));
	}

	// *
	public Object visit(ASTTimes node, Object data) {
		return doChild(node, 0).mult(doChild(node, 1));
	}

	// /
	public Object visit(ASTDivide node, Object data) {
		return doChild(node, 0).div(doChild(node, 1));
	}

	// NOT
	public Object visit(ASTUnaryNot node, Object data) {
		return doChild(node, 0).not();
	}

	// + (unary)
	public Object visit(ASTUnaryPlus node, Object data) {
		return doChild(node, 0).unary_plus();
	}

	// - (unary)
	public Object visit(ASTUnaryMinus node, Object data) {
		return doChild(node, 0).unary_minus();
	}

	// Return string literal
	public Object visit(ASTCharacter node, Object data) {
		if (node.optimised == null)
			node.optimised = ValueString.stripDelimited(node.tokenValue);
		return node.optimised;
	}

	// Return integer literal
	public Object visit(ASTInteger node, Object data) {
		if (node.optimised == null)
			node.optimised = new ValueInteger(Long.parseLong(node.tokenValue));
		return node.optimised;
	}

	// Return floating point literal
	public Object visit(ASTRational node, Object data) {
		if (node.optimised == null)
			node.optimised = new ValueRational(Double.parseDouble(node.tokenValue));
		return node.optimised;
	}

	// Return true literal
	public Object visit(ASTTrue node, Object data) {
		if (node.optimised == null)
			node.optimised = new ValueBoolean(true);
		return node.optimised;
	}

	// Return false literal
	public Object visit(ASTFalse node, Object data) {
		if (node.optimised == null)
			node.optimised = new ValueBoolean(false);
		return node.optimised;
	}

	// Quit command
	public Object visit(ASTQuit node, Object data) {
		ICommand command = new QuitGameCommand();
		commands.add(command);
		return data;
	}

	// Execute the MOVE LEFT statement
	public Object visit(ASTMoveLeft node, Object data) {
		ICommand command = new MoveCommand(Direction.LEFT);
		commands.add(command);
		return data;
	}

	// Execute the MOVE RIGHT statement
	public Object visit(ASTMoveRight node, Object data) {
		ICommand command = new MoveCommand(Direction.RIGHT);
		commands.add(command);
		return data;
	}

	// Execute the MOVE DOWN statement
	public Object visit(ASTMoveDown node, Object data) {
		ICommand command = new MoveCommand(Direction.DOWN);
		commands.add(command);
		return data;
	}

	// Execute the MOVE UP statement
	public Object visit(ASTMoveUp node, Object data) {
		ICommand command = new MoveCommand(Direction.UP);
		commands.add(command);
		return data;
	}

	// Execute the SHOOT statement
	public Object visit(ASTShoot node, Object data) {
		Value expressionValue = doChild(node, 0);
		ICommand command;
		switch (expressionValue.stringValue().toUpperCase()) {
		case "L":
			command = new ShootCommand(Direction.LEFT);
			commands.add(command);
			break;
		case "R":
			command = new ShootCommand(Direction.RIGHT);
			commands.add(command);
			break;
		case "D":
			command = new ShootCommand(Direction.DOWN);
			commands.add(command);
			break;
		case "U":
			command = new ShootCommand(Direction.UP);
			commands.add(command);
			break;
		default:
			break;
		}
		return data;
	}

	// Execute Keyboard on statement
	public Object visit(ASTKeyboardOn node, Object data) {
		ICommand command = new KeyboardInputCommand(true);
		commands.add(command);
		return data;
	}

	// Execute Keyboard off statement
	public Object visit(ASTKeyboardOff node, Object data) {
		ICommand command = new KeyboardInputCommand(false);
		commands.add(command);
		return data;
	}

	// Execute Freeze enemies statement
	public Object visit(ASTFreeze node, Object data) {
		ICommand command = new FreezeCommand();
		commands.add(command);
		return data;
	}

	// Execute Answer statement to open the door
	public Object visit(ASTAnswer node, Object data) {
		Value expressionValue = doChild(node, 0);
		ICommand command = new OpenDoorCommand(expressionValue.stringValue());
		commands.add(command);
		return data;
	}

	// Execute Array definition statement
	public Object visit(ASTArrayDef node, Object data) {
		// Already defined?
		if (node.optimised != null)
			return data;
		// Child 0 - identifier (Array name)
		String arrayName = getTokenOfChild(node, 0);
		if (arrayStructure.findArray(arrayName) != null)
			throw new ExceptionSemantic("Array " + arrayName + " already exists.");
		Vector<Value> elements = new Vector<Value>();
		// Child 1 - elements list
		doChild(node, 1, elements);
		arrayStructure.addArray(arrayName, elements);
		return data;
	}

	// Execute parameter list statement (get elements of an array)
	public Object visit(ASTArrayParamList node, Object data) {
		@SuppressWarnings("unchecked")
		Vector<Value> elements = (Vector<Value>) data;
		for (int i = 0; i < node.jjtGetNumChildren(); i++)
			elements.add(doChild(node, i));
		return data;
	}

	// Execute array assignment statement
	public Object visit(ASTArrayAssignment node, Object data) {
		String arrayName = getTokenOfChild(node, 0);
		Vector<Value> elements = arrayStructure.findArray(arrayName);
		if (elements == null) {
			throw new ExceptionSemantic("Array " + arrayName + " is undefined.");
		}
		Value index = doChild(node, 1);
		if (!(index instanceof ValueInteger))
			throw new ExceptionSemantic("The index of an array must be integer.");
		int indexValue = (int) index.longValue();
		if (indexValue >= elements.size()) {
			throw new ExceptionSemantic("Array index out of range " + indexValue);
		}
		elements.set(indexValue, doChild(node, 2));
		return data;
	}

	// Execute array invocation statement
	public Object visit(ASTArrayInvoke node, Object data) {
		// Child 0 - identifier (array name)
		String arrayName = getTokenOfChild(node, 0);
		Vector<Value> elements = arrayStructure.findArray(arrayName);
		if (elements == null) {
			throw new ExceptionSemantic("Array " + arrayName + " is undefined.");
		}
		Value index = doChild(node, 1);
		if (!(index instanceof ValueInteger))
			throw new ExceptionSemantic("The index of an array must be integer.");
		int indexValue = (int) index.longValue();

		if (indexValue >= elements.size()) {
			throw new ExceptionSemantic("Array index out of range " + indexValue);
		}
		Value element = elements.get(indexValue);
		return element;
	}
}

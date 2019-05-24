package interpreter;

import parser.ast.ASTAdd;
import parser.ast.ASTAnd;
import parser.ast.ASTAnswer;
import parser.ast.ASTArgList;
import parser.ast.ASTArrayAssignment;
import parser.ast.ASTArrayDef;
import parser.ast.ASTArrayInvoke;
import parser.ast.ASTArrayParamList;
import parser.ast.ASTAssignment;
import parser.ast.ASTBlock;
import parser.ast.ASTCall;
import parser.ast.ASTCharacter;
import parser.ast.ASTCode;
import parser.ast.ASTCompEqual;
import parser.ast.ASTCompGT;
import parser.ast.ASTCompGTE;
import parser.ast.ASTCompLT;
import parser.ast.ASTCompLTE;
import parser.ast.ASTCompNequal;
import parser.ast.ASTDereference;
import parser.ast.ASTDivide;
import parser.ast.ASTFalse;
import parser.ast.ASTFnBody;
import parser.ast.ASTFnDef;
import parser.ast.ASTFnInvoke;
import parser.ast.ASTForLoop;
import parser.ast.ASTFreeze;
import parser.ast.ASTIdentifier;
import parser.ast.ASTIfStatement;
import parser.ast.ASTInteger;
import parser.ast.ASTKeyboardOff;
import parser.ast.ASTKeyboardOn;
import parser.ast.ASTMoveDown;
import parser.ast.ASTMoveLeft;
import parser.ast.ASTMoveRight;
import parser.ast.ASTMoveUp;
import parser.ast.ASTOr;
import parser.ast.ASTParmlist;
import parser.ast.ASTQuit;
import parser.ast.ASTRational;
import parser.ast.ASTReturnExpression;
import parser.ast.ASTShoot;
import parser.ast.ASTStatement;
import parser.ast.ASTSubtract;
import parser.ast.ASTTimes;
import parser.ast.ASTTrue;
import parser.ast.ASTUnaryMinus;
import parser.ast.ASTUnaryNot;
import parser.ast.ASTUnaryPlus;
import parser.ast.ASTWhileLoop;
import parser.ast.ASTWrite;
import parser.ast.LabyrinthGrammarVisitor;
import parser.ast.SimpleNode;

/**
 * This class can be used for debugging of the abstract syntax tree
 * 
 * @author Martin Holecek
 *
 */
public class ParserDebugger implements LabyrinthGrammarVisitor {

	private int indent = 0;

	private String indentString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < indent; ++i) {
			sb.append(" ");
		}
		return sb.toString();
	}

	/** Debugging dump of a node. */
	private Object dump(SimpleNode node, Object data) {
		System.out.println(indentString() + node);
		++indent;
		data = node.childrenAccept(this, data);
		--indent;
		return data;
	}

	public Object visit(SimpleNode node, Object data) {
		System.out.println(node + ": acceptor not implemented in subclass?");
		return data;
	}

	// Execute a Sili program
	public Object visit(ASTCode node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute a statement
	public Object visit(ASTStatement node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute a block
	public Object visit(ASTBlock node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute an IF
	public Object visit(ASTIfStatement node, Object data) {
		dump(node, data);
		return data;
	}

	// Function definition parameter list
	public Object visit(ASTParmlist node, Object data) {
		dump(node, data);
		return data;
	}

	// Function body
	public Object visit(ASTFnBody node, Object data) {
		dump(node, data);
		return data;
	}

	// Function definition
	public Object visit(ASTFnDef node, Object data) {
		dump(node, data);
		return data;
	}

	// Function return expression
	public Object visit(ASTReturnExpression node, Object data) {
		dump(node, data);
		return data;
	}

	// Function argument list
	public Object visit(ASTArgList node, Object data) {
		dump(node, data);
		return data;
	}

	// Function call
	public Object visit(ASTCall node, Object data) {
		dump(node, data);
		return data;
	}

	// Function invocation in an expression
	public Object visit(ASTFnInvoke node, Object data) {
		dump(node, data);
		return data;
	}

	// Dereference a variable, and push its value onto the stack
	public Object visit(ASTDereference node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute a FOR loop
	public Object visit(ASTForLoop node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute a WHILE loop
	public Object visit(ASTWhileLoop node, Object data) {
		dump(node, data);
		return data;
	}

	// Process an identifier
	// This doesn't do anything, but needs to be here because we need an
	// ASTIdentifier node.
	public Object visit(ASTIdentifier node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute the WRITE statement
	public Object visit(ASTWrite node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute an assignment statement, by popping a value off the stack and
	// assigning it
	// to a variable.
	public Object visit(ASTAssignment node, Object data) {
		dump(node, data);
		return data;
	}

	// OR
	public Object visit(ASTOr node, Object data) {
		dump(node, data);
		return data;
	}

	// AND
	public Object visit(ASTAnd node, Object data) {
		dump(node, data);
		return data;
	}

	// ==
	public Object visit(ASTCompEqual node, Object data) {
		dump(node, data);
		return data;
	}

	// !=
	public Object visit(ASTCompNequal node, Object data) {
		dump(node, data);
		return data;
	}

	// >=
	public Object visit(ASTCompGTE node, Object data) {
		dump(node, data);
		return data;
	}

	// <=
	public Object visit(ASTCompLTE node, Object data) {
		dump(node, data);
		return data;
	}

	// >
	public Object visit(ASTCompGT node, Object data) {
		dump(node, data);
		return data;
	}

	// <
	public Object visit(ASTCompLT node, Object data) {
		dump(node, data);
		return data;
	}

	// +
	public Object visit(ASTAdd node, Object data) {
		dump(node, data);
		return data;
	}

	// -
	public Object visit(ASTSubtract node, Object data) {
		dump(node, data);
		return data;
	}

	// *
	public Object visit(ASTTimes node, Object data) {
		dump(node, data);
		return data;
	}

	// /
	public Object visit(ASTDivide node, Object data) {
		dump(node, data);
		return data;
	}

	// NOT
	public Object visit(ASTUnaryNot node, Object data) {
		dump(node, data);
		return data;
	}

	// + (unary)
	public Object visit(ASTUnaryPlus node, Object data) {
		dump(node, data);
		return data;
	}

	// - (unary)
	public Object visit(ASTUnaryMinus node, Object data) {
		dump(node, data);
		return data;
	}

	// Push string literal to stack
	public Object visit(ASTCharacter node, Object data) {
		dump(node, data);
		return data;
	}

	// Push integer literal to stack
	public Object visit(ASTInteger node, Object data) {
		dump(node, data);
		return data;
	}

	// Push floating point literal to stack
	public Object visit(ASTRational node, Object data) {
		dump(node, data);
		return data;
	}

	// Push true literal to stack
	public Object visit(ASTTrue node, Object data) {
		dump(node, data);
		return data;
	}

	// Push false literal to stack
	public Object visit(ASTFalse node, Object data) {
		dump(node, data);
		return data;
	}

	// Quit
	public Object visit(ASTQuit node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute the Move left statement
	public Object visit(ASTMoveLeft node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute the Move right statement
	public Object visit(ASTMoveRight node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute the Move down statement
	public Object visit(ASTMoveDown node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute the Move up statement
	public Object visit(ASTMoveUp node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute the Shoot statement
	public Object visit(ASTShoot node, Object data) {
		dump(node, data);
		return data;
	}

	// Turn Keyboard on statement
	public Object visit(ASTKeyboardOn node, Object data) {
		dump(node, data);
		return data;
	}

	// Turn Keyboard off statement
	public Object visit(ASTKeyboardOff node, Object data) {
		dump(node, data);
		return data;
	}

	// Freeze enemies statement
	public Object visit(ASTFreeze node, Object data) {
		dump(node, data);
		return data;
	}

	// Answer a question to open the door
	public Object visit(ASTAnswer node, Object data) {
		dump(node, data);
		return data;
	}

	// Array definition node
	public Object visit(ASTArrayDef node, Object data) {
		dump(node, data);
		return data;
	}

	// Elements of an array node
	public Object visit(ASTArrayParamList node, Object data) {
		dump(node, data);
		return data;
	}

	// Assignment statement of an array
	public Object visit(ASTArrayAssignment node, Object data) {
		dump(node, data);
		return data;
	}

	// Invocation of an array
	public Object visit(ASTArrayInvoke node, Object data) {
		dump(node, data);
		return data;
	}
}

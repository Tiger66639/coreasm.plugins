/**
 * 
 */
package org.coreasm.plugins.aspects.pointcutmatching;

import java.util.ArrayList;
import java.util.HashMap;

import org.coreasm.engine.interpreter.ASTNode;
import org.coreasm.engine.interpreter.ScannerInfo;
import org.coreasm.plugins.aspects.AoASMPlugin;
import org.coreasm.plugins.aspects.errorhandling.AspectException;

/**
 * @author Marcel Dausend
 *
 */
public class NotASTNode extends PointCutASTNode {

	private static final long serialVersionUID = 1L;
	private static final String NODE_TYPE = NotASTNode.class.getSimpleName();

	/**
	 * this constructor is needed to support duplicate
	 * @param self this object
	 */
	public NotASTNode(NotASTNode self){
		super(self);
	}
	
	/**
	 * @param scannerInfo
	 */
	public NotASTNode(ScannerInfo scannerInfo) {
		super(AoASMPlugin.PLUGIN_NAME, org.coreasm.engine.interpreter.Node.OTHER_NODE, NotASTNode.NODE_TYPE, null, scannerInfo);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * returns the inverted boolean value of its subexpression node
	 * @throws AspectException 
	 */
	@Override
	public Binding matches(ASTNode compareToNode) throws AspectException {
		Binding result = this.getFirstChild().matches(compareToNode);
		//TODO not for args nodes - what is an inverted return value of args list?!
		//\attention argslist not yet inverted!!!
		if (result.getBinding() == null)
			return new Binding(compareToNode, this, new HashMap<String, ASTNode>());
		else
			return new Binding(compareToNode, this);
	}

	@Override
	public String getCflowBindings() {
		ArrayList<ASTNode> children = (ArrayList<ASTNode>) this.getAbstractChildNodes();
		//just one node which must be a ExpressionASTNode according to the grammar;
		//return the result of the child node.
		if (children.size() == 1 && children.get(0) instanceof BinOrASTNode
				&& !((BinOrASTNode) children.get(0)).getCflowBindings().isEmpty())
			return "NotBinding(" + ((BinOrASTNode) children.get(0)).getCflowBindings() + ")";
		return "";
	}

	@Override
	public String getCondition() {
		ArrayList<ASTNode> children = (ArrayList<ASTNode>) this.getAbstractChildNodes();

		if (children.size() == 1 && children.get(0) instanceof BinOrASTNode
				&& !((BinOrASTNode) children.get(0)).getCondition().isEmpty())
			return "not " + ((BinOrASTNode) children.get(0)).getCondition();
		return "";
	}
}

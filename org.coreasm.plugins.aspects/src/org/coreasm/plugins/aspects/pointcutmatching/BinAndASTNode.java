/**
 * 
 */
package org.coreasm.plugins.aspects.pointcutmatching;

import java.util.ArrayList;

import org.coreasm.engine.interpreter.ASTNode;
import org.coreasm.engine.interpreter.ScannerInfo;
import org.coreasm.plugins.aspects.AoASMPlugin;
import org.coreasm.plugins.aspects.errorhandling.AspectException;

/**
 * @author Marcel Dausend
 *
 */
public class BinAndASTNode extends PointCutASTNode {

	private static final String NODE_TYPE = BinAndASTNode.class.getSimpleName();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * this constructor is needed to support duplicate
	 * @param self this object
	 */
	public BinAndASTNode(BinAndASTNode self){
		super(self);
	}
	/**
	 * @param scannerInfo
	 */
	public BinAndASTNode(ScannerInfo scannerInfo) {
		super(AoASMPlugin.PLUGIN_NAME, org.coreasm.engine.interpreter.Node.OTHER_NODE, BinAndASTNode.NODE_TYPE, null, scannerInfo);
	}
	
	@Override
	public Binding matches(ASTNode compareToNode) throws AspectException {
		ArrayList<ASTNode> children = (ArrayList<ASTNode>) this.getAbstractChildNodes();
		//just one node which must be a ExpressionASTNode according to the grammar;
		//return the result of the child node.
		if (children.size() == 1 && this.getFirstChild() instanceof ExpressionASTNode)
			return this.getFirstChild().matches(compareToNode);
		//exactly two nodes: if one of those nodes returns 'true', this node returns 'true', too.
		else if (children.size() == 2 &&
				this.getFirstChild() instanceof ExpressionASTNode &&
				this.getSecondChild() instanceof BinAndASTNode)
		{
			Binding firstChildResult, secondChildResult, resultingBinding;
			firstChildResult = this.getFirstChild().matches(compareToNode);
			secondChildResult = this.getSecondChild().matches(compareToNode);
			resultingBinding = new Binding(firstChildResult, secondChildResult, this);
			return resultingBinding;
		}
		else
			return new Binding(compareToNode, this);
	}

	@Override
	public String getCondition() {
		ArrayList<ASTNode> children = (ArrayList<ASTNode>)this.getAbstractChildNodes();
		//just one node which must be a ExpressionASTNode according to the grammar;
		//return the result of the child node.
		if (children.size() == 1 && children.get(0) instanceof ExpressionASTNode
				&& !((ExpressionASTNode) children.get(0)).getCondition().isEmpty())
			return ((ExpressionASTNode)children.get(0)).getCondition();
		//exactly two nodes: if one of those nodes returns 'true', this node returns 'true', too.
		else if (children.size()==2 && 
				children.get(0) instanceof ExpressionASTNode && 
				children.get(1) instanceof BinAndASTNode) {

			String leftChild = ((ExpressionASTNode) children.get(0)).getCondition();
			String rightChild = ((BinAndASTNode) children.get(1)).getCondition();

			if (!leftChild.isEmpty() && !rightChild.isEmpty())
				return leftChild + " and " + rightChild;
			else
				return leftChild + rightChild;
		}
		return "";
	}

	@Override
	public String getCflowBindings() {
		ArrayList<ASTNode> children = (ArrayList<ASTNode>) this.getAbstractChildNodes();
		//just one node which must be a ExpressionASTNode according to the grammar;
		//return the result of the child node.
		if (children.size() == 1 && children.get(0) instanceof ExpressionASTNode
				&& !((ExpressionASTNode) children.get(0)).getCflowBindings().isEmpty())
			return ((ExpressionASTNode) children.get(0)).getCflowBindings();
		//exactly two nodes: if one of those nodes returns 'true', this node returns 'true', too.
		else if (children.size() == 2 &&
				children.get(0) instanceof ExpressionASTNode &&
				children.get(1) instanceof BinAndASTNode) {
			String leftChild = ((ExpressionASTNode) children.get(0)).getCflowBindings();
			String rightChild = ((BinAndASTNode) children.get(1)).getCflowBindings();

			if (!leftChild.isEmpty() && !rightChild.isEmpty())
				return "AndBinding( "
						+ leftChild + ", " + rightChild
						+ " )";
			else
				return leftChild + rightChild;
		}
		return "";
	}
}

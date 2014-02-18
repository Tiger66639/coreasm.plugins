/**
 * 
 */
package org.coreasm.aspects.pointcutmatching;

import org.coreasm.aspects.AopASMPlugin;
import org.coreasm.engine.interpreter.ASTNode;
import org.coreasm.engine.interpreter.ScannerInfo;

import java.util.LinkedList;

/**
 * @author Marcel Dausend
 *
 */
public class CFlowBelowASTNode extends PointCutASTNode {

	private static final long serialVersionUID = 1L;
	private static final String NODE_TYPE = CFlowBelowASTNode.class.getSimpleName();

	/**
	 * this constructor is needed to support duplicate
	 * @param self this object
	 */
	public CFlowBelowASTNode(CFlowBelowASTNode self){
		super(self);
	}
	
	/**
	 * @param scannerInfo
	 */
	public CFlowBelowASTNode(ScannerInfo scannerInfo) {
		super(AopASMPlugin.PLUGIN_NAME, org.coreasm.engine.interpreter.Node.OTHER_NODE, CFlowBelowASTNode.NODE_TYPE, null, scannerInfo);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public PointCutMatchingResult matches(ASTNode compareToNode) {
		return new PointCutMatchingResult(true, new LinkedList<ArgsASTNode>());
	}

	@Override
	public String generateExpressionString() {
		// TODO Auto-generated method stub
		return null;
	}

}

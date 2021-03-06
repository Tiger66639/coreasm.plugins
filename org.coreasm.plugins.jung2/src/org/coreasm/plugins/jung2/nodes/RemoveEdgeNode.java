package org.coreasm.plugins.jung2.nodes;

import org.coreasm.engine.interpreter.ASTNode;
import org.coreasm.engine.interpreter.ScannerInfo;
import org.coreasm.plugins.jung2.Jung2Plugin;

/**
 * A node representing an vertex add operation on a tree
 * @author Marcel Dasuend
 *
 */
@SuppressWarnings("serial")
public class RemoveEdgeNode extends ASTNode {

	public RemoveEdgeNode(ScannerInfo info) {
		super(Jung2Plugin.getPluginName(), ASTNode.RULE_CLASS, "EdgeNode", null, info);
	}

	public RemoveEdgeNode(RemoveEdgeNode node) {
		super(node);
	}

	public String getParentId() {
		return this.getFirst().getNext().getToken();
	}

	public String getChildId(){
		return this.getFirst().getToken();
	}

	public boolean descendants(){
		return this.getChildNodes().get(2).getToken().equals(Jung2Plugin.KEYWORD_DESCENDANTS);
	}
}

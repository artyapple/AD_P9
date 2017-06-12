package graph.link;

import graph.node.INode;

public interface IEdge {
	
	/**
	 * gets owner node
	 * @return
	 */
	public INode getOwnerNode();
	
	/**
	 * gets owner id
	 * @return
	 */
	public int getOwnerId();
	
	/**
	 * gets linked node
	 * @return
	 */
	public INode getLinkedNode();
	
	/**
	 * gets cost of this edge
	 * @return
	 */
	public int getCost();
	
}

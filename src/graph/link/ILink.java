package graph.link;

import graph.node.INode;

public interface ILink {
	
	public int getOwnerId();
	
	public INode getLinkedNode();
	
	public int getCost();
	
}

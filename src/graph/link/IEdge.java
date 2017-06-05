package graph.link;

import java.util.UUID;

import graph.node.INode;

public interface IEdge {
	
	public INode getOwnerNode();
	
	public UUID getOwnerId();
	
	public INode getLinkedNode();
	
	public int getCost();
	
}

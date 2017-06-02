package graph.link;

import java.util.UUID;

import graph.node.INode;

public interface ILink {
	
	public UUID getOwnerId();
	
	public INode getLinkedNode();
	
	public int getCost();
	
}

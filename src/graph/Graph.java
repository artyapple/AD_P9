package graph;

import graph.node.INode;

public interface Graph {
	
	public void traverse();
	
	public boolean isConnected();
	
	public boolean isNeighbors(INode n, INode m);
	
	public int getCost(INode node); 

}

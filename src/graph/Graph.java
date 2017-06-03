package graph;

import graph.node.INode;

public interface Graph {
	
	public boolean traverse(INode startNode, INode destinationNode);
	
	public boolean isConnected();
	
	public boolean isNeighbors(INode n, INode m);
	
	public int getCost(INode from, INode to); 
	
	public int getSize();

}


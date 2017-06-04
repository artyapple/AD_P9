package graph;

import java.util.List;

import graph.node.INode;

public interface Graph {
	
	public List<INode> getINodes();
	
	public boolean traverse(INode startNode, INode destinationNode);
	
	public boolean isConnected(INode n, INode m);
	
	public boolean isNeighbors(INode n, INode m);
	
	public int getCost(INode from, INode to); 
	
	public int getSize();

}


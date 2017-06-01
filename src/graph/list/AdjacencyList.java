package graph.list;

import java.util.List;

import graph.Graph;
import graph.node.INode;

public class AdjacencyList implements Graph {
	
	private List<INode> nodes;
	private int size; 
	
	public AdjacencyList() {
		
	}
	
	@Override
	public void traverse() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNeighbors(INode n, INode m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCost(INode from, INode to) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getSize() {
		return size;
	}

}

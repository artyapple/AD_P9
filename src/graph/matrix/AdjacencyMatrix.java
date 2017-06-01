package graph.matrix;

import java.util.List;

import graph.Graph;
import graph.node.INode;

public class AdjacencyMatrix implements Graph {

	private static final int NOTNEIGHBOURS = -1;
	private List<INode> nodeArray;
	private int[][] costs;
	private int size;

	public void add(INode node) {
		nodeArray.add(node);
	}

	@Override
	public void traverse() {
		// TODO
	}

	@Override
	public boolean isConnected() {
		//TODO
		return false;
	}

	@Override
	public boolean isNeighbors(INode n, INode m) {
		return (costs[nodeArray.indexOf(n)][nodeArray.indexOf(m)] != NOTNEIGHBOURS);
	}

	@Override
	public int getCost(INode from, INode to) {
		return costs[nodeArray.indexOf(from)][nodeArray.indexOf(to)];
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}

package graph;

import java.util.List;

import graph.link.IEdge;
import graph.node.INode;

public interface Graph {

	public List<INode> getINodes();

	public List<IEdge> getIEdges();

	public boolean traverse(INode startNode, INode destinationNode);

	public boolean isConnected(INode n, INode m);

	public boolean isNeighbors(INode n, INode m);

	public int getCost(INode from, INode to);

	public int getSize();

	public INode getINode(int index);

	public void addNode(INode node);

	public boolean addEdge(IEdge edge);

}

package graph;

import java.util.List;

import data.json.graph.NodeDataContainer;
import graph.node.INode;

public interface Graph {
	
	/**
	 * gets node by id
	 * 
	 * @param name
	 * @return
	 */
	public INode getNode(int id);
	
	/**
	 * gets node by name
	 * 
	 * @param name
	 * @return
	 */
	public INode getNode(String name);
	
	/**
	 * gets size of graph (node count)
	 * 
	 * @return
	 */
	public int size();
	
	/**
	 * gets list of neighbors
	 * 
	 * @param node
	 * @return
	 */
	public List<INode> getNeighbors(INode node);
	
	/**
	 * travers graph
	 * 
	 * @param startNode
	 * @param destinationNode
	 * @return
	 */
	public boolean traverse(INode startNode, INode destinationNode);
	
	/**
	 * checks whether two nodes are neighbors.
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public boolean isNeighbors(INode n, INode m);
	
	/**
	 * gets the cost of an edge between two edges
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public int getCost(INode from, INode to); 
	
	/**
	 * Initializes the graph from the json-based structure
	 * 
	 * @param list
	 */
	public void initilize(List<NodeDataContainer> list);

}


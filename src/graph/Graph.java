package graph;

import java.util.List;

import data.json.graph.NodeDataContainer;
import graph.link.IEdge;
import graph.node.INode;

public interface Graph {
	
	/**
	 * 
	 * @return
	 */
	public INode getNode(int id);
	
	public INode getNode(String name);
	
	public int size();
	
	
	public List<INode> getNeighbors(INode node);
	
	/**
	 * 
	 * @param startNode
	 * @param destinationNode
	 * @return
	 */
	public boolean traverse(INode startNode, INode destinationNode);
	
	/**
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public boolean isNeighbors(INode n, INode m);
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public int getCost(INode from, INode to); 
	
	/**
	 * 
	 * @param list
	 */
	public void initilize(List<NodeDataContainer> list);

}


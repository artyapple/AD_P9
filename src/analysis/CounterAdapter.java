package analysis;

import java.util.List;

import data.json.graph.NodeDataContainer;
import graph.Graph;
import graph.list.AdjacencyList;
import graph.matrix.AdjacencyMatrix;
import graph.node.INode;
import graph.node.ListNode;

public class CounterAdapter implements Graph {

	private double counter;
	private Graph graph;
	
	public CounterAdapter(Graph graph) {
		 this.graph = graph;
	}

	@Override
	public INode getNode(int id) {
		increaseCounter();
		return graph.getNode(id);
	}

	@Override
	public INode getNode(String name) {
		increaseCounter();
		return graph.getNode(name);
	}

	@Override
	public List<INode> getNeighbors(INode node) {
		increaseCounter();
		if(graph instanceof AdjacencyMatrix){
			increaseBy(graph.size());
		} else if(graph instanceof AdjacencyList){
			increaseBy(((ListNode)node).getEdges().size());
		}
		return graph.getNeighbors(node);
	}

	@Override
	public boolean traverse(INode startNode, INode destinationNode) {
		increaseCounter();
		return graph.traverse(startNode, destinationNode);
	}

	@Override
	public boolean isNeighbors(INode n, INode m) {
		increaseCounter();
		return graph.isNeighbors(n, m);
	}

	@Override
	public int getCost(INode from, INode to) {
		increaseCounter();
		if(graph instanceof AdjacencyList){
			increaseBy(((ListNode) from).getEdges().size());
		}
		return graph.getCost(from, to);
	}

	@Override
	public void initilize(List<NodeDataContainer> list) {
		graph.initilize(list);
	}
	
	private void increaseCounter(){
		counter++;
	}
	
	private void increaseBy(int x){
		counter= counter +x;
	}
	
	public double getCounter(){
		return counter;
	}

	@Override
	public int size() {
		return graph.size();
	}
}

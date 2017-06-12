package graph.node;

import java.util.ArrayList;
import java.util.List;

import graph.link.Edge;
import graph.link.IEdge;

public class ListNode implements INode {
	
	private String name;
	private int id;
	private List<IEdge> edges;
	private boolean isVisited;
	
	
	public ListNode(String name, int id) {
		this.name = name;
		this.id = id;
		this.edges = new ArrayList<>();
		this.isVisited=false;
	}
	
	public void setLink(INode node, int cost){
		IEdge edge = new Edge(node, cost, id,this);
		edges.add(edge);
	}
	

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getNodeId() {
		return id;		
	}
	
	public List<IEdge> getEdges(){
		return this.edges;
	}
	
	public boolean getMark() {
		return isVisited;
	}
		
	public void mark() {
		this.isVisited=true;
	}

	public void unmark() {
		this.isVisited=false;
	}
}

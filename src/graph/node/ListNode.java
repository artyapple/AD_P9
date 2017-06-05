package graph.node;

import graph.link.IEdge;
import graph.link.Edge;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListNode implements INode {

	private String name;
	private UUID id;
	private List<IEdge> edges;
	private boolean isVisited;

	public ListNode(String name) {
		this.name = name;
		this.id = UUID.randomUUID();
		this.edges = new ArrayList<>();
		this.isVisited = false;
	}

	public ListNode(String name, String links) {
		this.name = name;
		this.id = UUID.randomUUID();
	}

	public void setLink(INode node, int cost) {
		IEdge edge = new Edge(node, cost, this);
		edges.add(edge);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public UUID getNodeId() {
		return id;
	}

	public List<IEdge> getEdges() {
		return this.edges;
	}

	@Override
	public boolean isNeighbors(INode otherNode) {

		List<IEdge> linkNodes = ((ListNode) otherNode).getEdges();
		Iterator<IEdge> iterLinkNodes = linkNodes.iterator();
		while (iterLinkNodes.hasNext()) {
			if (iterLinkNodes.next().getLinkedNode().equals(this)) {
				return true;
			}
		}
		return false;
	}

	public boolean getMark() {
		// TODO Auto-generated method stub
		return isVisited;
	}

	public void mark() {
		this.isVisited = true;

	}

	public void unmark() {
		this.isVisited = false;

	}

}

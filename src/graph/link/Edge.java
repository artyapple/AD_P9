package graph.link;

import graph.node.INode;
import java.util.UUID;
/**
 * 
 * @author daexel
 *
 *Diese Klasse representiert die Edges. Die Anzahl der Edges in der List ist die Anzahl der Edges
 *
 */
public class Edge implements IEdge {

	private int ownerId;
	private int cost;
	private INode linkedNode;
	private INode ownerNode;

	public Edge(INode linkedNode, int cost, int ownerId, INode ownerNode) {
		this.linkedNode = linkedNode;
		this.cost = cost;
		this.ownerId = ownerId;
		this.ownerNode = ownerNode;
	}

	@Override
	public int getOwnerId() {
		return ownerId;
	}
	
	@Override
	public INode getOwnerNode() {	
		return this.ownerNode;
	}

	@Override
	public INode getLinkedNode() {
		return linkedNode;
	}

	@Override
	public int getCost() {
		return cost;
	}

	public void setCost(int cost){
		this.cost=cost;
	}
	
	public void setOwnerId(int ownerId){
		this.ownerId=ownerId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * (result + ((ownerNode == null) ? 0 : ownerNode.hashCode()) + ((linkedNode == null) ? 0 : linkedNode.hashCode()));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		
		return (this.ownerNode.equals(other.ownerNode) && linkedNode.equals(other.linkedNode)) || (ownerNode.equals(other.linkedNode) && linkedNode.equals(other.ownerNode));
	}

}

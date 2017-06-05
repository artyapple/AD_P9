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

	private UUID ownerId;
	private int cost;
	private INode linkedNode;
	private INode ownerNode;

	public Edge( INode linkedNode,int cost, INode ownerNode) {
		this.linkedNode = linkedNode;
		this.cost = cost;
		this.ownerId = ownerNode.getNodeId();
		this.ownerNode=ownerNode;
	}

	@Override
	public UUID getOwnerId() {
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
	public void setOwnerId(UUID ownerId){
		this.ownerId=ownerId;
	}



}

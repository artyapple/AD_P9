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
public class LinkNode implements ILink {

	private UUID ownerId;
	private int cost;
	private INode linkedNode;

	public LinkNode( INode node,int cost, UUID ownerId) {
		this.linkedNode = node;
		this.cost = cost;
		this.ownerId = ownerId;
	}

	@Override
	public UUID getOwnerId() {
		return ownerId;
	}

	@Override
	public INode getLinkedNode() {
		return linkedNode;
	}

	@Override
	public int getCost() {
		return cost;
	}
//	public void setlinkedNode(INode node){
//		this.linkedNode=node;
//	}
	public void setCost(int cost){
		this.cost=cost;
	}
	public void setOwnerId(UUID ownerId){
		this.ownerId=ownerId;
	}

}

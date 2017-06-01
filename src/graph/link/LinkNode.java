package graph.link;

import graph.node.INode;

public class LinkNode implements ILink {

	private int ownerId;
	private int cost;
	private INode linkedNode;

	public LinkNode(INode node, int cost, int ownerId) {
		this.linkedNode = node;
		this.cost = cost;
		this.ownerId = ownerId;
	}

	@Override
	public int getOwnerId() {
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

}

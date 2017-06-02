package graph.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import graph.Graph;
import graph.link.ILink;
import graph.link.LinkNode;
import graph.node.INode;
import graph.node.ListNode;

public class AdjacencyList implements Graph {

	private List<ListNode> nodes;
	private int size;

	public AdjacencyList(int numberNodes) {
		this.size = numberNodes;
		this.nodes = new ArrayList<ListNode>();

	}

	public void initList() {
		for (int i = 0; i < size; i++) {
			add(new ListNode("Node" + i));
		}
	}

	public void createLinks() {
		for (int i = 0; i < size; i++) {
			int connections = (int) (Math.random() * 10);
			for (int k = 0; k < connections; k++) {
				int randomLinks = (int) (Math.random() * size);

				int cost = (int) (Math.random() * 20);

				if ((this.nodes.get(randomLinks) != nodes.get(i))) {

					LinkNode linknode = new LinkNode(this.nodes.get(randomLinks), cost, nodes.get(i).getNodeId());
					nodes.get(i).getLinkList().add(linknode);
				}

				//Check if Neighbor already exists in Linked Node
				if (isNeighbors(nodes.get(randomLinks), nodes.get(i)) == false) {
					nodes.get(randomLinks).setLink(nodes.get(i), cost);
				}

			}
		}

	}

	public void add(ListNode node) {
		nodes.add(node);
	}

	@Override
	public void traverse() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		return false;

	}

	@Override
	public boolean isNeighbors(INode n, INode m) {

		ListNode listNode = (ListNode) n;
		List<ILink> linkNodes = listNode.getLinkList();

		Iterator<ILink> iterLinkNodes = linkNodes.iterator();

		while (iterLinkNodes.hasNext()) {
			if (iterLinkNodes.next().getLinkedNode() == m) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getCost(INode from, INode to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSize() {
		return size;
	}

	public INode getNode(int index) {
		return this.nodes.get(index);
	}

	public static void main(String[] args) {
		AdjacencyList list = new AdjacencyList(10);
		list.initList();
		list.createLinks();
		System.out.println("Ende");
	}

}

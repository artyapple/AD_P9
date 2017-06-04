package graph.list;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;


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

				int cost = (int) (Math.random() * 20) + 2;

				if ((!nodes.get(randomLinks).equals(nodes.get(i)))) {

					LinkNode linknode = new LinkNode(this.nodes.get(randomLinks), cost, nodes.get(i).getNodeId());
				
					if (isNeighbors(nodes.get(i), nodes.get(randomLinks)) == false) {
						// wenn der Linkknoten(EDGE) noch nicht in der Linkliste
						// des i-ten Knoten existiert
						nodes.get(i).getLinkList().add(linknode);
					}
					// Check if Neighbor already exists in Linked Node
					if (isNeighbors(nodes.get(randomLinks), nodes.get(i)) == false) {
						nodes.get(randomLinks).setLink(nodes.get(i), cost);
					}
				}
			}
		}
	}

	public void add(ListNode node) {
		nodes.add(node);
	}
/**
 * Breitensuche
 */
	@Override
	public boolean traverse(INode startNode, INode destinationNode) {
		int zähler = 0;
		String result = "";
		ListNode startListNode = (ListNode) startNode;
		Queue<ListNode> queue = new ArrayDeque<>();
		queue.add(startListNode);
		startListNode.mark();
		while (!queue.isEmpty()) { // solange queue nicht leer ist
			ListNode node = (ListNode) queue.poll(); // erstes Element von der
														// queue nehmen
			if (node.equals(destinationNode)) {
				System.out.println("Zähler: "+zähler);
				return true; // testen, ob Ziel-Knoten gefunden
			}
			List<ILink> list = node.getLinkList();
			for (int i = 0; i < list.size(); i++) {
				if (!((ListNode) list.get(i).getLinkedNode()).getMark()) {
					queue.add((ListNode) list.get(i).getLinkedNode());
					((ListNode) list.get(i).getLinkedNode()).mark();
					result += node.getName() + "-" + list.get(i).getLinkedNode().getName();

				}
			}
			zähler++;
			
			System.out.println(result+"||");
		}
		return false; // Knoten kann nicht erreicht werden

	}

	@Override
	public boolean isConnected(INode n, INode m) {
		return false;

	}

	@Override
	public boolean isNeighbors(INode n, INode m) {

		ListNode listNode = (ListNode) n;
		List<ILink> linkNodes = listNode.getLinkList();

		Iterator<ILink> iterLinkNodes = linkNodes.iterator();
		while (iterLinkNodes.hasNext()) {
			if (iterLinkNodes.next().getLinkedNode().equals((ListNode) m)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getCost(INode from, INode to) {
		if (isNeighbors(from, to)) {
			ListNode fromList = (ListNode) from;
			List<ILink> linkNodes = fromList.getLinkList();
			Iterator<ILink> iterLinkNodes = linkNodes.iterator();
			while (iterLinkNodes.hasNext()) {
				LinkNode linkNode = (LinkNode) iterLinkNodes.next();
				INode node = linkNode.getLinkedNode();
				if (node.equals(to)) {
					return linkNode.getCost();
				}
			}

		}
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
		int anzahlKnoten = 10;
		AdjacencyList list = new AdjacencyList(anzahlKnoten);
		list.initList();
		list.createLinks();
		for (int i = 0; i < anzahlKnoten; i++) {
			ListNode listNode = (ListNode) list.getNode(i);
			System.out.println("\nNode: " + (list.getNode(i)).getNodeId());
			List<ILink> linkNodes = listNode.getLinkList();
			for (int j = 0; j < linkNodes.size(); j++) {
				System.out.println(linkNodes.get(j).getLinkedNode().getNodeId());
			}

		}
		System.out.println("Node0 ist Nachbar zu Node1: " + list.getNode(0).isNeighbors(list.getNode(1)));
		System.out.println("Kosten Node0 zu Node1: " + list.getCost(list.getNode(0), list.getNode(1)));
		System.out.println("Ende");

		list.traverse(list.getNode(0), list.getNode(1));

	}

}

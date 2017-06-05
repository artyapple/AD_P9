package graph.list;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import graph.Graph;
import graph.link.IEdge;
import graph.link.Edge;
import graph.node.INode;
import graph.node.ListNode;

public class AdjacencyList implements Graph {

	private List<INode> nodes;
	private int size;

	public AdjacencyList(int numberNodes) {
		this.size = numberNodes;
		this.nodes = new ArrayList<INode>();

	}

	public void initList() {
		for (int i = 0; i < size; i++) {
			add(new ListNode("Node" + i));
		}
	}

	public void createEdges() {
		for (int i = 0; i < size; i++) {
			int connections = (int) (Math.random() * 10);
			for (int k = 0; k < connections; k++) {
				int randomLinks = (int) (Math.random() * size);

				int cost = (int) (Math.random() * 20) + 2;

				if ((!nodes.get(randomLinks).equals(nodes.get(i)))) {

					Edge edge = new Edge(this.nodes.get(randomLinks), cost, nodes.get(i).getNodeId(), nodes.get(i));

					if (isNeighbors(nodes.get(i), nodes.get(randomLinks)) == false) {
						// wenn der Linkknoten(EDGE) noch nicht in der Linkliste
						// des i-ten Knoten existiert
						((ListNode) nodes.get(i)).getEdges().add(edge);
					}
					// Check if Neighbor already exists in Linked Node
					if (isNeighbors(nodes.get(randomLinks), nodes.get(i)) == false) {
						((ListNode) nodes.get(randomLinks)).setLink(nodes.get(i), cost);
					}
				}
			}
		}
	}

	public void add(ListNode node) {
		nodes.add(node);
	}

	@Override
	public List<INode> getINodes() {
		return this.nodes;
	}

	/**
	 * Return alle Edges of the Graph
	 */
	@Override
	public List<IEdge> getIEdges() {
		List<IEdge> listEdges = new ArrayList<IEdge>();
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j < nodes.size(); j++) {
				// Node1 und Node2 sind Nachbarn
				if (nodes.get(i).isNeighbors(nodes.get(j))) {
					// Edges von nodes(i) erstellen
					List<IEdge> listEdgesI = new ArrayList<IEdge>();
					listEdgesI = ((ListNode) nodes.get(i)).getEdges();
					;
					int index = 0;
					// Suche nach der Edge in der Liste des Nodes(i)
					while (!listEdgesI.get(index).getLinkedNode().equals(nodes.get(j))) {
						index++;
					}
					// Check if Edge exists already
					Iterator<IEdge> iterListEdges = listEdges.iterator();
					boolean edgeExists = false;
					while (iterListEdges.hasNext()) {
						IEdge edge = (IEdge) iterListEdges.next();

						if (edge.getLinkedNode().equals(listEdgesI.get(index).getOwnerNode())) {
							edgeExists = true;
						}
					}
					if (edgeExists == false) {

						listEdges.add(listEdgesI.get(index));
					}

				}
			}
		}
		return listEdges;
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
				System.out.println("Zähler: " + zähler);
				return true; // testen, ob Ziel-Knoten gefunden
			}
			List<IEdge> list = node.getEdges();
			for (int i = 0; i < list.size(); i++) {
				if (!((ListNode) list.get(i).getLinkedNode()).getMark()) {
					queue.add((ListNode) list.get(i).getLinkedNode());
					((ListNode) list.get(i).getLinkedNode()).mark();
					result += node.getName() + "-" + list.get(i).getLinkedNode().getName();

				}
			}
			zähler++;

			System.out.println(result + "||");
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
		List<IEdge> linkNodes = listNode.getEdges();

		Iterator<IEdge> iterLinkNodes = linkNodes.iterator();
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
			List<IEdge> linkNodes = fromList.getEdges();
			Iterator<IEdge> iterLinkNodes = linkNodes.iterator();
			while (iterLinkNodes.hasNext()) {
				Edge edge = (Edge) iterLinkNodes.next();
				INode node = edge.getLinkedNode();
				if (node.equals(to)) {
					return edge.getCost();
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
		list.createEdges();
		for (int i = 0; i < anzahlKnoten; i++) {
			ListNode listNode = (ListNode) list.getNode(i);
			System.out.println("\nNode: " + (list.getNode(i)).getNodeId());
			List<IEdge> linkNodes = listNode.getEdges();
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

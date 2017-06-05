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
	private List<IEdge> edges;
	private int size;

	public AdjacencyList(int numberNodes) {
		this.size = numberNodes;
		this.nodes = new ArrayList<INode>();
		this.edges = new ArrayList<IEdge>();

	}

	public AdjacencyList() {
		this.nodes = new ArrayList<INode>();
		this.edges = new ArrayList<IEdge>();

	}

	@Override
	public void addNode(INode node) {
		this.nodes.add(node);
	}

	@Override
	public boolean addEdge(IEdge edge) {
		this.edges.add(edge);
		int i = nodes.indexOf(edge.getOwnerNode());
		int j = nodes.indexOf(edge.getLinkedNode());
		((ListNode) nodes.get(i)).setLink(nodes.get(j), edge.getCost());
		return true;
	}

	@Override
	public List<INode> getINodes() {
		return this.nodes;
	}

	@Override
	public INode getINode(int index) {
		return nodes.get(index);
	}

	/**
	 * Return alle Edges of the Graph
	 */
	@Override
	public List<IEdge> getIEdges() {
		return this.edges;
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
		return nodes.size();
	}

	public INode getNode(int index) {
		return this.nodes.get(index);
	}

	public void initList() {
		for (int i = 0; i < size; i++) {
			addNode(new ListNode("Node" + i));
		}
	}

	public void createEdges() {
		for (int i = 0; i < size; i++) {
			// Zufällige Anzahl von Verbindungen je Knoten
			int connections = (int) (Math.random() * 10);
			for (int k = 0; k < connections; k++) {
				int randomNode = (int) (Math.random() * nodes.size());
				// Kosten erstellen
				int cost = (int) (Math.random() * 20) + 1;

				if ((!nodes.get(randomNode).equals(nodes.get(i)))) {

					Edge edge = new Edge(this.nodes.get(randomNode), cost, nodes.get(i));

					if (isNeighbors(nodes.get(i), nodes.get(randomNode)) == false) {
						// wenn der Linkknoten(EDGE) noch nicht in der Linkliste
						// des i-ten Knoten existiert
						((ListNode) nodes.get(i)).getEdges().add(edge);
						edges.add(edge);
					}
					// Check if Neighbor already exists in Linked Node
					if (isNeighbors(nodes.get(randomNode), nodes.get(i)) == false) {
						((ListNode) nodes.get(randomNode)).setLink(nodes.get(i), cost);
						edges.add(edge);
					}
				}
			}
		}
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

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

	//TODO init list or init with add
	//was macht diese methode
	public void add(ListNode node) {
		nodes.add(node);
	}

	@Override
	public List<INode> getINodes() {
		return this.nodes;
	}

	
	//TODO was macht diese methode
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
		int z�hler = 0;
		String result = "";
		ListNode startListNode = (ListNode) startNode;
		Queue<ListNode> queue = new ArrayDeque<>();
		queue.add(startListNode);
		startListNode.mark();
		while (!queue.isEmpty()) { // solange queue nicht leer ist
			ListNode node = (ListNode) queue.poll(); // erstes Element von der
														// queue nehmen
			if (node.equals(destinationNode)) {
				System.out.println("Z�hler: " + z�hler);
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
			z�hler++;

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

	//TODO was macht diese methode
	public INode getNode(int index) {
		return this.nodes.get(index);
	}

}

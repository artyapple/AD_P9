package graph.list;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import data.json.graph.LinkDataContainer;
import data.json.graph.NodeDataContainer;
import graph.Graph;
import graph.link.Edge;
import graph.link.IEdge;
import graph.node.INode;
import graph.node.ListNode;

public class AdjacencyList implements Graph {

	private List<INode> nodes;

	public AdjacencyList() {
		this.nodes = new ArrayList<INode>();
	}

	@Override
	public INode getNode(int id) {
		for (INode node : nodes) {
			if (node.getNodeId() == id) {
				return node;
			}
		}
		return null;
	}

	@Override
	public INode getNode(String name) {
		for (INode node : nodes) {
			if (node.getName().equals(name)) {
				return node;
			}
		}
		return null;
	}

	@Override
	public List<INode> getNeighbors(INode node) {
		List<INode> neighbors = new ArrayList<INode>();
		for (IEdge edge : ((ListNode) node).getEdges()) {
			neighbors.add(edge.getLinkedNode());
		}
		return neighbors;
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
	public int size() {
		return nodes.size();
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

		return 0;
	}

	@Override
	public void initilize(List<NodeDataContainer> list) {
		initList(list);
		createEdges(list);

	}

	private void initList(List<NodeDataContainer> list) {
		for (NodeDataContainer cont : list) {
			add(new ListNode(cont.getNodeName(), cont.getNodeId()));
		}
	}

	private void createEdges(List<NodeDataContainer> list) {

		for (int i = 0; i < nodes.size(); i++) {

			ListNode currentnode = (ListNode) nodes.get(i);
			List<LinkDataContainer> links = list.get(i).getLinkedNode();
			for (LinkDataContainer link : links) {
				IEdge edge = new Edge(nodes.get(link.getLinkId()), link.getCost(), currentnode.getNodeId(),
						currentnode);
				currentnode.getEdges().add(edge);
			}
		}
	}

	private void add(ListNode node) {
		nodes.add(node);
	}

}

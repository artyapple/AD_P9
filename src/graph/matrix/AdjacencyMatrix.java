package graph.matrix;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import data.json.graph.LinkDataContainer;
import data.json.graph.NodeDataContainer;
import graph.Graph;
import graph.node.INode;
import graph.node.MatrixNode;

public class AdjacencyMatrix implements Graph {

	private static final int NOTNEIGHBOURS = -1;
	private List<INode> nodes;
	private byte[][] costs;

	/**
	 * @param nodes
	 * @param costs
	 * @param size
	 */
	public AdjacencyMatrix(int size) {
		this.nodes = new ArrayList<INode>();
		this.costs = new byte[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				costs[i][j] = NOTNEIGHBOURS;
			}
		}
	}

	@Override
	public List<INode> getNeighbors(INode node) {
		List<INode> neighbors = new ArrayList<INode>();
		for (int i = 0; i < nodes.size(); i++) {
			INode neighbor = nodes.get(i);
			if (this.isNeighbors(node, neighbor)) {
				neighbors.add(neighbor);
			}
		}
		return neighbors;
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
	public boolean isNeighbors(INode n, INode m) {
		return (costs[nodes.indexOf(n)][nodes.indexOf(m)] != NOTNEIGHBOURS);
	}

	@Override
	public int getCost(INode from, INode to) {
		return costs[nodes.indexOf(from)][nodes.indexOf(to)];
	}

	@Override
	public int size() {
		return nodes.size();
	}

	@Override
	public boolean traverse(INode startNode, INode destinationNode) {
		// Sind die Knoten verbunden?
		if (isNeighbors(startNode, destinationNode) == true) {
			int zähler = 0;
			String result = "";
			MatrixNode startListNode = (MatrixNode) startNode;
			Queue<MatrixNode> queue = new ArrayDeque<>();
			queue.add(startListNode);
			startListNode.mark();
			while (!queue.isEmpty()) { // solange queue nicht leer ist
				// erstes Element von der queue nehmen
				MatrixNode node = (MatrixNode) queue.poll();
				if (node.equals(destinationNode)) {
					System.out.println("Zähler: " + zähler);
					return true; // testen, ob Ziel-Knoten gefunden
				}
				for (int i = 0; i < nodes.size(); i++) {
					if (isNeighbors(node, nodes.get(i))) {
						if (!((MatrixNode) nodes.get(i)).getMark()) {
							queue.add((MatrixNode) nodes.get(i));
							((MatrixNode) nodes.get(i)).mark();
							result += node.getName() + "-" + nodes.get(i).getName();
						}
					}
				}
				zähler++;
				System.out.println(result + "||");
			}
		}
		return false; // Knoten kann nicht erreicht werden
	}

	@Override
	public void initilize(List<NodeDataContainer> list) {
		initList(list);
		createLinks(list);
	}

	private void initList(List<NodeDataContainer> list) {
		for (NodeDataContainer cont : list) {
			add(new MatrixNode(cont.getNodeName(), cont.getNodeId()));
		}
	}

	private void createLinks(List<NodeDataContainer> list) {

		for (int i = 0; i < nodes.size(); i++) {

			INode currentnode = nodes.get(i);
			List<LinkDataContainer> links = list.get(i).getLinkedNode();
			for (LinkDataContainer link : links) {
				addEdge(currentnode.getNodeId(), link.getLinkId(), (byte)link.getCost());
			}
		}
	}

	private boolean addEdge(int fromId, int toId, byte cost) {
		int i = fromId;
		int j = toId;
		if (i != NOTNEIGHBOURS && j != NOTNEIGHBOURS) {
			costs[i][j] = cost;
			costs[j][i] = cost;
			return true;
		}
		return false;
	}

	private void add(INode node) {
		nodes.add(node);
	}
}

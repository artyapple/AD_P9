package graph.matrix;

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
import graph.node.MatrixNode;

public class AdjacencyMatrix implements Graph {

	private static final int NOTNEIGHBOURS = -1;
	private List<INode> nodes;
	//private List<IEdge> edges;
	private int[][] costs;
	private int size;

	/**
	 * @param nodes
	 * @param costs
	 * @param size
	 */
	public AdjacencyMatrix(int size) {
		this.nodes = new ArrayList<INode>();
		//this.edges = new ArrayList<IEdge>();
		this.costs = new int[size][size];
		this.size = size;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				costs[i][j] = NOTNEIGHBOURS;
			}
		}
	}

	@Override
	public List<INode> getINodes() {
		return this.nodes;
	}

	@Override
	public List<IEdge> getIEdges() {
		List<IEdge> edges = new ArrayList<IEdge>();
		for (int i = 0; i < nodes.size(); i++) {
			MatrixNode currentnode = (MatrixNode) nodes.get(i);
			for (int j = 0; j < nodes.size(); j++) {
				MatrixNode linkedNode = (MatrixNode) nodes.get(j);
					if(isNeighbors(currentnode, linkedNode)){
						IEdge edge = new Edge(linkedNode, getCost(linkedNode, currentnode), currentnode.getNodeId(),
								currentnode);
						if (!edges.contains(new Edge(currentnode, getCost(currentnode, linkedNode),
								linkedNode.getNodeId(), linkedNode))) {
							edges.add(edge);
					}
					}
				
			}
		}
		return edges;
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
	public int getSize() {
		return this.size;
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
				for (int i = 0; i < size; i++) {
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
		//createEdges(list);

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
				addEdge(currentnode.getNodeId(), link.getLinkId(), link.getCost());
			}
		}
	}

	private boolean addEdge(int fromId, int toId, int cost) {
		int i = fromId;
		int j = toId;
		if (i != NOTNEIGHBOURS && j != NOTNEIGHBOURS) {
			costs[i][j] = cost;
			costs[j][i] = cost;
			return true;
		}
		return false;
	}

	// private boolean removeEdge(INode from, INode to) {
	// int i = getNodeIndex(from);
	// int j = getNodeIndex(to);
	// if (i != NOTNEIGHBOURS && j != NOTNEIGHBOURS) {
	// costs[i][j] = NOTNEIGHBOURS;
	// costs[j][i] = NOTNEIGHBOURS;
	// return true;
	// }
	// return false;
	// }

	private void add(INode node) {
		nodes.add(node);
	}


}

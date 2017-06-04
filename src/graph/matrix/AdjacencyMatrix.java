package graph.matrix;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


import graph.Graph;
import graph.node.INode;
import graph.node.MatrixNode;

public class AdjacencyMatrix implements Graph {

	private static final int NOTNEIGHBOURS = -1;
	private List<INode> nodeArray;
	private int[][] costs;
	private int size;

	/**
	 * @param nodeArray
	 * @param costs
	 * @param size
	 */
	public AdjacencyMatrix(int size) {
		this.nodeArray = new ArrayList<INode>();
		this.costs = new int[size][size];
		this.size = size;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				costs[i][j] = NOTNEIGHBOURS;
			}
		}
	}

	public void initList() {
		for (int i = 0; i < size; i++) {
			add(new MatrixNode("Node" + i));
		}
	}

	public void createLinks() {
		int anzahlEdges =5;
		for (int i = 0; i < size; i++) {
			INode from = nodeArray.get(i);
			for(int j=0;j<anzahlEdges;j++){
			INode to = nodeArray.get((int) (Math.random() * size));
			if (!from.equals(to)) {
				addEdge(from, to);
			}
			}
		}
	}

	public List<INode> getList() {
		return this.nodeArray;
	}

	public void add(INode node) {
		nodeArray.add(node);
	}

	public boolean addEdge(INode from, INode to) {
		int i = getNodeIndex(from);
		int j = getNodeIndex(to);
		if (i != NOTNEIGHBOURS && j != NOTNEIGHBOURS) {
			int cost = (int) (Math.random() * 20) + 1;
			costs[i][j] = cost;
			costs[j][i] = cost;
			return true;
		}
		return false;
	}

	public boolean removeEdge(INode from, INode to) {
		int i = getNodeIndex(from);
		int j = getNodeIndex(to);
		if (i != NOTNEIGHBOURS && j != NOTNEIGHBOURS) {
			costs[i][j] = NOTNEIGHBOURS;
			costs[j][i] = NOTNEIGHBOURS;
			return true;
		}
		return false;
	}

	public int getNodeIndex(INode node) {
		
		return nodeArray.indexOf(node);
	}


	@Override
	public boolean isConnected(INode n, INode m) {
		if(costs[getNodeIndex(n)][getNodeIndex(m)] > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean isNeighbors(INode n, INode m) {
		return (costs[nodeArray.indexOf(n)][nodeArray.indexOf(m)] != NOTNEIGHBOURS);
	}

	@Override
	public int getCost(INode from, INode to) {
		return costs[nodeArray.indexOf(from)][nodeArray.indexOf(to)];
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
					if(isConnected(node, nodeArray.get(i))){
						if (!((MatrixNode) nodeArray.get(i)).getMark()) {
							queue.add((MatrixNode) nodeArray.get(i));
							((MatrixNode) nodeArray.get(i)).mark();
							result += node.getName() + "-" + nodeArray.get(i).getName();
						}
					}
				}
				zähler++;
				System.out.println(result + "||");
			}
		}
		return false; // Knoten kann nicht erreicht werden
	}

	public int getNumberEdges(INode node) {
		int numberEdges = 0;
		for (int i = 0; i < size; i++) {
			if (isConnected(node, nodeArray.get(i))) {
				numberEdges++;
			}
		}
		return numberEdges;
	}

	public static void main(String[] args) {
		int anzahlKnoten = 10;
		AdjacencyMatrix matrix = new AdjacencyMatrix(anzahlKnoten);
		matrix.initList();
		matrix.createLinks();
		for (int i = 0; i < anzahlKnoten; i++) {
			MatrixNode nodeFrom = (MatrixNode) matrix.getList().get(i);
			System.out.println("\nNode: " + nodeFrom.getNodeId());

			for (int j = 0; j < anzahlKnoten; j++) {
				MatrixNode nodeTo = (MatrixNode) matrix.getList().get(j);
				if (!nodeFrom.equals(nodeTo) && matrix.isConnected(nodeFrom, nodeTo)) {
					System.out.println(nodeTo.getNodeId());
				}
			}

		}
		System.out.println(
				"Node0 ist Nachbar zu Node1: " + matrix.isNeighbors(matrix.getList().get(0), matrix.getList().get(1)));
		System.out
				.println("Kosten Node0 zu Node1: " + matrix.getCost(matrix.getList().get(0), matrix.getList().get(1)));
		System.out.println("Ende");

		if ((matrix.traverse(matrix.getList().get(0), matrix.getList().get(1))) == false) {
			System.out.println("Die Bedingungen eines Graphen wurden nicht erfüllt");
		}

	}

}

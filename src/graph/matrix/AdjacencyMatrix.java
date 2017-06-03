package graph.matrix;

import java.util.ArrayDeque;
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
		this.costs = new int[size][size];
		this.size = size;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				costs[i][j] = NOTNEIGHBOURS;
			}
		}
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
		int index = -1;
		int i = 0;
		while (index < 0 && i < nodeArray.size()) {
			if (nodeArray.get(i).equals((node))) {
				index = i;
			}
			i++;
		}
		return index;
	}

	@Override
	public boolean isConnected() {
		// TODO
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
		int zähler = 0;
		String result = "";
		MatrixNode startListNode = (MatrixNode) startNode;
		Queue<MatrixNode> queue = new ArrayDeque<>();
		queue.add(startListNode);
		startListNode.mark();
		while (!queue.isEmpty()) { // solange queue nicht leer ist
			MatrixNode node = (MatrixNode) queue.poll(); // erstes Element von
															// der
															// queue nehmen
			if (node.equals(destinationNode)) {
				System.out.println("Zähler: " + zähler);
				return true; // testen, ob Ziel-Knoten gefunden
			}
			int numberEgdes = getNumberEdges(node);
			for (int i = 0; i < size; i++) {
				if (costs[getNodeIndex(node)][i] > 0) {

				}
				if (!((MatrixNode) nodeArray.get(i)).getMark()) {
					queue.add((MatrixNode) nodeArray.get(i));
					((MatrixNode) nodeArray.get(i)).mark();
					result += node.getName() + "-" + nodeArray.get(i).getName();

				}
			}
			zähler++;

			System.out.println(result + "||");
		}
		return false; // Knoten kann nicht erreicht werden

	}

	public int getNumberEdges(INode node) {
		int numberEdges = 0;
		for (int i = 0; i < size; i++) {
			if (costs[getNodeIndex(node)][i] > 0) {
				numberEdges++;
			}
		}
		return numberEdges;
	}

}

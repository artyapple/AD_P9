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

	@Override
	public List<INode> getINodes() {
		return this.nodeArray;
	}

	@Override
	public List<IEdge> getIEdges() {
		List<IEdge> listEdges = new ArrayList<IEdge>();
		for (int i = 0; i < nodeArray.size(); i++) {
			for (int j = 0; j < nodeArray.size(); j++) {
				if (isConnected(nodeArray.get(i), nodeArray.get(j))) {
					Iterator<IEdge> iterListEdges = listEdges.iterator();
					boolean edgeExists = false;
					while (iterListEdges.hasNext()) {
						IEdge edge = (IEdge) iterListEdges.next();

						if (edge.getLinkedNode().getNodeId() == nodeArray.get(i).getNodeId()) {
							edgeExists = true;
						}
					}
					if (edgeExists == false) {

						Edge edge = new Edge(nodeArray.get(j), getCost(nodeArray.get(i), nodeArray.get(j)),
								nodeArray.get(i).getNodeId(), nodeArray.get(i));
						listEdges.add(edge);
					}
				}
			}
		}
		return listEdges;
	}

	// TODO was macht diese methode
	public List<INode> getList() {
		return this.nodeArray;
	}

	@Override
	public boolean isConnected(INode n, INode m) {
		if (costs[getNodeIndex(n)][getNodeIndex(m)] > 0) {
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
					if (isConnected(node, nodeArray.get(i))) {
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

	// TODO was macht diese methode
	public int getNumberEdges(INode node) {
		int numberEdges = 0;
		for (int i = 0; i < size; i++) {
			if (isConnected(node, nodeArray.get(i))) {
				numberEdges++;
			}
		}
		return numberEdges;
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
		
		for(int i = 0; i< nodeArray.size(); i++){
			
			INode currentnode = nodeArray.get(i);
			List<LinkDataContainer> links =  list.get(i).getLinkedNode();
			for(LinkDataContainer link : links){
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

//	private boolean removeEdge(INode from, INode to) {
//		int i = getNodeIndex(from);
//		int j = getNodeIndex(to);
//		if (i != NOTNEIGHBOURS && j != NOTNEIGHBOURS) {
//			costs[i][j] = NOTNEIGHBOURS;
//			costs[j][i] = NOTNEIGHBOURS;
//			return true;
//		}
//		return false;
//	}

	private void add(INode node) {
		nodeArray.add(node);
	}
	
	private int getNodeIndex(INode node) {
		return nodeArray.indexOf(node);
	}
}

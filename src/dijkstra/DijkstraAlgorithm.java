package dijkstra;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.Graph;
import graph.node.INode;

/**
 * 
 * @author daexel
 *
 *         Klasse zum Anwenden des DijkstraAlgoithm auf Graphen.
 *
 */
public class DijkstraAlgorithm implements IDijkstraAlgorithm {

	private final Graph graph;
	private Set<INode> settledNodes;
	private Set<INode> unSettledNodes;
	private Map<INode, INode> predecessors;
	private Map<INode, Integer> distance;

	public DijkstraAlgorithm(Graph graph) {
		this.graph = graph;
	}

	/**
	 * Methode zur Ausführung des Algoithmus
	 * 
	 * @param source
	 *            StartNode
	 */
	@Override
	public void execute(INode source) {
		// initialisieren der Listen
		settledNodes = new HashSet<INode>();
		unSettledNodes = new HashSet<INode>();
		distance = new HashMap<INode, Integer>();
		predecessors = new HashMap<INode, INode>();
		// Mit dem Gewünschten Knoten beginnen.
		distance.put(source, 0);
		unSettledNodes.add(source);
		// Solange die unberühten Nodes in der Liste unSettledNodes >0;
		while (unSettledNodes.size() > 0) {
			// Referenzieren des Nodes mit dem kleinsten Kosten
			INode node = getMinimum(unSettledNodes);
			// Diesen Node in die settledNotes einfügen
			settledNodes.add(node);
			// Aus der unSettledNodes Liste entfernen
			unSettledNodes.remove(node);
			// nächsten Node mit nächstkleineren Kosten bearbeiten
			findMinimalDistances(node);
		}
	}

	@Override
	public LinkedList<INode> getPath(INode target) {

		LinkedList<INode> path = new LinkedList<INode>();
		INode step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}

		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		if (path != null) {

			for (INode node : path) {
				System.out.println(node.getNodeId());
			}
		}
		return path;
	}

	public void findMinimalDistances(INode node) {
		List<INode> adjacentNodes = graph.getNeighbors(node);
		System.out.println("aufruf id" + node.getNodeId());
		for (INode target : adjacentNodes) {
			System.out.println("nachbarn: " + target.getNodeId());
			if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
				distance.put(target, getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);// VorgängerNodes
				unSettledNodes.add(target);
			}
		}

	}

	public int getDistance(INode node, INode target) {
		return graph.getCost(node, target);
	}

	public INode getMinimum(Set<INode> iNodes) {
		INode minimum = null;
		for (INode node : iNodes) {
			if (minimum == null) {
				minimum = node;
			} else {
				if (getShortestDistance(node) < getShortestDistance(minimum)) {
					minimum = node;
				}
			}
		}
		return minimum;
	}

	public int getShortestDistance(INode destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}
}

package dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.Graph;
import graph.link.ILink;
import graph.link.LinkNode;
import graph.list.AdjacencyList;
import graph.node.INode;
import graph.node.ListNode;
import graph.node.MatrixNode;

public class DijkstraAlgorithm<INode> {
	private final List<INode> nodes;
    private final List<ILink> edges;
    private Set<INode> settledNodes;
    private Set<INode> unSettledNodes;
    private Map<INode, INode> predecessors;
    private Map<INode, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<INode>();
        nodes=(List<INode>) graph.getINodes();
        if(graph instanceof AdjacencyList){
        	//Hier brauchen wir eine Liste aller Verbindungen
        this.edges = new ArrayList<ILink>(graph.ge);
        }
    }

    public void execute(INode source) {
        settledNodes = new HashSet<INode>();
        unSettledNodes = new HashSet<INode>();
        distance = new HashMap<INode, Integer>();
        predecessors = new HashMap<INode, INode>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            INode node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(INode node) {
        List<INode> adjacentNodes = getNeighbors(node);
        for (INode target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(INode node, INode target) {
    	if(node instanceof ListNode){
    		for (ILink edge : edges) {
                if (edge.getOwnerId().equals(((ListNode) node).getNodeId())
                        && edge.getLinkedNode().equals(target)) {
                    return edge.getCost();
                }
            }
            throw new RuntimeException("Should not happen");
    	}
    	if(node instanceof MatrixNode){
    		//Über die Indizes
    	}
 
    	
    }

    private List<INode> getNeighbors(INode node) {
    	if(node instanceof ListNode){
    		 List<INode> neighbors = new ArrayList<INode>();
    	        for (ILink edge : edges) {
    	            if (edge.getOwnerId().equals(((ListNode) node).getNodeId())
    	                    && !isSettled((INode) edge.getLinkedNode())) {
    	                neighbors.add((INode) edge.getLinkedNode());
    	            }
    	        }
    	        return neighbors;
    		
    		
    	}
    
       
    }

    private INode getMinimum(Set<INode> iNodes) {
        INode minimum = null;
        for (INode vertex : iNodes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(INode vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(INode destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
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
        return path;
}
}

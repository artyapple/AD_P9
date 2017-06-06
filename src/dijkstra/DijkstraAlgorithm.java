package dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.Graph;
import graph.link.IEdge;
import graph.link.Edge;
import graph.list.AdjacencyList;
import graph.matrix.AdjacencyMatrix;
import graph.node.INode;
import graph.node.ListNode;
import graph.node.MatrixNode;
/**
 * 
 * @author daexel
 *
 *Klasse zum Anwenden des DijkstraAlgoithm auf Graphen.
 *
 */
public class DijkstraAlgorithm {
	private final List<INode> nodes;
    private final List<IEdge> edges;
    private Set<INode> settledNodes;
    private Set<INode> unSettledNodes;
    private Map<INode, INode> predecessors;
    private Map<INode, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<INode>((List<INode>)graph.getINodes());       	
        this.edges = new ArrayList<IEdge>(graph.getIEdges());//Liste aller Verbindungen
      
       
    }
/**
 * Methode zur Ausführung des Algoithmus
 * 
 * @param source StartNode
 */
    public void execute(INode source) {
    	//initialisieren der Listen
        settledNodes = new HashSet<INode>();
        unSettledNodes = new HashSet<INode>();
        distance = new HashMap<INode, Integer>();
        predecessors = new HashMap<INode, INode>();
        //Mit dem Gewünschten Knoten beginnen.
        distance.put(source, 0);
        unSettledNodes.add(source);
        //Solange die unberühten Nodes in der Liste unSettledNodes >0;
        while (unSettledNodes.size() > 0) {
        	//Referenzieren des Nodes mit dem kleinsten Kosten
            INode node = getMinimum(unSettledNodes);
            //Diesen Node in die settledNotes einfügen
            settledNodes.add(node);
            //Aus der unSettledNodes Liste entfernen
            unSettledNodes.remove(node);
            //nächsten Node mit nächstkleineren Kosten bearbeiten
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
                predecessors.put(target, node);//VorgängerNodes
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(INode node, INode target) {
    	if(node instanceof ListNode){
    		for (IEdge edge : edges) {
                if (edge.getOwnerNode().equals( node)
                        && edge.getLinkedNode().equals(target)) {
                    return edge.getCost();
                }
            }
            throw new RuntimeException("Should not happen");
    	}
    	if(node instanceof MatrixNode){
    		Iterator<IEdge> iter = edges.iterator(); 
    		while (iter.hasNext()) {
				IEdge edge = (IEdge) iter.next();
				if(edge.getOwnerNode().equals(node)&&edge.getLinkedNode().equals(target)){
					return edge.getCost();
				} 	
    		}}
    	return 0;
    	}

    private List<INode> getNeighbors(INode node) {
    	
    		 List<INode> neighbors = new ArrayList<INode>();
    	        for (IEdge edge : edges) {
    	            if (edge.getOwnerNode().equals(node)
    	                    && !isSettled((INode) edge.getLinkedNode())) {
    	                neighbors.add((INode) edge.getLinkedNode());
    	            }
    	        }
    	        return neighbors;
		}
       
    

    private INode getMinimum(Set<INode> iNodes) {
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

    private boolean isSettled(INode node) {
        return settledNodes.contains(node);
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
    
    
    
    // was macht diese methode ?!?!?!
    public static void main(String[] args) {
		int anzahlKnoten = 10;
		AdjacencyMatrix matrix = new AdjacencyMatrix(anzahlKnoten);
		matrix.initList();
		matrix.createLinks();
		for (int i = 0; i < anzahlKnoten; i++) {
			MatrixNode nodeFrom = (MatrixNode) matrix.getList().get(i);
			//System.out.println("\nNode: " + nodeFrom.getNodeId());

			for (int j = 0; j < anzahlKnoten; j++) {
				MatrixNode nodeTo = (MatrixNode) matrix.getList().get(j);
				if (!nodeFrom.equals(nodeTo) && matrix.isConnected(nodeFrom, nodeTo)) {
					//System.out.println(nodeTo.getNodeId());
				}
			}

		}
		AdjacencyList list = new AdjacencyList(anzahlKnoten);
		list.initList();
		list.createEdges();
		for (int i = 0; i < anzahlKnoten; i++) {
			ListNode listNode = (ListNode) list.getNode(i);
			//System.out.println("\nNode: " + (list.getNode(i)).getNodeId());
			List<IEdge> linkNodes = listNode.getEdges();
			for (int j = 0; j < linkNodes.size(); j++) {
				//System.out.println(linkNodes.get(j).getLinkedNode().getNodeId());
			}

		}
		DijkstraAlgorithm algo= new DijkstraAlgorithm(list);
		algo.execute(list.getINodes().get(0));
		LinkedList<INode> path = algo.getPath(list.getINodes().get(9));

        //assertNotNull(path);
        //assertTrue(path.size() > 0);
		if(path!=null){
        for (INode node : path) {
            System.out.println(node.getNodeId());
        }
		}
        System.out.println("Ende");
    }
}
		


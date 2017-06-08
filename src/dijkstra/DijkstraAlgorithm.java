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

import data.json.graph.LinkDataContainer;
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
public class DijkstraAlgorithm implements IDijkstraAlgorithm {
	 
    private final Graph graph;
    private Set<INode> settledNodes;
    private Set<INode> unSettledNodes;
    private Map<INode, INode> predecessors;
    private Map<INode, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
    	this.graph=graph; 
        // create a copy of the array so that we can operate on this array   
    }
/**
 * Methode zur Ausführung des Algoithmus
 * 
 * @param source StartNode
 */
    @Override
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
            System.out.println(node.getNodeId());
            findMinimalDistances(node);
        }
    }
    @Override
    public void findMinimalDistances(INode node) {
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
    @Override
    public int getDistance(INode node, INode target) {
   
    		if(graph.isNeighbors(node, target)){
    			return graph.getCost(node, target);
    		}
//    		for (IEdge edge : graph.getIEdges()) {
//                if ((edge.getOwnerNode().equals( node)
//                        && edge.getLinkedNode().equals(target))||(edge.getOwnerNode().equals( target)
//                                && edge.getLinkedNode().equals(node))) {
//                    return edge.getCost();
//                }
//            }
            throw new RuntimeException("Should not happen");
   }
    @Override
    public List<INode> getNeighbors(INode node) {
    	if(node instanceof ListNode){
    		 	List<INode> neighbors = new ArrayList<INode>();
    	        for (IEdge edge :((ListNode) node).getEdges()) {
    	        neighbors.add(edge.getLinkedNode());	
    	        }
   	        return neighbors;
		}
    	if(node instanceof MatrixNode){
    		List<INode> neighbors = new ArrayList<INode>();
    		for (int i=0;i<graph.getINodes().size();i++){
    			INode neighbor = graph.getINodes().get(i);
    			if(graph.isNeighbors(node, neighbor)){
    				neighbors.add(neighbor);
    			}
    		}
    		return neighbors;
    	}
    	return null;
    }
       
    
    @Override
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
    @Override
    public boolean isSettled(INode node) {
        return settledNodes.contains(node);
    }
    @Override
    public int getShortestDistance(INode destination) {
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
        
        if(path!=null){
        	
          for (INode node : path) {
              System.out.println(node.getNodeId());
          }
  		}
        
        return path;
}
    
    
    

}
		


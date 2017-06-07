package dijkstra;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import graph.node.INode;

public interface IDijkstraAlgorithm {

	  public void execute(INode source);
	  
	  public void findMinimalDistances(INode node);
	  
	  public int getDistance(INode node, INode target);
	  
	  public List<INode> getNeighbors(INode node);
	  
	  public INode getMinimum(Set<INode> iNodes);
	  
	  public LinkedList<INode> getPath(INode target);
	  
	  public int getShortestDistance(INode destination);
	  
	  public boolean isSettled(INode node);
}

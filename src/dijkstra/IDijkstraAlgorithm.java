package dijkstra;

import java.util.LinkedList;

import graph.node.INode;

public interface IDijkstraAlgorithm {

	/**
	 * starts dijkstra algorithm
	 * @param source
	 */
	public void execute(INode source);

	/**
	 * This method returns the path from the source to the selected target
	 * and NULL if no path exists
	 * 
	 * @param target
	 * @return
	 */
	public LinkedList<INode> getPath(INode target);
}

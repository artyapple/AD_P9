package dijkstra;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import graph.node.INode;

public interface IDijkstraAlgorithm {

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

import data.DataConstants;
import data.reader.JSONGraphReader;
import dijkstra.DijkstraAlgorithm;
import graph.Graph;
import graph.list.AdjacencyList;
import graph.matrix.AdjacencyMatrix;
import graph.node.INode;

public class Main {

	public static void main(String [] args){
		JSONGraphReader reader = new JSONGraphReader();
		
		//######## TEST DATA INPUT ############
		int size = 1000;
		int from = 56;
		int to = 687;
		//#####################################
		
		Graph gr = new AdjacencyList(size);
		gr = reader.getGraph(gr, size, DataConstants.GRAPH_CONFIG_BASE_PATH);
		INode toNode = gr.getINodes().get(to);
		INode fromNode = gr.getINodes().get(from);
		System.out.println("Find path from: "+fromNode.getName()+"(id="+fromNode.getNodeId()+")"+" to "+toNode.getName()+"(id="+toNode.getNodeId()+") :");
		
		DijkstraAlgorithm algo = new DijkstraAlgorithm(gr);
		algo.execute(toNode);
		algo.getPath(fromNode);
		
	}	
}

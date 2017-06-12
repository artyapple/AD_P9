package analysis;

import data.DataConstants;
import data.reader.JSONGraphReader;
import dijkstra.DijkstraAlgorithm;
import graph.Graph;
import graph.matrix.AdjacencyMatrix;
import graph.node.INode;

public class EffortAnalysis {

	public void execute() {
		JSONGraphReader reader = new JSONGraphReader();

		// ######## TEST DATA INPUT ############
		int size = 100;
		int from = 1;
		int to = 9;
		// #####################################

		Graph gr = new AdjacencyMatrix(size);
		
		//Graph gr = new AdjacencyList();
		
		
		gr = reader.getGraph(gr, size, DataConstants.GRAPH_CONFIG_BASE_PATH);
		INode toNodeM = gr.getNode(to);
		INode fromNodeM = gr.getNode(from);
		System.out.println("AdjacencyMatrix:\nFind path from: " + fromNodeM.getName() + "(id=" + fromNodeM.getNodeId()
				+ ")" + " to " + toNodeM.getName() + "(id=" + toNodeM.getNodeId() + ") :");

		CounterAdapter adapter = new CounterAdapter(gr);
		
		DijkstraAlgorithm algoMatrix = new DijkstraAlgorithm(adapter);
		algoMatrix.execute(toNodeM);
		algoMatrix.getPath(fromNodeM);
		System.out.println("Counter: "+adapter.getCounter());
	}
}

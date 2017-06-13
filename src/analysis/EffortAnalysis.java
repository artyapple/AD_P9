package analysis;

import data.DataConstants;
import data.reader.JSONGraphReader;
import dijkstra.DijkstraAlgorithm;
import graph.Graph;
import graph.list.AdjacencyList;
import graph.matrix.AdjacencyMatrix;
import graph.node.INode;

public class EffortAnalysis {

	// ######## TEST DATA INPUT ############
	int from = 1;
	int to = 9;
	// #####################################
	private JSONGraphReader reader = new JSONGraphReader();
	private Graph gr;
	private CounterAdapter adapter;
	private DijkstraAlgorithm alg;
	INode toNodeM;
	INode fromNodeM;

	public static void main(String[] args) {
		EffortAnalysis a = new EffortAnalysis();
		a.execute();
	}

	public void execute() {
		System.out.println("===== Start analysis =====");

		for (int i = 1; i < DataConstants.PROBLEM_SIZE.length - 1; i++) {
			// execute matrix
			executeAdjMatrix(DataConstants.PROBLEM_SIZE[i]);
			// execute list
			executeAdjList(DataConstants.PROBLEM_SIZE[i]);
			// change ids
			from = from * 10;
			to = to * 10;
		}

		// execute matrix
		//executeAdjMatrix(DataConstants.PROBLEM_SIZE[DataConstants.PROBLEM_SIZE.length - 1]);
		// execute list
		executeAdjList(DataConstants.PROBLEM_SIZE[DataConstants.PROBLEM_SIZE.length - 1]);
		// change ids
	}

	private void executeAdjMatrix(int size) {
		gr = new AdjacencyMatrix(size);
		gr = reader.getGraph(gr, size, DataConstants.GRAPH_CONFIG_BASE_PATH);
		toNodeM = gr.getNode(to);
		fromNodeM = gr.getNode(from);
		printInfo(size, dijkstra(fromNodeM, toNodeM), DataConstants.ADJ_MATR);
	}

	private void executeAdjList(int size) {

		gr = new AdjacencyList();
		gr = reader.getGraph(gr, size, DataConstants.GRAPH_CONFIG_BASE_PATH);
		toNodeM = gr.getNode(to);
		fromNodeM = gr.getNode(from);
		printInfo(size, dijkstra(fromNodeM, toNodeM), DataConstants.ADJ_LIST);
	}

	private double dijkstra(INode fromNodeM, INode toNodeM) {
		adapter = new CounterAdapter(gr);
		alg = new DijkstraAlgorithm(adapter);
		alg.execute(toNodeM);
		alg.getPath(fromNodeM);
		return adapter.getCounter();
	}

	private void printInfo(int size, double cnt, String graphType) {
		System.out.println(graphType + " : Find path from: " + fromNodeM.getName() + "(id=" + fromNodeM.getNodeId()
				+ ")" + " to " + toNodeM.getName() + "(id=" + toNodeM.getNodeId() + ") " + "| nodes: " + size
				+ " counter: " + cnt);
	}
}

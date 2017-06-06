package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import data.DataConstants;
import data.reader.JSONGraphReader;
import dijkstra.DijkstraAlgorithm;
import graph.Graph;
import graph.matrix.AdjacencyMatrix;
import graph.node.INode;

public class DijkstraAlgorithmTest {

	@Test
	public void test() {
		JSONGraphReader reader = new JSONGraphReader();
		int size = 10;
		Graph gr = new AdjacencyMatrix(10);
		gr = reader.getGraph(gr, size, DataConstants.GRAPH_CONFIG_BASE_PATH);
		INode node = gr.getINodes().get(2);
		INode ziel = gr.getINodes().get(5);
		
		DijkstraAlgorithm algo = new DijkstraAlgorithm(gr);
		algo.execute(node);
		algo.getPath(ziel);
	}
	
	
	

}

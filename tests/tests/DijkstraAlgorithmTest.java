package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import data.DataConstants;
import data.reader.JSONGraphReader;
import dijkstra.DijkstraAlgorithm;
import graph.Graph;
import graph.list.AdjacencyList;
import graph.matrix.AdjacencyMatrix;
import graph.node.INode;

public class DijkstraAlgorithmTest {

	private JSONGraphReader reader = new JSONGraphReader();
	private Graph graph;
	// ######## TEST DATA INPUT ############
	private final int[] ids = {28, 10};
	private final int size = 100;
	private final int from = 1;
	private final int to = 99;
	// #####################################

	@Test
	public void testDijkastraWithMatrix() {
		graph = new AdjacencyMatrix(size);
		testDijkstra();
	}
	
	@Test
	public void testDijkastraWithList() {
		graph = new AdjacencyList();
		testDijkstra();
	}
	
	private void testDijkstra(){
		graph = reader.getGraph(graph, size, DataConstants.GRAPH_CONFIG_UNITTEST);
		INode toNodeM = graph.getNode(to);
		INode fromNodeM = graph.getNode(from);
		DijkstraAlgorithm algoMatrix = new DijkstraAlgorithm(graph);
		algoMatrix.execute(toNodeM);
		List<INode> path = algoMatrix.getPath(fromNodeM);
		assertEquals(to, path.get(0).getNodeId());
		assertEquals(from, path.get(path.size()-1).getNodeId());
		assertEquals(ids[0], path.get(1).getNodeId());
		assertEquals(ids[1], path.get(2).getNodeId());
	}
}

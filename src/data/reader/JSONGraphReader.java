package data.reader;

import data.DataConstants;
import data.json.graph.NodeDataListWrapper;
import data.json.service.JSONService;
import graph.Graph;
import graph.matrix.AdjacencyMatrix;

public class JSONGraphReader {
	
	private JSONService jserv = new JSONService();
	private NodeDataListWrapper json;
	
//	private void read(String path, Graph graph){
//		json = jserv.readJsonGraph(path);
//		//TODO
//	}
	
	public Graph getGraph(Graph graph, int size, String basepath){
		json = jserv.readJsonGraph(basepath+size+DataConstants.JSON);
		graph = new AdjacencyMatrix(size);
		graph.initilize(json.getNodesDataContainers());
		return graph;
	}
	
	
	
	

}

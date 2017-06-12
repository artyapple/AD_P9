package data.reader;

import data.DataConstants;
import data.json.graph.NodeDataListWrapper;
import data.json.service.JSONService;
import graph.Graph;
import graph.list.AdjacencyList;
import graph.matrix.AdjacencyMatrix;

public class JSONGraphReader {
	
	private JSONService jserv = new JSONService();
	private NodeDataListWrapper json;
	
	/**
	 * creates graph from json config
	 * 
	 * @param graph
	 * @param size
	 * @param basepath
	 * @return
	 */
	public Graph getGraph(Graph graph, int size, String basepath){
		json = jserv.readJsonGraph(basepath+size+DataConstants.JSON);;
		graph.initilize(json.getNodesDataContainers());
		return graph;
	}
	
	
	
	

}

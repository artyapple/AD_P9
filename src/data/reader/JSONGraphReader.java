package data.reader;

import data.DataConstants;
import data.json.graph.NodeDataListWrapper;
import data.json.service.JSONService;
import graph.Graph;

public class JSONGraphReader {
	
	private JSONService jserv = new JSONService();
	private NodeDataListWrapper json;
	
	public void read(String path, Graph graph){
		json = jserv.readJsonGraph(path);
		//TODO
	}
	
	public static void main(String[] args){
		JSONGraphReader jr = new JSONGraphReader();
		//jr.read(DataConstants.GRAPH_CONFIG);
		System.out.println("ready");
	}

}
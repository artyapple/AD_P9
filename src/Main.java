import data.DataConstants;
import data.reader.JSONGraphReader;
import dijkstra.DijkstraAlgorithm;
import graph.Graph;
import graph.list.AdjacencyList;
import graph.node.INode;

public class Main {

	public static void main(String [] args){
		JSONGraphReader reader = new JSONGraphReader();
		int size = 10;
		Graph gr = new AdjacencyList(size);
		gr = reader.getGraph(gr, size, DataConstants.GRAPH_CONFIG_BASE_PATH);
		INode node = gr.getINodes().get(3);
		INode ziel = gr.getINodes().get(5);
		System.out.println("Name of element num 5: "+ziel.getName());
		
		DijkstraAlgorithm algo = new DijkstraAlgorithm(gr);
		algo.execute(node);
		algo.getPath(ziel);
	}
}

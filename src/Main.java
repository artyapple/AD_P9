import analysis.EffortAnalysis;
import data.DataConstants;
import data.reader.JSONGraphReader;
import dijkstra.DijkstraAlgorithm;
import graph.Graph;
import graph.list.AdjacencyList;
import graph.matrix.AdjacencyMatrix;
import graph.node.INode;

public class Main {

	public static void main(String [] args){
		
		EffortAnalysis a = new EffortAnalysis();
		
		a.execute();
//		JSONGraphReader reader = new JSONGraphReader();
//		
//		//######## TEST DATA INPUT ############
//		int size = 100;
//		int from = 1;
//		int to = 99;
//		//#####################################
//		
////		Graph gr = new AdjacencyList(size);
////		gr = reader.getGraph(gr, size, DataConstants.GRAPH_CONFIG_BASE_PATH);
////		INode toNode = gr.getINodes().get(to);
////		INode fromNode = gr.getINodes().get(from);
////		System.out.println("AdjacencyList:\nFind path from: "+fromNode.getName()+"(id="+fromNode.getNodeId()+")"+" to "+toNode.getName()+"(id="+toNode.getNodeId()+") :");
////		
////		DijkstraAlgorithm algoList = new DijkstraAlgorithm(gr);
////		algoList.execute(toNode);
////		algoList.getPath(fromNode);
//	
//		Graph matrix = new AdjacencyMatrix(size);
//		matrix= reader.getGraph(matrix,size,DataConstants.GRAPH_CONFIG_BASE_PATH);
//		INode toNodeM = matrix.getNode(to);
//		INode fromNodeM = matrix.getNode(from);
//		System.out.println("AdjacencyMatrix:\nFind path from: "+fromNodeM.getName()+"(id="+fromNodeM.getNodeId()+")"+" to "+toNodeM.getName()+"(id="+toNodeM.getNodeId()+") :");
//		
//		DijkstraAlgorithm algoMatrix = new DijkstraAlgorithm(matrix);
//		algoMatrix.execute(toNodeM);
//		algoMatrix.getPath(fromNodeM);
	}	
}

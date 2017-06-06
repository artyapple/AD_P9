package graph.node;


import java.util.UUID;


public class MatrixNode implements INode {
	
	public MatrixNode(String name, int id) {
		this.name = name;
		this.id = id;
		this.isVisited=false;
		
	}
	private boolean isVisited;
	private String name;
	private int id;

	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getNodeId() {
		return this.id;
	}

	//TODO methode loschen?
	@Override
	public boolean isNeighbors(INode otherNode) {
		return false;
	}
	public boolean getMark() {
		return isVisited;
	}
	
	public void mark() {
		this.isVisited=true;
		
	}

	public void unmark() {
		this.isVisited=false;
	}




}

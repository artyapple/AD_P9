package graph.node;


import java.util.UUID;


public class MatrixNode implements INode {
	
	public MatrixNode(String name) {
		this.name = name;
		this.id = UUID.randomUUID();
		this.isVisited=false;
		
	}
	private boolean isVisited;
	private String name;
	private UUID id;

	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public UUID getNodeId() {
		return this.id;
	}

	@Override
	public boolean isNeighbors(INode otherNode) {
		// TODO Auto-generated method stub
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

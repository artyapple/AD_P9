package graph.node;

import java.util.UUID;

public interface INode {
	
	public String getName();
	
	public UUID getNodeId();
	
	public boolean isNeighbors(INode otherNode);
	
	public void mark();
	
	public void unmark();
	
	public boolean getMark();
}

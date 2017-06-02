package graph.node;

import java.util.UUID;

public interface INode {
	
	public String getName();
	
	public UUID getNodeId();
	
	public boolean isNeighbors(INode otherNode);
}

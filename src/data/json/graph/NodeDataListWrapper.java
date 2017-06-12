package data.json.graph;

import java.util.List;

/**
 * JSON wrapper for graph config
 * 
 * @author AI
 *
 */
public class NodeDataListWrapper {
	
	private List<NodeDataContainer> nodesDataContainers;

	public List<NodeDataContainer> getNodesDataContainers() {
		return nodesDataContainers;
	}

	public void setNodesDataContainers(List<NodeDataContainer> nodesDataContainers) {
		this.nodesDataContainers = nodesDataContainers;
	}
}

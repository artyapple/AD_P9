package data.json.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON wrapper for node
 * 
 * @author AI
 *
 */
public class NodeDataContainer {

	private String nodeName;
	private int nodeId;
	private List<LinkDataContainer> linkedNode;

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public List<LinkDataContainer> getLinkedNode() {
		return linkedNode;
	}

	public void setLinkedNode(List<LinkDataContainer> linkedNode) {
		this.linkedNode = linkedNode;
	}

	public void addLink(LinkDataContainer ln) {
		if(linkedNode==null){
			linkedNode = new ArrayList<>();
		}
		linkedNode.add(ln);
	}
}

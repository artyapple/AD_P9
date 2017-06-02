package graph.node;

import graph.link.ILink;
import graph.link.LinkNode;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class ListNode implements INode {
	
	private String name;
	private UUID id;
	private List<ILink> links;
	
	
	public ListNode(String name) {
		this.name = name;
		this.id = UUID.randomUUID();
		this.links = new ArrayList<>();
	}
	
	public ListNode(String name, String links) {
		this.name = name;
		this.id = UUID.randomUUID(); 
	}
	
	public void setLink(INode node, int cost){
		ILink link = new LinkNode(node, cost, id);
		links.add(link);
	}
	

	@Override
	public String getName() {
		return name;
	}

	@Override
	public UUID getNodeId() {
		return id;		
	}
	
	private List<ILink> createLinks(){
		
		return null;
	}
	
	public List<ILink>  getLinkList(){
		return this.links;
	}

	@Override
	public boolean isNeighbors(INode otherNode) {
		// TODO Auto-generated method stub
		return false;
	}

}

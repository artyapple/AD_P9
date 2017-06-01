package graph.node;

import graph.link.ILink;
import graph.link.LinkNode;

import java.util.List;

public class ListNode implements INode {
	
	private String name;
	private int id;
	private List<ILink> links;
	
	
	public ListNode(String name) {
		this.name = name;
	}
	
	public ListNode(String name, String links) {
		this.name = name;
		//links = createLinks(); 
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
	public int getNodeId() {
		return id;		
	}
	
	private List<ILink> createLinks(){
		return null;
	}

}

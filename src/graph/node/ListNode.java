package graph.node;

import graph.link.ILink;
import graph.link.LinkNode;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListNode implements INode {
	
	private String name;
	private UUID id;
	private List<ILink> links;
	private boolean isVisited;
	
	
	public ListNode(String name) {
		this.name = name;
		this.id = UUID.randomUUID();
		this.links = new ArrayList<>();
		this.isVisited=false;
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
	
	
	public List<ILink>  getLinkList(){
		return this.links;
	}

	@Override
	public boolean isNeighbors(INode otherNode) {
		
		List<ILink> linkNodes = ((ListNode) otherNode).getLinkList();
		Iterator<ILink> iterLinkNodes = linkNodes.iterator();
		while (iterLinkNodes.hasNext()){
			if(iterLinkNodes.next().getLinkedNode().equals(this)){
				return true;
			}
		}
		return false;
	}

	public boolean getMark() {
		// TODO Auto-generated method stub
		return isVisited;
	}
	
	
	public void mark() {
		this.isVisited=true;
		
	}


	public void unmark() {
		this.isVisited=false;
		
	}


	

}

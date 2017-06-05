package data.gen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import data.DataConstants;
import data.json.graph.LinkDataContainer;
import data.json.graph.NodeDataContainer;
import data.json.graph.NodeDataListWrapper;
import data.json.names.CitiData;
import data.json.service.JSONService;

public class GraphDataGenerator {

	private JSONService jserv = new JSONService();
	private List<CitiData> cd;
	private int sizeNamesList;

	private List<NodeDataContainer> nodes = new ArrayList<>();
	private NodeDataListWrapper json = new NodeDataListWrapper();

	public void createGraphAsJson(int cnt) {
		initNames();
		createNodes(cnt);
		buildLinks(cnt);
		json.setNodesDataContainers(nodes);
		jserv.writeJsonGraph(DataConstants.GRAPH_CONFIG, json);
	}

	private void createNodes(int cnt) {

		NodeDataContainer node;

		for (int i = 0; i < cnt; i++) {
			node = new NodeDataContainer();
			node.setNodeId(i);
			node.setNodeName(getRandomName());
			nodes.add(node);
		}
	}

	private void initNames() {
		cd = jserv.getCitiList(DataConstants.NAMES_LIST);
		sizeNamesList = cd.size();
	}

	private String getRandomName() {

		return cd.get(ThreadLocalRandom.current().nextInt(0, sizeNamesList)).getName();
	}

	private void buildLinks(int cnt) {

		int currentId;
		int linkId;
		int cost;

		for (NodeDataContainer cont : nodes) {
			if(cont.getLinkedNode()==null || cont.getLinkedNode().size()==DataConstants.MAX_EDGES_CNT){
				currentId = cont.getNodeId();
				LinkDataContainer link;
				LinkDataContainer refl;
				
				for (int i = 0; i < DataConstants.MAX_EDGES_CNT; i++) {
					linkId = getRandomId(cnt);
					if (isLinkIdValid(linkId, currentId)) {
						link = new LinkDataContainer();
						refl = new LinkDataContainer();
						cost = getRandomCost();
						link.setCost(cost);
						link.setLinkId(linkId);
						cont.addLink(link);
						// other
						refl.setLinkId(currentId);
						refl.setCost(cost);
						nodes.get(linkId).addLink(refl);
					}
				}
			}
		}
	}

	private boolean isLinkIdValid(int linkid, int currentId) {

		if (linkid == currentId) {
			return false;
		}

		List<LinkDataContainer> links = nodes.get(currentId).getLinkedNode();
		if (links != null) {
			for (LinkDataContainer ln : links) {
				if (ln.getLinkId() == linkid) {
					return false;
				}
			}
			return true;
		}
		return true;
	}


	private int getRandomId(int cnt) {
		return ThreadLocalRandom.current().nextInt(0, cnt);
	}

	private int getRandomCost() {
		return ThreadLocalRandom.current().nextInt(1, DataConstants.MAX_COST_VALUE);
	}

	public static void main(String[] args) {

		GraphDataGenerator g = new GraphDataGenerator();
		g.createGraphAsJson(1000);
		System.out.println("ready");
	}

}

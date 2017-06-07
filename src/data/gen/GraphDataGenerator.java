package data.gen;

import java.util.ArrayList;
import java.util.Collections;
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

	private List<NodeDataContainer> nodes;
	private NodeDataListWrapper json;

	public GraphDataGenerator() {
		cd = jserv.getCitiList(DataConstants.NAMES_LIST);
		sizeNamesList = cd.size();
	}

	public void createGraphAsJson(int cnt, int maxEdges, String path) {
		nodes = new ArrayList<>();
		json = new NodeDataListWrapper();
		createNodes(cnt);
		buildLinks(cnt, maxEdges);
		json.setNodesDataContainers(nodes);
		jserv.writeJsonGraph(path, json);
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

	private String getRandomName() {
		return cd.get(ThreadLocalRandom.current().nextInt(0, sizeNamesList)).getName();
	}

	private void buildLinks(int cnt, int linksCount) {
		bindAllNodes(cnt);

		int currentId, linkId, cost;
		LinkDataContainer link, refl;

		//int linksCount = getLinksCount(cnt);

		for (NodeDataContainer cont : nodes) {
			while (cont.getLinkedNode().size() <= linksCount) {

				currentId = cont.getNodeId();
				// int linksCount = cnt/DataConstants.MAX_EDGES_CNT;
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

	private void bindAllNodes(int cnt) {

		int cost, realId, realId2, j, s;
		LinkDataContainer link, refl;

		List<Integer> rnd = generateBindId(cnt);
		s = rnd.size() - 1;

		for (int i = 0; i < s; i++) {
			realId = rnd.get(i);
			j = i + 1;
			realId2 = rnd.get(j);
			link = new LinkDataContainer();
			refl = new LinkDataContainer();
			cost = getRandomCost();
			link.setCost(cost);
			refl.setCost(cost);
			link.setLinkId(realId2);
			refl.setLinkId(realId);
			nodes.get(realId).addLink(link);
			nodes.get(realId2).addLink(refl);
		}

		// while (rnd.size() != 0) {
		// cont = nodes.get(realId);
		//
		// linkId = ThreadLocalRandom.current().nextInt(0, rnd.size());
		// realId2 = rnd.get(linkId);
		// rnd.remove(linkId);
		// cont2 = nodes.get(realId2);
		//
		// link = new LinkDataContainer();
		// refl = new LinkDataContainer();
		//
		// cost = getRandomCost();
		// link.setCost(cost);
		// link.setLinkId(realId);
		// cont.addLink(link);
		// // other
		// refl.setLinkId(realId2);
		// refl.setCost(cost);
		// cont2.addLink(refl);
		// realId = realId2;
		// }
	}

	private int getLinksCount(int cnt) {

		int val = Integer.toString(cnt).length();
		return (int) Math.pow(DataConstants.MAX_EDGES_FACTOR, val);
	}

	private boolean isLinkIdValid(int linkid, int currentId) {

		// not myself id
		if (linkid == currentId) {
			return false;
		}

		List<LinkDataContainer> links = nodes.get(currentId).getLinkedNode();

		for (LinkDataContainer ln : links) {
			if (ln.getLinkId() == linkid) {
				return false;
			}
		}
		return true;
	}

	private List<Integer> generateBindId(int cnt) {
		List<Integer> candidates = new ArrayList<Integer>();
		for (int i = 0; i < cnt; i++) {
			candidates.add(i);
		}
		Collections.shuffle(candidates);
		return candidates;
	}

	private int getRandomId(int cnt) {
		return ThreadLocalRandom.current().nextInt(0, cnt);
	}

	private int getRandomCost() {
		return ThreadLocalRandom.current().nextInt(1, DataConstants.MAX_COST_VALUE);
	}

	public static void main(String[] args) {

		GraphDataGenerator g = new GraphDataGenerator();
		for (int i = 0; i< DataConstants.PROBLEM_SIZE.length; i++) {
			int size = DataConstants.PROBLEM_SIZE[i];
			int maxEdges = DataConstants.MAX_EDGES_NUMBER[i];
			g.createGraphAsJson(size, maxEdges ,DataConstants.GRAPH_CONFIG_BASE_PATH + size + DataConstants.JSON);
		}

		System.out.println("ready");
	}

}

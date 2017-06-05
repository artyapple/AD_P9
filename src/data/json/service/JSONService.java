package data.json.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.json.graph.LinkDataContainer;
import data.json.graph.NodeDataContainer;
import data.json.graph.NodeDataListWrapper;
import data.json.names.CitiData;
import data.json.names.CitiesList;

public class JSONService {
	
	private static final int MAXPRICE = 100;
	private static final String GRAPH_CONFIG = "C:\\Users\\AI\\workspace\\AD_P9\\graphtest_with_costs.json";
	//private static final String NAMES_LIST = "C:\\Users\\AI\\workspace\\AD_P9\\cities.json";
	private static final String NAMES_LIST = "C:\\Users\\AI\\workspace\\AD_P9\\cities_write.json";

	List<NodeDataContainer> nodesDataContainers = new ArrayList<>();
	//NodeDataListWrapper json = new NodeDataListWrapper();
	NodeDataListWrapper readedjson = new NodeDataListWrapper();
	
	private ObjectMapper mapper;

	CitiesList citiesjson = new CitiesList();
	CitiesList readedcities = new CitiesList();

//	public static void main(String[] args) {
//
//		GraphDataGenerator g = new GraphDataGenerator();
//		g.createData(4);
//		g.writeJsonGraph(GRAPH_CONFIG);
//		g.readJsonGraph(GRAPH_CONFIG);
//		g.printer();
//		
//		
//		g.createCities(1);
//		//g.writeCityNames(NAMES_LIST_WRITE);
//		g.readCitiesJson(NAMES_LIST_WRITE);
//		g.printCitinames();
//
//		// list.add(new CitiData("x", "Amster", "101","102"));
//		// list.add(new CitiData("x", "Berlin", "59","39"));
//		// //cities.setCitieslist(list);
//
//		// jserv.writeJson(NAMES_LIST_WRITE, list);
//		//// ArrayList<CitiData> citiData = new ArrayList<>();
//		//
//		// List<CitiData> listraed = new ArrayList<>();
//		// jserv.readJson(NAMES_LIST_WRITE, new
//		// TypeReference<List<CitiData>>(){} , listraed);
//
//		//
//		// System.out.println("cities ok");
//		// System.out.println("name first city: "+listraed.get(0).getName());
//		// System.out.println("name two city: "+listraed.get(1).getName());
//		//
//		System.out.println("break point");
//		// g.makeJson();
//		// g.readJsonGraph();
//	}
//
	public void writeJsonGraph(String path, NodeDataListWrapper json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(path), json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeCityNames(String path) {
		mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(path), citiesjson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void printer() {
		System.out.println("1: " + readedjson.getNodesDataContainers().get(0).getNodeName());
		System.out.println("2: " + readedjson.getNodesDataContainers().get(1).getNodeName());
	}
	
	private void printCitinames() {
		System.out.println("1: " + readedcities.getCitieslist().get(0).getName());
		System.out.println("2: " + readedcities.getCitieslist().get(1).getName());
	}

//	public void createData(int cnt) {
//
//		// Names
//		List<String> list = new ArrayList<>();
//		list.add("A");
//		list.add("B");
//		list.add("C");
//		list.add("D");
//
//		List<LinkDataContainer> links;
//		NodeDataContainer node;
//		LinkDataContainer link;
//
//		for (int i = 0; i < cnt; i++) {
//			node = new NodeDataContainer();
//			node.setNodeId(i);
//			node.setNodeName(list.get(i));
//			links = new ArrayList<>();
//
//			for (int j = 0; j < 2; j++) {
//				link = new LinkDataContainer();
//				link.setLinkId(ThreadLocalRandom.current().nextInt(0, cnt));
//				link.setCost(ThreadLocalRandom.current().nextInt(1, MAXPRICE));
//				links.add(link);
//			}
//
//			node.setLinkedNode(links);
//			nodesDataContainers.add(node);
//		}
//
//		json.setNodesDataContainers(nodesDataContainers);
//	}
//
//	public void createCities(int cnt) {
//
//		CitiData d1 = new CitiData();
//		CitiData d2 = new CitiData();
//		CitiData d3 = new CitiData();
//
//		d1.setName("Amster");
//		d2.setName("Berlin");
//		d3.setName("Paris");
//
//		List<CitiData> cd = new ArrayList<>();
//		cd.add(d1);
//		cd.add(d2);
//		cd.add(d3);
//
//		citiesjson.setCitieslist(cd);
//	}

	public void readJsonGraph(String path) {
		mapper = new ObjectMapper();
		try {
			readedjson = mapper.readValue(new File(path), NodeDataListWrapper.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<CitiData> getCitiList(String path){
		readCitiesJson(path);
		return readedcities.getCitieslist();
	}
	
	private void readCitiesJson(String path) {
		mapper = new ObjectMapper();

		try {
			readedcities = mapper.readValue(new File(path), CitiesList.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

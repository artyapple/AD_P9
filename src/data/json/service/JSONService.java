package data.json.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.json.graph.NodeDataListWrapper;
import data.json.names.CitiData;
import data.json.names.CitiesList;

/**
 * JSON Service for serialization and deserialization
 * 
 * @author AI
 *
 */
public class JSONService {
	
	private ObjectMapper mapper;

	CitiesList citiesjson = new CitiesList();
	CitiesList readedcities = new CitiesList();


	public void writeJsonGraph(String path, NodeDataListWrapper json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(path), json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public NodeDataListWrapper readJsonGraph(String path) {
		mapper = new ObjectMapper();
		try {
			return mapper.readValue(new File(path), NodeDataListWrapper.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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

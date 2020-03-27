package server;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import client.Delete;
import client.Insert;
import client.Select;
import client.Update;

public class ResponseJSON {
	 private Select readJSONselect() throws JsonParseException, JsonMappingException, IOException{
	      ObjectMapper mapper = new ObjectMapper();
	      Select select = mapper.readValue(new File("select.json"), Select.class);
	      return select;
	   }
	 private Insert readJSONinsert() throws JsonParseException, JsonMappingException, IOException{
	      ObjectMapper mapper = new ObjectMapper();
	      Insert insert = mapper.readValue(new File("insert.json"), Insert.class);
	      return insert;
	   }
	 private Update readJSONupdate() throws JsonParseException, JsonMappingException, IOException{
	      ObjectMapper mapper = new ObjectMapper();
	      Update update = mapper.readValue(new File("update.json"), Update.class);
	      return update;
	   }
	 private Delete readJSON() throws JsonParseException, JsonMappingException, IOException{
	      ObjectMapper mapper = new ObjectMapper();
	      Delete delete = mapper.readValue(new File("delete.json"), Delete.class);
	      return delete;
	   }
	 
	} 


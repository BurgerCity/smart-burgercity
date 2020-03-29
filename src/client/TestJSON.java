package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Request;

public class TestJSON {

	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Request rq = new Request();
		rq.setOperation_type("SELECT");
		rq.setFirstname("firstname");
		rq.setLastname("lastname");
		//objectMapper.writeValue(new File("request.json"), rq);
		String rqAsString = objectMapper.writeValueAsString(rq);
		Request request = objectMapper.readValue(rqAsString, Request.class);
		System.out.println(request.getFirstname());
	}

}

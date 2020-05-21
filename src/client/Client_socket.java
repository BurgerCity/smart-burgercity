package client;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Message;
import common.Request;
import common.Response;

import java.io.*;

public class Client_socket {

	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private ObjectMapper objectMapper;
	private Request rq;
	private Response rp;
	private String rqAsString;
	private Message msg;
	
	public OutputStreamWriter startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));	
		msg = new Message();
		objectMapper = new ObjectMapper();
		return out;
	}

	public OutputStreamWriter getOut() {
		return out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setOut(OutputStreamWriter out) {
		this.out = out;
	}
}
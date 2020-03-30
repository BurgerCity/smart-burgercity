package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Message {
	private String json;
	public String readMessage(BufferedReader in) throws IOException {
		json = in.readLine();
		return json;
	}
	
	public void sendMessage(OutputStreamWriter out, String s) throws IOException {
		out.write(s + "\n");
		out.flush();
	}
}

package server;

import java.util.Properties;
import java.io.*;

public class PropertyLoader {
	Properties prop;
	InputStream in = getClass().getClassLoader().getResourceAsStream("server/ressources.properties");
	PropertyLoader() {
		prop = new Properties();
	}
	public void loaded() throws IOException {
		try {
			prop.load(in);
		} catch (Exception e) {}
	}
	public void close() throws IOException {
		in.close();
	}
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
}

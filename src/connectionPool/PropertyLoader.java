package connectionPool;

import java.util.Properties;
import java.io.*;

public class PropertyLoader {
	Properties prop;
	PropertyLoader() {
		prop = new Properties();
	}
	public void loaded() throws IOException {
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream("connectionPool/ressources.properties");
			prop.load(in);
		} catch (Exception e) {}
	}
	public String getProperty(String key) {
		return prop.getProperty(key);
	}	
}

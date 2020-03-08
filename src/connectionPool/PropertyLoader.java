package connectionPool;

import java.util.Properties;
import java.io.IOException;
import java.io.*;

public class PropertyLoader {
	Properties prop;
	PropertyLoader() {
		prop = new Properties();
	}
	public void load(InputStream input) throws IOException 	{
		try {
			input = new FileInputStream("C:\\Users\\mathi\\Documents\\GitHub\\smart-burgercity\\src\\connectionPool\\ressources.properties");
			prop.load(input);
			
		} catch (Exception e) {}
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void loaded() throws  IOException {
		InputStream in = null;
		this.load(in);
	}
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
}

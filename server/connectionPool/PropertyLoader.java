package connectionPool;

import java.util.Properties;
import java.io.IOException;
import java.io.*;

public class PropertyLoader {
	Properties prop = new Properties();
	InputStream input = null;
	public void load(InputStream inStream) throws IOException 	{
		try {
			input = new FileInputStream("/smart-burgercity/server/connectionPool/ressources.properties");
			prop.load(input);
			
		} catch (Exception e) {}
	}
	public static String getProperty( String propertyName ) {
		
	}
}

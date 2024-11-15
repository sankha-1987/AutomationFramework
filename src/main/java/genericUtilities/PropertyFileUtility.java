package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consists of generic methods related to Property File
 * @author User
 */
public class PropertyFileUtility {
	/**
	 * This method is used to read data from property file and return the value
	 * @param key
	 * @return
	 * @throws IOException
	 */
	
	public String readDataFromPropertyFile(String key) throws IOException
	{
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;
	}
}
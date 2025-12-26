package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {
	
	/**
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readDataFromPropertyFile(String key) throws IOException {
		
		FileInputStream fis = new FileInputStream(IpathConstants.filePath);
		
		Properties pObj = new Properties();
		pObj.load(fis);
		
		String value = pObj.getProperty(key);
		
		return value;
	}

}

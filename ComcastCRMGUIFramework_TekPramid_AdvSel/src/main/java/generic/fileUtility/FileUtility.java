package generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class FileUtility {

	public String getDatafromPropertiesFile(String key) throws Exception {
		FileInputStream fis= new FileInputStream("./configAppData/commondata.properties");
		Properties prop= new Properties();
		prop.load(fis);
	  String data = prop.getProperty(key);
		return data;
	}
}

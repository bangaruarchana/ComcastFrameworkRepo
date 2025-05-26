package generic.fileUtility;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {

	public String getDatafromJsonFile(String key) throws Exception {
		JSONParser parser=new JSONParser();
		Object obj = parser.parse(new FileReader("./configAppData/appCommonData.json"));
		//downcasting to jsonobject so that we can read the data
		JSONObject map=(JSONObject)obj;
		String data =(String) map.get(key);
		return data;
	}
}

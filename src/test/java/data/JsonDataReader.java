package data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.node.ObjectNode;

import utilities.Helper;


public class JsonDataReader 
{
	public String firstname, lastname , email , password;

	public void JsonReader() throws IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir") + "/src/test/java/data/UserData.json";

		File srcFile = new File(filePath); 

		JSONParser parser = new JSONParser(); 
		JSONArray jarray = (JSONArray)parser.parse(new FileReader(srcFile)); 

		for(Object jsonObj : jarray) 
		{
			JSONObject person = (JSONObject) jsonObj ; 
			firstname  = (String) person.get("firstname"); 
			System.out.println(firstname);

			lastname = (String) person.get("lastname"); 
			System.out.println(lastname);

			email = (String) person.get("email");
			email = Helper.getUniqueEmail();
			System.out.println(email);

			password = (String) person.get("password"); 
			System.out.println(password);

		}

	}

}

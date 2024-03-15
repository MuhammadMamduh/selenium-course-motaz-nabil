package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import utilities.Helper;

public class LoadProperties 
{
	// Load the properties file from the folder
	public static Properties userData =  
			loadProperties(System.getProperty("user.dir") + "/src/main/java/properties/userdata.properties"); 
	
	
	private static Properties loadProperties(String path)
	{
		Properties pro = new Properties();
		// stream for reading file 
		try {
			FileInputStream stream = new FileInputStream(path);
			pro.load(stream);
			pro.setProperty("email", Helper.getUniqueEmail());
			
			// *** OPTIONAL *** If you want to update the properties file also
			OutputStream output = new FileOutputStream(path);
            pro.store(output, null);
		} catch (FileNotFoundException e) {
		System.out.println("Error occurred :  " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error occurred :  " + e.getMessage());
		} 
		catch (NullPointerException e) {
			System.out.println("Error occurred :  " + e.getMessage());
		} 
	
		return pro; 
	}
}

package com.rbms.common.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
	
	Properties getApplicationProperties()
	{
		Properties props = new Properties();
		try {
			String path = "application.properties";
		    InputStream fis = getClass().getClassLoader().getResourceAsStream(path);
		  
		    //loading properites from properties file
		    props.load(fis);
		
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		return props;
	}
}

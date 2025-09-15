package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import com.constants.Env;

public class PropertiesUtil {
	
	private static final Logger logger = LoggerUtility.getLogger(PropertiesUtil.class);
	private static final String DEFAULT_PROPERTIES = "config/default_properties";
	private static Properties properties;
	
	public static void initDefaultProperties() {
		// load default properties and check for any overrides
		properties = loadDefaultProperties();
		
		for(String key: properties.stringPropertyNames()) {
			if(System.getProperties().containsKey(key)) {
				properties.setProperty(key, System.getProperty(key));
			}
		}
		
		logger.info("Loaded default properties");
	}
	
	public static String getDefaultProperty(String key) {
		return properties.getProperty(key);
	}
	
	private static Properties loadDefaultProperties() {
		File propertiesFile = new File(DEFAULT_PROPERTIES);
		FileReader fileRead = null;
		Properties properties = new Properties();

		try {
			fileRead = new FileReader(propertiesFile);
			properties.load(fileRead);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties;
		
	}

	
	public static String getEnvProperties(Env env, String propertyName) {
		// System.getProperty("user.dir") -> current working directory of your Java application.
		String fileLoc = Paths.get(System.getProperty("user.dir"), "config", env + "_properties").toString();
		// System.out.println(fileLoc);
		File propertiesFile = new File(fileLoc);
		FileReader fileRead = null;
		Properties properties = new Properties();

		try {
			fileRead = new FileReader(propertiesFile);
			properties.load(fileRead);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(propertyName.toUpperCase());
	}

}

package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {
	
	public static String getProperties(Env env, String propertyName) {
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

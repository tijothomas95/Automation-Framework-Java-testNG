package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

public class JSONUtility {

	public static Environment readJSON(Env env) {		
		String fileLoc = Paths.get(System.getProperty("user.dir"), "config", "config.json").toString();
		// System.out.println(fileLoc);
		File jsonFile = new File(fileLoc);
		Gson gson = new Gson();
	
		FileReader fileRead = null;
		try {
			fileRead = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}			

		Config config = gson.fromJson(fileRead, Config.class);
		return config.getEnvironments().get(env.toString());

	}

}

package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;

public class LoginDataProvider {
	
	@DataProvider(name="LoginTestJSONDataProvider")
	public Iterator<Object[]> loginTestJSONDataProvider() {
		
		String fileLoc = Paths.get(System.getProperty("user.dir"), "testData", "logindata.json").toString();
		File jsonFile = new File(fileLoc);
		Gson gson = new Gson();
		FileReader fileRead = null;
		
		try {
			fileRead = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		TestData data = gson.fromJson(fileRead, TestData.class);
		List<Object []> users = new ArrayList<Object[]>();
		for(User user: data.getData()) {
			users.add(new Object[] {user});
		}
		
		return users.iterator();
	}
	
	@DataProvider(name="LoginTestCSVDataProvider")
	public Iterator<User> loginTestCSVDataProvider() {
		return CSVReaderUtility.readCSVFile("logindata.csv");
		
	}
	
	@DataProvider(name="LoginTestExcelDataProvider")
	public Iterator<User> loginTestExcelDataProvider() {
		return ExcelReaderUtility.readExcelFile("logindata.xlsx");
		
	}

}

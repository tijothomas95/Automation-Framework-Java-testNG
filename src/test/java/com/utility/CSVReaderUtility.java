package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtility {
	
	public static Iterator<User> readCSVFile(String fileName) {
		
		String fileLoc = Paths.get(System.getProperty("user.dir"), "testData", fileName).toString();
		File file = new File(fileLoc);
		FileReader fileReader = null;
		CSVReader csvReader;
		String[] line;
		List<User> userList = null;
		User userData;

		try {
			fileReader = new FileReader(file);
			csvReader =  new CSVReader(fileReader);
			csvReader.readNext(); // Skip the Row 1, column headers
			// data = csvReader.readNext(); Row 2 ... so read all rows, add while with check on null value return

			userList = new ArrayList<User>();
			while((line = csvReader.readNext())!=null) {
				userData = new User(line[0], line[1]);
				userList.add(userData);
			}

		} catch (CsvValidationException | IOException e) {
			e.printStackTrace();
		}
		
		return userList.iterator();
		 
		
	}

}

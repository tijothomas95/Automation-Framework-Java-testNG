package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {
	
	
	  public static Iterator<User> readExcelFile(String fileName) {
	  
	  String fileLoc = Paths.get(System.getProperty("user.dir"), "testData", fileName).toString();
	  
	  File file = new File(fileLoc); 
	  XSSFWorkbook xssfWorkbook; 
	  XSSFSheet xssfSheet; 
	  List<User> userList = new ArrayList<>(); 
	  Row row; 
	  Cell email, password; 
	  User user;
	  
	  //xlsx file 
	  try { 
		  xssfWorkbook = new XSSFWorkbook(file); 
		  xssfSheet = xssfWorkbook.getSheet("logindata");
		  Iterator<Row> rowIterator = xssfSheet.iterator();
	  
		  rowIterator.next(); // first row, which has the column names
		  while(rowIterator.hasNext()) { 
			  row = rowIterator.next();
			  email = row.getCell(0); 
			  password = row.getCell(1); 
			  user = new User(email.toString(), password.toString());
			  userList.add(user);
			  xssfWorkbook.close(); 
			}
		  
		  } 
	  catch (InvalidFormatException | IOException e) {
		  e.printStackTrace();
		  }
	  return userList.iterator();
	  }
}

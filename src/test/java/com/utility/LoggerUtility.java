package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {
	
	private LoggerUtility() {
		// Loggers are always going to follow singleton design pattern,
		// so this class can have only one object
	}
	
	public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}

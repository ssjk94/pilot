package com.pilot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
	public static Logger logger = LoggerFactory.getLogger(Log.class);
	public static Logger getLog() {
		return logger;
	}
}

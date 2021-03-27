package com.application.constant;

/**
 * Constant file
 * @author 
 */

public class IAppConstant {
	
	public static final String STARTED="STARTED";
	public static final String FINISHED="FINISHED";
	
	public static final String UTF="UTF-8";
	
	public static final String FILE_PATH="./resource/application.properties";
	
	public static final String PATH = "file.path";

	public static final String QUERY  = "CREATE TABLE IF NOT EXISTS logging_table "
			+ "(event_id VARCHAR(10) NOT NULL,event_duration int,type VARCHAR(20) , host VARCHAR(20), alert VARCHAR(10))";
	
}

package com.application.Connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.application.Entity.Log;
import com.application.constant.IAppConstant;
import com.google.gson.Gson;

/**
 * 
 * Java class for file connection and returing the List of log 
 *
 */
public class FileConnection {
	
	private static final Logger logger = LogManager.getLogger(FileConnection.class); 
	static List<Log> feature= new ArrayList<Log>();		
    
	/**
	 * Logic to retrive the log from file in the arrayList
	 * @return ArrayList
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<Log>  fileconnection() throws FileNotFoundException, IOException{
		logger.debug("Start file connection");
 	    long start = System.currentTimeMillis();
		FileInputStream inputStream = null;	    
		Gson gson = new Gson();
		Scanner sc = null;
		try {			
			FileInputStream fis = new FileInputStream(IAppConstant.FILE_PATH);			
			Properties p=new Properties();  
			p.load(fis); 
            inputStream = new FileInputStream(p.getProperty(IAppConstant.PATH));
            sc = new Scanner(inputStream, IAppConstant.UTF);
            while (sc.hasNextLine()) {
    		    String line = sc.nextLine();  
    		   feature.add(gson.fromJson(line,Log.class)); 
    		}                        
	}
		finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
           
        } 
		long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
		logger.debug("End file connection , Took - " + timeElapsed + " ms");
		return feature;
	}
}



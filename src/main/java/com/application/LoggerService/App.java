package com.application.LoggerService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.application.Connection.DBconnection;
import com.application.Connection.FileConnection;
import com.application.Entity.Log;
import com.application.constant.IAppConstant;

/**
 * Main class 
 *
 */
public class App 
{
	private static final Logger logger = LogManager.getLogger(App.class); 
    public static void main( String[] args )  {
    	BasicConfigurator.configure();  
		logger.debug("Main class started");
		long start = System.currentTimeMillis();
          List<Log> feature = new ArrayList<Log>();
		try {
			feature = FileConnection.fileconnection(); // call to retrive log information
		} catch (FileNotFoundException e1) {
			logger.error("File not found exception" + e1);
		} catch (IOException e1) {
			logger.error("IO exception - " + e1);
		}catch(Exception e) {
			logger.error("Exception" + e);
		}
               Map<String, List<Log>> groupByPriceMap = 
                   feature.stream().collect(Collectors.groupingBy(Log::getId)); // This will group the record based on id and keep that as a key
         
           try {               
               long firstLog =0;
               long secondLog =0;
               String alert = null;
               int count = 0;
               long event_duration=0;
               
               for (Map.Entry<String, List<Log>> entry : groupByPriceMap.entrySet()) { // Logic to massage log information based on  the log id as key
                   count =0;
                   for (Log log : entry.getValue()) {
                	   if(log.getState().equals(IAppConstant.STARTED)){
                		   firstLog = log.getTimestamp();
                		   count++;
                	   }
                	   else if(log.getState().equals(IAppConstant.FINISHED)) {
                		   secondLog= log.getTimestamp();
                		   count++;
                	   }
                	   if(count==2) {
                		   event_duration = secondLog-firstLog;
                		   alert = (event_duration > 4) ?"true":"false";                		   
                		   if(event_duration > 4) {
                		   DBconnection.dbUpdate(log,event_duration,alert);// DB update id the event is greate then 4 ms
                		   }   
                	   }
                     }
               }
           } 
           catch(Exception e) {
        	   logger.error("Processing log exception" + e);
           }
           long finish = System.currentTimeMillis();
           long timeElapsed = finish - start;
           logger.debug("Main class end , took - " + timeElapsed + " ms");
    }
    


}
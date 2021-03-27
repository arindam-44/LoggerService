package com.application.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.application.Entity.Log;
/**
 * Class for DB operation
 * @author 
 *
 */
public class DBconnection {
	
	private static final Logger logger = LogManager.getLogger(DBconnection.class); 
	
	/**
	 * Data base connection
	 * @return Connection
	 */
	public static Connection getConnection() {
		logger.debug("Db connection started");
		long start = System.currentTimeMillis();
		Connection con = null;
		try {			
			Class.forName("org.hsqldb.jdbc.JDBCDriver"); // load the Driver Class
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", ""); //  create  connection 
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Db connection error" + e);
		}
		 long finish = System.currentTimeMillis();
         long timeElapsed = finish - start;
		logger.debug("Db connection Finished , Took -" + timeElapsed + " ms");
		return con;
	}
	
	/**
	 * Logic to save DB 
	 * @param log
	 * @param diff
	 * @param alert
	 */
	public static int dbUpdate(Log log, long diff, String alert) {	
	 	   Statement stmt = null;
	 	      int result1 = 0;
	 	     logger.debug("Db update method started");
	 	    long start = System.currentTimeMillis();
	       try {
	    	   Connection conn= DBconnection.getConnection();
			stmt = conn.createStatement();
			result1 = stmt.executeUpdate(" INSERT INTO logging_table " +
		             " VALUES ( '" + log.getId() +"', "
		             + diff + ", '" 
		             + log.getType() + "' , '"
		             + log.getHost() + "' ,'" 
		             + alert +   "');");
			logger.debug("Total Record updated - " + result1);
		       conn.commit(); 
		} catch (SQLException e) {
			logger.error("Record update error " + e);
		}
	       long finish = System.currentTimeMillis();
	         long timeElapsed = finish - start;
	       logger.debug("Db update method End , took - " +timeElapsed + " ms");
	       return result1;
	    }	
}
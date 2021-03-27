package com.application.LoggerService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.application.Connection.DBconnection;
import com.application.Entity.Log;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	@InjectMocks
	DBconnection dbconnection;
	
    @Before
    public void init() {
    	MockitoAnnotations.initMocks(this);
    }
    @Test
    public void dbconnection()
    {
    	//to do 
        
    }
}

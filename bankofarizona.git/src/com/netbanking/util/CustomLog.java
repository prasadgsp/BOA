package com.netbanking.util;

import java.io.IOException;

import java.util.Properties;

import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;


public class CustomLog {

public  CustomLog() 
{
        Properties props = new Properties();
    try {
        props.load(getClass().getClassLoader().getResourceAsStream("log4j.properties"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    PropertyConfigurator.configure(props);//PropertyConfigurator.configure("log4j.properties");
}

public Logger getLogger(Object obj) 
{
    Logger logger = Logger.getLogger(Object.class);
    return logger;
}

}
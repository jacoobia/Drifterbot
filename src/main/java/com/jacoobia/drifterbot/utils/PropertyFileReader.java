package com.jacoobia.drifterbot.utils;


import com.jacoobia.drifterbot.Drifter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader
{

    private static final String PATH = "/drifter.properties";

    public static String read(String key)
    {
        Properties properties = openFile();
        return properties.getProperty(key);
    }

    private static Properties openFile()
    {
        Properties properties = new Properties();
        InputStream stream = Drifter.class.getResourceAsStream(PATH);
        try
        {
            properties.load(stream);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return properties;
    }

}
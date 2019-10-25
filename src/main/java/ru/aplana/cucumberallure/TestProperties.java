package ru.aplana.cucumberallure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private Properties props = new Properties();

    private static TestProperties INSTANCE = null;

    private TestProperties(){
        try {
            props.load(new FileInputStream(new File("src/test/resources/"+System.getProperty("env", "dev")+".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Test properties created!");
    }

    public static TestProperties getInstance(){
        if (INSTANCE==null){
            INSTANCE = new TestProperties();
        }
        return INSTANCE;
    }

    public String getProperty(String key, String defaultValue){
        return props.getProperty(key, defaultValue);
    }

    public String getProperty(String key){
        return props.getProperty(key);
    }
}

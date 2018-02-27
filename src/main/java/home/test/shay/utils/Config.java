package home.test.shay.utils;

import home.test.shay.EntryPoint;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private Properties prop;

    public Config() throws IOException {
        InputStream  input = EntryPoint.class.getClassLoader().getResourceAsStream("config.properties");
        prop = new Properties();
        prop.load(input);
    }

    public String getValue(String key){
        return this.prop.getProperty(key);
    }

}

package example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DictionaryNumber implements Dictionary {
    private Map<String, String> map;
    private Properties prop;
    private Properties propertiesInfo;

    public DictionaryNumber() {
        map = new HashMap<>();
        prop = new Properties();
        loadDictionaryFromFile();

        propertiesInfo = new Properties();
        try {
            propertiesInfo.load(new FileInputStream("console.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDictionaryFromFile(){
        try {
            prop.load(new FileInputStream("file2.properties"));
        } catch (IOException e) {
            System.out.println(propertiesInfo.getProperty("file.not.found"));
        }

        for (String key : prop.stringPropertyNames()){
            map.put(key, prop.getProperty(key));
        }
    }

    @Override
    public void viewDictionary() {
        for (Map.Entry<String, String> entry : map.entrySet())
            System.out.println(entry.getKey() + " : " + entry.getValue());
    }

    @Override
    public void delete(String key) {
        map.entrySet().removeIf(next -> next.getKey().equals(key));
        prop.remove(key);
        try {
            prop.store(new PrintWriter(new FileOutputStream("file2.properties"), true), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(propertiesInfo.getProperty("remove.entry"));
    }

    @Override
    public void findForKey(String key) {
        for (Map.Entry<String, String> entry : map.entrySet()){
            if (entry.getKey().equals(key))
                System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Override
    public void addToDictionary(String key, String value) {
        if (key.matches("[0-9]{5}")){
            prop.setProperty(key, value);
            try {
                prop.store(new PrintWriter(new FileOutputStream("file2.properties"), true), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(propertiesInfo.getProperty("successful.entry"));
        }
        else
            System.out.println(propertiesInfo.getProperty("error.entry.number"));
    }
}

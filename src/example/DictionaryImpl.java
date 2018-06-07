package example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DictionaryImpl implements Dictionary{
    private Map<String, String> map;
    private Properties propFile;
    private Properties propConsole;
    private String fileName;
    private String keyMath;
    private int keyLength;

    public DictionaryImpl() {
        map = new HashMap<>();
        propFile = new Properties();
        propConsole = new Properties();

        try {
            propConsole.load(new FileInputStream("console.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void loadDictionaryFromFile(String filename){
        this.fileName = filename;
        try {
            propFile.load(new FileInputStream((propConsole.getProperty(fileName))));
        } catch (IOException e) {
                System.out.println(propConsole.getProperty("file.not.found"));
        }
        for (String key : propFile.stringPropertyNames()){
            map.put(key, propFile.getProperty(key));
        }
    }
    @Override
    public void viewDictionary(){
        for (Map.Entry<String, String> entry : map.entrySet())
            System.out.println(entry.getKey() + " : " + entry.getValue());
    }
    @Override
    public void delete(String key){
        map.entrySet().removeIf(next -> next.getKey().equals(key));
        propFile.remove(key);
        try {
            propFile.store(new PrintWriter(new FileOutputStream(propConsole.getProperty(fileName)), true), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void findForKey(String key){
        for (Map.Entry<String, String> entry : map.entrySet()){
            if (entry.getKey().equals(key))
                System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
    @Override
    public void addToDictionary(String key, String value){
        if (key.length() != keyLength)
            System.out.println(propConsole.getProperty(fileName + ".length"));
        else if (!key.matches(keyMath))
            System.out.println(propConsole.getProperty(fileName + ".math"));
        else {
            propFile.setProperty(key, value);
            try {
                propFile.store(new PrintWriter(new FileOutputStream(propConsole.getProperty(fileName)), true), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            map.put(key, value);
        }
    }

    @Override
    public void setKeyMath(String keyMath, int keyLength) {
        this.keyMath = keyMath;
        this.keyLength = keyLength;
    }
}

package example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class FileManager {
    private Properties propFile;
    private Properties propConsole;
    private String fileName;

    public FileManager() {
        loadConsoleFile();
        propFile = new Properties();
    }

    public Properties getPropFile() {
        return propFile;
    }

    private void loadConsoleFile() {
        propConsole = new Properties();
        try {
            propConsole.load(new FileInputStream("console.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean loadDictionary(String filename) {
        this.fileName = filename;
        try {
            propFile.load(new FileInputStream((propConsole.getProperty(fileName))));
            return true;
        } catch (IOException e) {
            System.out.println(propConsole.getProperty("file.not.found"));
            return false;
        }
    }
    public void deleteFromFile(String key){
        propFile.remove(key);
        try {
            propFile.store(new PrintWriter(new FileOutputStream(propConsole.getProperty(fileName)), true), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addToFile(String key, String value){
        propFile.setProperty(key, value);
        try {
            propFile.store(new PrintWriter(new FileOutputStream(propConsole.getProperty(fileName)), true), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getValidMessage(String message) {
        return propConsole.getProperty(fileName + message);
    }
}

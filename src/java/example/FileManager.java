package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Properties;

@Component("fileManager")
@PropertySource(value = "classpath:pathFiles.properties")
public class FileManager {
    private final Environment environment;
    private final MessageSource messageSource;

    private Properties propFile;
    private String fileName;

    @Autowired
    public FileManager(Environment environment, MessageSource messageSource) {
        propFile = new Properties();
        this.environment = environment;
        this.messageSource = messageSource;
    }

    public Properties getPropFile() {
        return propFile;
    }

    public boolean loadDictionary(String filename) {
        this.fileName = filename;
        try {
            propFile.load(new FileInputStream((environment.getProperty(filename))));
            return true;
        } catch (IOException e) {
            System.out.println(messageSource.getMessage("file.not.found", new Object[0], Locale.getDefault()));
            return false;
        }
    }
    public void deleteFromFile(String key){
        propFile.remove(key);
        try {
            propFile.store(new PrintWriter(new FileOutputStream(environment.getProperty(fileName)), true), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addToFile(String key, String value){
        propFile.setProperty(key, value);
        try {
            propFile.store(new PrintWriter(new FileOutputStream(environment.getProperty(fileName)), true), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getValidMessage(String message) {
        return messageSource.getMessage(fileName + message, new Object[0], Locale.getDefault());
    }
}

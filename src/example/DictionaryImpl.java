package example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DictionaryImpl implements Dictionary{
    private Map<String, String> map;
    private Properties prop;

    public DictionaryImpl() {
        map = new HashMap<>();
        prop = new Properties();
        loadDictionaryFromFile();
    }

    private void loadDictionaryFromFile(){
        try {
            prop.load(new FileInputStream("files/file.properties"));
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }

        for (String key : prop.stringPropertyNames()){
            map.put(key, prop.getProperty(key));
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
        prop.remove(key);
        try {
            prop.store(new PrintWriter(new FileOutputStream("files/file.properties"), true), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Запись удалена");
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
        if (key.matches("[A-z]{4}")){
            prop.setProperty(key, value);
            try {
                prop.store(new PrintWriter(new FileOutputStream("files/file.properties"), true), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println("Запись успешно добавленна");
        }
        else
            System.out.println("Не удалось добавить запись, длинна ключа может быть только 4 символа и эти символы только буквы латинской раскладки");
    }
}

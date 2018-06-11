package example;

import java.util.HashMap;
import java.util.Map;

public class DictionaryImpl implements Dictionary{
    private Map<String, String> map;
    private FileManager fileManager;

    public DictionaryImpl() {
        map = new HashMap<>();
        fileManager = new FileManager();
    }
    @Override
    public void loadDictionaryFromFile(String filename){
        if (fileManager.loadDictionary(filename)) {
            for (String key : fileManager.getPropFile().stringPropertyNames()) {
                map.put(key, fileManager.getPropFile().getProperty(key));
            }
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
        fileManager.deleteFromFile(key);
    }
    @Override
    public String findForKey(String key){
        for (Map.Entry<String, String> entry : map.entrySet()){
            if (entry.getKey().equals(key))
                return entry.getKey() + " : " + entry.getValue();
        }
        return "";
    }
    @Override
    public void addToDictionary(String key, String value){
        fileManager.addToFile(key, value);
        map.put(key, value);
    }
}

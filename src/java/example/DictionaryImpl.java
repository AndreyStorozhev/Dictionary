package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("dictionary")
public class DictionaryImpl implements Dictionary {
    private final Map<String, String> map;
    private final FileManager fileManager;

    @Autowired
    public DictionaryImpl(FileManager fileManager, @Qualifier("map") Map<String, String> map) {
        this.fileManager = fileManager;
        this.map = map;
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

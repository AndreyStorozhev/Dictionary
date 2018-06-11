package example;

public interface Dictionary {
    void viewDictionary();
    void delete(String key);
    String findForKey(String key);
    void addToDictionary(String key, String value);
    void loadDictionaryFromFile(String fileName);
}

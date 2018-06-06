package example;

public interface Dictionary {
    void viewDictionary();
    void delete(String key);
    void findForKey(String key);
    void addToDictionary(String key, String value);
    void loadDictionaryFromFile(String fileName);
    void setKeyMath(String keyMath, int keyLength);
}

package example.service;

import example.entity.KeyDictionary;

import java.util.List;

public interface DictionaryService {
    void saveOrUpdateKeyChar(KeyDictionary key, String value);
    void saveOrUpdateKeyNum(KeyDictionary key, String value);
    KeyDictionary getKeyByName(String key);
    void remove(int id);
    List<KeyDictionary> keyListChar();
    List<KeyDictionary> keyListNumber();
}

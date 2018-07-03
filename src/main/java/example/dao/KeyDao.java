package example.dao;

import example.entity.KeyDictionary;

import java.util.List;

public interface KeyDao {
    void saveOrUpdateKey(KeyDictionary key);
    KeyDictionary getKeyByName(String key);
    void remove(int id);
    List<KeyDictionary> keyListChar();
    List<KeyDictionary> keyListNumber();
}

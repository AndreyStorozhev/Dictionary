package example.service;

import example.entity.Key;

import java.util.List;

public interface DictionaryService {
    void saveOrUpdateKeyChar(Key key);
    void saveOrUpdateKeyNum(Key key);
    Key getKeyByName(String key);
    void remove(int id);
    List<Key> keyListChar();
    List<Key> keyListNumber();
}
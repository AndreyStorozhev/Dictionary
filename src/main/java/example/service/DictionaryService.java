package example.service;

import example.entity.Dictionary;

import java.util.List;

public interface DictionaryService {
    void saveOrUpdateKey(Dictionary dictionary);
    Dictionary getKeyByName(String key);
    void remove(int id);
    List<Dictionary> keyListChar();
    List<Dictionary> keyListNumber();
}

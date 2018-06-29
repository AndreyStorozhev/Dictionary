package example.dao;

import example.entity.Dictionary;

import java.util.List;

public interface DictionaryDao {
    void saveOrUpdateKey(Dictionary dictionary);
    Dictionary getKeyByName(String key);
    void remove(int id);
    List<Dictionary> keyListChar();
    List<Dictionary> keyListNumber();
}

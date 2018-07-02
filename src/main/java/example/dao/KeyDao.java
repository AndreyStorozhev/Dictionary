package example.dao;

import example.entity.Key;

import java.util.List;

public interface KeyDao {
    void saveOrUpdateKey(Key Key);
    Key getKeyByName(String key);
    void remove(int id);
    List<Key> keyListChar();
    List<Key> keyListNumber();
}

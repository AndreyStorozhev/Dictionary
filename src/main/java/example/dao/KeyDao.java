package example.dao;

import example.entity.Key;

import java.util.List;

public interface KeyDao {
    void saveOrUpdateKey(Key key);
    Key getKeyByName(String key);
    void remove(int id);
    List<Key> keyList();
    Key getKeyById(int id);
}

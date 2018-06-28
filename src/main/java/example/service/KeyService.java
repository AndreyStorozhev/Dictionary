package example.service;

import example.entity.Key;

import java.util.List;

public interface KeyService {
    void saveOrUpdateKey(Key key);
    Key getKeyByName(String key);
    void remove(int id);
    List<Key> keyList();
}

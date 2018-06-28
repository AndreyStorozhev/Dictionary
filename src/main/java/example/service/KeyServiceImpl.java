package example.service;

import example.dao.KeyDao;
import example.entity.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyServiceImpl implements KeyService {
    private final KeyDao keyDao;

    @Autowired
    public KeyServiceImpl(KeyDao keyDao) {
        this.keyDao = keyDao;
    }

    @Override
    public void saveOrUpdateKey(Key key) {
        keyDao.saveOrUpdateKey(key);
    }

    @Override
    public Key getKeyByName(String key) {
        return keyDao.getKeyByName(key);
    }

    @Override
    public void remove(int id) {
        keyDao.remove(id);
    }

    @Override
    public List<Key> keyList() {
        return keyDao.keyList();
    }
}

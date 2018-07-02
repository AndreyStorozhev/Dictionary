package example.service;

import example.dao.KeyDao;
import example.entity.Key;
import example.entity.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    private final KeyDao keyDao;

    @Autowired
    public DictionaryServiceImpl(KeyDao dictionaryDao) {
        this.keyDao = dictionaryDao;
    }

    @Override
    public void saveOrUpdateKeyChar(Key key, String value) {
        Value val = new Value();
        val.setValue(value);

        key.setFlag(0);
        key.getValues().add(val);
        keyDao.saveOrUpdateKey(key);
    }

    @Override
    public void saveOrUpdateKeyNum(Key key, String value) {
        Value val = new Value();
        val.setValue(value);

        key.setFlag(1);
        key.getValues().add(val);
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
    public List<Key> keyListChar() {
        return keyDao.keyListChar();
    }

    @Override
    public List<Key> keyListNumber() {
        return keyDao.keyListNumber();
    }
}

package example.service;

import example.dao.KeyDao;
import example.entity.KeyDictionary;
import example.entity.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    private final KeyDao keyDao;

    @Autowired
    public DictionaryServiceImpl(KeyDao keyDao) {
        this.keyDao = keyDao;
    }

    @Override
    public void saveOrUpdateKeyChar(KeyDictionary key, String value) {
        Value val = new Value();
        val.setValue(value);
        val.setKey(key);
        List<Value> list = new ArrayList<>();
        list.add(val);

        key.setFlag(0);
        key.setValues(list);
        keyDao.saveOrUpdateKey(key);
    }

    @Override
    public void saveOrUpdateKeyNum(KeyDictionary key, String value) {
        Value val = new Value();
        val.setValue(value);
        val.setKey(key);
        List<Value> list = new ArrayList<>();
        list.add(val);

        key.setFlag(1);
        key.setValues(list);
        keyDao.saveOrUpdateKey(key);
    }

    @Override
    public KeyDictionary getKeyByName(String key) {
        return keyDao.getKeyByName(key);
    }

    @Override
    public void remove(int id) {
        keyDao.remove(id);
    }

    @Override
    public List<KeyDictionary> keyListChar() {
        return keyDao.keyListChar();
    }

    @Override
    public List<KeyDictionary> keyListNumber() {
        return keyDao.keyListNumber();
    }
}

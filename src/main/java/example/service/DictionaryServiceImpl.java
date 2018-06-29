package example.service;

import example.dao.DictionaryDao;
import example.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryDao dictionaryDao;

    @Autowired
    public DictionaryServiceImpl(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public void saveOrUpdateKey(Dictionary dictionary) {
        dictionaryDao.saveOrUpdateKey(dictionary);
    }

    @Override
    public Dictionary getKeyByName(String key) {
        return dictionaryDao.getKeyByName(key);
    }

    @Override
    public void remove(int id) {
        dictionaryDao.remove(id);
    }

    @Override
    public List<Dictionary> keyListChar() {
        return dictionaryDao.keyListChar();
    }

    @Override
    public List<Dictionary> keyListNumber() {
        return dictionaryDao.keyListNumber();
    }
}

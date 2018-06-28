package example.service;

import example.dao.ValueDao;
import example.entity.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValueServiceImpl implements ValueService {
    private final ValueDao valueDao;

    @Autowired
    public ValueServiceImpl(ValueDao valueDao) {
        this.valueDao = valueDao;
    }

    @Override
    public Value getByName(String value) {
        return valueDao.getByName(value);
    }

    @Override
    public List<Value> listValue() {
        return valueDao.listValue();
    }

    @Override
    public void remove(Value value) {
        valueDao.remove(value);
    }
}

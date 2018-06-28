package example.dao;

import example.entity.Value;

import java.util.List;

public interface ValueDao {
    Value getByName(String value);
    List<Value> listValue();
    void remove(int id);
    Value getValueById(int id);
}

package example.dao;

import example.entity.Value;

public interface ValueDao {
    void remove(int id);
    Value getByName(String name);
    void saveOrUpdateValue(Value value);
}

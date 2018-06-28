package example.service;

import example.entity.Value;

import java.util.List;

public interface ValueService {
    Value getByName(String value);
    List<Value> listValue();
    void remove(Value value);
}

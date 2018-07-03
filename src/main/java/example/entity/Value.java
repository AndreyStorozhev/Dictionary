package example.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "VALUE_TABLE")
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne
    @JoinColumn(name = "KEY_ID")
    private KeyDictionary key;

    public KeyDictionary getKey() {
        return key;
    }

    public void setKey(KeyDictionary keyDictionary) {
        this.key = keyDictionary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value = (Value) o;
        return id == value.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



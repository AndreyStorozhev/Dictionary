package example.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dictionary")
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "key_char")
    private String key_char;

    @Column(name = "key_num")
    private String key_num;

    @OneToMany(mappedBy = "dictionary", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Value> values;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey_num() {
        return key_num;
    }

    public void setKey_num(String key_num) {
        this.key_num = key_num;
    }

    public String getKey_char() {
        return key_char;
    }

    public void setKey_char(String key) {
        this.key_char = key;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dictionary dictionary = (Dictionary) o;
        return id == dictionary.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

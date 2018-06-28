package example.dao;

import example.entity.Value;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ValueDaoImpl implements ValueDao {
    private final SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(KeyDaoImpl.class);

    @Autowired
    public ValueDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Value getByName(String value) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Value where value = :value");
        query.setParameter("value", value);
        Value getValue = (Value) query.getSingleResult();
        logger.info("GET VALUE " + getValue);
        return getValue;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Value> listValue() {
        return sessionFactory.getCurrentSession().createQuery("from Value").list();
    }

    @Override
    @Transactional
    public void remove(Value value) {
        sessionFactory.getCurrentSession().remove(value);
        logger.info("REMOVE VALUE " + value);
    }

    @Override
    public Value getValueById(int id) {
        Value value = sessionFactory.getCurrentSession().get(Value.class, id);
        logger.info("VALUE LOAD " + value);
        return value;
    }
}

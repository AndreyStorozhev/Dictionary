package example.dao;

import example.entity.Value;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ValueDaoImpl implements ValueDao {
    private final SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(KeyDaoImpl.class);

    @Autowired
    public ValueDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        Value value = session.get(Value.class, id);
        if (value != null)
            session.delete(value);
        logger.info("VALUE REMOVE " + value);
    }

    @Override
    public Value getByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Value where value = :name");
        query.setParameter("name", name);
        Value singleResult = (Value) query.getSingleResult();
        logger.info("VALUE GET " + singleResult);

        return singleResult;
    }

    @Override
    public void saveOrUpdateValue(Value value) {
        sessionFactory.getCurrentSession().saveOrUpdate(value);
    }
}

package example.dao;

import example.entity.Key;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class KeyDaoImpl implements KeyDao {
    private final SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(KeyDaoImpl.class);

    @Autowired
    public KeyDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveOrUpdateKey(Key key) {
        sessionFactory.getCurrentSession().saveOrUpdate(key);
        logger.info("KEY SAVE " + key);
    }

    @Override
    @Transactional
    public Key getKeyByName(String key) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Key where key = :key");
        query.setParameter("key", key);
        Key getKey = (Key) query.getSingleResult();
        logger.info("KEY GET " + getKey);
        return getKey;
    }

    @Override
    @Transactional
    public void remove(Key key) {
        sessionFactory.getCurrentSession().remove(key);
        logger.info("KEY REMOVE " + key);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Key> keyList() {
        return sessionFactory.getCurrentSession().createQuery("from Key").list();
    }
}

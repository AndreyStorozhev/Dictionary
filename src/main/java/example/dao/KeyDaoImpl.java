package example.dao;

import example.entity.KeyDictionary;
import org.apache.log4j.Logger;
import org.hibernate.Session;
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
    public void saveOrUpdateKey(KeyDictionary key) {
        sessionFactory.getCurrentSession().saveOrUpdate(key);
        logger.info("KEY SAVE " + key);
    }

    @Override
    @Transactional
    public KeyDictionary getKeyByName(String key) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from KeyDictionary where key = :key");
        query.setParameter("key", key);
        KeyDictionary getKeyDictionary = (KeyDictionary) query.getSingleResult();
        session.getTransaction().commit();
        logger.info("KEY GET " + getKeyDictionary);
        return getKeyDictionary;
    }

    @Override
    @Transactional
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        KeyDictionary keyDictionary = session.get(KeyDictionary.class, id);
        if (keyDictionary != null)
            session.delete(keyDictionary);
        logger.info("KEY REMOVE " + keyDictionary);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<KeyDictionary> keyListChar() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<KeyDictionary> list = session.createQuery("from KeyDictionary where flag = 0").list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<KeyDictionary> keyListNumber() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<KeyDictionary> list = session.createQuery("from KeyDictionary where flag = 1").list();
        session.getTransaction().commit();
        return list;
    }
}

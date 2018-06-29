package example.dao;

import example.entity.Dictionary;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DictionaryDaoImpl implements DictionaryDao {
    private final SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(DictionaryDaoImpl.class);

    @Autowired
    public DictionaryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveOrUpdateKey(Dictionary dictionary) {
        sessionFactory.getCurrentSession().saveOrUpdate(dictionary);
        logger.info("KEY SAVE " + dictionary);
    }

    @Override
    @Transactional
    public Dictionary getKeyByName(String key) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Dictionary where key_char = :key");
        query.setParameter("key", key);
        Dictionary getDictionary = (Dictionary) query.getSingleResult();
        logger.info("KEY GET " + getDictionary);
        return getDictionary;
    }

    @Override
    @Transactional
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        Dictionary dictionary = session.get(Dictionary.class, id);
        if (dictionary != null)
            session.delete(dictionary);
        logger.info("KEY REMOVE " + dictionary);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Dictionary> keyListChar() {
        return (List<Dictionary>) sessionFactory.getCurrentSession().createQuery("from Dictionary where key_char is not null ").list();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Dictionary> keyListNumber() {
        return (List<Dictionary>) sessionFactory.getCurrentSession().createQuery("from Dictionary where key_num is not null ").list();
    }

}

package com.dietsheet.DAO;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO< T > {

    private Class< T > clazz;

    @Autowired
    SessionFactory sessionFactory;


    public final void setClazz( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }

    public T get( long id ){
        try (Session session = sessionFactory.openSession()) {
            return session.get(clazz, id);
        }
    }

    public List< T > getAll(){
        try (Session session = sessionFactory.openSession()) {
            try {
                return session.createQuery("from " + clazz.getName(), clazz).list();
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
    }

    public void save( T entity ) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update( T entity ){
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete( T entity ){
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete( long entityId ) {
        T entity = get( entityId );
        delete( entity );
    }

    public void deleteAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete from " + clazz.getName()).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

}

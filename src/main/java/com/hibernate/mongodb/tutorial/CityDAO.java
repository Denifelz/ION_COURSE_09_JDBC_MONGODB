package com.hibernate.mongodb.tutorial;

import org.hibernate.SessionFactory;
import org.hibernate.Session;

import java.util.List;

public class CityDAO {
    private final SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void deleteAll() {
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from City").executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void create(final City city) {
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(city);
            session.getTransaction().commit();
        }
    }

    public List<City> readAll() {
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<City> cities = session.createQuery("from City").list();
            session.getTransaction().commit();

            return cities;
        }
    }
}

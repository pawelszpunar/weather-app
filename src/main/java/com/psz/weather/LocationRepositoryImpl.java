package com.psz.weather;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Optional;

public class LocationRepositoryImpl implements LocationRepository{

    private SessionFactory sessionFactory;

    public LocationRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Location save(Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(location);
        transaction.commit();
        session.close();
        return location;
    }

    @Override
    public List<Location> getLocations() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Location> locations = session.createQuery("SELECT loc FROM localization loc", Location.class).getResultList();
        transaction.commit();
        session.close();
        return locations;
    }

    @Override
    public Optional<Location> findById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Location location = session.createQuery("SELECT loc FROM localization loc WHERE loc.id = :id", Location.class)
                .setParameter("id", id)
                .getSingleResult();
        transaction.commit();
        session.close();

        //todo to implement -> implemented
        return Optional.of(location);
    }

}
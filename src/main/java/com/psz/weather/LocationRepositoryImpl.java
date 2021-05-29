package com.psz.weather;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class LocationRepositoryImpl implements LocationRepository{

    private final SessionFactory sessionFactory;

    public LocationRepositoryImpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
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
}
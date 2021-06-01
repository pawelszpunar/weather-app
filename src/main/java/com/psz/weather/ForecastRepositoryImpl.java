package com.psz.weather;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Optional;

public class ForecastRepositoryImpl implements ForecastRepository{

    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public ForecastRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<Forecast> getForecastByLocationAndDate(Location location, LocalDate localDate) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Forecast forecast = session.createQuery("SELECT f FROM forecast f INNER JOIN location l WHERE l.id = :locationId AND f.localDate = :localDate", Forecast.class)
                .setParameter("locationId", location.getId())
                .setParameter("localDate", localDate)
                .getSingleResult();

        transaction.commit();
        session.close();

        return Optional.of(forecast);
    }

    @Override
    public Forecast saveForecast(Forecast forecast) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.persist(forecast);
        transaction.commit();
        session.close();
        return forecast;
    }
}

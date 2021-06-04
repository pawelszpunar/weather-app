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

        try {
            session = sessionFactory.openSession();

            Forecast forecast = session.createQuery("SELECT f FROM Forecast f WHERE f.location.id = :locationId AND f.localDate = :localDate", Forecast.class)
                    .setParameter("locationId", location.getId())
                    .setParameter("localDate", localDate)
                    .getSingleResult();

            //System.out.println("Forecast found in database: [ " + forecast + " ]");

            session.close();

            return Optional.of(forecast);
        } catch (Exception e) {
            return Optional.empty();
        }


    }

    @Override
    public Forecast saveForecast(Forecast forecast) {
        System.out.println("Saving forecast to database!");
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.persist(forecast);
        transaction.commit();
        session.close();
        return forecast;
    }
}

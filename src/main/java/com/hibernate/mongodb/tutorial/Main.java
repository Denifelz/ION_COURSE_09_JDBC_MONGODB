package com.hibernate.mongodb.tutorial;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            final CityDAO cityDAO = new CityDAO(sessionFactory);

            try {
                cityDAO.deleteAll();

                System.out.println("INFO: Delete executed successfully.");
                System.out.println();
            } catch (Exception e) {
                System.out.println("ERROR: Unable to delete records!");
                throw new RuntimeException(e);
            }

            try {
                final City cityChisinau = new City();
                cityChisinau.set_id("1");
                cityChisinau.setName("Chisinau");
                cityChisinau.setPopulation(1000000);
                cityChisinau.setBudget(999999.99);
                cityDAO.create(cityChisinau);

                final City cityBalti = new City();
                cityBalti.set_id("2");
                cityBalti.setName("Balti");
                cityBalti.setPopulation(200000);
                cityBalti.setBudget(100000);
                cityDAO.create(cityBalti);

                final City cityCahul = new City();
                cityCahul.set_id("3");
                cityCahul.setName("Cahul");
                cityCahul.setPopulation(100000);
                cityCahul.setBudget(90000);
                cityDAO.create(cityCahul);

                System.out.println("INFO: Insert executed successfully.");
                System.out.println();
            } catch (Exception e) {
                System.out.println("ERROR: Unable to insert records!");
                throw new RuntimeException(e);
            }

            try {
                List<City> cities = cityDAO.readAll();
                System.out.println("INFO: Query created successfully.");
                System.out.println();

                try {
                    System.out.println("City|Population|Budget");
                    System.out.println("======================");
                    cities.forEach(city -> {
                        System.out.println(city.getName() + "|" + city.getPopulation() + "|" + city.getBudget());
                    });
                    System.out.println();

                    System.out.println("INFO: Fetch executed successfully.");
                    System.out.println();
                } catch (Exception e) {
                    System.out.println("ERROR: Unable to fetch records!");
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                System.out.println("ERROR: Unable to create query!");
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            System.out.println("ERROR: Unable to load MongoDB JDBC Driver!");
            throw new RuntimeException(e);
        }
    }
}

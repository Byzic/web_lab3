package org.lera.lab3.repository;

import org.lera.lab3.model.Point;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.util.*;

@Named
@SessionScoped
public class PointRepository implements Serializable {


    public List<Point> getPoints(String userId) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("data").createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT c FROM Point c WHERE c.userId LIKE :userId")
                .setParameter("userId", userId);
        List<Point> items = query.getResultList();

        return items;
    }

    public void addPoint(Point point) {


        EntityManager entityManager = Persistence.createEntityManagerFactory("data").createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(point);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}



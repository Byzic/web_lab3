package org.lera.lab3.repository;

import org.lera.lab3.model.Point;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class PointRepository implements Serializable {

    public List<Point> getPoints(String userId) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("data").createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT c FROM Point c WHERE c.userId LIKE :userId")
                .setParameter("userId", userId);
        List<Point> items = query.getResultList();
        Collections.reverse(items);
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

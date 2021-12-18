package org.lera.lab3.repository;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
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
    private static final String PERSISTENCE_UNIT_NAME = "data";
    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    public List<Point> getPoints(String userId) {
        return entityManager.createQuery("SELECT c FROM Point c WHERE c.userId LIKE :userId").getResultList();
//        EntityManager entityManager = Persistence.createEntityManagerFactory("data").createEntityManager();
//        Query query = entityManager.createQuery(
//                "SELECT c FROM Point c WHERE c.userId LIKE :userId")
//                .setParameter("userId", userId);
//        List<Point> items = query.getResultList();
//        Collections.reverse(items);
//        return items;
    }

    public void addPoint(Point point) {
        try {
            userTransaction.begin();
            entityManager.persist(point);
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        EntityManager entityManager = Persistence.createEntityManagerFactory("data").createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(point);
//        entityManager.getTransaction().commit();
//        entityManager.close();
    }
}

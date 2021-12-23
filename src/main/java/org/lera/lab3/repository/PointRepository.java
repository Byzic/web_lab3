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
    private static final String PERSISTENCE_UNIT_NAME = "data";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    public List<Point> getPoints(String userId) {

        Query query = entityManager.createQuery(
                "SELECT c FROM Point c WHERE c.userId LIKE :userId")
                .setParameter("userId", userId);
        List<Point> items = query.getResultList();
        return items;
    }

    public void addPoint(Point point) {
        try{
            userTransaction.begin();
            entityManager.persist(point);
            userTransaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}







import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@SessionScoped
public class PointsBean implements Serializable {
    private Point point;
    private List<Point> listWithPoints;

    @PersistenceContext(unitName = "data")
    private EntityManager entityManager;

//    private EntityManagerFactory entityManagerFactory;
//    private EntityManager entityManager;
//    private EntityTransaction transaction;

    @Resource
    private UserTransaction userTransaction;


    public PointsBean() {
        point = new Point();
//        point.setX(0);
//        point.setY(0.0);
        //point.setR(3.0);
        listWithPoints = new ArrayList<>();
        //connection();
        loadPoints();
    }

    @PostConstruct
    private void loadPoints() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("data");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        listWithPoints = entityManager.createQuery("SELECT e FROM Point e").getResultList();

    }

    public String addPoint() {
        try { listWithPoints.add(point);
            System.out.println("Добавили точку \n \n \n \n \n ");
            userTransaction.begin();
            System.out.println(point.getX() );
            point.checkResult();

            System.out.println("Добавили точку");

            for (Point point:listWithPoints) {
                System.out.println(point);
            }
            entityManager.persist(point);
            point = new Point();
            userTransaction.commit();
        } catch (RuntimeException exception) {
            try {
                userTransaction.rollback();
            } catch (SystemException e) {
                e.printStackTrace();
            }
        } catch (SystemException | NotSupportedException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return "redirect";
    }


    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public List<Point> getListWithPoints() {
        return listWithPoints;
    }

    public void setListWithPoints(List<Point> listWithPoints) {
        this.listWithPoints = listWithPoints;
    }
}


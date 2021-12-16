import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PointsBean  implements Serializable {
    private Point point;
    private List<Point> listWithPoints;

    public PointsBean(){
        point=new Point();
        listWithPoints=new ArrayList<>();
    }

    public String addPoint() {

        point.checkResult();
        System.out.println(point);
        listWithPoints.add(point);
        int i;
        for (i=0; i<listWithPoints.size();i++){
            System.out.println(listWithPoints.get(i).getX()+" "+listWithPoints.get(i).getY()+"/n");
        }
        point = new Point();
//        point.setxValue(0.0);
//        point.setyValue(0.0);
//        point.setrValue(3.0);
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
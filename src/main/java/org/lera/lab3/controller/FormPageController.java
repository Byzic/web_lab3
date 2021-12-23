package org.lera.lab3.controller;

import org.lera.lab3.model.Point;
import org.lera.lab3.repository.PointRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class FormPageController implements Serializable {

    @Inject
    private PointRepository pointRepository;
    private List<Point> listWithPoints=new ArrayList();

    private String userId;


    private Double x = 0.0;
    private Double y = 0.0;
    private Double r = 3.0;

    @PostConstruct
    public void init() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        listWithPoints=getHistory();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        userId = session.getId();
    }

    public void shotSubmitted() {

        Point point = getResultEntry(x, y, r);
        listWithPoints.add(point);

        pointRepository.addPoint(point);
    }

    private Point getResultEntry(double x, double y, double r) {
        boolean hit;
        hit = checkTriangle() || checkRectangle() || checkCircle() ;
        Point point = new Point();
        point.setUserId(userId);
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setHit(hit);
        return point;
    }
    private boolean checkTriangle() {
        return x <= 0 && y >= 0 && y <= x + (double) r /2;
    }

    private boolean checkRectangle() {
        return x >= 0 && y >= 0 && x <= r && y <= r;
    }

    private boolean checkCircle() {
        return x <= 0 && y <= 0 && y * y <= r * r - x * x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getX() {
        return x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getY() {
        return y;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getR() {
        return r;
    }

    public void setListWithPoints(List<Point> listWithPoints){
        this.listWithPoints=listWithPoints;
    }
    public List<Point> getListWithPoints(){
        return listWithPoints;
    }

    public List<Point> getHistory() {
        return pointRepository.getPoints(userId);
    }
}

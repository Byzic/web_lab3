package org.lera.lab3.controller;

import org.lera.lab3.model.Point;
import org.lera.lab3.repository.PointRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class FormPageController implements Serializable {
    private Double MIN_X=-5.0;
    private Double MAX_X=3.0;
    private Double MIN_Y=-5.0;
    private Double MAX_Y=3.0;

    @Inject
    private PointRepository pointRepository;

    private String userId;

    private Double x = 0.0;
    private Double y = 0.0;
    private Double r = 3.0;

    @PostConstruct
    public void init() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        userId = session.getId();
    }

    public void shotSubmitted() {

        if (x<MIN_X || x>MAX_X || y<=MIN_Y || y>=MAX_Y){
            return;
        }
        Point point = getResultEntry(x, y, r);
        pointRepository.addPoint(point);
    }

    private Point getResultEntry(double x, double y, double r) {
        boolean hit;


        hit = checkTriangle() || checkRectangle() || checkCircle() ? true : false;

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

    public List<Point> getHistory() {
        return pointRepository.getPoints(userId);
    }
}

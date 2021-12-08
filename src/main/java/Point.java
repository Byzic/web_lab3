import javax.persistence.*;
import java.io.Serializable;



public class Point implements Serializable {

    
    private int id;
    private Integer xValue;
    private Double yValue;
    private Double rValue;
    private String result;

    public Point() { }

    private boolean checkTriangle() {
        return xValue <= 0 && yValue >= 0 && yValue <= xValue + (double)rValue/2;
    }

    private boolean checkRectangle() {
        return xValue >= 0 && yValue >= 0 && xValue<=rValue && yValue<=rValue;
    }

    private boolean checkCircle() {
        return xValue <= 0 && yValue <= 0 && yValue*yValue<=rValue*rValue-xValue*xValue;
    }

    public void checkResult() {
        result = checkTriangle() || checkRectangle() || checkCircle() ? "Попадание" : "Промах";
    }

    public Integer getxValue() {
        return xValue;
    }

    public void setxValue(Integer xValue) {
        this.xValue = xValue;
    }

    public Double getyValue() {
        return yValue;
    }

    public void setyValue(Double yValue) {
        this.yValue = yValue;
    }

    public Double getrValue() {
        return rValue;
    }

    public void setrValue(Double rValue) {
        this.rValue = rValue;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



}

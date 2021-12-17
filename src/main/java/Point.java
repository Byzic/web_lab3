import java.io.Serializable;

public class Point implements Serializable {

    
    private int id;
    private Double x;
    private Double y;
    private Double r;
    private String result;

    public Point() { }

    private boolean checkTriangle() {
        return x <= 0 && y >= 0 && y <= x + (double) r /2;
    }

    private boolean checkRectangle() {
        return x >= 0 && y >= 0 && x <= r && y <= r;
    }

    private boolean checkCircle() {
        return x <= 0 && y <= 0 && y * y <= r * r - x * x;
    }

    public void checkResult() {

        result = checkTriangle() || checkRectangle() || checkCircle() ? "Попадание" : "Промах";

        }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



}

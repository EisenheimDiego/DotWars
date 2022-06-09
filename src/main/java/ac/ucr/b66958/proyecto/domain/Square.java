package ac.ucr.b66958.proyecto.domain;

public class Square {

    private Integer x;
    private Integer y;

    public Square(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Square() {
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}

package beginningClasses;

public class Coordinates {
    private Double x; //Значение поля должно быть больше -386, Поле не может быть null
    private double y;

    public Coordinates(){};
    public Coordinates(Double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
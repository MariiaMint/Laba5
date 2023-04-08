package beginningClasses;

public class Car {
    private String name; //Поле может быть null
    private boolean cool;

    public Car() {
    }

    public Car(String name, boolean cool) {
        this.name = name;
        this.cool = cool;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCool(boolean cool) {
        this.cool = cool;
    }

    @Override
    public String toString() {
        return name + "," + cool;
    }
}

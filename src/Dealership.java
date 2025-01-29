package Assignment_4;

import java.util.ArrayList;

class Dealership {
    private int id;
    private String name;
    private ArrayList<Car> cars = new ArrayList<Car>();
    
    Dealership(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public void removeCar(Car car) {
        this.cars.remove(car);
    }

    public Car searchCar(String brand_name) {
        for (Car car : cars) {
            if (car.getBrand().toLowerCase().equals(brand_name.toLowerCase())) {
                return car;
            }
        }
        return null;
    }

    public String toString() {
        return getName();
    }
}
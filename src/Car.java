package Assignment_4;

import java.util.ArrayList;

public class Car {
    private int id;
    private String brand_name;
    private int numberOfPassengers;
    private int cost;
    private int quantity;
    private Dealership dealership;
    private ArrayList<PurchaseRequest> requests = new ArrayList<PurchaseRequest>();

    public Car(int id, String brand, int passengers, int cost, int quantity, Dealership dls) {
        this.id = id;
        this.brand_name = brand;
        this.numberOfPassengers = passengers;
        this.cost = cost;
        this.quantity = quantity;
        this.dealership = dls;
    }

    public String toString() {
        return getBrand();
    }

    public int getID() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand_name;
    }

    public void setBrand(String name) {
        this.brand_name = name;
    }

    public int getPassengers() {
        return this.numberOfPassengers;
    }

    public void setPassengers(int passengers) {
        this.numberOfPassengers = passengers;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getAvailability() {
        return (this.quantity > 0);
    }

    public Car getCar() {
        return this;
    }

    public void setDealership(Dealership dls) {
        this.dealership = dls;
    }

    public Dealership getDealership() {
        return this.dealership;
    }

    public void addRequest(PurchaseRequest req) {
        this.requests.add(req);
    }

    public ArrayList<PurchaseRequest> getRequests() {
        return this.requests;
    }

    public void removeRequest(PurchaseRequest request) {
        this.requests.remove(request);
    }
}

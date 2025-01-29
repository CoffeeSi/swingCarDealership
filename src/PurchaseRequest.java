package Assignment_4;

public class PurchaseRequest {
    private int id;
    private String buyer_name;
    private String phone_number;
    private Car car;

    PurchaseRequest(int id, String name, String phone, Car car) {
        this.id = id;
        this.buyer_name = name;
        this.phone_number = phone;
        this.car = car;
    }

    public int getID() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuyerName() {
        return this.buyer_name;
    }

    public void setBuyerName(String name) {
        this.buyer_name = name;
    }

    public String getPhone() {
        return this.phone_number;
    }

    public void setPhone(String phone) {
        this.phone_number = phone;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String toString() {
        return this.buyer_name;
    }
}

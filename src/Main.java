package Assignment_4;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Dealership havalCentre = new Dealership("HavalCentre");
        // Dealership allur = new Dealership("Allur");
        // Database.dealerToDatabase(allur);
        // Car haval = new Car("Haval", 5, 20000, 3, havalCentre);
        // Database.carToDatabase(haval);
        // Car lambo = new Car("Lambo", 2, 100000, 2, havalCentre);
        // Database.carToDatabase(lambo);
        // Car huyndai = new Car("Huyndai", 4, 10000, 2, allur);
        // Database.carToDatabase(huyndai);

        // PurchaseRequest denis = new PurchaseRequest("Denis", "770442", havalH6);
        // PurchaseRequest meirhan = new PurchaseRequest("Meirhan", "707665", havalM6);
        // PurchaseRequest arlan = new PurchaseRequest("Arlan", "707434", havalM6);
        // PurchaseRequest erdaulet = new PurchaseRequest("Erdaulet", "707232", havalM6);
        ArrayList<Dealership> dealers = Database.getDealers();
        for (Dealership dd : dealers) {
            Database.getCars(dd);
            for (Car car : dd.getCars()) {
                System.out.println("Brand: " + car.getBrand());
                System.out.println("Dealership: " + car.getDealership().getName());
                // System.out.println("Purchase Requests: " + car.getRequest());
            }
        
        // Database.getCars(havalCentre);
        
        }

        // Database.carToDatabase(havalM6);
        // try (Connection con = Database.connect();
        //     Statement stat = con.createStatement()){
        // } catch (SQLException e) {
        //     System.err.println(e.getMessage()); 
        // }
        
    }
}

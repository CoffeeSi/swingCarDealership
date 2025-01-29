package Assignment_4;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static Connection connect() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "admin";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Dealers

    public static void dealerToDatabase(Dealership dealer) {
        try (Connection con = Database.connect();
            Statement stat = con.createStatement()){
                String querry = "INSERT INTO " +
                    "dealerships(name) VALUES('" + dealer.getName() + "')";
                stat.execute(querry);
        } catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
    }

    public static void dealerToDatabase(String dealerName) {
        try (Connection con = Database.connect();
            Statement stat = con.createStatement()){
                String querry = "INSERT INTO " +
                    "dealerships(name) VALUES('" + dealerName + "')";
                stat.execute(querry);
        } catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
    }

    public static ArrayList<Dealership> getDealers() {
        try (Connection con = Database.connect();
            Statement stat = con.createStatement()){
                String querry = "SELECT id,name FROM dealerships";
                ResultSet rs = stat.executeQuery(querry);
                ArrayList<Dealership> dealers = new ArrayList<Dealership>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Dealership temp = new Dealership(id, name);
                    dealers.add(temp);
                }
                return dealers;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static void removeDealer(Dealership dealer) {
        try (Connection con = Database.connect();
            Statement stat = con.createStatement()) {
            stat.execute("DELETE FROM dealerships WHERE id=" + dealer.getID());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Dealership getLastDealer() {
        try (Connection con = Database.connect();
        Statement stat = con.createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT id,name FROM dealerships ORDER BY id DESC LIMIT 1");
            rs.next();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Dealership dr = new Dealership(id, name);
            return dr;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    // Cars

    public static void carToDatabase(Car car) {
        try (Connection con = Database.connect();
            Statement stat = con.createStatement()) {
                String querry = "INSERT INTO " +
                    "cars(brand, passengers, cost, quantity, available, dealership) " +
                    "VALUES('" +
                    car.getBrand() + "','" + car.getPassengers() + "','" +
                    car.getCost() + "','" + car.getQuantity() + "','" +
                    car.getAvailability() + "','" + 
                    car.getDealership().getID() + "')";
                stat.execute(querry);
        } catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
    }

    public static void getCars(Dealership dealer) {
        try (Connection con = Database.connect();
            Statement stat = con.createStatement()){
                String querry = "SELECT * FROM cars WHERE dealership='" + dealer.getID() + "'";
                ResultSet rs = stat.executeQuery(querry);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String brand = rs.getString("brand");
                    int passengers = rs.getInt("passengers");
                    int cost = rs.getInt("cost");
                    int quantity = rs.getInt("quantity");
                    Car temp = new Car(id, brand, passengers, cost, quantity, dealer);
                    dealer.addCar(temp);
                }
        } catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
    }

    public static void removeCar(Car car) {
        try (Connection con = Database.connect();
            Statement stat = con.createStatement()) {
            stat.execute("DELETE FROM cars WHERE id=" + car.getID());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int getLastCar() {
        try (Connection con = Database.connect();
        Statement stat = con.createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT id,brand,passengers,cost,quantity,dealership FROM cars ORDER BY id DESC LIMIT 1");
            if (rs.next()) {
                int id = rs.getInt("id");
                String brand = rs.getString("brand");
                int passengers = rs.getInt("passengers");
                int cost = rs.getInt("cost");
                int quantity = rs.getInt("quantity");
                int dealership = rs.getInt("dealership");
                return id;
            }
            return 0;
            // Car dr = new Dealership(id, name);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    // Requests

    public static void requestToDatabase(PurchaseRequest req) {
        try (Connection con = Database.connect();
            Statement stat = con.createStatement()){
                String querry = "INSERT INTO " +
                    "purchaserequest(buyer_name, phone_number, car) " +
                    "VALUES('" +req.getBuyerName() + "','" + 
                    req.getPhone() + "','" + req.getCar().getID() + "')";
                stat.execute(querry);
        } catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
    }

    public static void getRequests(Car car) {
        try (Connection con = Database.connect();
            Statement stat = con.createStatement()){
                String querry = "SELECT * FROM purchaserequest WHERE car='" + car.getID() + "'";
                ResultSet rs = stat.executeQuery(querry);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("buyer_name");
                    String phone = rs.getString("phone_number");
                    PurchaseRequest temp = new PurchaseRequest(id, name, phone, car);
                    car.addRequest(temp);
                }
        } catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
    }

    public static void removeRequest(PurchaseRequest request) {
        try (Connection con = Database.connect();
            Statement stat = con.createStatement()) {
            stat.execute("DELETE FROM purchaserequest WHERE id=" + request.getID());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int getLastRequest() {
        try (Connection con = Database.connect();
        Statement stat = con.createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT id FROM purchaserequest ORDER BY id DESC LIMIT 1");
            if (rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
            return 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }
}

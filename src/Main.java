package Assignment_4;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Main extends JFrame{
    final String[] TEMPLATES = {"Dealership:       ", "Car:                 ",
                                "Passengers:     ","Cost:                ",
                                "Quantity:           ", "Available:          ", 
                                "Buyer`s name:   ", "Phone number:  "};

    private DefaultListModel<Dealership> dealersModel = new DefaultListModel<>();
    private DefaultListModel<Car> carsModel = new DefaultListModel<>();
    private DefaultListModel<PurchaseRequest> reqsModel = new DefaultListModel<>();

    Main() {
        setTitle("Car dealership");
        setSize(1000,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10 ,15 ,10 ,15));

        ArrayList<Dealership> dealers = Database.getDealers();
        dealersModel.addAll(dealers);
        for (Dealership dd : dealers){
            Database.getCars(dd);
            for (int i = 0; i < dd.getCars().size(); i++)
                Database.getRequests(dd.getCars().get(i));
        }

        // Main Label
        JLabel label = new JLabel("Car Dealership");
        label.setFont(new Font("Sans-serif", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Info Panel

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel dealerJLabel = new JLabel();
        dealerJLabel.setText("Dealership: ");
        dealerJLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        JLabel brandJLabel = new JLabel();
        brandJLabel.setText("Car: ");
        brandJLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        JLabel passengersJLabel = new JLabel();
        passengersJLabel.setText("Passengers: ");
        passengersJLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        JLabel costJLabel = new JLabel();
        costJLabel.setText("Cost: ");
        costJLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        JLabel quantityJLabel = new JLabel();
        quantityJLabel.setText("Quantity: ");
        quantityJLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        JLabel availableJLabel = new JLabel();
        availableJLabel.setText("Available: ");
        availableJLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        JLabel buyerJLabel = new JLabel();
        buyerJLabel.setText("Buyer`s name: ");
        buyerJLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        JLabel phoneJLabel = new JLabel();
        phoneJLabel.setText("Phone number: ");
        phoneJLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        infoPanel.add(dealerJLabel);
        infoPanel.add(brandJLabel);
        infoPanel.add(passengersJLabel);
        infoPanel.add(costJLabel);
        infoPanel.add(quantityJLabel);
        infoPanel.add(availableJLabel);
        infoPanel.add(buyerJLabel);
        infoPanel.add(phoneJLabel);

        // ----Left Panel----
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Dealerships"), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // JList left panel
        JList<Dealership> dealerJList = new JList<Dealership>();
        dealerJList.setModel(dealersModel);
        dealerJList.setVisibleRowCount(5);
        dealerJList.setFixedCellWidth(160);
        dealerJList.setFixedCellHeight(24);
        JScrollPane scrollPaneLeft = new JScrollPane(dealerJList);

        // Creating JButtons 
        JPanel btnsPanelLeft = new JPanel();
        btnsPanelLeft.setLayout(new FlowLayout());
        JButton addJButtonLeft = new JButton("New");
        JButton remJButtonLeft = new JButton("Remove");

        btnsPanelLeft.add(addJButtonLeft);
        btnsPanelLeft.add(remJButtonLeft);

        // Adding objects to left panel
        leftPanel.add(scrollPaneLeft);
        leftPanel.add(btnsPanelLeft, BorderLayout.SOUTH);

        // ----Center Panel----
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Cars"), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JList<Car> carsJList = new JList<Car>();
        carsJList.setModel(carsModel);
        carsJList.setVisibleRowCount(5);
        carsJList.setFixedCellWidth(160);
        carsJList.setFixedCellHeight(24);
        JScrollPane scrollPaneCenter = new JScrollPane(carsJList);


        ArrayList<Dealership> selectedDealerships = new ArrayList<>();

        dealerJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    carsJList.clearSelection();
                    carsModel.clear();
                    Dealership selectedDealership = dealerJList.getSelectedValue();
                    if (selectedDealership != null) {
                        selectedDealerships.add(selectedDealership);
                        if (selectedDealership.getCars().isEmpty()) {
                            carsModel.clear();
                            reqsModel.clear();
                        };
                        carsModel.addAll(selectedDealership.getCars());

                        dealerJLabel.setText(TEMPLATES[0] + selectedDealership.getName());
                    }
                }
            }
        });

        JPanel btnsPanelCenter = new JPanel();
        btnsPanelCenter.setLayout(new FlowLayout());

        JButton addJButtonCenter = new JButton("New");
        JButton remJButtonCenter = new JButton("Remove");
        JButton searchJButtonCenter = new JButton("Search");

        btnsPanelCenter.add(addJButtonCenter);
        btnsPanelCenter.add(remJButtonCenter);
        btnsPanelCenter.add(searchJButtonCenter);

        centerPanel.add(scrollPaneCenter);
        centerPanel.add(btnsPanelCenter, BorderLayout.SOUTH);

        // Right Panel

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Purchase Requests"), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JList<PurchaseRequest> reqsJList = new JList<PurchaseRequest>();
        reqsJList.setModel(reqsModel);
        reqsJList.setVisibleRowCount(5);
        reqsJList.setFixedCellWidth(160);
        reqsJList.setFixedCellHeight(24);
        JScrollPane scrollPaneRight = new JScrollPane(reqsJList);
        rightPanel.add(scrollPaneRight);

        ArrayList<Car> selectedCars = new ArrayList<>();

        carsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (dealersModel.getSize() != 0) {
                        Car selectedCar = carsJList.getSelectedValue(); 
                        if (selectedCar != null) {
                            reqsModel.clear();
                            selectedCars.add(selectedCar);
                            if (selectedCar.getRequests().isEmpty()) {
                                reqsModel.clear();
                            };
                            reqsModel.addAll(selectedCar.getRequests());

                            brandJLabel.setText(TEMPLATES[1] + selectedCar.getBrand());
                            passengersJLabel.setText(TEMPLATES[2] + selectedCar.getPassengers());
                            costJLabel.setText(TEMPLATES[3] + selectedCar.getQuantity());
                            quantityJLabel.setText(TEMPLATES[4] + selectedCar.getCost());
                            availableJLabel.setText(TEMPLATES[5] + selectedCar.getAvailability());
                            buyerJLabel.setText(TEMPLATES[6]);
                            phoneJLabel.setText(TEMPLATES[7]);
                        }
                    }
                }
            }
        });

        reqsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (dealersModel.getSize() != 0) {
                        PurchaseRequest selectedRequest = reqsJList.getSelectedValue(); 
                        if (selectedRequest != null) {
                            buyerJLabel.setText(TEMPLATES[6] + selectedRequest.getBuyerName());
                            phoneJLabel.setText(TEMPLATES[7] + selectedRequest.getPhone());
                        }
                    }
                }
            }
        });

        JPanel btnsPanelRight = new JPanel();
        btnsPanelRight.setLayout(new FlowLayout());

        JButton addJButtonRight = new JButton("New");
        JButton remJButtonRight = new JButton("Remove");

        btnsPanelRight.add(addJButtonRight);
        btnsPanelRight.add(remJButtonRight);

        rightPanel.add(scrollPaneRight);
        rightPanel.add(btnsPanelRight, BorderLayout.SOUTH);

    // =======================BUTTONS===============================

        // Left JButton ADD action
        addJButtonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("Add new dealership");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    
                    JPanel panel = new JPanel();
                    panel.setBorder(BorderFactory.createEmptyBorder(10 ,16,10 ,16));
                    panel.setLayout(new BorderLayout());

                    JPanel nameJPnael = new JPanel();
                    nameJPnael.setLayout(new FlowLayout());
                    JLabel labelName = new JLabel("Name: ");
                    JTextField inputName = new JTextField(14);
                    nameJPnael.add(labelName);
                    nameJPnael.add(inputName);

                    JButton buttonOK = new JButton("Save");
                    buttonOK.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Database.dealerToDatabase(inputName.getText());
                            dealers.add(Database.getLastDealer());
                            dealersModel.addElement(Database.getLastDealer());
                            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        }
                    });

                    panel.add(nameJPnael, BorderLayout.NORTH);
                    panel.add(buttonOK, BorderLayout.CENTER);

                    frame.add(panel, BorderLayout.CENTER);
                    frame.pack();
                    frame.setLocationByPlatform(true);
                    frame.setResizable(false);
                    frame.setVisible(true);
                }
            });
            }
        });

        // Left Jbutton REMOVE action
        remJButtonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dealership selectedDealership = dealerJList.getSelectedValue();
                if (selectedDealership != null) {
                    dealers.remove(selectedDealership);
                    Database.removeDealer(selectedDealership);
                    dealersModel.removeElement(selectedDealership);

                    carsModel.clear();
                    reqsModel.clear();

                    dealerJLabel.setText(TEMPLATES[0]);
                }
            }
        });

        // Center JButton ADD action
        addJButtonCenter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("Add new car");
                    frame.setLayout(new BorderLayout());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    
                    JPanel panel = new JPanel();
                    panel.setBorder(BorderFactory.createEmptyBorder(10 ,16 ,10 ,16));
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                    JPanel brandJPnael = new JPanel();
                    brandJPnael.setLayout(new FlowLayout());
                    JLabel labelBrand = new JLabel("     Brand:      ");
                    JTextField inputBrand = new JTextField(14);
                    brandJPnael.add(labelBrand);
                    brandJPnael.add(inputBrand);

                    JPanel passengersJPnael = new JPanel();
                    passengersJPnael.setLayout(new FlowLayout());
                    JLabel labelPassengers = new JLabel("Passengers: ");

                    JTextField inputPassengers = new JTextField(14);
                    passengersJPnael.add(labelPassengers);
                    passengersJPnael.add(inputPassengers);

                    JPanel costJPnael = new JPanel();
                    costJPnael.setLayout(new FlowLayout());
                    JLabel labelCost = new JLabel("      Cost:        ");
                    JTextField inputCost = new JTextField(14);
                    costJPnael.add(labelCost);
                    costJPnael.add(inputCost);

                    JPanel quantityJPnael = new JPanel();
                    quantityJPnael.setLayout(new FlowLayout());
                    JLabel labelQuantity = new JLabel("    Quantity:    ");
                    JTextField inputQuantity = new JTextField(14);
                    quantityJPnael.add(labelQuantity);
                    quantityJPnael.add(inputQuantity);

                    JPanel errorJPanel = new JPanel();
                    errorJPanel.setLayout(new FlowLayout());
                    JLabel errorJLabel = new JLabel(" ");
                    errorJLabel.setForeground(Color.red);
                    errorJPanel.add(errorJLabel);

                    JButton buttonOK = new JButton("Save");
                    buttonOK.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String brand = inputBrand.getText();
                            int passengers = 0;
                            int cost = 0;
                            int quantity = 0;
                            try {
                                passengers = Integer.parseInt(inputPassengers.getText());
                                cost = Integer.parseInt(inputCost.getText());
                                quantity = Integer.parseInt(inputQuantity.getText());
                            } catch (NumberFormatException ex) {
                                errorJLabel.setText(ex.getMessage() + "must be integer");
                                return;
                            }
                            try {
                                if (dealersModel.getSize() != 0) {
                                    Car temp = new Car(Database.getLastCar()+1, brand, passengers, cost, quantity, selectedDealerships.getLast());
                                    Database.carToDatabase(temp);
                                    selectedDealerships.getLast().addCar(temp);
                                    carsModel.addElement(temp);
                                } else throw new NoSuchElementException();
                            } catch (NoSuchElementException ex) {
                                errorJLabel.setText("You need to choose a dealership!");
                                return;
                            }

                            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        }
                    });

                    panel.add(brandJPnael);
                    panel.add(passengersJPnael);
                    panel.add(costJPnael);
                    panel.add(quantityJPnael);
                    panel.add(errorJPanel);
                    panel.add(buttonOK);

                    frame.add(panel, BorderLayout.CENTER);
                    frame.pack();
                    frame.setLocationByPlatform(true);
                    frame.setResizable(false);
                    frame.setVisible(true);
                }
            });
            }
        });

        // Center Jbutton REMOVE action
        remJButtonCenter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = carsJList.getSelectedValue();
                if (selectedCar != null) {
                    selectedDealerships.getLast().removeCar(selectedCar);
                    Database.removeCar(selectedCar);
                    carsModel.removeElement(selectedCar);

                    reqsModel.clear();

                    brandJLabel.setText(TEMPLATES[1]);
                    passengersJLabel.setText(TEMPLATES[2]);
                    costJLabel.setText(TEMPLATES[3]);
                    quantityJLabel.setText(TEMPLATES[4]);
                    availableJLabel.setText(TEMPLATES[5]);
                }
            }
        });

        searchJButtonCenter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new JFrame("Search for car");
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        
                        JPanel panel = new JPanel();
                        panel.setBorder(BorderFactory.createEmptyBorder(10 ,16,10 ,16));
                        panel.setLayout(new BorderLayout());
    
                        JPanel nameJPnael = new JPanel();
                        nameJPnael.setLayout(new FlowLayout());
                        JLabel labelName = new JLabel("Brand: ");
                        JTextField inputName = new JTextField(14);
                        nameJPnael.add(labelName);
                        nameJPnael.add(inputName);
    
                        JButton buttonOK = new JButton("Search");
                        buttonOK.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Car foundCar = selectedDealerships.getLast().searchCar(inputName.getText());
                                if (foundCar != null) {
                                    carsJList.setSelectedValue(foundCar, true);
                                }
                                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                            }
                        });
    
                        panel.add(nameJPnael, BorderLayout.NORTH);
                        panel.add(buttonOK, BorderLayout.CENTER);
    
                        frame.add(panel, BorderLayout.CENTER);
                        frame.pack();
                        frame.setLocationByPlatform(true);
                        frame.setResizable(false);
                        frame.setVisible(true);
                    }
                });
            }
        });

        // Right JButton ADD action
        addJButtonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("Add new purchase request");
                    frame.setLayout(new BorderLayout());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    
                    JPanel panel = new JPanel();
                    panel.setBorder(BorderFactory.createEmptyBorder(10 ,16 ,10 ,16));
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                    JPanel nameJPnael = new JPanel();
                    nameJPnael.setLayout(new FlowLayout());
                    JLabel labelName = new JLabel("Buyer's name: ");
                    JTextField inputName = new JTextField(14);
                    nameJPnael.add(labelName);
                    nameJPnael.add(inputName);

                    JPanel phoneJPnael = new JPanel();
                    phoneJPnael.setLayout(new FlowLayout());
                    JLabel labelPhone = new JLabel("Phone number: ");
                    JTextField inputPhone = new JTextField(14);
                    phoneJPnael.add(labelPhone);
                    phoneJPnael.add(inputPhone);

                    JPanel errorJPanel = new JPanel();
                    errorJPanel.setLayout(new FlowLayout());
                    JLabel errorJLabel = new JLabel(" ");
                    errorJLabel.setForeground(Color.red);
                    errorJPanel.add(errorJLabel);

                    JButton buttonOK = new JButton("Save");
                    buttonOK.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String brand = inputName.getText();
                            String phone = inputPhone.getText();
                            try {
                                if (carsModel.getSize() != 0) {
                                    System.out.println(selectedCars.getLast().getID());
                                    PurchaseRequest temp = new PurchaseRequest(Database.getLastRequest()+1, brand, phone, selectedCars.getLast());
                                    Database.requestToDatabase(temp);
                                    selectedCars.getLast().addRequest(temp);
                                    reqsModel.addElement(temp);
                                } else throw new NoSuchElementException();
                            } catch (NoSuchElementException ex) {
                                errorJLabel.setText("You need to choose a dealership!");
                                return;
                            }

                            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        }
                    });

                    panel.add(nameJPnael);
                    panel.add(phoneJPnael);
                    panel.add(errorJPanel);
                    panel.add(buttonOK);

                    frame.add(panel, BorderLayout.CENTER);
                    frame.pack();
                    frame.setLocationByPlatform(true);
                    frame.setResizable(false);
                    frame.setVisible(true);
                }
            });
            }
        });

        // Right Jbutton REMOVE action
        remJButtonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PurchaseRequest selectedRequest = reqsJList.getSelectedValue();
                if (selectedRequest != null) {
                    selectedCars.getLast().removeRequest(selectedRequest);;
                    Database.removeRequest(selectedRequest);
                    reqsModel.removeElement(selectedRequest);

                    buyerJLabel.setText(TEMPLATES[6]);
                    phoneJLabel.setText(TEMPLATES[7]);
                }
            }
        });
        
        // Body panel adding
        bodyPanel.add(leftPanel);
        bodyPanel.add(centerPanel);
        bodyPanel.add(rightPanel);
        bodyPanel.add(infoPanel);

        add(bodyPanel, BorderLayout.WEST);
        // add(infoPanel, BorderLayout.LINE_END);
        setVisible(true);
    }
        
    // Method main
    public static void main(String[] args) {
        Main main = new Main();
    }
}
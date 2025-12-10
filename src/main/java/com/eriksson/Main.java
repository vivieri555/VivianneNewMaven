package com.eriksson;

import File.FileService;
import Member.Member;
import Member.MemberRegistry;
import Member.MembershipService;
import Other.AlertBox;
import Rental.Inventory;
import Rental.RentalService;
import Vehicle.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);

     /*
//            System.out.println("Tryck 5 för att lista/söka på fordon");
//            System.out.println("Tryck 6 för att boka en bil");
//            System.out.println("Tryck 7 för att avsluta en uthyrning av bil");
//            System.out.println("Tryck 8 för att summera intäkter");
                case 5:
                    rentalService.cars();
                    break;
                case 6:
                    Rental rental = new Rental();
                    input.nextLine();
                    System.out.println("Ange namn på medlem du vill boka bil på?");
                    String searchName = input.nextLine();
                    Member searchNamed = membershipService.searchMemberList(searchName);
                    if(searchNamed == null){
                        System.out.println("Medlemmen finns inte, skriv in medlemmen i menyval 1");
                    }
                    else{
                        System.out.println("Medlemmen finns Id: " + searchNamed.getId() + ", " + searchNamed.getName());
                        rental.setMember(searchNamed);
                        System.out.println("Vilken bil vill du boka? Ange varumärke");
                        String car = input.nextLine();
                        Vehicle car1 = rentalService.searchCar(car);
                        if(car1 == null || !car1.isLoanable()){
                            System.out.println("Bilen går inte att låna");
                        }
                        else{
                            car1.setLoanable(false);
                            rental.setVehicle(car1);
                            System.out.println("Hur många dagar vill du låna?");
                            int days = input.nextInt();
                            rental.setRentalDays(days);
                            double amount = rentalService.cost(days);
                            System.out.println("Kostnaden blir " + rentalService.cost(days) + " kr.");
                            rental.setCost(amount);
                            rentalService.add(rental);
                            double discount = rentalService.getDiscountedCost(rental);
                            rental.setCost(discount);
                            rentalService.listRental();
                            input.nextLine();
                            System.out.println("Skriv in en ändring på historiken");
                            searchNamed.setHistory(input.nextLine());
                        }
                    }
                    break;
                case 7:
                    input.nextLine();
                    System.out.println("Vilken medlem vill du avsluta bokningen på?");
                    String name = input.nextLine();
                    rentalService.terminateRental(name);
                    vehicle.setLoanable(true);
                    break;
                case 8:
                    rentalService.sum();
                    break;
                 */
    }

    @Override
    public void start(Stage stage) throws Exception {


        stage.setTitle("Vivis Biluthyrning");
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        Label headLabel = new Label("\tVälkommen\n till Vivis biluthyrning!");
        headLabel.getStyleClass().add("label-green");
        Scene scene1 = new Scene(borderPane, 700, 700);

        FileService fileService = new FileService();
        Vehicle vehicle = new Vehicle();
        ObservableList<Member> members = fileService.readMembers();
        ObservableList<Vehicle> vehicles = fileService.readVehicles();
        MemberRegistry memberRegistry = new MemberRegistry(members);
        Inventory inventory = new Inventory(vehicles);
        getInventory();
        MembershipService membershipService = new MembershipService(memberRegistry);
        RentalService rentalService = new RentalService(inventory);
        AlertBox alertBox = new AlertBox(membershipService);

        TableView<Vehicle> vehicleTable = new TableView<>();

        TableColumn<Vehicle, String> brandColumn = new TableColumn("Märke");
        brandColumn.setMinWidth(200);
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        TextField brandInput = new TextField();
        brandInput.setPromptText("Märke");
        brandInput.setMinWidth(200);

        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Modell");
        modelColumn.setMinWidth(200);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        TextField modelInput = new TextField("Modell");
        modelInput.setPromptText("Modell");
        modelInput.setMinWidth(200);

        TableColumn<Vehicle, Boolean> loanableColumn = new TableColumn<>("Tillgänglig");
        loanableColumn.setMinWidth(200);
        loanableColumn.setCellValueFactory(new PropertyValueFactory<>("loanable"));
        TextField loanableInput = new TextField();
        loanableInput.setPromptText("Tillgänglig");
        loanableInput.setMinWidth(200);

        TableColumn<Vehicle, String> batteryColumn = new TableColumn<>("Batteri");
        batteryColumn.setMinWidth(200);
        batteryColumn.setCellValueFactory(new PropertyValueFactory<>("batterylevel"));
        TextField batteryInput = new TextField();
        batteryInput.setPromptText("Batteri");
        batteryInput.setMinWidth(200);

        TableColumn<Vehicle, String> vehicleTypeColumn = new TableColumn<>("Fordonstyp");
        vehicleTypeColumn.setMinWidth(200);
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        TextField vehicleTypeInput = new TextField();
        vehicleTypeInput.setMinWidth(100);

        TableColumn<Vehicle, String> hasRearCameraColumn = new TableColumn<>("Har backkamera");
        hasRearCameraColumn.setMinWidth(200);
        hasRearCameraColumn.setCellValueFactory(new PropertyValueFactory<>("hasRearCamera"));
        TextField hasRearCameraInput = new TextField();
        hasRearCameraInput.setMinWidth(200);

        TableColumn<Vehicle, String> gearboxColumn = new TableColumn<>("Gearbox");
        gearboxColumn.setMinWidth(100);
        gearboxColumn.setCellValueFactory(new PropertyValueFactory<>("gearbox"));
        TextField gearboxInput = new TextField();
        gearboxInput.setMinWidth(100);

        vehicleTable.setItems(inventory.getVehicleList());
        vehicleTable.getColumns().addAll(brandColumn, modelColumn, loanableColumn,
                batteryColumn, vehicleTypeColumn, hasRearCameraColumn, gearboxColumn);

        //TableView medlemmar
        TableView<Member> table = new TableView<>();
        TableColumn<Member, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.setItems(membershipService.getMembers());

        TextField idInput = new TextField();
        idInput.setPromptText("ID");
        idInput.setMinWidth(100);

        TableColumn<Member, String> nameColumn = new TableColumn<>("Namn");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TextField nameInput = new TextField();
        nameInput.setPromptText("Namn");
        nameInput.setMinWidth(150);

        TableColumn<Member, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        TextField statusInput = new TextField();
        statusInput.setPromptText("Status");
        statusInput.setMinWidth(100);

        TableColumn<Member, String> historyColumn = new TableColumn<>("Historik");
        historyColumn.setMinWidth(200);
        historyColumn.setCellValueFactory(new PropertyValueFactory<>("history"));
        TextField historyInput = new TextField();
        historyInput.setPromptText("Historik");
        historyInput.setMinWidth(200);


        Button returnScene = new Button("Gå tillbaka till huvudmenyn");
        returnScene.setMinWidth(100);
        Button returnScene2 = new Button("Gå tillbaka till huvudmenyn");
        returnScene2.setMinWidth(100);


        table.getColumns().addAll(idColumn, nameColumn, statusColumn, historyColumn);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);

        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(10, 10, 10, 10));
        hBox2.setSpacing(10);

        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(20, 20, 20, 20));
        Scene scene2 = new Scene(vbox1);

        VBox vBox3 = new VBox();
        vBox3.setPadding(new Insets(10, 10, 10, 10));
        vBox3.setSpacing(10);


        VBox vBox2 = new VBox();
        vBox2.setPadding(new Insets(10, 10, 10, 10));
        vBox2.setSpacing(10);

        VBox vBox4 = new VBox();
        vBox4.setPadding(new Insets(10, 10, 10, 10));
        vBox4.setSpacing(10);

        //Vbox5 till bilarna
        VBox vBox5 = new VBox();
        vBox5.setPadding(new Insets(10, 10, 10, 10));
        vBox5.setSpacing(10);
        Scene scene3 = new Scene(vBox5, 1000, 1000);

        HBox hBox3 = new HBox();
        hBox3.setPadding(new Insets(10, 10, 10, 10));
        hBox3.setSpacing(10);


        //Button
        returnScene.setOnAction(e -> {
            stage.setScene(scene1);
        });

        returnScene2.setOnAction(e -> {
            stage.setScene(scene1);
        });

        Menu memberMenu = new Menu("Medlemmar");
        MenuItem addMemberM = new MenuItem("Hantera medlemmar...");
        MenuItem searchMemberM = new MenuItem("Sök upp information om medlem");

        Menu carMenu = new Menu("Bilar");
        MenuItem listCarMenu = new MenuItem("Sök/lista bilar...");
        MenuItem bookCarMenu = new MenuItem("Boka en bil...");
        MenuItem terminateRental = new MenuItem("Avsluta en uthyrning...");

        Menu revenueMenu = new Menu("Intäkter");
        MenuItem revenue = new MenuItem("Summera intäkter...");
        revenueMenu.getItems().add(revenue);

        Label writerLabel = new Label("----------------------\nFilinläsning:\n");
        Button writerButton = new Button("Läs in medlemmar från fil");
        TextField writerText = new TextField();

        Label idLabel = new Label();
        TextField idText = new TextField();
        idText.setPromptText("ID");
        Button searchMButton = new Button("Sök");
        Label saveLabel = new Label();

        Label addNameLabel = new Label();
        TextField addNameText = new TextField();
        addNameText.setPromptText("Namn");
        TextField addName = new TextField();
        Label statusLabel = new Label();
        TextField statusText = new TextField();
        statusText.setPromptText("Standard eller Premium");
        Label addM = new Label();
        Button addButton = new Button("Spara medlem");
        Button deleteButton = new Button("Radera medlem");
        Button changeMButton = new Button("Ändra medlem");
        Label changeLabel = new Label("Vilken medlem vill du ändra på?");
        TextField changeName = new TextField();
        changeName.setPromptText("Namn");
        TextField changeStatus = new TextField();
        changeStatus.setPromptText("Standard eller Premium");
        TextField changeHistory = new TextField();
        changeHistory.setPromptText("Historik");
        Label deleteL = new Label("Vilken medlem vill du radera?");
        TextField deleteT = new TextField();

        //Knappar för Vehicle
        Button saveVButton = new Button("Spara fordon");
        Button deleteVButton = new Button("Radera fordon");
        Button changeVButton = new Button("Ändra fordon");

        //Label fordon

        //knappar buttons fordon
        saveVButton.setOnAction(e -> {});
        deleteVButton.setOnAction(e -> {});
        changeVButton.setOnAction(e -> {});

        addMemberM.setOnAction(e -> {
            stage.setScene(scene2);
            idLabel.setText("Ange uppgifter för en ny medlem:");
            idText.getText();
            nameInput.getText();
            statusLabel.setText(statusText.getText());

        });
        addButton.setOnAction(e -> {
            Member member3 = new Member();
            saveLabel.setText(String.valueOf(membershipService.addId(idText, member3)));
            member3.setId(Integer.parseInt(idText.getText()));
            member3.setName(nameInput.getText());
            member3.setStatus(statusText.getText());
            member3.setHistory(historyInput.getText());
            memberRegistry.add(member3);
            String added = "Du har nu lagt till medlem " + idText.getText() + ", "+ nameInput.getText() +", " +statusText.getText();
            addM.setText(added);
            idText.clear();
            nameInput.clear();
            statusText.clear();
            historyInput.clear();
        });

        deleteButton.setOnAction(e -> {
            Member memberDel = membershipService.searchMemberList(deleteT.getText());
            membershipService.delMember(memberDel);
            alertBox.display("Radera medlem", "Medlem raderad", memberDel);
        });

        writerButton.setOnAction(e -> {
            table.refresh();
        });

        searchMemberM.setOnAction(e -> {
            borderPane.setCenter(vBox3);
            addNameLabel.setText("Skriv in namnet på medlem du vill söka efter:");
            String s = addNameText.getText();
            System.out.println(s);
        });
        searchMButton.setOnAction(e -> {
            Member searchedMember = membershipService.searchMemberList(addNameText.getText());
            if(searchedMember == null){
                String s = "Medlemmen finns inte";
                saveLabel.setText(s);
            } else {
                String found = "Hittat medlemmen: " + searchedMember.getId() + ", "+ searchedMember.getName() + ", "
                        + searchedMember.getStatus() + ", " + searchedMember.getHistory();
                saveLabel.setText(found);
                addNameText.clear();
            }
        });

        changeMButton.setOnAction(e -> {
            Member member = new Member();
            int valdRad = table.getSelectionModel().getSelectedCells().get(0).getRow();
            member = table.getItems().get(valdRad);
            alertBox.change("Ändra medlem", "Fyll i ändringar på medlem", member, idInput, nameInput, changeStatus, changeHistory, table);
            idInput.getText();
            nameInput.getText();
            changeStatus.getText();
            changeHistory.getText();
        });

        listCarMenu.setOnAction(e -> {
            stage.setScene(scene3);
        });
        Button revenueButton = new Button("Visa intäkter");
        revenue.setOnAction(e -> {
            borderPane.setCenter(revenueButton);
        });
        revenueButton.setOnAction(e -> {rentalService.sum();});

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(memberMenu, carMenu, revenueMenu);
        memberMenu.getItems().addAll(addMemberM, searchMemberM);
        carMenu.getItems().addAll(listCarMenu, bookCarMenu, terminateRental);

        borderPane.setCenter(headLabel);
        borderPane.setTop(menuBar);

        //Översta Hbox i tableView
        hbox.getChildren().addAll(idInput, nameInput, statusInput, historyInput);

        //Lägga till/ta bort medlemmar/lista tabell
        hBox2.getChildren().addAll(idText, nameInput, statusText, historyInput, addButton);

        vbox1.getChildren().addAll(writerLabel, writerButton, writerText, table, returnScene
                , idLabel, hBox2, searchMButton, saveLabel, addM, changeLabel, changeMButton, deleteL, deleteT, deleteButton);

        //sökningen medlemmar
        vBox3.getChildren().addAll(addNameLabel, addNameText, searchMButton, saveLabel);
        //Ändra medlem
//        vBox2.getChildren().addAll(changeLabel, changeNameL, changeName, changeStatusL
//                , changeStatus, changeHistoryL, changeHistory, changeMButton);

        //Bilars lista, meny
        hBox3.getChildren().addAll(brandInput, modelInput, loanableInput, batteryInput);
        vBox5.getChildren().addAll(returnScene2, vehicleTable);

        borderPane.getChildren().addAll();

        scene1.getStylesheets().add("MenuColors.css");
        stage.setScene(scene1);
        stage.show();


    }

    private Inventory getInventory() {
        Inventory inventory = new Inventory();

        inventory.addVehicle(new Car("BMW", "z4", true, "Elektrisk bil", "Ja", "Automat"));
        inventory.addVehicle(new Car("Volvo", "v90", true, "Familjebil", "Ja", "Automat"));
        inventory.addVehicle(new Car("Tesla", "Model X", true, "Elektrisk bil", "Ja", "Automat"));
        inventory.addVehicle(new Bike("Kawasaki", "i3", true, "7-växlar", "Finns cykelkorg"));
        inventory.addVehicle(new Bike("Turbo", "v8", true, "5-växlar", "Ingen cykelkorg"));
        return inventory;
    }
    private MemberRegistry getMemberRegistry() {
        MemberRegistry memberRegistry = new MemberRegistry();
        memberRegistry.add(new Member(2, "Nora", "Standard", "Ingen historik"));
        return memberRegistry;
    }
}
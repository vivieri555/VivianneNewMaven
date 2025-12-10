package File;

import Member.*;
import Rental.Inventory;
import Vehicle.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.io.*;

public class FileService {
    MemberRegistry memberRegistry = new MemberRegistry();
    TextField writerText = new TextField();
    String memberFile = "members.csv";
    String vehicleFile = "vehicles.csv";

    // private Marshaller marshaller;
    //  private JAXBContext context;
    public FileService() {
    }

    /*public FileService() throws JAXBException {
        context = JAXBContext.newInstance(MemberRegistry.class, Member.class,
            Inventory.class, Vehicle.class, Car.class, Bike.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    public Inventory readXmlVehicle () throws JAXBException {
        //ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Inventory read = (Inventory) unmarshaller.unmarshal(new File("vehicleFile.xml"));
        return read;
    } */


    public ObservableList<Member> readMembers() {
        ObservableList<Member> members = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(memberFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Member member = new Member(Integer.parseInt(values[0]), values[1], values[2], values[3]);
                members.add(member);
            }
        } catch (NumberFormatException e) {
            TextField error = new TextField("Felaktigt nummer format");
            error.getText();
        } catch (IOException eFile) {
            writerText.setText("Fel vid utskrift");
        }
        return members;
    }

    public void writeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(memberFile))) {
            writer.write(memberRegistry.getMembers().toString());
            writerText.setText("Filen har sparats " + memberFile);
        } catch (IOException eFile) {
            writerText.setText("Gick inte att spara");
        }
    }

    public ObservableList<Vehicle> readVehicles() {
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(vehicleFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Vehicle vehicle = new Vehicle(values[0], values[1], Boolean.parseBoolean(values[2]), values[3], values[4], values[5]);
                vehicles.add(vehicle);
            }
        } catch (IOException error) {
            writerText.setText("Problem att läsa in");
        }
        return vehicles;
    }
}



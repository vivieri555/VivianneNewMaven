package Other;

import Member.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    Button closeButton = new Button("Ok");
    Stage stage2;
    MembershipService membershipService;
    Button changeMember = new Button("Annan knapp?");

    Label labelName = new Label();
    Label labelId = new Label();
    Label labelStatus = new Label();
    Label labelHistory = new Label();
    TextField changeID = new TextField();
    TextField changeName = new TextField();
    TextField changeStatus = new TextField();
    TextField changeHistory = new TextField();

    public AlertBox(MembershipService membershipService) {this.membershipService = membershipService;}

    public void display (String title, String message, Member member) {
        Stage stage2 = new Stage();
        stage2.setTitle(title);
        stage2.initModality(Modality.APPLICATION_MODAL);
        stage2.setMinWidth(250);
        Label label = new Label(message);
        closeButton.setOnAction(e -> { stage2.close(); } );

        VBox layout = new VBox();
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage2.setScene(scene);
        stage2.showAndWait();
    }
    public void change (String title, String message, Member member, TextField idInput,
                        TextField nameInput, TextField changeStatus, TextField changeHistory, TableView<Member> table) {
        Stage stage3 = new Stage();
        stage3.setTitle(title);
        stage3.initModality(Modality.APPLICATION_MODAL);
        stage3.setMinWidth(250);
        Label label2 = new Label(message);
        labelName.setText("Ändra namn");

        idInput.setText(String.valueOf(member.getId()));
        nameInput.setText(member.getName());
        changeStatus.setText(member.getStatus());
        changeHistory.setText(member.getHistory());

//        member.setId(membershipService.addId(idInput, member));
//        member.setName(nameInput.getText());
//        member.setStatus(changeStatus.getText());
//        member.setHistory(changeHistory.getText());



        closeButton.setOnAction(e -> {
            membershipService.change(member, idInput, nameInput, changeStatus, changeHistory);
            table.refresh();
            stage3.close(); } );
        VBox layout2 = new VBox();
        layout2.getChildren().addAll(label2, idInput, nameInput, changeStatus, changeHistory, closeButton);
        layout2.setAlignment(Pos.CENTER);

        Scene scene2 = new Scene(layout2);
        stage3.setScene(scene2);
        stage3.showAndWait();
    }
}


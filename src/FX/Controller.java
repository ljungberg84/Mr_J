package FX;

import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Account;
import model.Program;
import parsers.*;

public class Controller {

    @FXML
    ListView listview;
    @FXML
    Button button;
    @FXML
    TextField search;
    @FXML
    AnchorPane anchorPane;
    @FXML
    ToggleButton Netflix, HBO, Viaplay, SVT, Showtime;
    @FXML
    ScrollPane scrollPane;
    @FXML
    Menu accounts;
    @FXML
    MenuItem netflixAccount;


    Program p = new Program();


    public void initialize(){
        ServiceParser hboParser = new HboParser();
        ServiceParser netflixParser = new NetflixParser();
        ServiceParser viaplayParser = new ViaplayParser();
        //ServiceParser showtime = new ShowtimeParser();
        ServiceParser svtParser = new SVTPlayParser();

        //p.addService("hbo", hboParser);
        p.addService("netflix", netflixParser);
        //p.addService("Svt play", svtParser);
        //p.addService("viaplay", viaplayParser);
        //program.addService(showtime);
        //program.startSearch();
        listview.setItems(p.getHits());
        p.startLogin();
        p.getHits().addListener((ListChangeListener<MovieInfo>) c -> listview.refresh());


    }


    @FXML
    public void search(){
        Thread searchThread = new Thread(()->p.startSearch(search.getText()));
        searchThread.start();

    }



    @FXML
    private void printText() {
        System.out.println(search.getText());
    }


    @FXML
    public void popup(ActionEvent actionEvent){
        Stage popupWindow=new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Netflix account");
        Label label1= new Label("Please enter your login details:");
        Label label2 = new Label("Username:");
        TextField userNameField = new TextField ();
        userNameField.setMaxWidth(180);
        Label label3 = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(180);

        Button button1= new Button("Save");
        button1.setOnAction(e -> {
            userNameField.getText();
            passwordField.getText();
            popupWindow.close();
        });

        VBox layout= new VBox(10);
        layout.getChildren().addAll(label2, userNameField, label3, passwordField, label1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 200);
        popupWindow.setScene(scene1);
        popupWindow.showAndWait();

    }
}


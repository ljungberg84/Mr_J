package fx;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.MovieCell;
import model.Program;
import model.Service;
import parsers.*;

import java.sql.SQLOutput;

public class Controller {

    Program p = Program.getInstance();

    @FXML
    ListView<MovieInfo> listView;
    @FXML
    Button searchButton;
    @FXML
    TextField searchField;
    @FXML
    AnchorPane anchorPane;
    //@FXML
    //ScrollPane scrollPane;

    @FXML
    ImageView imageView;
    @FXML
    MenuItem Netflix;
    @FXML
    MenuItem HBO;
    @FXML
    MenuItem Viaplay;





    public void initialize(){
        ServiceHandler hboParser = new HboService();
        ServiceHandler netflixParser = new NetflixService();
        ServiceHandler viaplayParser = new ViaplayService();
        //ServiceHandler showtime = new ShowtimeParser();
        ServiceHandler svtParser = new SvtService();

        p.addService("Hbo", hboParser);
        p.addService("Netflix", netflixParser);
        p.addService("SVT play", svtParser);
        p.addService("Viaplay", viaplayParser);
        //program.addService(showtime);
        //program.startSearch();
        listView.setItems(p.getHits());
        //-------------------------------------------
        p.getHits().addListener(new ListChangeListener<MovieInfo>() {
            @Override
            public void onChanged(Change<? extends MovieInfo> c) {
                listView.refresh();
            }
        });
        //-------------------------------------------
        listView.setCellFactory(new Callback<ListView<MovieInfo>, ListCell<MovieInfo>>() {
            @Override
            public ListCell call(ListView param) {
               return new MovieCell();
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MovieInfo>() {
            @Override
            public void changed(ObservableValue<? extends MovieInfo> observable, MovieInfo oldValue, MovieInfo newValue) {
                //System.out.println("listview select imtem listener: " + newValue.getUrl() + newValue.toString());
                String service = newValue.getSource().substring(0, newValue.getSource().length() - 4);
                System.out.println("service key: " + service);
                p.getServices().get(service).playMovie(newValue);
            }
        });
        p.startLogin();
    }

    @FXML
    public void search(){
        //p.getHits().clear();
        //searchButton.setDisable(true);
        p.startSearch(searchField.getText());
        p.getHits().clear();
    }

    @FXML
    private void printText() {
        System.out.println(searchField.getText());
    }

    @FXML
    public void accountButtonHandler(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(Netflix)) {
            popup(p.getServices().get("Netflix"));
        } else if(actionEvent.getSource().equals(HBO)) {
            popup(p.getServices().get("Hbo"));
        } else if(actionEvent.getSource().equals(Viaplay)) {
            popup(p.getServices().get("Viaplay"));
        }

    }

    public void popup(Service service){
        Stage popupWindow=new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle(service.toString());
        Label label1= new Label("Please enter your " + service + " login details:");
        Label label2 = new Label("Username:");
        TextField userNameField = new TextField ();
        userNameField.setMaxWidth(180);
        Label label3 = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(180);

        Button button1= new Button("Save");
        button1.setOnAction(e -> {
            saveAccount(service, userNameField.getText(), passwordField.getText());
            popupWindow.close();
        });
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, label2, userNameField, label3, passwordField, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 200);
        popupWindow.setScene(scene1);
        popupWindow.showAndWait();
    }

    private void saveAccount(Service service, String username, String password) {
        service.getAccount().setUserName(username);
        service.getAccount().setPassword(password);
        System.out.println(service + " login details saved.");
        p.startLogin();

    }

    public void setStage(){

    }
}

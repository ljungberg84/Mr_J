package FX;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import model.Program;
import parsers.*;

public class Controller {

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

    Program p = new Program();


    public void initialize(){
        ServiceParser hboParser = new HboParser();
        ServiceParser netflixParser = new NetflixParser();
        ServiceParser viaplayParser = new ViaplayParser();
        //ServiceParser showtime = new ShowtimeParser();

        p.addService("hbo", hboParser);
        p.addService("netflix", netflixParser);
        p.addService("viaplay", viaplayParser);
        //program.addService(showtime);
        //program.start();
    }

    @FXML
    public void buttonOnAction(){
        try {button.setOnAction(event -> printText()
        );
        }catch (NullPointerException n){
            System.out.println("Error");
        }

    }


    @FXML
    public void search(){
        p.start(search.getText());
    }



    @FXML
    private void printText() {
        System.out.println(search.getText());
    }

}

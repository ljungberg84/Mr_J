package FX;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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

    Program p = new Program();


    public void initialize(){
        //ServiceHandler hboParser = new HboParser();
        ServiceHandler netflixParser = new NetflixHandler();
        //ServiceHandler viaplayParser = new ViaplayParser();
        //ServiceHandler showtime = new ShowtimeParser();
        ServiceHandler svtParser = new SVTPlayHandler();

        //p.addService("hbo", hboParser);
        p.addService("netflix", netflixParser);
        p.addService("Svt play", svtParser);
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

}

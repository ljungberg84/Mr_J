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
        ServiceParser hboParser = new HboParser();
        ServiceParser netflixParser = new NetflixParser();
        ServiceParser viaplayParser = new ViaplayParser();
        //ServiceParser showtime = new ShowtimeParser();

        p.addService("hbo", hboParser);
        p.addService("netflix", netflixParser);
        p.addService("viaplay", viaplayParser);
        //program.addService(showtime);
        //program.start();
        listview.setItems(p.getHits());
        p.getHits().addListener(new ListChangeListener<MovieInfo>() {
            @Override
            public void onChanged(Change<? extends MovieInfo> c) {
                listview.refresh();
            }
        });
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
        Thread searchThread = new Thread(()->p.start(search.getText()));
        searchThread.start();

    }



    @FXML
    private void printText() {
        System.out.println(search.getText());
    }

}

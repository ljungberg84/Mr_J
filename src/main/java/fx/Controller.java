package fx;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.MovieCell;
import model.Program;
import parsers.*;



public class Controller {

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

    Program p = new Program();


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

    public void setStage(){
      
    }
}

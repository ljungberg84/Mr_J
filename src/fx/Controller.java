package fx;

import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.MovieCell;
import model.Program;
import parsers.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Controller {

    @FXML
    ListView listview;
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
        //ServiceHandler hboParser = new HboParser();
        ServiceHandler netflixParser = new NetflixService();
        //ServiceHandler viaplayParser = new ViaplayParser();
        //ServiceHandler showtime = new ShowtimeParser();
        //ServiceHandler svtParser = new SVTPlayHandler();

        //p.addService("hbo", hboParser);
        p.addService("netflix", netflixParser);
        //p.addService("Svt play", svtParser);
        //p.addService("viaplay", viaplayParser);
        //program.addService(showtime);
        //program.startSearch();
        listview.setItems(Program.getHits());
        listview.setCellFactory(new Callback<ListView<MovieInfo>, ListCell<MovieInfo>>() {
            @Override
            public ListCell call(ListView param) {
                return new MovieCell();
            }
        });
        p.startLogin();

        //listview.setItems(Program.getHits());
//        //--------------------------------------------------------
//        p.getHits().addListener(new ListChangeListener<MovieInfo>() {
//            @Override
//            public void onChanged(Change<? extends MovieInfo> c) {
//
//                //Image image = new Image("url:QXafzn2eLmaC9kHfnuuKgHQ8xXxh_MNKeT0WxzglNQkVt4wnuEFM7UQVjRxrS8fKhB_iy5MAEZhAwv3E5QKXUi1_TRT4j5H9NnbZ5ZbtOcj57iUA683gdtgO7ZCszpVUq_KLBg.webp");
//                String imgUrl = Program.getHits().get(0).getImagePath();
//
//                //----------------------------------------------
//                //working with jpg in working directory
//                //Image image = new Image("file:test.webp");
//                Image image = new Image(imgUrl);
//                imageView.setImage(image);
//                listview.refresh();
//                //----------------------------------------------
//            }
//        });//----------------------------------------------------
    }


    @FXML
    public void search(){
        Thread searchThread = new Thread(()->p.startSearch(searchField.getText()));
        searchThread.start();
    }



    @FXML
    private void printText() {
        System.out.println(searchField.getText());
    }

//    private Image downloadWithTask(String url) {
//        Task<Image> task = new Task<Image>() {
//            @Override
//            protected Image call() throws Exception {
//                Image image = new Image(url, false);
//                //throw new Exception();
//                return image;
//            }
//        };
//    }
}

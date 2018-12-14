package fx;

import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import model.Program;
import org.omg.CORBA.IMP_LIMIT;
import parsers.*;

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
        listview.setItems(p.getHits());
        p.startLogin();
        p.getHits().addListener(new ListChangeListener<MovieInfo>() {
            @Override
            public void onChanged(Change<? extends MovieInfo> c) {
                if(Program.getHits().get(0) != null){
                    String imgUrl = Program.getHits().get(0).getImagePath();
                    Image image = new Image(imgUrl);
                    System.out.println(imgUrl);
                }
                listview.refresh();
                //imageView = new ImageView(image);
//                Image image = d;
//                imageView.setImage(image);
//                //imageView.isPreserveRatio();

            }
        });
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

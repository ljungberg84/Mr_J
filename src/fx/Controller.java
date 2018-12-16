package fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        //ServiceHandler svtParser = new SVTPlayHandler();

        p.addService("Hbo", hboParser);
        p.addService("Netflix", netflixParser);
        //p.addService("Svt play", svtParser);
        p.addService("Viaplay", viaplayParser);
        //program.addService(showtime);
        //program.startSearch();
        listView.setItems(p.getHits());
        listView.setCellFactory(new Callback<ListView<MovieInfo>, ListCell<MovieInfo>>() {
            @Override
            public ListCell call(ListView param) {
               return new MovieCell();
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MovieInfo>() {
            @Override
            public void changed(ObservableValue<? extends MovieInfo> observable, MovieInfo oldValue, MovieInfo newValue) {
                System.out.println("listview select imtem listener: " + newValue.getUrl() + newValue.toString());
                String service = newValue.getSource().substring(0, newValue.getSource().length() - 4);
                System.out.println("service key: " + service);
                p.getServices().get(service).playMovie(newValue);
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
        System.out.println("deleting hits");
        p.getHits().clear();
        System.out.println("hits count: " + p.getHits().size());
        //Thread searchThread = new Thread(()->p.startSearch(searchField.getText()));
        //searchThread.start();
        p.startSearch(searchField.getText());
    }

//    public void goToPlayMovie(MovieInfo movie){
//        String service = movie.getSource().substring(0, movie.getSource().length() - 3);
//        p.getServices().get(service)
//    }



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

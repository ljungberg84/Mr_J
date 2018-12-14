package fx;

import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
        listview.setItems(p.getHits());
        p.startLogin();
        p.getHits().addListener(new ListChangeListener<MovieInfo>() {
            @Override
            public void onChanged(Change<? extends MovieInfo> c) {

                //Image image = new Image("url:QXafzn2eLmaC9kHfnuuKgHQ8xXxh_MNKeT0WxzglNQkVt4wnuEFM7UQVjRxrS8fKhB_iy5MAEZhAwv3E5QKXUi1_TRT4j5H9NnbZ5ZbtOcj57iUA683gdtgO7ZCszpVUq_KLBg.webp");
                String imgUrl = Program.getHits().get(0).getImagePath();

                //----------------------------------------------
                //working with jpg in working directory
                //Image image = new Image("file:test.webp");
                Image image = new Image(imgUrl);
                imageView.setImage(image);
                listview.refresh();
                //----------------------------------------------

//                String url = Program.getHits().get(0).getImagePath();
//                int count = 1;
//                BufferedImage saveImage = null;
//                URL imgUrl = null;
//                try{
//                    imgUrl = new URL(url);
//                    System.out.println(url);
//                    System.out.println(imgUrl);
//                    saveImage = ImageIO.read(imgUrl);
//                    if(saveImage != null){
//                        System.out.println("saveimage not null");
//                    }else{
//                        System.out.println("saveimage is null");
//                    }
//                    ImageIO.write(saveImage, "png",new File("img_" + count + ".png"));
//
//                }catch(MalformedURLException e){
//                    e.printStackTrace();
//                }catch(IOException e){
//                    e.printStackTrace();
//                }



//                for(MovieInfo movie: Program.getHits()){
//                    if(movie != null){
//                        try{
//                            imgUrl = new URL(movie.getImagePath());
//                            saveImage = ImageIO.read(imgUrl);
//                            ImageIO.write(saveImage, "png",new File("img_" + count + ".png"));
//
//                        }catch(MalformedURLException e){
//                            e.printStackTrace();
//                        }catch(IOException e){
//                            e.printStackTrace();
//                        }
//                    }
//                    Image image = new Image("img_1.png");
//                    imageView = new ImageView(image);
//
//                }

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

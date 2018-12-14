package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import parsers.MovieInfo;

public class MovieCell extends ListCell<MovieInfo> {
    VBox vbox = new VBox();
    ImageView imageView = new ImageView();
    //Image image;
    Label titleLabel = new Label();
    Label infoLabel = new Label();
    Label sourceLabel = new Label();
    //Label sourceLabel = new Label();
    Button button = new Button();


    public MovieCell(){
        super();
        vbox.getChildren().addAll(imageView, titleLabel, infoLabel, sourceLabel);

        titleLabel.setMinWidth(225);
        titleLabel.alignmentProperty().setValue(Pos.CENTER);
        titleLabel.setFont(new Font(30.0));

        infoLabel.setMinWidth(225);
        infoLabel.setFont(new Font(20));
        infoLabel.alignmentProperty().setValue(Pos.CENTER);

        sourceLabel.setMinWidth(225);
        sourceLabel.alignmentProperty().setValue(Pos.CENTER);
        sourceLabel.setFont(new Font(30.0));



        imageView.setFitWidth(225);
        imageView.setPreserveRatio(true);
        VBox.setVgrow(imageView, Priority.ALWAYS);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("button clicked");
            }
        });
    }

    @Override
    protected void updateItem(MovieInfo item, boolean empty) {
        super.updateItem(item, empty);
        if(empty || item == null){
            //titleLabel.setText("ingen hittades");
            //setText("test");
        }else{
            String imgURl = item.getImagePath();
            if(imgURl.substring(imgURl.length() - 4, imgURl.length()).equalsIgnoreCase("webp")){
                imageView.setImage(new Image("file:imagenotavailable.png"));
            }else{
                imageView.setImage(new Image(item.getImagePath()));
            }
            titleLabel.setText(item.getTitle());
            infoLabel.setText("Found on:");
            sourceLabel.setText(item.getSource());

            setGraphic(vbox);
            System.out.println(item.getUrl());
            System.out.println(item.getSource());
            System.out.println("test " + item.getTitle());
        }

        //do something to check if working image
        //imageView.setImage(new Image(item.getImagePath()));
        //titleLabel.setText(item.getTitle());
        //button.setText(item.getSource());


    }
}

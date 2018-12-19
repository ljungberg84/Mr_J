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

    private VBox vbox = new VBox();
    private ImageView imageView = new ImageView();
    private ImageView sourceImageView = new ImageView();
    private Label titleLabel = new Label();
    private Label infoLabel = new Label();
    private Label sourceLabel = new Label();

    public MovieCell(){
        super();
        vbox.getChildren().addAll(imageView, titleLabel, infoLabel, sourceImageView);
        vbox.alignmentProperty().setValue(Pos.CENTER);

        titleLabel.setMinWidth(225);
        titleLabel.alignmentProperty().setValue(Pos.CENTER);
        titleLabel.setFont(new Font(25.0));

        infoLabel.setMinWidth(225);
        infoLabel.setFont(new Font(20));
        infoLabel.alignmentProperty().setValue(Pos.CENTER);

        sourceLabel.setMinWidth(225);
        sourceLabel.alignmentProperty().setValue(Pos.CENTER);
        sourceLabel.setFont(new Font(30.0));

        sourceImageView.setFitWidth(120);
        sourceImageView.setPreserveRatio(true);

        imageView.setFitWidth(225);
        imageView.setPreserveRatio(true);
        VBox.setVgrow(imageView, Priority.ALWAYS);
    }

    @Override
    protected void updateItem(MovieInfo item, boolean empty) {
        super.updateItem(item, empty);
        if(empty || item == null){
            //System.out.println("empty");
        }else{
            //System.out.println("match");
            String imgURl = item.getImagePath();
            if(imgURl == null || imgURl.substring(imgURl.length() - 4, imgURl.length()).equalsIgnoreCase("webp")){
                imageView.setImage(new Image("file:src//main//resources//imagenotavailable.png"));
            }else{
                imageView.setImage(new Image(item.getImagePath()));
            }
            titleLabel.setText(item.getTitle());
            infoLabel.setText("Found on:");
            sourceImageView.setImage(new Image("file:src//main//resources//" + item.getSource()));

            setGraphic(vbox);

        }
    }
}

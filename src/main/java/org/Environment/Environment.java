package org.Environment;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.InterFace.IEnvironment;
import org.Player.Player;

public class Environment  extends Pane implements IEnvironment {
    HBox playheader;
    HBox playareabox;
    public Label time;
    public Label playercount;
    public Label playerleft;
    public Label playerskilled;
    public Label timelbl;
    public Label totalplayerslbl;
    public Label playersleftlbl;
    public Label playerskilledlbl;
    public Button Stop;
    public Button Start;
    public Button Restart;
    public Button Exit;

    StackPane stackpane=new StackPane();
    public Environment(){
        playheader=new HBox();

         Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        setMaxHeight(bounds.getHeight());
        setMinHeight(bounds.getHeight());
        setMaxWidth(bounds.getWidth());
        setMinWidth(bounds.getWidth());
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
        playheader.setPrefWidth(bounds.getWidth());
        playheader.setPrefHeight(80);

        playareabox=new HBox();
        playareabox.setTranslateY(80);
        playareabox.setPrefWidth(bounds.getWidth());
        playareabox.setPrefHeight(bounds.getHeight()-80);
        stackpane.getChildren().addAll(playheader,playareabox);




    }
    public void SetColor(String color){
        String value="-fx-background-color:"+color+";";
        playareabox.setStyle(value);
        //setStyle(value);

    }
    public void SetImage(String image){
        //image="https://www.google.com/images/srpr/logo3w.png";
        image="file:///"+image;
        setStyle("-fx-background-image: url(" + image + "); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

    }

    @Override
    public Pane GetYellowEnvironment() {
        SetColor("Yellow");
        getChildren().addAll(stackpane);
        return this;
    }

    @Override
    public Pane GetBlackEnvironment() {
        SetColor("Black");
        getChildren().addAll(stackpane);
        return this;
    }

    @Override
    public Pane GetPinkEnvironment() {
        SetColor("Pink");
        getChildren().addAll(stackpane);
        return this;
    }

    @Override
    public Pane GetImageEnvironment(String environmentvalue) {
        SetImage(environmentvalue);
        getChildren().addAll(stackpane);
        return this;
    }
}

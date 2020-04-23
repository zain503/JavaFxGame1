package org.Movement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class test  {
    public StringProperty Name = null;
    public IntegerProperty Health ;
    public StringProperty Movement = null;
    public StringProperty Shape = null;
    public StringProperty Weapon = null;
    public Object Graphic=null;
    public static int positionx=0;
    public static int positiony=0;
    ProgressBar pi ;



    public test(String Name,String Movement,String Shape,String Weapon,int Health){
        this.Health = new SimpleIntegerProperty(Health);
        this.Name = new SimpleStringProperty(Name);
        this.Movement = new SimpleStringProperty(Movement);
        this.Shape = new SimpleStringProperty(Shape);
        this.Weapon = new SimpleStringProperty(Weapon);
        pi = new ProgressBar(1);
        positionx+=120;
        positiony+=90;
        if(Shape=="Circle") {
            //Circle circle = new Circle();
            //circle.setRadius(50);
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(120, 0, 0, 0));
            vbox.setSpacing(50);

            Text   text   = createText(Name);
            Circle circle = encircle(text);
            circle.setRadius(50);
            vbox.getChildren().addAll(pi);
            StackPane layout = new StackPane();
            layout.getChildren().addAll(
                    circle,text,vbox

            );

            Graphic=layout;
        }
        else if(Shape=="Rectangle"){
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(120, 0, 0, 0));
            vbox.setSpacing(50);
            Text   text   = createText(Name);
            Rectangle rectangle = enrectangle(text);
            rectangle.setHeight(100);
            rectangle.setWidth(100);
            vbox.getChildren().addAll(pi);
            StackPane layout = new StackPane();
            layout.getChildren().addAll(
                    rectangle,text,vbox

            );

            Graphic=layout;
        }
        else if(Shape=="Ellipse"){
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(120, 0, 0, 0));
            vbox.setSpacing(50);
            Text   text   = createText(Name);
            Ellipse ellipse = enellipse(text);
            ellipse.setRadiusX(100);
            ellipse.setRadiusY(50);
            vbox.getChildren().addAll(pi);
            StackPane layout = new StackPane();
            layout.getChildren().addAll(
                    ellipse,text,vbox

            );

            Graphic=layout;
        }


    }
    private Text createText(String string) {
        Text text = new Text(string);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setStyle(
                "-fx-font-family: \"Times New Roman\";" +
                        "-fx-font-style: italic;" +
                        "-fx-font-size: 18px;"
        );

        return text;
    }
    private Circle encircle(Text text) {
        Circle circle = new Circle();
        circle.setFill(Color.ORCHID);
        final double PADDING = 10;
        // circle.setRadius(getWidth(text) / 2 + PADDING);

        return circle;
    }
    private Rectangle enrectangle(Text text) {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.ORCHID);
        final double PADDING = 10;
        // circle.setRadius(getWidth(text) / 2 + PADDING);

        return rectangle;
    }
    private Ellipse enellipse(Text text) {
        Ellipse ellipse = new Ellipse();
        ellipse.setFill(Color.ORCHID);
        final double PADDING = 10;
        // circle.setRadius(getWidth(text) / 2 + PADDING);

        return ellipse;
    }
    private double getWidth(Text text) {
        new Scene(new Group(text));
        text.applyCss();

        return text.getLayoutBounds().getWidth();
    }
    public String getName() {
        return this.Name.get();
    }

    public void setName(String Name) {
        this.Name.set(Name);
    }
    public StringProperty NameProperty() { return this.Name; }

    public int getHealth() {
        return this.Health.get();
    }

    public void setHealth(int Health) {
        this.Health.set(Health);
    }
    public IntegerProperty HealthProperty() { return this.Health; }

    public String getshape() {
        return this.Shape.get();
    }

    public void setShape(String Shape) {
        this.Shape.set(Shape);
    }
    public StringProperty ShapeProperty() { return this.Shape; }


    public String getMovement() {
        return this.Movement.get();
    }

    public void setMovement(String Movement) {
        this.Movement.set(Movement);
    }
    public StringProperty MovementProperty() { return this.Movement; }

    public String getWeapon() {
        return this.Weapon.get();
    }

    public void setWeapon(String Weapon) {
        this.Weapon.set(Weapon);
    }
    public StringProperty WeaponProperty() { return this.Weapon; }
}

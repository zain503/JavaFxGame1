package org.InterFace;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public interface IMovement {
    public void MoveForword(double  rightPaddleDXvalue);
    public void MoveBackword(double  rightPaddleDXvalue);
    public void MoveUp(double  rightPaddleDYvalue);
    public void MoveDown(double  rightPaddleDYvalue);
    public void SetParams(ArrayList<StackPane> nodeslist, StackPane nodeuser, Label time);
    public void Starttimer();
    public void Stoptimer();
    public void ShotForword(Circle gunshot);
    public void ShotBackword(Circle gunshot);
}

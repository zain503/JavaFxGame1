package org.Movement;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import org.InterFace.IMovement;
import org.JavaFxGame.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Movement implements IMovement {
    public static ArrayList<StackPane> nodes=null;
    public long startTime = System.currentTimeMillis();
    public long elapsedMillis ;
    public StackPane node;
    // public List<Circle> gunshot=new ArrayList<Circle>();
    public Circle gunshot;
    public double gunshotx;
    public double gunshotoffsetx;
    public double gunshoty;
    public double leftPaddleY = 260;
    public double rightPaddleX = 260;

    public double gunShotposY = 0;
    public double gunShotposX = 0;
    public Label time;
    public double rightPaddleY = 260;
    public double leftPaddleDY;
    public double rightPaddleDY;
    public double rightPaddleDX;

    @Override
    public void MoveForword(double  rightPaddleDXvalue) {
    rightPaddleDX=rightPaddleDXvalue;
    }

    @Override
    public void MoveBackword(double  rightPaddleDXvalue) {
        rightPaddleDX=rightPaddleDXvalue;
    }

    @Override
    public void MoveUp(double  rightPaddleDYvalue) {
        rightPaddleDY=rightPaddleDYvalue;
    }

    @Override
    public void MoveDown(double  rightPaddleDYvalue) {
        rightPaddleDY=rightPaddleDYvalue;
    }

    @Override
    public void SetParams(ArrayList<StackPane> nodeslist, StackPane nodeuser,Label timemain) {
        nodes= nodeslist;
        node=nodeuser;
        time=timemain;
    }

    @Override
    public void Starttimer() {
        timer.start();
    }

    @Override
    public void Stoptimer() {
        timer.stop();
    }

    @Override
    public void ShotForword(Circle gunshots) {
        if(gunshot==null) {
            gunshot = gunshots;
            gunShotposX = 8;
        }
    }

    @Override
    public void ShotBackword(Circle gunshots) {
        if(gunshot==null) {
            gunshot = gunshots;
            gunShotposX = -8;
        }
    }


    public void checkBounds(StackPane block) {
        //   node=block;
        boolean collisionDetected = false;
        for (StackPane static_bloc : nodes) {
            if (static_bloc != block) {
                Shape temp=(Shape)static_bloc.getChildren().get(0);
                temp.setFill(Color.GREEN);

                Shape intersect = Shape.intersect((Shape)block.getChildren().get(0), (Shape)static_bloc.getChildren().get(0));
                if (intersect.getBoundsInLocal().getWidth() != -1) {
                    collisionDetected = true;
                    VBox vbox=(VBox) block.getChildren().get(2);
                    ProgressBar pi=(ProgressBar)vbox.getChildren().get(0);
                    double val=pi.getProgress();
                    val=val-0.1;
                    pi.setProgress(val);
                    vbox.getChildren().set(0,pi);
                    block.getChildren().set(2,vbox);
                    if(val<0){
                        Text playertext=(Text)block.getChildren().get(1);
                        playertext.setText(playertext.getText()+" Dead");
                        App.playerleft.setText(String.valueOf(nodes.size()));
                        int playerkilled=Integer.parseInt(App.playercount.getText())-nodes.size();
                        App.playerskilled.setText(String.valueOf(playerkilled));
                        nodes.remove(block);
                        if(nodes.size()==1){
                            Text temptext=(Text)nodes.get(0).getChildren().get(1);
                            Label winlbl=new Label(temptext.getText()+" Has Won");
                            winlbl.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 26));
                            App.pane.setVisible(true);
                            App.pane.getChildren().add(winlbl); //add imageView to stackPane
                            StackPane.setAlignment(winlbl, Pos.CENTER);
                            // App.GameMsg.setText(temptext.getText()+" Has Won");
                            //App.GameMsg.setVisible(true);
                        }

                        //block.setOnMousePressed(null);
                        //  block.setOnMouseDragged(null);
                    }
                }
            }
        }

        if (collisionDetected) {
            Shape temp=(Shape)block.getChildren().get(0);
            temp.setFill(Color.BLUE);
        } else {
            Shape temp=(Shape)block.getChildren().get(0);
            temp.setFill(Color.GREEN);
        }
    }


    public void checkGunFireBounds(Circle block) {
        //   node=block;
        boolean collisionDetected = false;
        for (StackPane static_bloc : nodes) {
            //if (static_bloc != block)
            {
                Shape temp=(Shape)static_bloc.getChildren().get(0);
                temp.setFill(Color.GREEN);

                Shape intersect = Shape.intersect((Shape)block, (Shape)static_bloc.getChildren().get(0));
                if (intersect.getBoundsInLocal().getWidth() != -1) {
                    collisionDetected = true;
                    VBox vbox=(VBox) static_bloc.getChildren().get(2);
                    ProgressBar pi=(ProgressBar)vbox.getChildren().get(0);
                    double val=pi.getProgress();
                    val=val-0.1;
                    pi.setProgress(val);
                    vbox.getChildren().set(0,pi);
                    static_bloc.getChildren().set(2,vbox);
                    if(val<0){
                        Text playertext=(Text)static_bloc.getChildren().get(1);
                        playertext.setText(playertext.getText()+" Dead");
                        App.playerleft.setText(String.valueOf(nodes.size()));
                        int playerkilled=Integer.parseInt(App.playercount.getText())-nodes.size();
                        App.playerskilled.setText(String.valueOf(playerkilled));
                        nodes.remove(block);
                        if(nodes.size()==1){
                            Text temptext=(Text)nodes.get(0).getChildren().get(1);
                            Label winlbl=new Label(temptext.getText()+" Has Won");
                            winlbl.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 26));
                            App.pane.setVisible(true);
                            App.pane.getChildren().add(winlbl); //add imageView to stackPane
                            StackPane.setAlignment(winlbl, Pos.CENTER);
                           // App.GameMsg.setText(temptext.getText()+" Has Won");
                            //App.GameMsg.setVisible(true);
                        }

                        //block.setOnMousePressed(null);
                        //  block.setOnMouseDragged(null);
                    }
                }
            }
        }


    }

     AnimationTimer timer = new AnimationTimer() {


        @Override
        public void handle(long now) {

            long elapsedMillis = System.currentTimeMillis() - startTime ;
            time.setText(Long.toString(elapsedMillis / 1000));
            if(gunshot!=null) {
                gunshot.setLayoutX(gunshot.getLayoutX() + gunShotposX);
                gunshot.setLayoutY(gunshot.getLayoutY());
                checkGunFireBounds(gunshot);
                if(gunshot!=null) {
                    if (gunshot.getLayoutX() > Screen.getPrimary().getVisualBounds().getWidth()) {
                        gunShotposX = 0;
                        gunshot.setLayoutX(gunshotx);
                        gunshot.setLayoutY(gunshoty);
                        gunshot = null;

                    }
                }
                if(gunshot!=null) {
                    if (gunshot.getLayoutX() < 0) {
                        gunShotposX = 0;
                        gunshot.setLayoutX(gunshotx);
                        gunshot.setLayoutY(gunshoty);
                        gunshot = null;
                    }
                }
            }
                leftPaddleY += leftPaddleDY;
                rightPaddleY += rightPaddleDY;
                rightPaddleX += rightPaddleDX;

            if (leftPaddleY < 0) {
                leftPaddleY = 0;
            }
            if (rightPaddleY < 0) {
                rightPaddleY = 0;
            }
            if(rightPaddleX<0){
                rightPaddleX=0;
            }
            if(rightPaddleX>1200){
                rightPaddleX=1200;
            }
            if(rightPaddleY>580){
                rightPaddleY=580;
            }
            if(rightPaddleY<=70){
                rightPaddleY=70;
            }
            for(int i=0;i<nodes.size();i++){
                Random r = new Random();
                int val=r.nextInt(4);
                ////up
                if(val==0){
                    if(nodes.get(i).getLayoutY()-6>=70 && nodes.get(i).getLayoutY()-6<=580) {
                        nodes.get(i).relocate(nodes.get(i).getLayoutX(), nodes.get(i).getLayoutY() - 6);
                    }
                }
                ///down
                else if(val==1){
                    if(nodes.get(i).getLayoutY()+6>=70 && nodes.get(i).getLayoutY()+6<=580) {
                        nodes.get(i).relocate(nodes.get(i).getLayoutX(), nodes.get(i).getLayoutY() + 6);
                    }
                }
                ///right
                else if(val==2){
                    if(nodes.get(i).getLayoutX()+6>=0 && nodes.get(i).getLayoutX()+6<=1200) {
                        nodes.get(i).relocate(nodes.get(i).getLayoutX() + 6, nodes.get(i).getLayoutY());
                    }
                }
                else if(val==3){
                    if(nodes.get(i).getLayoutX()-6>=0 && nodes.get(i).getLayoutX()-6<=1200) {
                        nodes.get(i).relocate(nodes.get(i).getLayoutX() - 6, nodes.get(i).getLayoutY());
                    }

                }
                checkBounds(nodes.get(i));

            }
            //  leftPaddle.setY(leftPaddleY);
            // node.setY(rightPaddleY);
            // node.setX(rightPaddleX);

            node.relocate(rightPaddleX,rightPaddleY);
        }

    };
}

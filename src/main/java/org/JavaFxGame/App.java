package org.JavaFxGame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.Environment.Environment;
import org.InterFace.IEnvironment;
import org.InterFace.IMovement;
import org.InterFace.IPlayer;
import org.InterFace.IWeapon;
import org.Movement.Movement;
import org.Player.Player;
import org.Weapon.Weapon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    VBox vbox;
    //////////////game playing parameters
    String environmenttype;
    String enviromentvalue;
    StackPane user=new StackPane();
    public Label time;
    public String userweapon;
    public static Label playercount;
    public static Label playerleft;
    public static Label playerskilled;
    public Label timelbl;
    public Label totalplayerslbl;
    public Label playersleftlbl;
    public Label playerskilledlbl;
    public Button Stop;
    public Button Start;
    public Button Restart;
    public Button Exit;
    Player rowData;
    public Pane Environmentpane;
    public static Label GameMsg;
    IMovement movementplugin;
    IEnvironment environmentplugin;
    IPlayer playerplugin;
    IWeapon Weaponplugin;
    public static StackPane pane=new StackPane();
    @Override
    public void start(Stage stage) throws Exception {
        loadPlugin();
        BorderPane BPane = new BorderPane();
        Group grp1=new Group();
        ObservableList<String> Environmentlist = FXCollections.observableArrayList("Image","Simple Plain");
        ObservableList<String> Shapelist = FXCollections.observableArrayList("Circle","Rectangle","Ellipse");
        ObservableList<String> Movementlist = FXCollections.observableArrayList("Random","Move By User");
        ObservableList<String> Weaponlist = FXCollections.observableArrayList("Only Collision","Gun");
        ComboBox plugin1 = new ComboBox(Environmentlist);
        //ComboBox plugin2 = new ComboBox();

        Button    Startbtn=new Button("Start Game");
        GameMsg=new Label();
        GameMsg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 26));
        Startbtn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 26));

        Label    pluginlbl1=new Label("Environment");
      //  Label    pluginlbl2=new Label("Plugin 2");
        Label    Guilbl=new Label("GUI");
        Guilbl.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 26));
        pluginlbl1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        //pluginlbl2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        vbox=new VBox(Guilbl);
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(50);
        HBox hbox = new HBox(pluginlbl1,plugin1,Startbtn);
        hbox.setPadding(new Insets(100, 120, 200, 220));
        hbox.setSpacing(50);
        grp1.getChildren().add(vbox);
        grp1.getChildren().add(hbox);
        Label    playerlbl=new Label("Players");
        Label    movementlbl=new Label("Movement");
        Label    weaponlbl=new Label("Weapon");
        Label    graphlbl=new Label("Graphism");
        ComboBox Movement = new ComboBox(Movementlist);
        ComboBox Weapon = new ComboBox(Weaponlist);
        ComboBox Graphism = new ComboBox(Shapelist);
        Button   addplayer=new Button("Add Player");
        addplayer.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Button   Removeplayer=new Button("Remove");
        Removeplayer.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        playerlbl.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 26));
        movementlbl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        weaponlbl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        graphlbl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Group grp2=new Group();
        Group grp3=new Group();
        // HBox hbox2 = new HBox(Startbtn);
        VBox vbox1=new VBox(playerlbl);
        vbox1.setPadding(new Insets(15, 12, 15, 12));
        vbox1.setSpacing(50);
        HBox hbox1 = new HBox(movementlbl,Movement,weaponlbl, Weapon,graphlbl,Graphism,addplayer,Removeplayer);
        hbox1.setPadding(new Insets(100, 120, 200, 220));
        hbox1.setSpacing(50);
        grp3.getChildren().add(vbox1);
        grp3.getChildren().add(hbox1);

        TableView<Player> playertable=new TableView<>();
        TableColumn<Player,String> namecol=new TableColumn<>("Player Name");
        TableColumn<Player,String> Movementcol=new TableColumn("Player Movement");
        TableColumn<Player,String> shapecol=new TableColumn("Player Shape");
        TableColumn<Player,String> weaponcol=new TableColumn("Player Weapon");
        TableColumn<Player,String> healthcol=new TableColumn("Player Health");

        namecol.setCellValueFactory(data->data.getValue().NameProperty());
        Movementcol.setCellValueFactory(data->data.getValue().MovementProperty());
        shapecol.setCellValueFactory(data->data.getValue().ShapeProperty());
        weaponcol.setCellValueFactory(data->data.getValue().WeaponProperty());
        healthcol.setCellValueFactory(data->data.getValue().HealthProperty().asString());
        playertable.getColumns().add(namecol);
        playertable.getColumns().add(Movementcol);
        playertable.getColumns().add(shapecol);
        playertable.getColumns().add(weaponcol);
        playertable.getColumns().add(healthcol);
        VBox vbox3 = new VBox(playertable);
        playertable.setMaxSize(900,200);
        playertable.setMinSize(900,200);
        grp2.getChildren().add(vbox3);
        //grp2.getChildren().add(hbox2);
        // Group grp3=new Group();
        //grp3.getChildren().add(hbox2);
        // BPane.setRight(grp3);
        BPane.setTop(grp1);
        BPane.setCenter(grp2);
        BPane.setBottom(grp3);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        final var scene = new Scene(new StackPane(BPane), bounds.getWidth(), bounds.getHeight());
        stage.setScene(scene);
        stage.show();
        plugin1.setOnAction((e) -> {
            environmenttype=plugin1.getSelectionModel().getSelectedItem().toString();
            if(plugin1.getSelectionModel().getSelectedItem()=="Image")
            {
                Button    imgbtn=new Button("Select Image");
                imgbtn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 26));
                VBox vboximg=new VBox(imgbtn);
                vboximg.setPadding(new Insets(150, 10, 0, 50));
                vboximg.setSpacing(50);

                Stage stage1 = new Stage();
                stage1.setTitle("Select Image for Environment");
                stage1.setScene(new Scene(new StackPane(vboximg), 450, 450));
                stage1.show();

                imgbtn.setOnAction(( e1) -> {
                    FileChooser ImageChooser = new FileChooser();
                    ImageChooser.setTitle("Open Resource File");
                    File file =ImageChooser.showOpenDialog(stage);
                    enviromentvalue=file.getAbsolutePath();
                    System.out.println(enviromentvalue);
                });

            }
            else{
                ToggleGroup tg = new ToggleGroup();
                VBox vbox = new VBox();
                vbox.setSpacing(5);
                vbox.setPadding(new Insets(100, 120, 200, 220));
                vbox.setSpacing(50);
                RadioButton rb1 = new RadioButton("black");
                rb1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                rb1.setToggleGroup(tg);
                rb1.setSelected(true);
                rb1.setUserData("Black");
                RadioButton rb2 = new RadioButton("yellow");
                rb2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                rb2.setToggleGroup(tg);
                rb2.setSelected(true);
                rb2.setUserData("Yellow");
                RadioButton rb3 = new RadioButton("pink");
                rb3.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                rb3.setUserData("Pink");
                rb3.setToggleGroup(tg);

                tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
                    public void changed(ObservableValue<? extends Toggle> ov,
                                        Toggle old_toggle, Toggle new_toggle) {
                        if (tg.getSelectedToggle() != null) {
                            enviromentvalue=tg.getSelectedToggle().getUserData().toString();
                        }
                    }
                });

                vbox.getChildren().add(rb1);
                vbox.getChildren().add(rb2);
                vbox.getChildren().add(rb3);

                Stage stage1 = new Stage();
                stage1.setTitle("Select Color for Environment");
                stage1.setScene(new Scene(new StackPane(vbox), 450, 450));
                stage1.show();

            }

        });
        addplayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Class<?> PlayerpluginClass = null;
                try {
                    PlayerpluginClass = getClass().getClassLoader().loadClass("org.Player.Player");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    playerplugin =
                            (IPlayer) PlayerpluginClass.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                String player="player "+(playertable.getItems().size()+1);


                playertable.getItems().add( playerplugin.Player(player, Movement.getSelectionModel().getSelectedItem().toString(),Graphism.getSelectionModel().getSelectedItem().toString(),Weapon.getSelectionModel().getSelectedItem().toString(),10));
                playertable.refresh();
            }
        });
        playertable.setRowFactory(tv -> {
            TableRow<Player> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    rowData = row.getItem();
                   // System.out.println("Double click on: "+rowData.getName());
                }
            });
            return row ;
        });
        Removeplayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                playertable.getItems().remove(rowData);
            }
        });
        Startbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // environment=new Environment();
                if(enviromentvalue.equals("Black")) {
                    Environmentpane=environmentplugin.GetBlackEnvironment();
                }
                else if(enviromentvalue.equals("Yellow")) {
                    Environmentpane=environmentplugin.GetYellowEnvironment();
                }
                else if(enviromentvalue.equals("Pink")) {
                    Environmentpane=environmentplugin.GetPinkEnvironment();
                }
                else  {
                    Environmentpane=environmentplugin.GetImageEnvironment(enviromentvalue);
                }
                StackPane node=null;
                StackPane sp=null;
                GridPane grid = new GridPane();
                ArrayList<StackPane> objlist=new ArrayList<>();
                int col=0;
                int row=100;
                grid.setHgap(90);
                grid.setVgap(50);
                Circle gunshot=null;
                for(int i=0;i<playertable.getItems().size();i++){
                    playerplugin=  playertable.getItems().get(i);

                    if(playertable.getItems().get(i).getshape().equals("Circle"))
                    {
                        sp= (StackPane) playerplugin.GetCircle(playertable.getItems().get(i).getName(),playertable.getItems().get(i).getMovement(),playertable.getItems().get(i).getshape(),playertable.getItems().get(i).getWeapon(),1);
                    }
                    else if(playertable.getItems().get(i).getshape().equals("Ellipse"))
                    {
                        sp= (StackPane) playerplugin.GetEllipse(playertable.getItems().get(i).getName(),playertable.getItems().get(i).getMovement(),playertable.getItems().get(i).getshape(),playertable.getItems().get(i).getWeapon(),1);

                    }
                    else if(playertable.getItems().get(i).getshape().equals("Rectangle"))
                    {
                        sp= (StackPane) playerplugin.GetRectangle(playertable.getItems().get(i).getName(),playertable.getItems().get(i).getMovement(),playertable.getItems().get(i).getshape(),playertable.getItems().get(i).getWeapon(),1);

                    }
                    objlist.add(sp);
//                    if(i==playertable.getItems().size()-1){
//                        environment.getChildren().add(sp);
//                        break;
//                    }
                    if((i+1)%4==0){

                        row=row+200;
                        col=0;
                        //grid.add(sp,col+1,row);
                        sp.relocate(col,row);
                        Environmentpane.getChildren().add(sp);
                        col=col+400;
                    }
                    else{
                        //grid.add(sp,col+1,row);
                        sp.relocate(col,row);

                        Environmentpane.getChildren().add(sp);

                        col=col+400;
                    }


                }
                Environmentpane.getChildren().add(GameMsg);
                GameMsg.setVisible(false);
                StackPane headersp=(StackPane)Environmentpane.getChildren().get(0);
                HBox headerbox=(HBox)headersp.getChildren().get(0);
                headerbox.setPadding(new Insets(10, 12, 20, 22));
                headerbox.setSpacing(50);
                timelbl=new Label("Time elapsed");
                time=new Label("00:00");

                totalplayerslbl=new Label("Total Players");
                playercount=new Label("0");

                playersleftlbl=new Label("Players Left");
                playerleft=new Label("0");

                playerskilledlbl=new Label("Players Killed");
                playerskilled=new Label("0");

                Stop=new Button("Stop");
                Start=new Button("Start");
                Restart=new Button("Restart");
                Exit=new Button("Exit");
                Stop.setFocusTraversable(false);
                Start.setFocusTraversable(false);
                Restart.setFocusTraversable(false);
                Exit.setFocusTraversable(false);

                timelbl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                time.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                totalplayerslbl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                playercount.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                playersleftlbl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                playerleft.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                playerskilledlbl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                playerskilled.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                Stop.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
                Start.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
                Restart.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
                Exit.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
                headerbox.getChildren().addAll(timelbl,time,totalplayerslbl,playercount,playersleftlbl,playerleft,playerskilledlbl,playerskilled,Start,Stop,Restart,Exit);
                headersp.getChildren().set(0,headerbox);
                playercount.setText(String.valueOf(playertable.getItems().size()));

                BPane.getChildren().clear();


                pane.setPrefSize(bounds.getWidth(), bounds.getHeight());
                pane.setBackground(new Background(new BackgroundFill(Color.web("#fc0341"), CornerRadii.EMPTY, Insets.EMPTY)));
                pane.setVisible(false);
                pane.getChildren().add(Restart);
                pane.getChildren().add(Exit);
                pane.setAlignment(Restart,Pos.CENTER_LEFT);
                pane.setAlignment(Exit,Pos.CENTER_RIGHT);
                //Environmentpane.getChildren().clear();
                Environmentpane.getChildren().add(pane);

               // Environmentpane.getChildren().get(Environmentpane.getChildren().size()-1).setVisible(false);
                Scene  scene=new Scene(Environmentpane, bounds.getWidth(), bounds.getHeight());

                for(int i=0;i<playertable.getItems().size();i++)
                {
                    Player player=playertable.getItems().get(i);
                    if(player.getMovement().equals("Move By User")){
                        user=objlist.get(i);
                        userweapon=playertable.getItems().get(i).getWeapon();
                    }
                }
                movementplugin=new Movement();
                movementplugin.SetParams(objlist,user,time);
                scene.setOnKeyPressed(keyPressed);
                scene.setOnKeyReleased(keyReleased);
                movementplugin.Starttimer();
                stage.setScene(scene);
                stage.show();
                Stop.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //movement.timer.stop();
                        movementplugin.Stoptimer();
                    }
                });
                Start.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        movementplugin.Starttimer();
                    }
                });
                Exit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Platform.exit();
                    }
                });
                Restart.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                       App.launch();
                    }
                });
            }
        });


    }
    public EventHandler<KeyEvent> keyReleased = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case W:
                case S:
                   // leftPaddleDY = 0;
                    break;
                case UP:
                    movementplugin.MoveUp(0);
                    break;
                case DOWN:
                    movementplugin.MoveDown(0);
                    break;
                case LEFT:
                    movementplugin.MoveForword(0);
                    break;
                case RIGHT:
                    movementplugin.MoveForword(0);
                    break;

            }
        }

    };
    public EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {

            System.out.println("Pressed");
            switch (event.getCode()) {
                case W:

                    break;
                case F:
                    if(userweapon.equals("Gun")) {
                        Circle forwordgunshot = new Circle();
                        forwordgunshot.setRadius(10);
                        forwordgunshot.setFill(Color.RED);
                        forwordgunshot.setLayoutX(user.getLayoutX());
                        forwordgunshot.setLayoutY(user.getLayoutY());
                        Environmentpane.getChildren().add(forwordgunshot);
                        movementplugin.ShotForword(forwordgunshot);
                    }
                    break;
                case S:
                    if(userweapon.equals("Gun")) {
                        Circle backwordgunshot = new Circle();
                        backwordgunshot.setRadius(10);
                        backwordgunshot.setFill(Color.RED);
                        backwordgunshot.setLayoutX(user.getLayoutX());
                        backwordgunshot.setLayoutY(user.getLayoutY());
                        Environmentpane.getChildren().add(backwordgunshot);
                        movementplugin.ShotBackword(backwordgunshot);
                    }
                    break;
                case UP:
                    movementplugin.MoveUp(-6);
                    break;
                case DOWN:
                    movementplugin.MoveDown(6);
                    break;
                case LEFT:
                    movementplugin.MoveForword(-6);
                    break;
                case RIGHT:
                    movementplugin.MoveForword(6);
                    break;



            }
        }

        private void Moveforword(Circle circle) {

        }
    };
    private void loadPlugin()
            throws Exception {
        Class<?> MovementpluginClass = getClass().getClassLoader()
                .loadClass("org.Movement.Movement");
         movementplugin =
                (IMovement) MovementpluginClass.newInstance();
        Class<?> EnvironmentpluginClass = getClass().getClassLoader()
                .loadClass("org.Environment.Environment");
        environmentplugin =
                (IEnvironment) EnvironmentpluginClass.newInstance();
        Class<?> WeaponpluginClass = getClass().getClassLoader()
                .loadClass("org.Weapon.Weapon");
        Weaponplugin =
                (IWeapon) WeaponpluginClass.newInstance();



    }
    public static void main(String[] args) {
        launch();
    }

}
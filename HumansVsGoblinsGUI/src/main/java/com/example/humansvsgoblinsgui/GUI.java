package com.example.humansvsgoblinsgui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    // Give this game's instance of Map
    // actually probably just give this an instance of game and take everything from there
    // start with a basic map and see how things look, then add more functionality
    // after all functionality added, trim console output, probably.

    @Override
    public void start(Stage stage) throws IOException {
        Game game = new Game();
        Map map = game.getMap();

        BorderPane root = new BorderPane();

        GridPane gameView = new GridPane();
        gameView.setAlignment(Pos.CENTER);
        updateMap(gameView, map);
        gameView.setGridLinesVisible(true);

        root.setTop(new Text("Goblins VS Humans"));
        root.setCenter(gameView);
        root.setBottom(new Text("Status"));
        root.setRight(new Text("Combat Info"));
        //stack pane on each tile node
        Scene scene = new Scene(root, 800, 800, Color.GRAY);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.UP))
                    game.playerMove("N");
                else if (keyEvent.getCode().equals(KeyCode.DOWN))
                    game.playerMove("S");
                else if (keyEvent.getCode().equals(KeyCode.RIGHT))
                    game.playerMove("E");
                else if (keyEvent.getCode().equals(KeyCode.LEFT))
                    game.playerMove("W");
                updateMap(gameView, map);
                if (!game.checkForGoblins()) {
                    HBox end = new HBox();
                    Text poopoo = new Text("YOU WIN :)");
                    end.getChildren().add(poopoo);
                    Scene endScene = new Scene(end, scene.getHeight(), scene.getWidth(), Color.GRAY);
                    stage.setScene(endScene);
                }
            }
        });

        stage.setTitle("Layout Testing");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void updateMap(GridPane gameView, Map map){
        int size = map.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                StackPane pane = new StackPane(new Rectangle(48,48, Color.AQUAMARINE));
                if (map.getMap()[i][j] instanceof Goblin) { //entity
                    pane.getChildren().add(new Text("G"));
                } else if (map.getMap()[i][j] instanceof Human)
                    pane.getChildren().add(new Text("H"));
                else
                    pane.getChildren().add(new Text(map.getMap()[i][j].toString()));
                gameView.add(pane,j,i);
            }
        }
    }
}
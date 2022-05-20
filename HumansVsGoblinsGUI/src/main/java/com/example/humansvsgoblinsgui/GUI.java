package com.example.humansvsgoblinsgui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GUI extends Application {
    private Game game;
    private String gameFont = "Sylfaen";
    private ImagePattern background;
    private ImagePattern tile;
    private ImagePattern borderSide;
    private ImagePattern borderTop;
    private ImagePattern borderCorner;
    private final int height = 600;
    private final int width = 900;

    // Give this game's instance of Map
    // actually probably just give this an instance of game and take everything from there
    // start with a basic map and see how things look, then add more functionality
    // after all functionality added, trim console output, probably.

    @Override
    public void start(Stage stage) throws IOException {
        game = new Game();
        Map map = game.getMap();
        tile = new ImagePattern(new Image(
                Files.newInputStream(Paths.get("src/main/resources/tiles/DungeonTile1.jpg"))));
        background = new ImagePattern(new Image(
                Files.newInputStream(Paths.get("src/main/resources/tiles/BrickPattern.jpg"))));
        borderSide = new ImagePattern(new Image(
                Files.newInputStream(Paths.get("src/main/resources/tiles/TileVWall.jpg"))));
        borderTop = new ImagePattern(new Image(
                Files.newInputStream(Paths.get("src/main/resources/tiles/TileHWall.jpg"))));
        borderCorner = new ImagePattern(new Image(
                Files.newInputStream(Paths.get("src/main/resources/tiles/TileXwall.jpg"))));

        Scene scene = createMainScene(map);

        stage.setTitle("Goblins Vs Humans");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Updates map view in center of screen according to current map state
     * @param gameView
     * @param map
     */
    public void updateMap(GridPane gameView, Map map){
        int size = map.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                StackPane pane = new StackPane();
                if (map.getMap()[i][j] instanceof Character) {
                    Character symbol = (Character) map.getMap()[i][j];
                    if (symbol == Map.corner)
                        pane.getChildren().add(new Rectangle(48, 48, borderCorner));
                    else if (symbol == Map.borderSide)
                        pane.getChildren().add(new Rectangle(48, 48, borderSide));
                    else if (symbol == Map.borderTop)
                        pane.getChildren().add(new Rectangle(48, 48, borderTop));
                    else
                        pane.getChildren().add(new Rectangle(48, 48, tile));
                } else
                    pane.getChildren().add(new Rectangle(48, 48, tile));
                if (map.getMap()[i][j] instanceof Goblin) { //entity
                    Text goblin = new Text("\uD83D\uDC7A");
                    goblin.setFont(Font.font("", 30));
                    goblin.setFill(Color.GREENYELLOW);
                    pane.getChildren().add(goblin);
                } else if (map.getMap()[i][j] instanceof Human) {
                    Text human = new Text("\uD83E\uDDD9\u200D♂️");
                    human.setFont(Font.font("", 30));
                    human.setFill(Color.CORNFLOWERBLUE);
                    pane.getChildren().add(human);
                }
                gameView.add(pane,j,i);
            }
        }
    }

    private Scene createMainScene(Map map) {
        BorderPane root = new BorderPane();

        GridPane gameView = new GridPane();
        gameView.setAlignment(Pos.CENTER);
        updateMap(gameView, map);
        gameView.setGridLinesVisible(true);

        VBox cbLogContainer = new VBox();
        VBox cbLogBufferTop = new VBox();
        HBox cbLogContainerMid = new HBox();
        VBox combatLog = new VBox();
        HBox cblRightBuffer = new HBox();
        cbLogContainerMid.getChildren().addAll(combatLog, cblRightBuffer);
        VBox cbLogBufferBot = new VBox();
        cbLogBufferTop.setMinHeight(100);
        cbLogBufferBot.setMinHeight(100);
        cblRightBuffer.setMinWidth(25);
        cbLogContainer.getChildren().addAll(cbLogBufferTop,cbLogContainerMid,cbLogBufferBot);
        updateCBLog(combatLog, map.getPlayer());
        combatLog.setPrefWidth(300);
        combatLog.setMinHeight(150);
        combatLog.setAlignment(Pos.CENTER);
        combatLog.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

        Text title = new Text("Goblins Vs Humans");
        title.setFont(Font.font(gameFont,60));
        title.setFill(Color.GOLD);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(0.5);

        root.setTop(title);
        root.setAlignment(title, Pos.CENTER);
        root.setMargin(title, new Insets(25,0,0,0));
        root.setCenter(gameView);
        root.setRight(cbLogContainer);

        Scene scene = new Scene(root, width, height);
        root.setBackground(new Background(new BackgroundFill(background,null,null)));

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
                updateCBLog(combatLog, map.getPlayer());
                if (!game.checkForGoblins()) {
                    ((Stage) scene.getWindow()).setScene(createEndScene(true));
                } else if (map.getPlayer().getHealth() <= 0) {
                    ((Stage) scene.getWindow()).setScene(createEndScene(false));
                }
            }
        });

        return scene;
    }

    /**
     * Updates combat log on right side of screen with relevant information to that turn
     * @param view
     * @param player
     */
    public void updateCBLog(VBox view, Human player) {
        view.getChildren().clear();
        Text logTitle = new Text("COMBAT LOG");
        view.getChildren().add(logTitle);
        view.getChildren().add(new Text("Health: " + player.getHealth()));
        view.getChildren().add(new Text("Strength: " + player.getStrength()));
        view.getChildren().add(new Text(game.getStatus()));
        view.getChildren().stream().forEach(n-> {
            ((Text)n).setFont(new Font(gameFont, 15));
            ((Text)n).setFill(Color.GOLD);
            ((Text)n).setTextAlignment(TextAlignment.LEFT);
        });
        logTitle.setFont(Font.font(gameFont, FontWeight.BLACK, 30));
    }

    /**
     * Creates end game scene. Take win/loss as true or false.
     * @param win
     * @return
     */
    private Scene createEndScene(boolean win) {
        Scene endScene;
        try {
            if (win)
                endScene = new Scene(FXMLLoader.load(getClass().getResource("endScreen.fxml")),width, height);
            else
                endScene = new Scene(FXMLLoader.load(getClass().getResource("endScreenFail.fxml")),width, height);
        } catch (IOException e) {
            System.err.println("Endscreen file not found.");
            HBox end = new HBox();
            Text poopoo = new Text("YOU WIN :)");
            end.getChildren().add(poopoo);
            endScene = new Scene(end, width, height);
        }
        return endScene;
    }
}
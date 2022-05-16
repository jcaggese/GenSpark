package com.example.humansvsgoblinsgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EndScreenController {
    @FXML
    private Button button;

    @FXML
    protected void playAgain() throws IOException {
        System.out.println("Button clicked :)");
        GUI gui = new GUI();
        Stage parent = (Stage) button.getScene().getWindow();
        gui.start(parent);
    }
}
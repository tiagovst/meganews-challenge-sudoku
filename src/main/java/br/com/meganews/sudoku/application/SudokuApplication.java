package br.com.meganews.sudoku.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/meganews/sudoku/view/main-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
module br.com.meganews.sudokumeganews {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens br.com.meganews.sudoku.view to javafx.fxml;
    exports br.com.meganews.sudoku.controller;
    opens br.com.meganews.sudoku.controller to javafx.fxml;
    exports br.com.meganews.sudoku.application;
    opens br.com.meganews.sudoku.application to javafx.fxml;
}
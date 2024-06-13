package br.com.meganews.sudoku.controller;

import br.com.meganews.sudoku.model.Sudoku;
import br.com.meganews.sudoku.service.SudokuService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.Objects;

public class SudokuController {
    private Sudoku sudoku;
    private SudokuService sudokuService;

    private TextField[][] textFields = new TextField[9][9];

    @FXML
    private Button btnShowInitialBoard;

    @FXML
    private Button btnSolveBoard;

    @FXML
    private Button btnClearBoard;

    @FXML
    private Label lblResult;

    @FXML
    private GridPane Grid;

    @FXML
    public void initialize() {
        sudoku = new Sudoku();

        // Colocando os TextFields dentro da grid e fazendo as configs. necessárias.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField textField = new TextField();

                textField.setPrefWidth(50);
                textField.setPrefHeight(50);
                textField.setMaxHeight(50);
                textField.setMaxWidth(50);

                textField.getStyleClass().add("text-field-style");

                GridPane.setVgrow(textField, Priority.NEVER);
                GridPane.setHgrow(textField, Priority.NEVER);


                // Lambda para limitar TextField a 1 dígito.
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d?")) { // verifica se contém 1 dígito numérico
                        textField.setText(oldValue);
                    }
                });

                // Evento ao clicar no TextField.
                int finalRow = row;
                int finalCol = col;
                textField.setOnMouseClicked(mouseEvent -> onTextFieldClick(finalRow, finalCol));

                Grid.add(textField, row, col);
                textFields[row][col] = textField;

            }
        }

        // Aplicando os estilos à grid.
        Grid.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                "/br/com/meganews/sudoku/view/style/style.css")
        ).toExternalForm());

    }

    // Ação do botão "Limpar".
    @FXML
    private void onBtnClearBoard() {
        lblResult.setLayoutX(405);
        lblResult.setText("Preencha a matriz para obter o resultado:");

        clearBoard();
    }

    // Ação do botão "Resolver matriz".
    @FXML
    private void onBtnSolveBoardClick() {
        int[][] tempMatrix = new int[9][9];

        if (fillTempMatrix(tempMatrix)) {

            sudoku.setMatrix(tempMatrix);
            sudokuService = new SudokuService(sudoku);

            if (sudokuService.solveMatrix()) {
                lblResult.setLayoutX(545);
                lblResult.setText("Resultado da matriz:");
                showMatrix(sudoku.getMatrix());

                btnShowInitialBoard.setDisable(false);

            }
        } else {
            Utils.alert(Alert.AlertType.ERROR, "Erro", "Insira uma matriz válida!");
            btnShowInitialBoard.setDisable(true);
        }
    }

    // Ação do botão "Mostrar matriz inicial".
    @FXML
    private void onBtnShowInitialBoard() {

        // Se tiver informação nos fields, acessa a matriz inicial e mostra na tela.
        if (checkTextFields()) {
            int[][] initialMatrix = sudokuService.getInitialMatrix();

            clearBoard();

            lblResult.setLayoutX(600);
            lblResult.setText("Matriz inicial:");

            showMatrix(initialMatrix);
        } else {
            Utils.alert(Alert.AlertType.ERROR, "Erro", "Matriz inicial não informada.");
        }
    }

    // Método para limpar a matriz e pintar de branco.
    private void clearBoard() {
        paintTextFieldWhite();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                setTextInTextField(row, col, "");
            }
        }
    }

    // Metódo para setar como branco o background do TextField.
    private void paintTextFieldWhite() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                textFields[row][col].setStyle("-fx-background-color: white;");
            }
        }
    }

    // Método para iterar pela matriz e mostrar os dados na tela.
    private void showMatrix(int[][] matrix) {
        paintTextFieldWhite();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (matrix[row][col] == 0) {
                    setTextInTextField(row, col, "");
                } else {
                    setTextInTextField(row, col, String.valueOf(matrix[row][col]));
                }
            }
        }
    }

    private boolean fillTempMatrix(int[][] matrix) {
        // Se tiver informação nos fields, passa-os para um vetor temporário.
        if (checkTextFields()) {

            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {

                    if (textFields[row][col].getText().isEmpty()) {
                        matrix[row][col] = 0;
                    } else {
                        matrix[row][col] = Integer.parseInt(textFields[row][col].getText());
                    }
                }
            }
            return Utils.checkBoard(matrix);
        } else {
            Utils.alert(Alert.AlertType.ERROR, "Erro!", "Preencha a matriz para obter o resultado!");
            return false;
        }
    }

    // Método para verificar se os TextFields estão vazios. (False = vazios)
    private boolean checkTextFields() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!textFields[row][col].getText().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }


    // Método para definir texto em um TextField específico
    private void setTextInTextField(int row, int col, String text) {
        if (row >= 0 && row < 9 && col >= 0 && col < 9) {
            textFields[row][col].setText(text);
        }
    }

    // Método para mudar a cor do bloco clicado.
    private void onTextFieldClick(int row, int col) {

        // Limpando a matriz ao clicar em outro bloco.
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j].setStyle("-fx-background-color: white;");
            }
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        // Mudando a cor do bloco.
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                textFields[i][j].setStyle("-fx-background-color: lightblue;");
            }
        }
    }

}
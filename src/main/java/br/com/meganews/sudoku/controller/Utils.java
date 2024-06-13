package br.com.meganews.sudoku.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Utils {
    // Método para mostrar um alerta ao usuário.
    protected static void alert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }

    protected static boolean checkBoard(int[][] matrix) {
        for (int row = 0; row <= 8; row ++) {
            for (int col = 0; col <= 8; col ++) {

                int currentNumber = matrix[row][col];
                if (currentNumber == 0) {
                    continue; // Não verifica se for 0.
                }

                // Verificação na linha e coluna
                for (int i = 0; i < 9; i++) {
                    if (i != col) { // Não verifica se estiver no próprio campo da coluna.
                        if (matrix[row][i] == currentNumber) {
                            return false;
                        }
                    }

                    if (i != row) { // Não verifica se estiver no próprio campo da linha.
                        if (matrix[i][col] == currentNumber) {
                            return false;
                        }
                    }
                }

                // Encontrando o início do bloco.
                int blockInitialPositionX = (row / 3) * 3;
                int blockInitialPositionY = (col / 3) * 3;

                // Verificação no bloco.
                for (int i = blockInitialPositionX; i < blockInitialPositionX + 3; i++) {
                    for (int j = blockInitialPositionY; j < blockInitialPositionY + 3; j++) {
                        if (i != row || j != col) { // Não verifica o própria campo do bloco.
                            if (matrix[i][j] == currentNumber) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}

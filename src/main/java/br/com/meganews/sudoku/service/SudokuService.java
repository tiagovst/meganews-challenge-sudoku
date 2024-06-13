package br.com.meganews.sudoku.service;

import br.com.meganews.sudoku.model.Sudoku;

public class SudokuService {
    private final Sudoku sudoku;
    private final int[][] solvedMatrix;
    private final int[][] initialMatrix;

    public SudokuService(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.initialMatrix = sudoku.getMatrix();
        this.solvedMatrix = copyMatrix(sudoku.getMatrix());
    }

    // Copiando a matriz inicial para usar como referência na tela final.
    private int[][] copyMatrix(int[][] initialMatrix) {
        int[][] newMatrix = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(initialMatrix[i], 0, newMatrix[i], 0, 9);
        }
        return newMatrix;
    }

    // Método para fazer a comparação entre o número para preencher e os atuais na matriz.
    private boolean checkBoard(int row, int col, int number) {
        // Encontrando o início do bloco 3x3.
        int blockInitialPositionX = (row / 3) * 3;
        int blockInitialPositionY = (col / 3) * 3;

        // Verificação na linha e coluna
        for (int i = 0; i <= 8; i++) {
            if ((solvedMatrix[row][i] == number) || (solvedMatrix[i][col] == number)) {return false;}
        }

        // Verificação no bloco.
        for (int i = blockInitialPositionX; i < blockInitialPositionX + 3; i++) {
            for (int j = blockInitialPositionY; j < blockInitialPositionY + 3; j++) {
                if (solvedMatrix[i][j] == number) {return false;}
            }
        }

        return true;
    }

    // Método para resolver a matriz
    public boolean solveMatrix(){
        for (int row = 0; row <= 8; row ++) {
            for (int col = 0; col <= 8; col ++) {

                // Caso a posição atual seja vazia (0), tenta um número entre 1 e 9, baseado nas regras acima.
                if (solvedMatrix[row][col] == 0) {

                    for (int number = 1; number <= 9; number++) {

                        if (checkBoard(row, col, number)) {
                            solvedMatrix[row][col] = number;

                            if (solveMatrix()) {
                                sudoku.setMatrix(solvedMatrix);
                                return true;
                            }
                            solvedMatrix[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getInitialMatrix() {
        return initialMatrix;
    }

    public void printBoard() {
        for (int row = 0; row <= 8; row ++) {
            for (int col = 0; col <= 8; col ++) {
                System.out.print(initialMatrix[row][col] + " ");
            }
            System.out.println();
        }

        System.out.println("\nSolved:");

        for (int row = 0; row <= 8; row ++) {
            for (int col = 0; col <= 8; col ++) {
                System.out.print(solvedMatrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}

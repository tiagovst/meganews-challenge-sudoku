package br.com.meganews.sudoku.model;

public class Sudoku {
    private int[][] matrix = new int[9][9];

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}

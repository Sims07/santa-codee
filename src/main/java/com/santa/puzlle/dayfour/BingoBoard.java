package com.santa.puzlle.dayfour;

public class BingoBoard {

  public static final int BOARD_SIZE = 5;
  private BingoNumber[][] board = new BingoNumber[BOARD_SIZE][BOARD_SIZE];
  private int raw = 0;
  private int column = 0;
  private boolean won;

  public BingoBoard(int[][] inputValues) {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        board[i][j] = new BingoNumber(inputValues[i][j], false);
      }
    }
  }

  public BingoBoard() {}

  public void add(int value) {
    board[raw][column] = new BingoNumber(value, false);
    if (column == BOARD_SIZE - 1) {
      raw++;
    }
    column = (column + 1) % 5;
  }

  public void mark(int currentNumber) {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        if (board[i][j].value() == currentNumber) {
          board[i][j].marked(true);
        }
      }
    }
  }

  public boolean winner() {
    return lineComplete() || columnCompleted();
  }

  private boolean columnCompleted() {
    for (int i = 0; i < BOARD_SIZE; i++) {
      boolean columnCompleted = true;
      for (int j = 0; j < BOARD_SIZE && columnCompleted; j++) {
        columnCompleted = columnCompleted && board[j][i].marked();
      }
      if (columnCompleted) {
        return true;
      }
    }
    return false;
  }

  private boolean lineComplete() {
    for (int i = 0; i < BOARD_SIZE; i++) {
      boolean lineCompleted = true;
      for (int j = 0; j < BOARD_SIZE && lineCompleted; j++) {
        lineCompleted = lineCompleted && board[i][j].marked();
      }
      if (lineCompleted) {
        return true;
      }
    }
    return false;
  }

  public int unmarkedSum() {
    int sum = 0;
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        System.out.println(board[i][j].value());
        sum += board[i][j].marked() ? 0 : board[i][j].value();
      }
    }
    return sum;
  }

  public void won() {
    this.won = true;
  }

  public boolean hasWon() {
    return this.won;
  }
}

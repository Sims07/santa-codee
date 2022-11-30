package com.santa.puzlle.dayfour;

import java.util.ArrayList;
import java.util.List;

public class Bingo {

  private final BingoNumbers numbers;
  private final List<BingoBoard> boardList;
  private boolean winnerExist;
  private BingoBoard winnerBoard;
  private int lastNumberPlayed;

  public Bingo(BingoNumbers bingoNumbers) {
    this.numbers = bingoNumbers;
    this.boardList = new ArrayList<>();
  }

  public void play() {
    lastNumberPlayed = numbers.next();
    System.out.println("Play the number :" + lastNumberPlayed);
    boardList.forEach(
        board -> {
          board.mark(lastNumberPlayed);
          if (board.winner()) {
            winnerExist = true;
            winnerBoard = board;
          }
        });
  }

  public Bingo add(BingoBoard bingoBoard) {
    boardList.add(bingoBoard);
    return this;
  }

  public boolean winnerExist() {
    return this.winnerExist;
  }

  public int score() {
    return winnerBoard.unmarkedSum() * lastNumberPlayed;
  }
}

package com.santa.puzlle.y2021.dayfour;

import java.util.ArrayList;
import java.util.List;

public class BingoLastWin {

  private final BingoNumbers numbers;
  private final List<BingoBoard> boardList;
  private final List<BingoBoard> winnerBoards;
  private boolean lastWinnerExist;
  private int lastNumberPlayed;
  private BingoBoard lastWinnerBoard;

  public BingoLastWin(BingoNumbers bingoNumbers) {
    this.numbers = bingoNumbers;
    this.boardList = new ArrayList<>();
    this.winnerBoards = new ArrayList<>();
  }

  public void play() {
    lastNumberPlayed = numbers.next();
    System.out.println("Play the number :" + lastNumberPlayed);
    boardList.forEach(
        board -> {
          board.mark(lastNumberPlayed);
          if (!board.hasWon() && board.winner()) {
            board.won();
            winnerBoards.add(board);
            lastWinnerExist = winnerBoards.size() == boardList.size();
            if (lastWinnerExist) {
              lastWinnerBoard = board;
            }
          }
        });
  }

  public BingoLastWin add(BingoBoard bingoBoard) {
    boardList.add(bingoBoard);
    return this;
  }

  public boolean lastWinnerExist() {
    return this.lastWinnerExist;
  }

  public int score() {
    return lastWinnerBoard.unmarkedSum() * lastNumberPlayed;
  }
}

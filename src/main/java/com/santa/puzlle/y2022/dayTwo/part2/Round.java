package com.santa.puzlle.y2022.dayTwo.part2;

public record Round(Play yours, Play mine) {

  public static Round from(String roundStr) {
    final String[] playStr = roundStr.split(" ");
    return new Round(
        new Play(Shape.from(playStr[0]), playStr[0]), new Play(Shape.from(playStr[1]), playStr[1]));
  }

  public Play playWinningRound() {
    return new Play(
        switch (yours.shape()) {
          case ROCK -> Shape.PAPER;
          case SCISSORS -> Shape.ROCK;
          case PAPER -> Shape.SCISSORS;
        },
        mine().letterCode());
  }

  public int myScore() {
    int score = mine.shape().value;
    if (iWin()) {
      score += 6;
    } else if (equality()) {
      score += 3;
    }
    return score;
  }

  public Round playMyTurn() {
    if (playDraw()) {
      return new Round(yours, new Play(yours.shape(), mine().letterCode()));
    } else if (playWin()) {
      return new Round(yours, playWinningRound());
    } else {
      return new Round(yours, playLoosingRound());
    }
  }

  private Play playLoosingRound() {
    return new Play(
        switch (yours.shape()) {
          case ROCK -> Shape.SCISSORS;
          case SCISSORS -> Shape.PAPER;
          case PAPER -> Shape.ROCK;
        },
        mine().letterCode());
  }

  private boolean playWin() {
    return mine.letterCode().equals("Z");
  }

  private boolean playDraw() {
    return mine.letterCode().equals("Y");
  }

  private boolean equality() {
    return mine.shape() == yours.shape();
  }

  private boolean iWin() {
    return playWinningRound().shape() == mine.shape();
  }
}

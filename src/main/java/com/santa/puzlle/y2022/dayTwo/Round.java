package com.santa.puzlle.y2022.dayTwo;

public record Round(Play yours, Play mine) {

  public static Round from(String roundStr) {
    final String[] playStr = roundStr.split(" ");
    return new Round(new Play(Shape.from(playStr[0])), new Play(Shape.from(playStr[1])));
  }

  public Play playWinningRound() {
    return new Play(
        switch (yours.shape()) {
          case ROCK -> Shape.PAPER;
          case SCISSORS -> Shape.ROCK;
          case PAPER -> Shape.SCISSORS;
        });
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

  private boolean equality() {
    return mine.shape() == yours.shape();
  }

  private boolean iWin() {
    return playWinningRound().shape() == mine.shape();
  }
}

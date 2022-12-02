package com.santa.puzlle.y2022.dayTwo;

import java.util.List;

public class PuzzleDayTwo {

  public long play(List<String> roundList) {
    return roundList.stream().map(Round::from).mapToLong(Round::myScore).sum();
  }
}

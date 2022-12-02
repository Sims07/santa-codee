package com.santa.puzlle.y2022.dayTwo.part2;

import java.util.List;

public class PuzzleDayTwoPartTwo {

  public long play(List<String> roundList) {
    return roundList.stream()
        .map(Round::from)
        .map(Round::playMyTurn)
        .mapToLong(Round::myScore)
        .sum();
  }
}

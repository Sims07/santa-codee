package com.santa.puzlle.y2022.dayTwo.part2;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class PuzzleDayTwoPartTwoTest {

  @Test
  void givenAAgainstY_play_shouldReturn1plus3() {
    final Round round = new Round(new Play(Shape.ROCK, "A"), new Play(null, "Y"));
    final Round playMyTurn = round.playMyTurn();
    then(playMyTurn.myScore()).isEqualTo(4);
  }

  @Test
  void givenBAgainstX_play_shouldReturn1plus0() {
    final Round round = new Round(new Play(Shape.PAPER, "B"), new Play(null, "X"));
    final Round playMyTurn = round.playMyTurn();
    then(playMyTurn.myScore()).isEqualTo(1);
  }

  @Test
  void givenCAgainstZ_play_shouldReturn1plus6() {
    final Round round = new Round(new Play(Shape.SCISSORS, "C"), new Play(null, "Z"));
    final Round playMyTurn = round.playMyTurn();
    then(playMyTurn.myScore()).isEqualTo(7);
  }

  @Test
  void givenExample_play_shouldReturnScore8() throws IOException {
    Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/dayTwo/day2part2.txt"));
    final PuzzleDayTwoPartTwo puzzleDayTwo = new PuzzleDayTwoPartTwo();
    then(puzzleDayTwo.play(lines.toList())).isEqualTo(12794);
  }
}

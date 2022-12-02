package com.santa.puzlle.y2022.dayTwo;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class PuzzleDayTwoPartOneTest {

  @Test
  void givenScissorsAgainstScissors_play_shouldReturn3plus3() {
    final Round round = new Round(new Play(Shape.SCISSORS), new Play(Shape.SCISSORS));
    then(round.myScore()).isEqualTo(6);
  }

  @Test
  void givenPaperAgainstRock_play_shouldReturn1plus0() {
    final Round round = new Round(new Play(Shape.PAPER), new Play(Shape.ROCK));
    then(round.myScore()).isEqualTo(1);
  }

  @Test
  void givenRound_play_shouldReturnScore8() {
    final Round round = new Round(new Play(Shape.ROCK), new Play(Shape.PAPER));
    then(round.myScore()).isEqualTo(8);
  }

  @Test
  void givenRound2_play_shouldReturnScore8() {
    final PuzzleDayTwo puzzleDayTwo = new PuzzleDayTwo();
    then(puzzleDayTwo.play(List.of("A Y"))).isEqualTo(8);
  }

  @Test
  void givenExample_play_shouldReturnScore8() throws IOException {
    Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/dayTwo/day2part1.txt"));
    final PuzzleDayTwo puzzleDayTwo = new PuzzleDayTwo();
    then(puzzleDayTwo.play(lines.toList())).isEqualTo(12794);
  }
}

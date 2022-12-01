package com.santa.puzlle.y2022;

import static org.assertj.core.api.BDDAssertions.then;

import com.santa.puzlle.y2022.dayone.Elves;
import com.santa.puzlle.y2022.dayone.PuzzleDayOnePartOne;
import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class PuzzleDayOnePartTwoTest {

  @Test
  void givenMoreThanOneElfWith2foodItem_findMaxElfCalories_shouldReturnElfCalories() {
    final Elves elves =
        new PuzzleDayOnePartOne().load(Stream.of("1000", "2000", "", "1000", "", "3200", "", "10"));

    then(elves.topThreeTotalCalories()).isEqualTo(7200);
  }

  @Test
  void givenPuzzle_findMaxElfCalories_shouldReturnTheAnswer() throws IOException {
    final Elves elves =
        new PuzzleDayOnePartOne()
            .load(
                "/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/day1part2.txt");

    System.out.println(elves.topThreeTotalCalories());
  }
}

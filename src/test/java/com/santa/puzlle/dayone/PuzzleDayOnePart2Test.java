package com.santa.puzlle.dayone;

import java.io.IOException;
import java.util.List;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class PuzzleDayOnePart2Test {

  @Test
  void givenNoPreviousMeasurement_numberOfIncrease_shouldBeZero() {
    BDDAssertions.then(new PuzzleDayOnePartTwo().load(List.of(1, 1, 1)).numberOfIncrease())
        .isEqualTo(0);
  }

  @Test
  void givenPreviousMeasurementHigher_numberOfIncrease_shouldBeOne() {
    BDDAssertions.then(new PuzzleDayOnePartTwo().load(List.of(1, 1, 0, 2)).numberOfIncrease())
        .isEqualTo(1);
  }

  @Test
  void givenHighAndLow_numberOfIncrease_shouldBeThree() {
    BDDAssertions.then(
            new PuzzleDayOnePartTwo().load(List.of(0, 1, 2, 3, 4, 15)).numberOfIncrease())
        .isEqualTo(3);
  }

  @Test
  void givenSameDepth_numberOfIncrease_shouldBeZero() {
    BDDAssertions.then(new PuzzleDayOnePartTwo().load(List.of(10, 10)).numberOfIncrease())
        .isEqualTo(0);
  }

  @Test
  void givenPuzzleOne_numberOfIncrease() throws IOException {
    BDDAssertions.then(
            new PuzzleDayOnePartTwo()
                .load(
                    "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/01-12-input-part2.txt")
                .numberOfIncrease())
        .isEqualTo(1190);
  }
}

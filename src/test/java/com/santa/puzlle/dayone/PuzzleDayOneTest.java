package com.santa.puzlle.dayone;

import java.io.IOException;
import java.util.List;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class PuzzleDayOneTest {

  @Test
  void givenNoPreviousMeasurement_numberOfIncrease_shouldBeZero() throws IOException {
    BDDAssertions.then(new PuzzleDayOne().load(List.of(197)).numberOfIncrease()).isEqualTo(0);
  }

  @Test
  void givenPreviousMeasurementHigher_numberOfIncrease_shouldBeOne() throws IOException {
    BDDAssertions.then(new PuzzleDayOne().load(List.of(197, 198)).numberOfIncrease()).isEqualTo(1);
  }

  @Test
  void givenHighAndLow_numberOfIncrease_shouldBeThree() throws IOException {
    BDDAssertions.then(new PuzzleDayOne().load(List.of(0, 10, 20, 10, 30, 30)).numberOfIncrease())
        .isEqualTo(3);
  }

  @Test
  void givenSameDepth_numberOfIncrease_shouldBeZero() throws IOException {
    BDDAssertions.then(new PuzzleDayOne().load(List.of(10, 10)).numberOfIncrease()).isEqualTo(0);
  }

  @Test
  void givenPuzzleOne_numberOfIncrease() throws IOException {
    BDDAssertions.then(
            new PuzzleDayOne()
                .load(
                    "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/01-12-input.txt")
                .numberOfIncrease())
        .isEqualTo(1162);
    BDDAssertions.then(
            new PuzzleDayOne()
                .load(
                    "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/01-12-input.txt")
                .numberOfIncrease())
        .isEqualTo(1162);
  }
}

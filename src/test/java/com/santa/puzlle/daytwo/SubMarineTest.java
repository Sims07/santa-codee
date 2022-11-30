package com.santa.puzlle.daytwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class SubMarineTest {
  @Test
  void givenXUp_depth_shouldDecreaseDepthOfX() {
    BDDAssertions.then(new SubMarine().up(5).depth()).isEqualTo(-5);
  }

  @Test
  void givenXDown_depth_shouldIncreaseDepthOfX() {
    BDDAssertions.then(new SubMarine().down(5).depth()).isEqualTo(5);
  }

  @Test
  void givenXForward_depth_shouldIncreaseHorizontalOfX() {
    BDDAssertions.then(new SubMarine().forward(5).horizontalPosition()).isEqualTo(5);
  }

  @Test
  void givenPosition_total_shouldBeHorizontalMultiplyByDepth() {
    BDDAssertions.then(new SubMarine().forward(5).down(5).total()).isEqualTo(25);
  }

  @Test
  void playTestFile() throws IOException {
    final SubMarine subMarine = new SubMarine();
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day2/day2part1.txt"))) {
      lines.forEach(subMarine::move);
    }
    BDDAssertions.then(subMarine.total()).isEqualTo(1989265);
  }
}

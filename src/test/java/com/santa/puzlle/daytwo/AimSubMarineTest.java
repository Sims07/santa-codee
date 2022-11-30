package com.santa.puzlle.daytwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class AimSubMarineTest {
  @Test
  void givenXUp_depth_shouldDecreaseAimOfX() {
    BDDAssertions.then(new AimSubMarine().up(5).aim()).isEqualTo(-5);
  }

  @Test
  void givenXDown_depth_shouldIncreaseAimOfX() {
    BDDAssertions.then(new AimSubMarine().down(5).aim()).isEqualTo(5);
  }

  @Test
  void givenXForward_depth_shouldIncreaseHorizontalOfXAndIncreaseDepthOfAimMultiplyByX() {
    final AbstractSubMarine forward = new AimSubMarine().forward(5);
    BDDAssertions.then(forward.horizontalPosition()).isEqualTo(5);
    BDDAssertions.then(forward.depth()).isEqualTo(0);
  }

  @Test
  void givenAimAndXForward_depth_shouldIncreaseHorizontalOfXAndIncreaseDepthOfAimMultiplyByX() {
    final AbstractSubMarine forward = new AimSubMarine().down(5).forward(5);
    BDDAssertions.then(forward.horizontalPosition()).isEqualTo(5);
    BDDAssertions.then(forward.depth()).isEqualTo(25);
  }

  @Test
  void playTestFile() throws IOException {
    final AimSubMarine subMarine = new AimSubMarine();
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day2/day2part2.txt"))) {
      lines.forEach(subMarine::move);
    }
    BDDAssertions.then(subMarine.total()).isEqualTo(1989265);
  }
}

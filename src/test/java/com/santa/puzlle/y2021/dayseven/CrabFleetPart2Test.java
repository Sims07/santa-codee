package com.santa.puzlle.y2021.dayseven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class CrabFleetPart2Test {
  @Test
  void givenOnePosition_alignFleet_shouldBeThePosition() {
    BDDAssertions.then(new CrabFleet().addCrab(17).fuelToAlign()).isEqualTo(17);
  }

  @Test
  void testReal() throws IOException {
    final ExpensiveCrabFleet crabFleet = new ExpensiveCrabFleet();
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day7/day7part1Test.txt"))) {
      final List<String> linesList = lines.toList();
      Arrays.stream(linesList.get(0).split(",")).map(Integer::valueOf).forEach(crabFleet::addCrab);
    }
    BDDAssertions.then(crabFleet.fuelToAlign()).isEqualTo(168);
  }

  @Test
  void testRealAoC() throws IOException {
    final ExpensiveCrabFleet crabFleet = new ExpensiveCrabFleet();
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day7/day7part1.txt"))) {
      final List<String> linesList = lines.toList();
      Arrays.stream(linesList.get(0).split(",")).map(Integer::valueOf).forEach(crabFleet::addCrab);
    }
    BDDAssertions.then(crabFleet.fuelToAlign()).isEqualTo(37);
  }
}

package com.santa.puzlle.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class DecoderTest {
  @Test
  void givenMatrix2x2() {
    RiskMatrix matrix = new RiskMatrix(2, 2).load(0, "13").load(1, "21");
    BDDAssertions.then(matrix.shortenRisk()).isEqualTo(3);
  }

  @Test
  void givenMatrixGraph2x2() {
    RiskMatrixGraph matrix = new RiskMatrixGraph(2, 2).load(0, "13").load(1, "21").init();
    BDDAssertions.then(matrix.shortenRisk()).isEqualTo(3);
  }

  @Test
  void givenTestGraph() throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day15/day15part1.txt"))) {
      final List<String> input = lines.collect(Collectors.toList());
      final RiskMatrixGraph riskMatrix = new RiskMatrixGraph(input.get(0).length(), input.size());
      int x = 0;
      for (String line : input) {
        riskMatrix.load(x, line);
        x++;
      }
      riskMatrix.init();
      BDDAssertions.then(riskMatrix.shortenRisk()).isEqualTo(40);
    }
  }

  @Test
  void givenTest() throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day15/day15part1.txt"))) {
      final List<String> input = lines.collect(Collectors.toList());
      final RiskMatrix riskMatrix = new RiskMatrix(input.get(0).length(), input.size());
      int x = 0;
      for (String line : input) {
        riskMatrix.load(x, line);
        x++;
      }
      BDDAssertions.then(riskMatrix.shortenRisk()).isEqualTo(40);
    }
  }

  @Test
  void givenPart1() throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day15/day15part1Real.txt"))) {
      final List<String> input = lines.collect(Collectors.toList());
      final RiskMatrixGraph riskMatrix = new RiskMatrixGraph(input.get(0).length(), input.size());
      int x = 0;
      for (String line : input) {
        riskMatrix.load(x, line);
        x++;
      }
      riskMatrix.init();
      BDDAssertions.then(riskMatrix.shortenRisk()).isEqualTo(687);
    }
  }

  @Test
  void givenPart2() throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day15/day15part1Real.txt"))) {
      final List<String> input = lines.collect(Collectors.toList());
      final RiskMatrixGraphPart2 riskMatrix =
          new RiskMatrixGraphPart2(input.get(0).length(), input.size());
      BDDAssertions.then(riskMatrix.nextRiskLevel(8, 1)).isEqualTo(9);
      BDDAssertions.then(riskMatrix.nextRiskLevel(9, 1)).isEqualTo(1);
      int x = 0;
      for (String line : input) {
        riskMatrix.load(x, line);
        x++;
      }
      riskMatrix.init();
      BDDAssertions.then(riskMatrix.shortenRisk()).isEqualTo(315);
    }
  }
}

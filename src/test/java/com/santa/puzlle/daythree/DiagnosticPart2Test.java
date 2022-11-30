package com.santa.puzlle.daythree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class DiagnosticPart2Test {

  @Test
  void oneDataValue_oxygen_shouldBeTheValue() {
    BDDAssertions.then(new OxygenDiagnostic().load(List.of("10111")).oxygenGeneratorRating())
        .isEqualTo(23);
  }

  @Test
  void testDataValue_oxygen_shouldBeTheValue() {
    BDDAssertions.then(
            new OxygenDiagnostic()
                .load(
                    List.of(
                        "00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100",
                        "10000", "11001", "00010", "01010"))
                .oxygenGeneratorRating())
        .isEqualTo(23);
  }

  @Test
  void testDataValue_co2_shouldBeTheValue() {
    BDDAssertions.then(
            new OxygenDiagnostic()
                .load(
                    List.of(
                        "00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100",
                        "10000", "11001", "00010", "01010"))
                .co2ScrubberRating())
        .isEqualTo(10);
  }

  @Test
  void playTestFile() throws IOException {
    final OxygenDiagnostic diagnostic = new OxygenDiagnostic();
    List<String> rawData = new ArrayList<>();
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day3/day3part2.txt"))) {
      lines.forEach(rawData::add);
    }
    diagnostic.load(rawData);
    BDDAssertions.then(diagnostic.oxygenGeneratorRating()).isEqualTo(2547);
    BDDAssertions.then(diagnostic.co2ScrubberRating()).isEqualTo(737);
    BDDAssertions.then(diagnostic.lifeSupport()).isEqualTo(1877139);
  }
}

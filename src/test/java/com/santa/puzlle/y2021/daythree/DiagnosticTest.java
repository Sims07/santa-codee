package com.santa.puzlle.y2021.daythree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class DiagnosticTest {

  @Test
  void bitValues_gamma_shouldBeTheMostCommon() {
    BDDAssertions.then(new Diagnostic().load(List.of("00100")).gamma()).isEqualTo(4);
  }

  @Test
  void bitValues_epsilon_shouldBeTheLessCommon() {
    BDDAssertions.then(new Diagnostic().load(List.of("00100")).epsilon()).isEqualTo(27);
  }

  @Test
  void bitValues_powerConsumption_shouldBeGammaMultiplyByEpsilon() {
    BDDAssertions.then(new Diagnostic().load(List.of("00100")).powerConsumption())
        .isEqualTo(27 * 4);
  }

  @Test
  void playTestFile() throws IOException {
    final Diagnostic diagnostic = new Diagnostic();
    List<String> rawData = new ArrayList<>();
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day3/day3part1.txt"))) {
      lines.forEach(rawData::add);
    }
    diagnostic.load(rawData);
    BDDAssertions.then(diagnostic.powerConsumption()).isEqualTo(2003336);
  }
}

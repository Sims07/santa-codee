package com.santa.puzlle.dayeight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

public class DecoderTest {
  @Test
  void givenInput_countUnique_shoultReturn26() {
    final Decoder decoder = new Decoder();
    decoder.load(
        "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb |"
            + "fdgacbe cefdb cefbgd gcbe");

    BDDAssertions.then(decoder.numberOfUniqueInput()).isEqualTo(2);
  }

  @Test
  void givenTest_countUnique_shoultReturn26() throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day8/day8part1.txt"))) {
      Integer total =
          lines
              .map(line -> new Decoder().load(line).numberOfUniqueInput())
              .mapToInt(Integer::intValue)
              .sum();
      BDDAssertions.then(total).isEqualTo(0);
    }
  }

  @Test
  void givenTest() throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day8/day8part1.txt"))) {
      Integer total =
          lines
              .map(line -> new Decoder().load(line).numberOfUniqueInput())
              .mapToInt(Integer::intValue)
              .sum();
      BDDAssertions.then(total).isEqualTo(0);
    }
  }
}

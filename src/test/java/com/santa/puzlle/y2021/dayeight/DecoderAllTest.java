package com.santa.puzlle.y2021.dayeight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class DecoderAllTest {
  @Test
  void givenInput_countUnique_shoultReturn26() {
    final DecoderAll decoder = new DecoderAll();
    decoder.load(
        "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab  | "
            + "cdfeb fcadb cdfeb cdbaf");

    BDDAssertions.then(decoder.total()).isEqualTo(5353);
  }

  @Test
  void givenInput_countUnique_shoultReturn261() {
    final DecoderAll decoder = new DecoderAll();
    decoder.load(
        "cgaed gcdbfa gcfaed gfcde gadfceb cdbfeg acg eacf eabgd ca | agc efcgbd cag eacf");

    BDDAssertions.then(decoder.total()).isEqualTo(7674);
  }

  @Test
  void givenTest_countUnique_shoultReturn26() throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day8/day8part1.txt"))) {
      System.out.println("ICI");
      Integer total =
          lines.map(line -> new DecoderAll().load(line).total()).mapToInt(Integer::intValue).sum();
      BDDAssertions.then(total).isEqualTo(1074888);
    }
  }
}

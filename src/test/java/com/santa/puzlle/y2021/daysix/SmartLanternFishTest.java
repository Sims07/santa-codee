package com.santa.puzlle.y2021.daysix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class SmartLanternFishTest {

  @Test
  void test() throws IOException {
    final SmartLanternFishFleet lanternFishFleet = new SmartLanternFishFleet();
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day6/day6part1Test.txt"))) {
      final List<String> linesList = lines.toList();
      Arrays.stream(linesList.get(0).split(","))
          .map(Integer::valueOf)
          .forEach(remainingDays -> lanternFishFleet.addLanternFish(remainingDays));
    }
    lanternFishFleet.simulateNextDays(18);
    BDDAssertions.then(lanternFishFleet.total()).isEqualTo(26);
  }

  @Test
  void testReal() throws IOException {
    final SmartLanternFishFleet lanternFishFleet = new SmartLanternFishFleet();
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day6/day6part1.txt"))) {
      final List<String> linesList = lines.toList();
      Arrays.stream(linesList.get(0).split(","))
          .map(Integer::valueOf)
          .forEach(lanternFishFleet::addLanternFish);
    }
    lanternFishFleet.simulateNextDays(80);
    BDDAssertions.then(lanternFishFleet.total()).isEqualTo(26);
  }

  @Test
  void testReal256() throws IOException {
    final SmartLanternFishFleet lanternFishFleet = new SmartLanternFishFleet();
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day6/day6part2.txt"))) {
      final List<String> linesList = lines.toList();
      Arrays.stream(linesList.get(0).split(","))
          .map(Integer::valueOf)
          .forEach(lanternFishFleet::addLanternFish);
    }
    lanternFishFleet.simulateNextDays(256);
    BDDAssertions.then(lanternFishFleet.total()).isEqualTo(1572358335990L);
  }
}

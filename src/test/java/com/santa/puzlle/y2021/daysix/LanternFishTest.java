package com.santa.puzlle.y2021.daysix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class LanternFishTest {

  @Test
  void oneDay() {
    final LanternFish lanternFish = new LanternFish(1);
    Optional<LanternFish> newLanternFish = lanternFish.nextDay();
    BDDAssertions.then(newLanternFish).isEmpty();
    BDDAssertions.then(lanternFish.remainingDays()).isEqualTo(0);
    newLanternFish = lanternFish.nextDay();
    BDDAssertions.then(newLanternFish).isPresent();
    BDDAssertions.then(newLanternFish.map(LanternFish::remainingDays).orElseThrow()).isEqualTo(8);
  }

  @Test
  void test() throws IOException {
    final LanternFishFleet lanternFishFleet = new LanternFishFleet();
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
    final LanternFishFleet lanternFishFleet = new LanternFishFleet();
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day6/day6part1.txt"))) {
      final List<String> linesList = lines.toList();
      Arrays.stream(linesList.get(0).split(","))
          .map(Integer::valueOf)
          .forEach(remainingDays -> lanternFishFleet.addLanternFish(remainingDays));
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
          .forEach(remainingDays -> lanternFishFleet.addLanternFish(remainingDays));
    }
    lanternFishFleet.simulateNextDays(256);
    BDDAssertions.then(lanternFishFleet.total()).isEqualTo(26);
  }
}

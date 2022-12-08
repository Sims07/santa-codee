package com.santa.puzlle.y2022.dayEight;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class PuzzleDayEightTest {

  @Test
  void givenText_of_shouldLoadTheGrid() {
    Grid grid = Grid.of(List.of("30373", "25512", "65332", "33549", "35390"));

    then(grid).isNotNull();
    then(grid.trees()).isNotNull();
    then(grid.trees().length).isEqualTo(5);
    then(grid.trees()[0].length).isEqualTo(5);

    then(grid.edgeTreeNumber()).isEqualTo(16);
    then(grid.innerVisibleTrees()).isEqualTo(5);
    then(grid.nbVisibleTrees()).isEqualTo(21);
    then(grid.highestScenicScore()).isEqualTo(8);
  }

  @Test
  void givenPartOne_of_shouldLoadTheGrid() throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/daEight/day8part1.txt"))) {
      System.out.println(Grid.of(lines.toList()).nbVisibleTrees());
    }
  }

  @Test
  void givenPartTwo_of_shouldLoadTheGrid() throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/daEight/day8part2.txt"))) {
      System.out.println(Grid.of(lines.toList()).highestScenicScore());
    }
  }
}

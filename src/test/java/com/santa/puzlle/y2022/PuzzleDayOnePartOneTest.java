package com.santa.puzlle.y2022;

import static org.assertj.core.api.BDDAssertions.then;

import com.santa.puzlle.y2022.dayone.Elf;
import com.santa.puzlle.y2022.dayone.Elves;
import com.santa.puzlle.y2022.dayone.PuzzleDayOne;
import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class PuzzleDayOnePartOneTest {

  @Test
  void givenOneElfWithOneFoodItem_findMaxElfCalories_shouldReturnElfCalories() {
    final Elves elves = new PuzzleDayOne().load(Stream.of("1000"));

    then(elves.elves()).isNotEmpty();
    final Elf elf = elves.elves().get(0);
    then(elf.calories()).isNotEmpty();
    then(elf.calories().get(0)).isEqualTo(1000);
    then(elf.calories().get(0)).isEqualTo(1000);
    then(elf.totalCalories()).isEqualTo(1000);
  }

  @Test
  void givenOneElfWith2foodItem_findMaxElfCalories_shouldReturnElfCalories() {
    final Elves elves = new PuzzleDayOne().load(Stream.of("1000", "2000"));

    then(elves.elves()).isNotEmpty();
    final Elf elf = elves.elves().get(0);
    then(elf.calories()).isNotEmpty();
    then(elf.calories().get(0)).isEqualTo(1000);
    then(elf.calories().get(0)).isEqualTo(1000);
    then(elf.totalCalories()).isEqualTo(3000);
  }

  @Test
  void givenMoreThanOneElfWith2foodItem_findMaxElfCalories_shouldReturnElfCalories() {
    final Elves elves = new PuzzleDayOne().load(Stream.of("1000", "2000", "", "1000"));

    then(elves.elves()).isNotEmpty();
    then(elves.elves().size()).isEqualTo(2);
    final Elf elf = elves.elves().get(1);
    then(elf.calories()).isNotEmpty();
    then(elf.calories().get(0)).isEqualTo(1000);
    then(elf.calories().get(0)).isEqualTo(1000);
    then(elf.totalCalories()).isEqualTo(1000);

    then(elves.elfWithMaxCalorie().totalCalories()).isEqualTo(3000);
  }

  @Test
  void givenExample_findMaxElfCalories_shouldReturn24000() throws IOException {
    final Elves elves =
        new PuzzleDayOne()
            .load(
                "/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/day1part1_ex.txt");

    then(elves.elfWithMaxCalorie().totalCalories()).isEqualTo(24000);
  }

  @Test
  void givenPuzzle_findMaxElfCalories_shouldReturn24000() throws IOException {
    final Elves elves =
        new PuzzleDayOne()
            .load(
                "/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/day1part1.txt");

    System.out.println(elves.elfWithMaxCalorie().totalCalories());
  }
}

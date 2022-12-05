package com.santa.puzlle.y2022.daythree.partTwo;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class Puzzle3Part2Test {

  @Test
  void givenRucksack_getDuplicate_returnTheDuplicatedItem(){
    RuckSackGroup ruckSackGroup= RuckSackGroup.from(List.of("vJrwpWtwJgWrhcsFMMfFFhFp","jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL","PmmdzqPrVvPwwTWBwg"));

    then(ruckSackGroup).isNotNull();
    then((char)ruckSackGroup.duplicateItem().charValue()).isEqualTo('r');
  }

  @Test
  void givenRucksackGroups_getDuplicate_returnTheDuplicatedItem(){
    RuckSackGroup ruckSackGroup= RuckSackGroup.from(List.of("vJrwpWtwJgWrhcsFMMfFFhFp","jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL","PmmdzqPrVvPwwTWBwg"));
    RuckSackGroup ruckSackGroup1= RuckSackGroup.from(List.of("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn","ttgJtRGJQctTZtZT","CrZsJsPPZsGzwwsLwLmpwMDw"));
    final RuckSacks ruckSacks = new RuckSacks(List.of(ruckSackGroup, ruckSackGroup1));
    then(ruckSacks).isNotNull();
    then(ruckSacks.duplicateItemsValue()).isEqualTo(70);
  }


  @Test
  void givenPuzzle_getDuplicates_returnTheDuplicatedItem() throws IOException {
    RuckSacks ruckSacks= new PuzzleDayThreePartTwo().load("/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/dayThree/day3part2.txt");
    then(ruckSacks.duplicateItemsValue()).isEqualTo(157);
  }
}
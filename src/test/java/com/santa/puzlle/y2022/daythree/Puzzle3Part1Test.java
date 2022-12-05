package com.santa.puzlle.y2022.daythree;

import static com.santa.puzlle.y2022.daythree.RuckSack.from;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class Puzzle3Part1Test {

  @Test
  void givenRucksack_getDuplicate_returnTheDuplicatedItem(){
    RuckSack ruckSack= from("vJrwpWtwJgWrhcsFMMfFFhFp");

    then(ruckSack).isNotNull();
    then(ruckSack.compartments()).isNotNull();
    then(ruckSack.compartments()).isNotEmpty();
    then(ruckSack.compartments().size()).isEqualTo(2);
    then(ruckSack.compartments().get(0).items().size()).isEqualTo(12);
    then(ruckSack.compartments().get(1).items().size()).isEqualTo(12);
    then((char)ruckSack.duplicateItem().charValue()).isEqualTo('p');
  }

  @Test
  void givenRucksacks_getDuplicates_returnTheDuplicatedItem(){
    RuckSacks ruckSacks= new RuckSacks(List.of(from("vJrwpWtwJgWrhcsFMMfFFhFp"),
        from("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"),
    from("PmmdzqPrVvPwwTWBwg"),
    from("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"),
    from("ttgJtRGJQctTZtZT"),
    from("CrZsJsPPZsGzwwsLwLmpwMDw")));
    System.out.println((int)'a');
    System.out.println((int)'p');
    System.out.println((int)'A');
    System.out.println((int)'L');
    then(ruckSacks.duplicateItems()).isNotNull();
    then((char)ruckSacks.duplicateItems().get(0).charValue()).isEqualTo('p');
    then(ruckSacks.duplicateItems().get(0).value()).isEqualTo(16);
    then((char)ruckSacks.duplicateItems().get(1).charValue()).isEqualTo('L');
    then(ruckSacks.duplicateItems().get(1).value()).isEqualTo(38);
    then((char)ruckSacks.duplicateItems().get(2).charValue()).isEqualTo('P');
    then(ruckSacks.duplicateItems().get(2).value()).isEqualTo(42);
    then((char)ruckSacks.duplicateItems().get(3).charValue()).isEqualTo('v');
    then(ruckSacks.duplicateItems().get(3).value()).isEqualTo(22);
    then((char)ruckSacks.duplicateItems().get(4).charValue()).isEqualTo('t');
    then(ruckSacks.duplicateItems().get(4).value()).isEqualTo(20);
    then((char)ruckSacks.duplicateItems().get(5).charValue()).isEqualTo('s');
    then(ruckSacks.duplicateItems().get(5).value()).isEqualTo(19);
    then(ruckSacks.duplicateItemsValue()).isEqualTo(157);
  }

  @Test
  void givenPuzzle_getDuplicates_returnTheDuplicatedItem() throws IOException {
    RuckSacks ruckSacks= new PuzzleDayThree().load("/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/dayThree/day3part1.txt");
    then(ruckSacks.duplicateItemsValue()).isEqualTo(157);
  }
}
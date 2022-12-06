package com.santa.puzlle.y2022.dayFive.partTwo;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class PuzzleDayFiveTest {

  @Test
  void givenOneMove_doMove_shouldReturnTopCrates(){
    Slots slots = Slots.of(
        List.of(
            Slot.of(1, List.of("Z", "N")), Slot.of(2, List.of("M","C", "D")),
            Slot.of(3, List.of("P"))));

    slots=slots.move(1,2,1);
    then(slots.configuration()).isEqualTo("DCP");
    slots=slots.move(3,1,3);
    then(slots.configuration()).isEqualTo("CD");
    slots=slots.move(2,2,1);
    System.out.println(slots);
    slots=slots.move(1,1,2);
    then(slots.configuration()).isNotNull();
    then(slots.configuration()).isEqualTo("MCD");
  }

  @Test
  void givenExampleFile_doMove_shouldReturnTopCrates() throws IOException {
    Slots slots = Slots.of(
        List.of(
            Slot.of(1, List.of("B", "S", "V","Z","G","P","W")),
            Slot.of(2, List.of("J","V","B","C","Z","F")),
            Slot.of(3, List.of("V","L","M","H","N","Z","D","C")),
            Slot.of(4, List.of("L","D","M","Z","P","F","J","B")),
            Slot.of(5, List.of("V","F","C","H","J","B","Q","H")),
            Slot.of(6, List.of("G","F","Q","T","S","L","B")),
            Slot.of(7, List.of("L","G","C","Z","V")),
            Slot.of(8, List.of("N","L","G")),
            Slot.of(9, List.of("J","F","H","C"))));

    List<Move> moves = new PuzzleDayFive().load(
        "/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/dayFive/day5part2.txt");
    slots=slots.playMoves(moves);
    System.out.println(slots.configuration());
  }
}
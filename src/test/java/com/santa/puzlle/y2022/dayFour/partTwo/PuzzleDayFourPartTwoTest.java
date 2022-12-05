package com.santa.puzlle.y2022.dayFour.partTwo;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class PuzzleDayFourPartTwoTest {


  @Test
  void givenOverlap_isOverlap_shouldReturnTrue(){
    final boolean overlapped = Pair.from(
        List.of(
            CleaningSection.from(2, 8), CleaningSection.from(3, 7))).isOverlapped();

    then(overlapped).isTrue();
  }

  @Test
  void givenOverlapReverse_isOverlap_shouldReturnTrue(){
    final boolean overlapped = Pair.from(
        List.of(
            CleaningSection.from(3, 7), CleaningSection.from(2, 8))).isOverlapped();

    then(overlapped).isTrue();
  }

  @Test
  void givenOverlapIn_isOverlap_shouldReturnTrue(){
    final boolean overlapped = Pair.from(
        List.of(
            CleaningSection.from(2, 8), CleaningSection.from(3, 10))).isOverlapped();

    then(overlapped).isTrue();
  }

  @Test
  void givenNotOverlapIn_isOverlap_shouldReturnFalse(){
    final boolean overlapped = Pair.from(
        List.of(
            CleaningSection.from(2, 8), CleaningSection.from(9, 10))).isOverlapped();

    then(overlapped).isFalse();
  }
  @Test
  void givenNotOverlapInUnder_isOverlap_shouldReturnFalse(){
    final boolean overlapped = Pair.from(
        List.of(
            CleaningSection.from(2, 8), CleaningSection.from(0, 1))).isOverlapped();

    then(overlapped).isFalse();
  }


  @Test
  void givenPairsWith1overlap_nbOverlap_shouldReturnOne(){
    Pair pair= Pair.from(
        List.of(
            CleaningSection.from(2, 8), CleaningSection.from(3, 7)));
    final Pairs pairs = new Pairs(List.of(pair));
    then(pairs.nbOverlapped()).isEqualTo(1);
  }

  @Test
  void givenPairsWith2overlap_nbOverlap_shouldReturnTwo(){
    Pair pair= Pair.from(
        List.of(
            CleaningSection.from(2, 8), CleaningSection.from(3, 7)));
    Pair pair1= Pair.from(
        List.of(CleaningSection.from(2, 8), CleaningSection.from(3, 7)));
    final Pairs pairs = new Pairs(List.of(pair,pair1));
    then(pairs.nbOverlapped()).isEqualTo(2);
  }

  @Test
  void givenPuzzle_getDuplicates_returnTheDuplicatedItem() throws IOException {
    Pairs pairs= new PuzzleDayFour().load("/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/dayFour/day4part2.txt");
    then(pairs.nbOverlapped()).isEqualTo(157);
  }
}
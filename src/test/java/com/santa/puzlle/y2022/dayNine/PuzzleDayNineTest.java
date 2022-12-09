package com.santa.puzlle.y2022.dayNine;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class PuzzleDayNineTest {

  @Test
  void givenR4_moveTailUntilCloseToHead_shouldReturn3() {
    final Head head = Head.of(Position.of(0, 0));
    final Tail tail = Tail.of(Position.of(0, 0));

    Rope rope = Rope.of(Position.of(0, 0), head, tail);
    //
    int nbPos = rope.moveHead(Move.of(Direction.R, 4));
    then(nbPos).isEqualTo(3);
    nbPos = rope.moveHead(Move.of(Direction.U, 4));
    then(nbPos).isEqualTo(3);
    nbPos = rope.moveHead(Move.of(Direction.L, 3));
    then(nbPos).isEqualTo(2);
    nbPos = rope.moveHead(Move.of(Direction.D, 1));
    then(nbPos).isEqualTo(0);
    nbPos = rope.moveHead(Move.of(Direction.R, 4));
    then(nbPos).isEqualTo(2);
    nbPos = rope.moveHead(Move.of(Direction.D, 1));
    then(nbPos).isEqualTo(0);
    nbPos = rope.moveHead(Move.of(Direction.L, 5));
    then(nbPos).isEqualTo(3);
    nbPos = rope.moveHead(Move.of(Direction.R, 2));
    then(nbPos).isEqualTo(0);
    then(rope.tailPosition().stream().distinct().toList().size()).isEqualTo(13);
  }

  @Test
  void givenExample_moveTailUntilCloseToHead_shouldReturn13() {
    final Head head = Head.of(Position.of(0, 0));
    final Tail tail = Tail.of(Position.of(0, 0));

    Rope rope = Rope.of(Position.of(0, 0), head, tail);
    then(rope.moves(
            List.of(
                Move.of(Direction.R, 4),
                Move.of(Direction.U, 4),
                Move.of(Direction.L, 3),
                Move.of(Direction.D, 1),
                Move.of(Direction.R, 4),
                Move.of(Direction.D, 1),
                Move.of(Direction.L, 5),
                Move.of(Direction.R, 2))))
        .isEqualTo(13);
    then(rope.tailPosition().stream().distinct().toList().size()).isEqualTo(13);
  }

  @Test
  void givenExampleNeg_moveTailUntilCloseToHead_shouldReturn13() {
    final Head head = Head.of(Position.of(0, 0));
    final Tail tail = Tail.of(Position.of(0, 0));

    Rope rope = Rope.of(Position.of(0, 0), head, tail);
    then(rope.moves(
            List.of(
                Move.of(Direction.L, 1),
                Move.of(Direction.U, 1),
                Move.of(Direction.L, 1),
                Move.of(Direction.D, 2),
                Move.of(Direction.U, 2),
                Move.of(Direction.L, 2))))
        .isEqualTo(3);
    System.out.println(rope.tailPosition().stream().distinct().toList().size());
  }

  @Test
  void givenPartOne_of_shouldLoadTheGrid() throws IOException {
    final Head head = Head.of(Position.of(0, 0));
    final Tail tail = Tail.of(Position.of(0, 0));
    Rope rope = Rope.of(Position.of(0, 0), head, tail);
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/dayNine/day9part1.txt"))) {
      System.out.println(rope.moves(lines.map(Move::of).toList()));
      System.out.println(rope.tailPosition().stream().distinct().toList().size());
    }
  }
}

package com.santa.puzlle.y2022.dayNine;

import java.util.ArrayList;
import java.util.List;

public class Rope {
  private final Position start;
  private final Head head;
  private final Tail tail;
  private final List<Position> tailPosition = new ArrayList<>();

  public Rope(Position start, Head head, Tail tail) {
    this.start = start;
    this.head = head;
    this.tail = tail;
  }

  public static Rope of(Position of, Head head, Tail tail) {
    return new Rope(of, head, tail);
  }

  public int moveHead(Move of) {
    RopePath headMovePath = head.move(of);
    return headMovePath.values().stream()
        .mapToInt(
            pos -> {
              int res = tail.follow(pos);
              tailPosition.add(tail.currentPosition());
              System.out.println(tail.currentPosition());
              return res;
            })
        .sum();
  }

  public List<Position> tailPosition() {
    return tailPosition;
  }

  public int nbPositionVisitByTheTail() {
    return 0;
  }

  public int moves(List<Move> headMoves) {
    return headMoves.stream().mapToInt(this::moveHead).sum();
  }
}

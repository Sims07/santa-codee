package com.santa.puzlle.y2022.dayNine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Knot {
  private final Position current;
  private final RopePath path;

  public Knot(Position current, RopePath path) {
    this.current = current;
    this.path = path;
    this.path.add(current);
  }

  public RopePath move(Move of) {
    return switch (of.dir()) {
      case R -> moveRight(of);
      case L -> moveLeft(of);
      case U -> moveUp(of);
      case D -> moveDown(of);
    };
  }

  private RopePath moveRight(Move of) {

    RopePath ropePath =
        RopePath.of(
            IntStream.range(current.x() + 1, current.x() + of.distance() + 1)
                .mapToObj(x -> new Position(x, current.y()))
                .toList());
    setCurrentX(current.x() + of.distance());
    return ropePath;
  }

  private RopePath moveLeft(Move of) {
    List<Position> positionList = new ArrayList<>();
    for (int i = current.x() - 1; i > current.x() - of.distance() - 1; i--) {
      positionList.add(Position.of(i, current.y()));
    }
    setCurrentX(current.x() - of.distance());
    return RopePath.of(positionList);
  }

  void setCurrentX(int newValue) {
    current.setX(newValue);
  }

  private RopePath moveUp(Move of) {
    List<Position> positionList = new ArrayList<>();
    for (int i = current.y() + 1; i < current.y() + of.distance() + 1; i++) {
      positionList.add(Position.of(current.x(), i));
    }
    setCurrentY(current.y() + of.distance());
    return RopePath.of(positionList);
  }

  private RopePath moveDown(Move of) {
    List<Position> positionList = new ArrayList<>();
    for (int i = current.y() - 1; i > current.y() - of.distance() - 1; i--) {
      positionList.add(Position.of(current.x(), i));
    }
    setCurrentY(current.y() - of.distance());
    return RopePath.of(positionList);
  }

  Position setCurrentY(int newValue) {
    return current.setY(newValue);
  }

  public Position currentPosition() {
    return new Position(current.x(), current.y());
  }
}

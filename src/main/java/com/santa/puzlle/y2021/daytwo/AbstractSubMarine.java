package com.santa.puzlle.y2021.daytwo;

public abstract sealed class AbstractSubMarine permits AimSubMarine, SubMarine {

  int depth;
  int horizontalPosition;

  public abstract AbstractSubMarine up(int step);

  public int depth() {
    return depth;
  }

  public abstract AbstractSubMarine forward(int step);

  public int horizontalPosition() {
    return horizontalPosition;
  }

  public void addToHorizontalPosition(int step) {
    horizontalPosition = horizontalPosition + step;
  }

  public void addToDepth(int step) {
    depth = depth + step;
  }

  public AbstractSubMarine down(int step) {
    return up(-step);
  }

  public int total() {
    return horizontalPosition * depth;
  }

  public void move(String move) {
    final String[] moveLine = move.split(" ");
    String moveType = moveLine[0];
    int movePosition = Integer.parseInt(moveLine[1]);
    switch (moveType) {
      case "forward" -> forward(movePosition);
      case "up" -> up(movePosition);
      case "down" -> down(movePosition);
      default -> throw new IllegalArgumentException("Move not take into account:" + moveType);
    }
  }
}

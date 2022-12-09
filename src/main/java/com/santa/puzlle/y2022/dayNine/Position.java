package com.santa.puzlle.y2022.dayNine;

import java.util.Objects;

public final class Position {

  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static Position of(int x, int y) {
    return new Position(x, y);
  }

  public int x() {
    return x;
  }

  public int y() {
    return y;
  }

  public Position setX(int x) {
    this.x = x;
    return this;
  }

  public Position setY(int y) {
    this.y = y;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return x == position.x && y == position.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "Position[" + "x=" + x + ", " + "y=" + y + ']';
  }
}

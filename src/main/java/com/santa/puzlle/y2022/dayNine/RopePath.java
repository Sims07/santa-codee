package com.santa.puzlle.y2022.dayNine;

import java.util.List;

public record RopePath(List<Position> values) {

  public static RopePath of(List<Position> path) {
    return new RopePath(path);
  }

  public void add(Position newPos) {
    values.add(newPos);
  }
}

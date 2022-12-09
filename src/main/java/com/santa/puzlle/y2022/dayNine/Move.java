package com.santa.puzlle.y2022.dayNine;

public record Move(Direction dir, int distance) {

  public static Move of(Direction r, int dist) {
    return new Move(r, dist);
  }
  public static Move of(String line) {
    final String[] split = line.split(" ");

    return new Move(Direction.valueOf(split[0]), Integer.valueOf(split[1]));
  }

}

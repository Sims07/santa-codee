package com.santa.puzlle.y2022.dayNine;

import java.util.ArrayList;

public class Head extends Knot {

  public Head(Position current, RopePath path) {
    super(current, path);
  }

  public static Head of(Position pos) {
    return new Head(pos, RopePath.of(new ArrayList<>()));
  }

  public void ropePath() {}
}

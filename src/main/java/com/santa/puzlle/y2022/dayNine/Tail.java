package com.santa.puzlle.y2022.dayNine;

import java.util.ArrayList;

public class Tail extends Knot {

  public Tail(Position current, RopePath path) {
    super(current, path);
  }

  public static Tail of(Position pos) {
    return new Tail(pos, RopePath.of(new ArrayList<>()));
  }

  public int follow(Position pos) {
    if (isFollowRight(pos)) {
      setCurrentY(pos.y());
      setCurrentX(currentPosition().x() + 1);
      return 1;
    } else if (isFollowUp(pos)) {
      setCurrentX(pos.x());
      setCurrentY(currentPosition().y() + 1);
      return 1;
    } else if (isFollowLeft(pos)) {
      setCurrentY(pos.y());
      setCurrentX(currentPosition().x() - 1);
      return 1;
    } else if (isFollowDown(pos)) {
      setCurrentX(pos.x());
      setCurrentY(currentPosition().y() - 1);
      return 1;
    }
    return 0;
  }

  private boolean isFollowLeft(Position pos) {
    return pos.x() < currentPosition().x() - 1;
  }

  private boolean isFollowRight(Position pos) {
    return pos.x() > currentPosition().x() + 1;
  }

  private boolean isFollowUp(Position pos) {
    return pos.y() > currentPosition().y() + 1;
  }

  private boolean isFollowDown(Position pos) {
    return pos.y() < currentPosition().y() - 1;
  }
}

package com.santa.puzlle.y2021.daytwo;

public final class SubMarine extends AbstractSubMarine {

  public SubMarine() {
    depth = 0;
  }

  public SubMarine up(int step) {
    depth = depth - step;
    return this;
  }

  public SubMarine forward(int step) {
    horizontalPosition = horizontalPosition + step;
    return this;
  }
}

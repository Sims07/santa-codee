package com.santa.puzlle.daytwo;

public final class AimSubMarine extends AbstractSubMarine {
  private int aim;

  public int aim() {
    return aim;
  }

  @Override
  public AimSubMarine up(int step) {
    aim = aim - step;
    return this;
  }

  @Override
  public AimSubMarine down(int step) {
    super.down(step);
    return this;
  }

  @Override
  public AimSubMarine forward(int step) {
    addToHorizontalPosition(step);
    addToDepth(aim * step);
    return this;
  }
}

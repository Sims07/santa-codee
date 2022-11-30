package com.santa.puzlle.day15;

import java.util.Objects;

public record RiskPosition(int riskLevel, boolean marked,
                           Position position) implements Cloneable {

  public RiskPosition marked(boolean marked) {
    return new RiskPosition(this.riskLevel, marked, this.position);
  }

  @Override
  public RiskPosition clone() {
    return new RiskPosition(this.riskLevel, this.marked, this.position);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RiskPosition that = (RiskPosition) o;
    return Objects.equals(position, that.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position);
  }
}

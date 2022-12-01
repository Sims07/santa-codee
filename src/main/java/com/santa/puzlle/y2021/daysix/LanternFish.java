package com.santa.puzlle.y2021.daysix;

import java.util.Objects;
import java.util.Optional;

public class LanternFish {
  private int remainingDays;

  public LanternFish(int i) {
    remainingDays = i;
  }

  public Optional<LanternFish> nextDay() {
    remainingDays--;
    if (remainingDays < 0) {
      remainingDays = 6;
      return Optional.of(new LanternFish(8));
    }
    return Optional.empty();
  }

  public int remainingDays() {
    return remainingDays;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LanternFish that = (LanternFish) o;
    return remainingDays == that.remainingDays;
  }

  @Override
  public int hashCode() {
    return Objects.hash(remainingDays);
  }
}

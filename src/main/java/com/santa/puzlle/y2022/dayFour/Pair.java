package com.santa.puzlle.y2022.dayFour;

import java.util.List;

public record Pair(List<CleaningSection> cleaningSections) {

  public static Pair from(List<CleaningSection> zones) {
    return new Pair(zones);
  }

  public boolean isOverlapped() {
    final CleaningSection cleaningSection1 = cleaningSections.get(0);
    final CleaningSection cleaningSection2 = cleaningSections.get(1);
    return cleaningSection1.in(cleaningSection2)||cleaningSection2.in(cleaningSection1);
  }
}

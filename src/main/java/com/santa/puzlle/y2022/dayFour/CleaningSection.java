package com.santa.puzlle.y2022.dayFour;

public record CleaningSection(int from, int to) {

  public static CleaningSection from(int from, int to) {
    return new CleaningSection(from,to);
  }

  public boolean in(CleaningSection cleaningSection) {
    return from>=cleaningSection.from && to<=cleaningSection.to;
  }
}

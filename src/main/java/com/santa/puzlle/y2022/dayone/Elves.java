package com.santa.puzlle.y2022.dayone;

import java.util.Comparator;
import java.util.List;

public record Elves(List<Elf> elves) {

  public Elf elfWithMaxCalory() {
    return elves.stream().max(Comparator.comparing(Elf::totalCalories)).orElseThrow();
  }

  public Long topThreeTotalCalories() {
    List<Elf> sortedElves =
        elves.stream().sorted(Comparator.comparing(Elf::totalCalories).reversed()).toList();
    return sortedElves.get(0).totalCalories()
        + sortedElves.get(1).totalCalories()
        + sortedElves.get(2).totalCalories();
  }
}

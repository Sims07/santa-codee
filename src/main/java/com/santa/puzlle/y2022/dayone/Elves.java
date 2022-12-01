package com.santa.puzlle.y2022.dayone;

import java.util.Comparator;
import java.util.List;

public record Elves(List<Elf> elves) {

  public Elf elfWithMaxCalorie() {
    return elves.stream().max(Comparator.comparing(Elf::totalCalories)).orElseThrow();
  }

  public Long topThreeTotalCalories() {
    return elves.stream()
        .sorted(Comparator.comparing(Elf::totalCalories).reversed())
        .toList()
        .subList(0, 3)
        .stream()
        .mapToLong(Elf::totalCalories)
        .sum();
  }
}

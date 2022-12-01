package com.santa.puzlle.y2022.dayone;

import java.util.ArrayList;
import java.util.List;

public record Elf(List<Integer> calories) {

  public Elf addCalorie(Integer calorie) {
    final ArrayList<Integer> newCalories = new ArrayList<>(calories);
    newCalories.add(calorie);
    return new Elf(newCalories);
  }

  public Long totalCalories() {
    return calories.stream().mapToLong(Integer::intValue).sum();
  }
}

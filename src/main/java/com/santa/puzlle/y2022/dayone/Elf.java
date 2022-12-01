package com.santa.puzlle.y2022.dayone;

import java.util.ArrayList;
import java.util.List;

public record Elf(List<Integer> calories) {

  public List<Integer> addCalory(Integer calory) {
    final ArrayList<Integer> newCalories = new ArrayList<>(calories);
    newCalories.add(calory);
    return newCalories;
  }

  public Long totalCalories() {
    return calories.stream().mapToLong(Integer::intValue).sum();
  }
}

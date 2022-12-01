package com.santa.puzlle.y2021.dayone;

import java.util.List;

public class Window {
  private final List<Integer> items;

  public Window(List<Integer> items) {
    this.items = items;
  }

  public int sum() {
    return items.stream().mapToInt(Integer::intValue).sum();
  }
}

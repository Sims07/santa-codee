package com.santa.puzlle.dayfour;

import java.util.ArrayList;
import java.util.List;

public class BingoNumbers {
  private int index = 0;
  private List<Integer> numbers = new ArrayList<>();

  public BingoNumbers add(int number) {
    numbers.add(number);
    return this;
  }

  public int next() {
    return numbers.get(index++);
  }
}

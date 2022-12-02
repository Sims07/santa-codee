package com.santa.puzlle.y2022.dayTwo;

import java.util.Arrays;
import java.util.List;

public enum Shape {
  ROCK(1, List.of("A", "X")),
  SCISSORS(3, List.of("C", "Z")),
  PAPER(2, List.of("B", "Y"));

  int value;
  int responseCode;
  List<String> codes;

  Shape(int value, List<String> code) {
    this.value = value;
    this.codes = code;
  }

  public static Shape from(String code) {
    return Arrays.stream(values()).filter(c -> c.codes.contains(code)).findAny().orElseThrow();
  }
}

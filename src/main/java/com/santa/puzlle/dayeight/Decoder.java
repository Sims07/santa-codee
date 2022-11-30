package com.santa.puzlle.dayeight;

import java.util.Arrays;
import java.util.List;

public class Decoder {

  private List<String> digits;
  private List<String> inputs;

  public Decoder load(String input) {
    final String[] inputData = input.split("\\|");
    this.digits = Arrays.stream(inputData[0].split(" ")).toList();
    this.inputs = Arrays.stream(inputData[1].split(" ")).toList();
    return this;
  }

  public int numberOfUniqueInput() {
    return this.inputs.stream()
        .map(String::length)
        .filter(l -> l == 4 || l == 2 || l == 3 || l == 7)
        .reduce(0, (a, b) -> a + 1);
  }
}

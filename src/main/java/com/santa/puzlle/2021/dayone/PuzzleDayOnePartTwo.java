package com.santa.puzlle.dayone;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PuzzleDayOnePartTwo extends com.santa.puzlle.dayone.AbstractPuzzleDayOne {

  public PuzzleDayOnePartTwo load(List<Integer> inputs) {
    depths(
        IntStream.range(0, inputs.size())
            .filter(i -> i + 3 <= inputs.size())
            .mapToObj(i -> new com.santa.puzlle.dayone.Window(inputs.subList(i, i + 3)))
            .map(com.santa.puzlle.dayone.Window::sum)
            .collect(Collectors.toList()));
    return this;
  }
}

package com.santa.puzlle.y2021.dayone;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PuzzleDayOnePartTwo extends AbstractPuzzleDayOne {

  public PuzzleDayOnePartTwo load(List<Integer> inputs) {
    depths(
        IntStream.range(0, inputs.size())
            .filter(i -> i + 3 <= inputs.size())
            .mapToObj(i -> new Window(inputs.subList(i, i + 3)))
            .map(Window::sum)
            .collect(Collectors.toList()));
    return this;
  }
}

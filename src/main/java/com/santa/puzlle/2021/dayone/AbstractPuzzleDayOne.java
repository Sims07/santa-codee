package com.santa.puzlle.dayone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPuzzleDayOne {
  private List<Integer> depths;
  private int previousDepthIndex = 0;
  private int numberOfIncrease = 0;
  private long timeElapsed;

  public AbstractPuzzleDayOne load(String path) throws IOException {
    try (Stream<String> lines = Files.lines(Paths.get(path))) {
      load(lines.map(Integer::valueOf).collect(Collectors.toList()));
    }
    return this;
  }

  public abstract AbstractPuzzleDayOne load(List<Integer> inputs);

  void depths(List<Integer> inputs) {
    this.depths = new ArrayList<>(inputs);
  }

  public int numberOfIncrease() {
    long start = System.nanoTime();
    while (currentDepthIndex() < depths.size()) {
      if (previousDepth() < currentDepth()) {
        incrementNumberOfIncrease();
      }
      goToNextDepthIndex();
    }
    long finish = System.nanoTime();
    this.timeElapsed = finish - start;
    System.out.println(timeElapsed);
    return numberOfIncrease;
  }

  private void goToNextDepthIndex() {
    previousDepthIndex++;
  }

  private void incrementNumberOfIncrease() {
    numberOfIncrease++;
  }

  private int currentDepthIndex() {
    return previousDepthIndex + 1;
  }

  private Integer currentDepth() {
    return depths.get(currentDepthIndex());
  }

  private Integer previousDepth() {
    return depths.get(previousDepthIndex);
  }
}

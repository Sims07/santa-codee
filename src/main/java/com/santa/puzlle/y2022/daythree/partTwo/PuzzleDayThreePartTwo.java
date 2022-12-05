package com.santa.puzlle.y2022.daythree.partTwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PuzzleDayThreePartTwo {
  public RuckSacks load(String path) throws IOException {
    RuckSacks ruckSacks;
    final AtomicInteger counter = new AtomicInteger(0);
    try (Stream<String> lines = Files.lines(Paths.get(path))) {
      Collection<List<String>> lineGrouped = lines
          .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 3 ))
          .values();

      ruckSacks = new RuckSacks( lineGrouped.stream().map(RuckSackGroup::from).toList());
    }
    return ruckSacks;
  }

}

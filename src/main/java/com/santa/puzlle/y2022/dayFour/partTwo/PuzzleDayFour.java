package com.santa.puzlle.y2022.dayFour.partTwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class PuzzleDayFour {
  public Pairs load(String path) throws IOException {
    try (Stream<String> lines = Files.lines(Paths.get(path))) {
      return new Pairs(lines.map(this::toPair).toList());
    }
  }

  private Pair toPair(String line) {
    final String[] split = line.split(",");
    CleaningSection cleaningSection1= toCleaningSection(split[0]);
    CleaningSection cleaningSection2= toCleaningSection(split[1]);
    return new Pair(List.of(cleaningSection1,cleaningSection2));  }

  private CleaningSection toCleaningSection(String subLine) {
    final String[] split = subLine.split("-");
    return new CleaningSection(Integer.valueOf(split[0]),Integer.valueOf(split[1]));
  }

}

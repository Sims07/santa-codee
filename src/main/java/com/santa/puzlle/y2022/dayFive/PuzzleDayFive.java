package com.santa.puzlle.y2022.dayFive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class PuzzleDayFive {
  public List<Move> load(String path) throws IOException {
    try (Stream<String> lines = Files.lines(Paths.get(path))) {
      return lines.map(Move::from).toList();
    }
  }

}

package com.santa.puzlle.y2022.daythree;

import static java.util.Collections.emptyList;

import com.santa.puzlle.y2022.dayone.Elf;
import com.santa.puzlle.y2022.dayone.Elves;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PuzzleDayThree {
  public RuckSacks load(String path) throws IOException {
    RuckSacks ruckSacks;
    try (Stream<String> lines = Files.lines(Paths.get(path))) {
      ruckSacks = load(lines);
    }
    return ruckSacks;
  }

  public RuckSacks load(Stream<String> lines) {
    List<RuckSack> ruckSacks = new ArrayList<>();
    return new RuckSacks(
        lines.map(RuckSack::from).toList());
  }

}

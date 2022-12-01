package com.santa.puzlle.y2022.dayone;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractPuzzleDayOne {

  public Elves load(String path) throws IOException {
    Elves elves;
    try (Stream<String> lines = Files.lines(Paths.get(path))) {
      elves = load(lines);
    }
    return elves;
  }

  public Elves load(Stream<String> lines) {
    List<Elf> elves = new ArrayList<>();
    elves.add(new Elf(new ArrayList<>()));
    return new Elves(
        lines
            .map(
                s -> {
                  if (s.isEmpty()) {
                    return -1;
                  } else {
                    return Integer.valueOf(s);
                  }
                })
            .toList()
            .stream()
            .reduce(
                elves,
                (subtotal, calory) -> {
                  if (calory < 0) {
                    subtotal.add(new Elf(new ArrayList<>()));
                  } else {
                    final int index = subtotal.size() - 1;
                    final Elf elf = subtotal.get(index);
                    subtotal.set(index, new Elf(elf.addCalory(calory)));
                  }
                  return subtotal;
                },
                (list1, list2) -> emptyList()));
  }
}

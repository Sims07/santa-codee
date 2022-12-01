package com.santa.puzlle.y2022.dayone;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class PuzzleDayOne {

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
        lines.map(toCalorie()).toList().stream()
            .reduce(elves, reduceToElves(), (list1, list2) -> emptyList()));
  }

  private static BiFunction<List<Elf>, Integer, List<Elf>> reduceToElves() {
    return (subtotal, calorie) -> {
      if (newElf(calorie)) {
        subtotal.add(new Elf(new ArrayList<>()));
      } else {
        final int index = subtotal.size() - 1;
        final Elf elf = subtotal.get(index);
        subtotal.set(index, elf.addCalorie(calorie));
      }
      return subtotal;
    };
  }

  private static boolean newElf(Integer calorie) {
    return calorie < 0;
  }

  private static Function<String, Integer> toCalorie() {
    return s -> {
      if (s.isEmpty()) {
        return -1;
      } else {
        return Integer.valueOf(s);
      }
    };
  }
}

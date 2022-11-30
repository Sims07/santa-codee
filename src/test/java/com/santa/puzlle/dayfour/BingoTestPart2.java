package com.santa.puzlle.dayfour;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class BingoTestPart2 {
  @Test
  void givenColumnCompleted_playExistingNumber_shouldMarkBoards() throws IOException {
    BingoNumbers bingoNumbers = new BingoNumbers();
    final BingoLastWin bingo = new BingoLastWin(bingoNumbers);
    loadFileData(
        bingoNumbers,
        bingo,
        "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day4/day4part2Test.txt");
    while (!bingo.lastWinnerExist()) {
      bingo.play();
    }
    BDDAssertions.then(bingo.score()).isEqualTo(1924);
  }

  @Test
  void givenPuzzleOne_numberOfIncrease() throws IOException {
    BingoNumbers bingoNumbers = new BingoNumbers();
    final BingoLastWin bingo = new BingoLastWin(bingoNumbers);
    loadFileData(
        bingoNumbers,
        bingo,
        "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day4/day4part2.txt");
    while (!bingo.lastWinnerExist()) {
      bingo.play();
    }
    BDDAssertions.then(bingo.score()).isEqualTo(20774);
  }

  private void loadFileData(BingoNumbers bingoNumbers, BingoLastWin bingo, String pathTestFile)
      throws IOException {
    try (Stream<String> lines = Files.lines(Paths.get(pathTestFile))) {
      final List<String> linesList = lines.toList();
      Arrays.stream(linesList.get(0).split(",")).map(Integer::valueOf).forEach(bingoNumbers::add);
      final List<String> boards = linesList.stream().filter(line -> !line.contains(",")).toList();
      AtomicReference<BingoBoard> currentBingoBoard = new AtomicReference<>();
      boards.forEach(
          l -> {
            System.out.println(l);
            if (l.isEmpty()) {
              currentBingoBoard.set(new BingoBoard());
              bingo.add(currentBingoBoard.get());
            } else {
              Scanner scanner = new Scanner(l);
              while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                  currentBingoBoard.get().add(scanner.nextInt());
                }
              }
            }
          });
    }
  }
}

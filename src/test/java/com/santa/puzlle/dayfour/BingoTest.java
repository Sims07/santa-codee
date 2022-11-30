package com.santa.puzlle.dayfour;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class BingoTest {
  @Test
  void givenLineCompleted_playExistingNumber_shouldMarkBoards() {
    final BingoNumbers bingoNumbers = new BingoNumbers().add(22).add(13).add(17).add(11).add(0);
    final int[][] board = {
      {22, 13, 17, 11, 0},
      {8, 2, 23, 4, 24},
      {21, 9, 14, 16, 7},
      {6, 10, 3, 18, 5},
      {1, 12, 20, 15, 19}
    };
    final BingoBoard bingoBoard = new BingoBoard(board);
    final Bingo bingo = new Bingo(bingoNumbers).add(bingoBoard);
    IntStream.range(0, 4).forEach(i -> bingo.play());
    BDDAssertions.then(bingo.winnerExist()).isFalse();
    bingo.play();
    BDDAssertions.then(bingo.winnerExist()).isTrue();
    BDDAssertions.then(bingo.score()).isEqualTo((61 + 67 + 42 + 57) * 0);
  }

  @Test
  void givenColumnCompleted_playExistingNumber_shouldMarkBoards() {
    final BingoNumbers bingoNumbers = new BingoNumbers().add(22).add(8).add(21).add(6).add(1);
    final int[][] board = {
      {22, 13, 17, 11, 0},
      {8, 2, 23, 4, 24},
      {21, 9, 14, 16, 7},
      {6, 10, 3, 18, 5},
      {1, 12, 20, 15, 19}
    };
    final BingoBoard bingoBoard = new BingoBoard(board);
    final Bingo bingo = new Bingo(bingoNumbers).add(bingoBoard);
    IntStream.range(0, 4).forEach(i -> bingo.play());
    BDDAssertions.then(bingo.winnerExist()).isFalse();
    bingo.play();
    BDDAssertions.then(bingo.winnerExist()).isTrue();
    BDDAssertions.then(bingo.score()).isEqualTo((41 + 53 + 46 + 36 + 66) * 1);
  }

  @Test
  void givenPuzzleOne_numberOfIncrease() throws IOException {
    BingoNumbers bingoNumbers = new BingoNumbers();
    final Bingo bingo = new Bingo(bingoNumbers);
    loadFileData(bingoNumbers, bingo);
    while (!bingo.winnerExist()) {
      bingo.play();
    }
    BDDAssertions.then(bingo.score()).isEqualTo(82440);
  }

  private void loadFileData(BingoNumbers bingoNumbers, Bingo bingo) throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-code/src/test/resources/day4/day4part1.txt"))) {
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

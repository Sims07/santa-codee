package com.santa.puzlle.y2022.daySeven;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class PuzzleDaySevenTest {
  @Test
  void givenCommandCDRoot_commandHome_shouldCreateAnEmptyDirectory() {
    Nodes nodes = Nodes.init();
    final Nodes nodes1 = nodes.apply(Command.of("$ cd /"));

    then(nodes1).isNotNull();
    then(nodes1.root()).isEqualTo(new Directory("/", new ArrayList<>()));
  }

  @Test
  void givenDirectoryNode_commandLs_shouldFillTheDirectory() {
    Nodes nodes = Nodes.init();
    final Nodes updatedNodes =
        nodes
            .apply(Command.of("$ cd /"))
            .apply(Command.of("$ ls", "dir a", "14848514 b.txt", "8504156 c.dat"));

    then(updatedNodes).isNotNull();
    then(updatedNodes.root().childNodes()).isNotEmpty();
    then(updatedNodes.root().childNodes().get(0)).isInstanceOf(Directory.class);
    then(updatedNodes.root().childNodes().get(0).name()).isEqualTo("a");
    then(updatedNodes.root().childNodes().get(0).size()).isEqualTo(-1L);
    then(updatedNodes.root().childNodes().get(1)).isInstanceOf(File.class);
    then(updatedNodes.root().childNodes().get(1).name()).isEqualTo("b.txt");
    then(updatedNodes.root().childNodes().get(1).size()).isEqualTo(14848514);
    then(updatedNodes.root().childNodes().get(2)).isInstanceOf(File.class);
    then(updatedNodes.root().childNodes().get(2).name()).isEqualTo("c.dat");
    then(updatedNodes.root().childNodes().get(2).size()).isEqualTo(8504156);
  }

  @Test
  void givenDirectoryNodeWithFile_commandLs_shouldFillTheDirectory() {
    Nodes nodes = Nodes.init();
    final Nodes updatedNodes =
        nodes
            .apply(Command.of("$ cd /"))
            .apply(Command.of("$ ls", "dir a", "14848514 b.txt", "8504156 c.dat"))
            .apply(Command.of("$ cd a"));

    then(updatedNodes).isNotNull();
    then(updatedNodes.path().peek()).isEqualTo("a");
  }

  @Test
  void givenDirectoryNodeWithFilesLs_commandLs_shouldFillTheDirectory() {
    Nodes nodes = Nodes.init();
    final Nodes updatedNodes =
        nodes
            .apply(Command.of("$ cd /"))
            .apply(Command.of("$ ls", "dir a", "14848514 b.txt", "8504156 c.dat"))
            .apply(Command.of("$ cd a"))
            .apply(Command.of("$ ls", "dir e", "29116 f", "2557 g", "62596 h.lst"));

    then(updatedNodes).isNotNull();
    then(updatedNodes.root().name()).isEqualTo("/");
    then(updatedNodes.root().childNodes().size()).isEqualTo(3);
    then(updatedNodes.path().peek()).isEqualTo("a");
  }

  @Test
  void givenCDirectoryBack_commandLs_shouldGetParentDirectory() {
    Nodes nodes = Nodes.init();
    final Nodes updatedNodes =
        nodes
            .apply(Command.of("$ cd /"))
            .apply(Command.of("$ ls", "dir a", "14848514 b.txt", "8504156 c.dat", "dir d"))
            .apply(Command.of("$ cd a"))
            .apply(Command.of("$ ls", "dir e", "29116 f", "2557 g", "62596 h.lst"))
            .apply(Command.of("$ cd e"))
            .apply(Command.of("$ ls", "584 i"));

    then(updatedNodes).isNotNull();
    then(updatedNodes.root().name()).isEqualTo("/");
    then(updatedNodes.root().childNodes().size()).isEqualTo(4);
    then(updatedNodes.root().childNodes().get(0).name()).isEqualTo("a");
    then(updatedNodes.root().childNodes().get(0).childNodes().size()).isEqualTo(4);

    final Nodes apply = updatedNodes.apply(Command.of("$ cd .."));
    then(apply.path().peek()).isEqualTo("a");

    final Nodes apply2 = updatedNodes.apply(Command.of("$ cd .."));
    then(apply2.path().peek()).isEqualTo("/");

    final Nodes apply3 =
        updatedNodes
            .apply(Command.of("$ cd d"))
            .apply(Command.of("$ ls", "4060174 j", "8033020 d.log", "5626152 d.ext", "7214296 k"));
    then(apply3.path().peek()).isEqualTo("d");

    then(apply3.computeDirectoriesSize()).isEqualTo(95437);
  }

  @Test
  void puzzle() throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/daySeven/day7part1.txt"))) {
      Nodes nodes = Nodes.init();
      AtomicBoolean inLs = new AtomicBoolean(false);
      List<String> lsArgs = new ArrayList<>();
      lines.forEach(
          line -> {
            if (line.contains("$ cd")) {
              if (inLs.get()) {
                inLs.set(false);
                nodes.apply(Command.of("$ ls", lsArgs));
                lsArgs.clear();
              }
              nodes.apply(Command.of(line));
            } else if (line.contains("$ ls")) {
              inLs.set(true);
            } else if (inLs.get()) {
              lsArgs.add(line);
            }
          });
      if (inLs.get()) {
        nodes.apply(Command.of("$ ls", lsArgs));
      }
      final long directoriesSize = nodes.computeDirectoriesSize();
      System.out.println(directoriesSize);
    }
  }

  @Test
  void puzzlePartTwo() throws IOException {
    try (Stream<String> lines =
        Files.lines(
            Paths.get(
                "/Users/sc/Documents/projets/pocs/santa-codee/src/test/resources/2022/dayOne/daySeven/day7part2.txt"))) {
      Nodes nodes = Nodes.init();
      AtomicBoolean inLs = new AtomicBoolean(false);
      List<String> lsArgs = new ArrayList<>();
      lines.forEach(
          line -> {
            if (line.contains("$ cd")) {
              if (inLs.get()) {
                inLs.set(false);
                nodes.apply(Command.of("$ ls", lsArgs));
                lsArgs.clear();
              }
              nodes.apply(Command.of(line));
            } else if (line.contains("$ ls")) {
              inLs.set(true);
            } else if (inLs.get()) {
              lsArgs.add(line);
            }
          });
      if (inLs.get()) {
        nodes.apply(Command.of("$ ls", lsArgs));
      }
      final long directoriesSize = nodes.computeDirectoriesSizeToDelete();
      System.out.println(directoriesSize);
    }
  }
}

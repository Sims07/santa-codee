package com.santa.puzlle.y2022.dayEight;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public record Grid(Tree[][] trees) {

  public static Grid of(List<String> line) {
    Tree[][] trees = new Tree[line.size()][line.get(0).length()];
    AtomicInteger rawIndex = new AtomicInteger(-1), colIndex = new AtomicInteger(0);
    line.stream()
        .map(
            lineCur -> {
              rawIndex.incrementAndGet();
              return lineCur.toCharArray();
            })
        .forEach(
            lineSizes -> {
              for (char c : lineSizes) {
                trees[rawIndex.get()][colIndex.getAndIncrement()] =
                    Tree.of(Integer.valueOf(String.valueOf(c)));
              }
              colIndex.set(0);
            });
    return new Grid(trees);
  }

  public long nbVisibleTrees() {
    return edgeTreeNumber() + innerVisibleTrees();
  }

  long innerVisibleTrees() {
    int nbVisible = 0;
    for (int rawCur = 1; rawCur < trees.length - 1; rawCur++) {
      for (int colCur = 1; colCur < trees[0].length - 1; colCur++) {
        final Tree tree = trees[rawCur][colCur];
        if (visible(tree, rawCur, colCur)) {
          nbVisible++;
        }
      }
    }
    return nbVisible;
  }

  private boolean visible(Tree tree, int rawCur, int colCur) {
    final boolean visibleRigh = isVisibleRigh(tree, rawCur, colCur);
    final boolean visibleLeft = isVisibleLeft(tree, rawCur, colCur);
    final boolean visibleTop = isVisibleTop(tree, rawCur, colCur);
    final boolean visibleBottom = isVisibleBottom(tree, rawCur, colCur);
    return visibleRigh || visibleLeft || visibleTop || visibleBottom;
  }

  private boolean isVisibleTop(Tree tree, int rawCur, int colCur) {
    boolean visible = true;
    for (int i = rawCur - 1; i >= 0; i--) {
      if (trees[i][colCur].size() >= tree.size()) {
        visible = false;
        break;
      }
    }
    return visible;
  }

  private boolean isVisibleBottom(Tree tree, int rawCur, int colCur) {
    boolean visible = true;
    for (int i = rawCur + 1; i < trees.length; i++) {
      if (trees[i][colCur].size() >= tree.size()) {
        visible = false;
        break;
      }
    }
    return visible;
  }

  private boolean isVisibleLeft(Tree tree, int rawCur, int colCur) {
    boolean visible = true;
    for (int j = colCur - 1; j >= 0; j--) {
      if (trees[rawCur][j].size() >= tree.size()) {
        visible = false;
        break;
      }
    }
    return visible;
  }

  boolean isVisibleRigh(Tree tree, int rawCur, int colCur) {
    boolean visible = true;
    for (int j = colCur + 1; j < trees.length; j++) {
      if (trees[rawCur][j].size() >= tree.size()) {
        visible = false;
        break;
      }
    }

    return visible;
  }

  private long scenicViewTop(Tree tree, int rawCur, int colCur) {
    int distance = 0;
    for (int i = rawCur - 1; i >= 0; i--) {
      distance++;
      if (trees[i][colCur].size() >= tree.size()) {
        break;
      }
    }
    return distance;
  }

  private long scenicViewBottom(Tree tree, int rawCur, int colCur) {
    int distance = 0;
    for (int i = rawCur + 1; i < trees.length; i++) {
      distance++;
      if (trees[i][colCur].size() >= tree.size()) {
        break;
      }
    }
    return distance;
  }

  private long scenicViewLeft(Tree tree, int rawCur, int colCur) {
    int distance = 0;
    for (int j = colCur - 1; j >= 0; j--) {
      distance++;
      if (trees[rawCur][j].size() >= tree.size()) {
        break;
      }
    }
    return distance;
  }

  long scenicViewRight(Tree tree, int rawCur, int colCur) {
    int distance = 0;
    for (int j = colCur + 1; j < trees.length; j++) {
      distance++;
      if (trees[rawCur][j].size() >= tree.size()) {
        break;
      }
    }

    return distance;
  }

  public long edgeTreeNumber() {
    return trees[0].length * 2 + trees.length * 2 - 4;
  }

  public long highestScenicScore() {
    long highestScenicScore = 0;
    for (int rawCur = 1; rawCur < trees.length - 1; rawCur++) {
      for (int colCur = 1; colCur < trees[0].length - 1; colCur++) {
        final Tree tree = trees[rawCur][colCur];
        final long scenicScore = scenicScore(tree, rawCur, colCur);
        if (scenicScore > highestScenicScore) {
          highestScenicScore = scenicScore;
        }
      }
    }
    return highestScenicScore;
  }

  private long scenicScore(Tree tree, int rawCur, int colCur) {
    return scenicViewBottom(tree, rawCur, colCur)
        * scenicViewTop(tree, rawCur, colCur)
        * scenicViewLeft(tree, rawCur, colCur)
        * scenicViewRight(tree, rawCur, colCur);
  }
}

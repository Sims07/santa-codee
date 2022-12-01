package com.santa.puzlle.y2021.day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RiskMatrix {
  private final RiskPosition[][] matrix;
  private final Position finalPosition;
  private int minCache = 100000000;
  private Map<Position, Integer> minPosition = new HashMap<>();

  public RiskMatrix(int horSize, int vertSize) {
    this.matrix = new RiskPosition[horSize][vertSize];
    this.finalPosition = new Position(horSize - 1, vertSize - 1);
  }

  public RiskMatrix load(int x, String matrixStr) {
    for (int y = 0; y < matrixStr.length(); y++) {
      matrix[x][y] =
          new RiskPosition(
              x == 0 && y == 0 ? 0 : matrixStr.codePointAt(y) - 48, false, new Position(x, y));
    }
    return this;
  }

  public int shortenRisk() {
    return minRisk(matrix, new Position(0, 0), 0);
  }

  private int minRisk(RiskPosition[][] markedMatrix, Position currentPosition, int riskTotal) {
    final int currentRiskLevel =
        riskPosition(currentPosition.x(), currentPosition.y(), markedMatrix).riskLevel();
    final int newRisk = riskTotal + currentRiskLevel;
    if (newRisk > this.minCache) {
      return 10000001;
    }
    if (currentPosition.equals(finalPosition)) {
      this.minCache = newRisk;
      return newRisk;
    }
    final List<RiskPosition> neighBoorRiskPositions =
        neighboorPositions(currentPosition, markedMatrix);
    final RiskPosition[][] markedMatrix1 = matrixMarked(markedMatrix, currentPosition);
    return neighBoorRiskPositions.parallelStream()
        .mapToInt(
            riskPosition -> {
              if (minPosition.get(riskPosition.position()) != null) {
                return minPosition.get(riskPosition.position());
              }
              final int minRisk = minRisk(markedMatrix1, riskPosition.position(), newRisk);
              System.out.println(
                  "Min risk:" + minRisk + " for position:" + riskPosition.position());
              minPosition.put(riskPosition.position(), minRisk);
              return minRisk;
            })
        .min()
        .orElse(10000000);
  }

  private RiskPosition[][] matrixMarked(RiskPosition[][] markedMatrix, Position currentPosition) {
    final RiskPosition[][] riskPositions = deepCopy(markedMatrix);
    riskPositions[currentPosition.x()][currentPosition.y()] =
        riskPositions[currentPosition.x()][currentPosition.y()].marked(true);
    return riskPositions;
  }

  public static RiskPosition[][] deepCopy(RiskPosition[][] original) {
    if (original == null) {
      return null;
    }

    final RiskPosition[][] result = new RiskPosition[original.length][];
    for (int i = 0; i < original.length; i++) {
      result[i] =
          Arrays.stream(original[i])
              .map(RiskPosition::clone)
              .collect(Collectors.toList())
              .toArray(new RiskPosition[original.length]);
    }
    return result;
  }

  private List<RiskPosition> neighboorPositions(
      Position currentPosition, RiskPosition[][] markedMatrix) {
    List<RiskPosition> neighboor = new ArrayList<>();
    // position(markedMatrix, neighboor, currentPosition.x() - 1, currentPosition.y());
    position(markedMatrix, neighboor, currentPosition.x(), currentPosition.y() + 1);
    position(markedMatrix, neighboor, currentPosition.x(), currentPosition.y() - 1);
    position(markedMatrix, neighboor, currentPosition.x() + 1, currentPosition.y());
    return neighboor;
  }

  private void position(RiskPosition[][] markedMatrix, List<RiskPosition> neighboor, int i, int y) {
    final RiskPosition markedMatrixUp = riskPosition(i, y, markedMatrix);
    if (markedMatrixUp != null && !markedMatrixUp.marked()) {
      neighboor.add(markedMatrixUp);
    }
  }

  private RiskPosition riskPosition(int i, int y, RiskPosition[][] markedMatrix) {
    try {
      return markedMatrix[i][y];
    } catch (Exception e) {
      return null;
    }
  }
}

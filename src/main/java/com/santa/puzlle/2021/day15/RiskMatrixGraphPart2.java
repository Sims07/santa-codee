package com.santa.puzlle.day15;

import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

public class RiskMatrixGraphPart2 {
  private final RiskPosition[][] matrix;
  private final int horSize;
  private final int vertSize;
  Graph<RiskPosition, DefaultWeightedEdge> graph =
      new DefaultDirectedWeightedGraph(DefaultWeightedEdge.class);

  public RiskMatrixGraphPart2(int horSize, int vertSize) {
    this.horSize = horSize;
    this.vertSize = vertSize;
    this.matrix = new RiskPosition[this.horSize * 5][this.vertSize * 5];
  }

  public RiskMatrixGraphPart2 load(int x, String matrixStr) {
    for (int y = 0; y < matrixStr.length(); y++) {
      final int riskLevel = matrixStr.codePointAt(y) - 48;
      matrix[x][y] = new RiskPosition(riskLevel, false, new Position(x, y));
      graph.addVertex(new RiskPosition(riskLevel, false, new Position(x, y)));
      for (int i = 1; i < 5; i++) {
        final int nextYPos = y + i * horSize;
        final int riskLevel1 = nextRiskLevel(riskLevel, i);
        matrix[x][nextYPos] = new RiskPosition(riskLevel1, false, new Position(x, nextYPos));
        // if (x > 300 && nextYPos < 200 || x < 200 && nextYPos > 200) {
        // System.out.println("escape");
        // } else {
        graph.addVertex(new RiskPosition(riskLevel1, false, new Position(x, nextYPos)));
        // }
      }
    }
    for (int y = 0; y < matrix[0].length; y++) {
      final int riskLevel = matrix[x][y].riskLevel();
      for (int i = 1; i < 5; i++) {
        final int nextXPos = x + i * vertSize;
        final int riskLevel1 = nextRiskLevel(riskLevel, i);
        matrix[nextXPos][y] = new RiskPosition(riskLevel1, false, new Position(nextXPos, y));
        // if (x > 300 && y < 200 || nextXPos < 200 && y > 200) {
        // System.out.println("escape");
        // } else {
        graph.addVertex(new RiskPosition(riskLevel1, false, new Position(nextXPos, y)));
        // }
      }
    }

    return this;
  }

  public int nextRiskLevel(int originalRiskLevel, int i) {
    int riskLevel = (originalRiskLevel + 1 * i) % 9;
    return riskLevel == 0 ? 9 : riskLevel;
  }

  public RiskMatrixGraphPart2 init() {
    for (int x = 0; x < matrix.length; x++) {
      for (int y = 0; y < matrix[0].length; y++) {
        weightEdge(x, y, x + 1, y);
        weightEdge(x, y, x - 1, y);
        weightEdge(x, y, x, y + 1);
        weightEdge(x, y, x, y - 1);
      }
    }
    return this;
  }

  private void weightEdge(int x, int y, int x1, int y1) {
    try {
      final DefaultWeightedEdge defaultEdge3 = graph.addEdge(matrix[x][y], matrix[x1][y1]);
      final int weight = matrix[x1][y1].riskLevel();
      graph.setEdgeWeight(defaultEdge3, weight);
      // System.out.println("Add adges from " + x + "," + y + " to " + x1 + "," + y1 + "=" +
      // weight);
    } catch (Exception e) {
    }
  }

  public int shortenRisk() {
    DijkstraShortestPath<RiskPosition, DefaultEdge> dijkstraShortestPath =
        new DijkstraShortestPath(graph);

    List<RiskPosition> shortestPath =
        dijkstraShortestPath
            .getPath(matrix[0][0], matrix[matrix.length - 1][matrix[0].length - 1])
            .getVertexList();
    final long nbPosZero =
        shortestPath.stream()
            .filter(riskPosition -> riskPosition.position().equals(new Position(0, 0)))
            .count();
    final int sum = shortestPath.stream().mapToInt(RiskPosition::riskLevel).sum();
    if (nbPosZero == 1) {
      final Integer sub =
          shortestPath.stream()
              .filter(riskPosition -> riskPosition.position().equals(new Position(0, 0)))
              .findAny()
              .map(RiskPosition::riskLevel)
              .orElse(0);
      final int res = sum - sub;
      System.out.println("RES1:" + res);
      return res;
    }
    System.out.println("RES2:" + sum);
    return sum;
  }
}

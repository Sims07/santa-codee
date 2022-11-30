package com.santa.puzlle.day15;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

public class RiskMatrixGraph {
  private final RiskPosition[][] matrix;
  private final Position finalPosition;
  private int minCache = 100000000;
  private Map<Position, Integer> minPosition = new HashMap<>();
  Graph<RiskPosition, DefaultWeightedEdge> graph =
      new DefaultDirectedWeightedGraph(DefaultWeightedEdge.class);

  public RiskMatrixGraph(int horSize, int vertSize) {
    this.matrix = new RiskPosition[horSize][vertSize];
    this.finalPosition = new Position(horSize - 1, vertSize - 1);
  }

  public RiskMatrixGraph load(int x, String matrixStr) {
    for (int y = 0; y < matrixStr.length(); y++) {
      final int riskLevel = matrixStr.codePointAt(y) - 48;
      matrix[x][y] = new RiskPosition(riskLevel, false, new Position(x, y));
      graph.addVertex(new RiskPosition(riskLevel, false, new Position(x, y)));
    }
    return this;
  }

  public RiskMatrixGraph init() {
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
      System.out.println("Add adges from " + x + "," + y + " to " + x1 + "," + y1 + "=" + weight);
    } catch (Exception e) {
    }
  }

  public int shortenRisk() {
    DijkstraShortestPath<RiskPosition, DefaultEdge> dijkstraShortestPath =
        new DijkstraShortestPath(graph);
    System.out.println(graph);
    List<RiskPosition> shortestPath =
        dijkstraShortestPath
            .getPath(matrix[0][0], matrix[matrix.length - 1][matrix[0].length - 1])
            .getVertexList();
    final long nbPosZero =
        shortestPath.stream()
            .filter(riskPosition -> riskPosition.position().equals(new Position(0, 0)))
            .count();
    final int sum = shortestPath.stream().mapToInt(riskPosition -> riskPosition.riskLevel()).sum();
    if (nbPosZero == 1) {
      final Integer sub =
          shortestPath.stream()
              .filter(riskPosition -> riskPosition.position().equals(new Position(0, 0)))
              .findAny()
              .map(RiskPosition::riskLevel)
              .orElse(0);
      return sum - sub;
    }
    return sum;
  }
}

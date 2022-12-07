package com.santa.puzlle.y2022.daySeven;

import java.util.List;

public record Directory(String name, List<Node> childNodes) implements Node {

  @Override
  public void addNode(String lsResLineToDecode) {
    childNodes.add(NodeFactory.of(lsResLineToDecode));
  }

  @Override
  public long size() {
    return childNodes.stream().mapToLong(Node::size).sum();
  }
}

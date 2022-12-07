package com.santa.puzlle.y2022.daySeven;

import java.util.List;

public record File(String name, List<Node> childNodes, long size) implements Node {

  public File(String name, long size) {
    this(name, null, size);
  }

  @Override
  public void addNode(String lsResLineToDecode) {
    throw new UnsupportedOperationException("File named %s".formatted(name));
  }
}

package com.santa.puzlle.y2022.daySeven;

import java.util.List;

public interface Node {

  String name();

  List<Node> childNodes();

  void addNode(String lsResLineToDecode);

  long size();
}

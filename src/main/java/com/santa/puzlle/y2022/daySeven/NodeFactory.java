package com.santa.puzlle.y2022.daySeven;

import java.util.ArrayList;

public class NodeFactory {

  public static Node of(String lsResLineToDecode) {
    final String[] splitLine = lsResLineToDecode.split(" ");
    final String nodeName = splitLine[1];
    if (splitLine[0].equals("dir")) {
      return new Directory(nodeName, new ArrayList<>());
    }
    return new File(nodeName, Long.valueOf(splitLine[0]));
  }
}

package com.santa.puzlle.y2022.daySeven;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public record Nodes(Node root, Stack<String> path) {

  public static Nodes init() {
    final Stack<String> path = new Stack<>();
    path.push("/");
    return new Nodes(new Directory("/", new ArrayList<>()), path);
  }

  public Nodes apply(Command cmd) {
    System.out.println(cmd);

    switch (cmd.action()) {
      case CD -> cd(cmd.argsOrRes().get(0));
      case LS -> ls(cmd.argsOrRes());
    }
    System.out.println(this);
    return this;
  }

  private Node ls(List<String> lsResLine) {
    Node currentNode = currentNode();
    for (String line : lsResLine) {
      currentNode.addNode(line);
    }
    return root;
  }

  private Node currentNode() {
    final List<String> pathList = path.stream().toList().subList(1, path.size());
    Node curren = root;
    for (String pathSgment : pathList) {
      curren =
          curren.childNodes().stream()
              .filter(n -> n.name().equals(pathSgment))
              .findAny()
              .orElseThrow();
    }
    return curren;
  }

  private Node findNode(String dir, List<Node> childNodes) {
    return childNodes.stream()
        .map(n -> findNode(dir, n))
        .filter(o -> o != null)
        .findAny()
        .orElse(null);
  }

  private Node findNode(String dir, Node node) {
    if (node.name().equals(dir)) {
      return node;
    }
    if (node.childNodes() != null) {
      return findNode(dir, node.childNodes());
    }
    return null;
  }

  private Stack<String> cd(String directoryName) {
    if (directoryName.equals("/")) {
      path.clear();
      path.push("/");
    } else if (directoryName.equals("..")) {
      path.pop();
    } else {
      path.push(directoryName);
    }
    return path;
  }

  public long computeDirectoriesSize() {
    final List<Directory> directories = new ArrayList<>();
    directories(root, directories);
    System.out.println(directories);
    final List<SizedDirectory> sizedDirectories =
        directories.stream()
            .map(dir -> new SizedDirectory(dir, dir.size()))
            .filter(dir -> dir.size() < 100000)
            .toList();
    System.out.println(sizedDirectories);
    return sizedDirectories.stream().mapToLong(SizedDirectory::size).sum();
  }

  public long computeDirectoriesSizeToDelete() {
    final List<Directory> directories = new ArrayList<>();
    directories(root, directories);
    System.out.println(directories);
    int unusedSpace = 70000000 - 41518953;
    int neededSpace = 30000000 - unusedSpace;
    final List<SizedDirectory> sizedDirectories =
        directories.stream()
            .map(dir -> new SizedDirectory(dir, dir.size()))
            .filter(d -> d.size() >= neededSpace)
            .toList();
    return sizedDirectories.stream().mapToLong(SizedDirectory::size).sum();
  }

  private List<Directory> directories(Node n, List<Directory> directories) {
    if (n instanceof Directory) {
      directories.add((Directory) n);
      return n.childNodes().stream()
          .map(node -> directories(node, directories))
          .flatMap(List::stream)
          .toList();
    }
    return new ArrayList<>();
  }

  private void computeSize(Node root) {
    root.size();
  }
}

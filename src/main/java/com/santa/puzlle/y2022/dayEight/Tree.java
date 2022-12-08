package com.santa.puzlle.y2022.dayEight;

public final class Tree {

  private final int size;
  private boolean visible;

  public Tree(int size) {
    this.size = size;
  }

  public static Tree of(int size) {
    return new Tree(size);
  }

  public int size() {
    return size;
  }

  public boolean visible() {
    return visible;
  }

  public Tree setVisible(boolean visible) {
    this.visible = visible;
    return this;
  }

  @Override
  public String toString() {
    return "Tree[" + "size=" + size + ']';
  }
}

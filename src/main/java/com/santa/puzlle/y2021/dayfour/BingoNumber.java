package com.santa.puzlle.y2021.dayfour;

public class BingoNumber {
  private int value;
  private boolean marked;

  public BingoNumber(int value, boolean marked) {
    this.value = value;
    this.marked = marked;
  }

  public int value() {
    return value;
  }

  public boolean marked() {
    return marked;
  }

  public BingoNumber marked(boolean marked) {
    this.marked = marked;
    return this;
  }
}

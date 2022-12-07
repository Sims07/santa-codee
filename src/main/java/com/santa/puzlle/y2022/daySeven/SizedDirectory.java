package com.santa.puzlle.y2022.daySeven;

public record SizedDirectory(Directory directory, long size) {

  @Override
  public String toString() {
    return "SizedDirectory{" + "directory=" + directory.name() + ", size=" + size + '}';
  }
}

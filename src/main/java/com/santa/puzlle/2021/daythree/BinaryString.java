package com.santa.puzlle.daythree;

import java.nio.CharBuffer;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryString {
  private final List<Integer> bytes;

  public BinaryString(String binaryString) {
    this.bytes =
        CharBuffer.wrap(binaryString.toCharArray()).chars().mapToObj(c -> c - '0').toList();
  }

  public List<Integer> bytes() {
    return this.bytes;
  }

  public int value() {
    return Integer.parseInt(bytes.stream().map(String::valueOf).collect(Collectors.joining()), 2);
  }
}

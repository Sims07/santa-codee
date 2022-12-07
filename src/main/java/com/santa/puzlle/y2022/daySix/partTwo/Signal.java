package com.santa.puzlle.y2022.daySix.partTwo;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public record Signal(Supplier<IntStream> signal) {

  public static final int MARKER_LENGTH = 14;

  public static Signal of(String signalStr) {
    return new Signal(signalStr::chars);
  }

  public Marker findMarker() {
    final int[] signal = this.signal.get().toArray();
    int index = 0;
    while (index + MARKER_LENGTH < signal.length) {
      if (isMarker(index)) {
        return new Marker(index + MARKER_LENGTH);
      }
      index++;
    }
    return new Marker(index + MARKER_LENGTH);
  }

  private boolean isMarker(int index) {
    final List<Integer> subList =
        signal.get().boxed().toList().subList(index, index + MARKER_LENGTH);
    final int originalSize = subList.size();
    final int distinctSize = subList.stream().distinct().toList().size();
    return originalSize == distinctSize;
  }
}

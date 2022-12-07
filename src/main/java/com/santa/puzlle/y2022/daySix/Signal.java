package com.santa.puzlle.y2022.daySix;

import java.util.List;
import java.util.stream.IntStream;

public record Signal(IntStream signal) {

  public static Signal of(String signalStr) {
    return new Signal(signalStr.chars());
  }

  public Marker findMarker() {
    final int[] signal = this.signal.toArray();
    int index = 0;
    while (index + 4 < signal.length) {
      if (isMarker(signal[index + 0], signal[index + 1], signal[index + 2], signal[index + 3])) {
        return new Marker(index + 4);
      }
      index++;
    }
    return new Marker(index + 4);
  }

  private boolean isMarker(int s1, int s2, int s3, int s4) {
    return diff(s1, List.of(s2, s3, s4)) && diff(s2, List.of(s3, s4)) && s3 != s4;
  }

  private boolean diff(int s1, List<Integer> s2) {
    return s2.stream().noneMatch((s -> s.equals(s1)));
  }
}

package com.santa.puzlle.y2021.dayseven;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.LongStream;

public class ExpensiveCrabFleet {
  private final Map<Long, Long> crabPositions = new HashMap<>();
  private long max = -1L;
  private long min = 1000;

  public ExpensiveCrabFleet addCrab(long position) {
    crabPositions.merge(position, 1L, Long::sum);
    if (position > max) {
      max = position;
    }
    if (position < min) {
      min = position;
    }
    return this;
  }

  public long fuelToAlign() {
    AtomicReference<Long> fuelNeeded = new AtomicReference<>(Long.MAX_VALUE);
    LongStream.range(min, max)
        .forEach(
            position -> {
              final long computeFuelToGoTo = computeFuelToGoTo(position);
              if (computeFuelToGoTo < fuelNeeded.get()) {
                fuelNeeded.set(computeFuelToGoTo);
              }
            });
    return fuelNeeded.get();
  }

  private long computeFuelToGoTo(long position) {
    return crabPositions.entrySet().stream()
        .map(
            positionCrabs -> {
              final long n = Math.abs(positionCrabs.getKey() - position);
              return (n * (n + 1)) / 2 * positionCrabs.getValue();
            })
        .reduce(0L, Long::sum);
  }
}

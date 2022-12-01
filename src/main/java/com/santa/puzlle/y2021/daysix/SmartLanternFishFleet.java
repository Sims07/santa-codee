package com.santa.puzlle.y2021.daysix;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class SmartLanternFishFleet {
  private Map<Integer, Long> lanternFishFleet = new HashMap<>();

  void addLanternFish(int remainingDays) {
    addLanternFish(remainingDays, lanternFishFleet, 1);
  }

  void addLanternFish(int remainingDays, Map<Integer, Long> lanternFishFleet, long nbFishes) {
    lanternFishFleet.merge(remainingDays, nbFishes, Long::sum);
  }

  private void removeLanternFish(
      Integer remainingDays, Map<Integer, Long> lanternFishFleet, long nbFishes) {
    if (lanternFishFleet.get(remainingDays) != null) {
      lanternFishFleet.put(remainingDays, lanternFishFleet.get(remainingDays) - nbFishes);
    } else {
      lanternFishFleet.put(remainingDays, 0L);
    }
  }

  public void simulateNextDays(int daysToSimulate) {
    final long begin = System.nanoTime();
    IntStream.range(0, daysToSimulate)
        .forEach(
            iteration -> {
              HashMap<Integer, Long> lanternFishFleetTmp = new HashMap<>(lanternFishFleet);
              lanternFishFleet.forEach(
                  (remainingDays, nbFishes) -> {
                    if (nbFishes > 0) {
                      if (remainingDays - 1 < 0) {
                        addLanternFish(8, lanternFishFleetTmp, nbFishes);
                        addLanternFish(6, lanternFishFleetTmp, nbFishes);
                      } else {
                        addLanternFish(remainingDays - 1, lanternFishFleetTmp, nbFishes);
                      }
                      removeLanternFish(remainingDays, lanternFishFleetTmp, nbFishes);
                    }
                  });
              lanternFishFleet = new HashMap<>(lanternFishFleetTmp);
            });
    final long end = System.nanoTime();
    System.out.println(end - begin);
  }

  public long total() {
    return lanternFishFleet.values().stream().reduce(0L, Long::sum);
  }
}

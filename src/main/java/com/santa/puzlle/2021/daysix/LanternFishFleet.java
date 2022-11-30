package com.santa.puzlle.daysix;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class LanternFishFleet {
  private List<LanternFish> lanternFishFleet = new ArrayList<>();

  void addLanternFish(int remainingDays) {
    lanternFishFleet.add(new LanternFish(remainingDays));
  }

  public void simulateNextDays(int daysToSimulate) {
    IntStream.range(0, daysToSimulate)
        .forEach(
            iteration -> {
              System.out.println("Iteration :" + iteration);
              List<LanternFish> newLanternFishes = new ArrayList<>();
              lanternFishFleet.forEach(
                  lanternFish -> {
                    final Optional<LanternFish> newLanternFish = lanternFish.nextDay();
                    newLanternFish.ifPresent(newLanternFishes::add);
                  });
              lanternFishFleet.addAll(newLanternFishes);
            });
  }

  public int total() {
    return lanternFishFleet.size();
  }
}

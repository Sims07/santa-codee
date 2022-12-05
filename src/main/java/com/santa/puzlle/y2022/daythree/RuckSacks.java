package com.santa.puzlle.y2022.daythree;

import java.util.List;

public record RuckSacks(List<RuckSack> ruckSacks) {

  public List<Item> duplicateItems() {
    return ruckSacks.stream().map(RuckSack::duplicateItem).toList();
  }

  public long duplicateItemsValue() {
    return duplicateItems().stream().mapToInt(Item::value).sum();
  }
}

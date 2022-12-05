package com.santa.puzlle.y2022.daythree.partTwo;

import java.util.List;

public record RuckSacks(List<RuckSackGroup> ruckSacks) {

  public List<Item> duplicateItems() {
    return ruckSacks.stream().map(RuckSackGroup::duplicateItem).toList();
  }

  public long duplicateItemsValue() {
    return duplicateItems().stream().mapToInt(Item::value).sum();
  }

}

package com.santa.puzlle.y2022.daythree.partTwo;

import java.util.List;

public record RuckSackGroup(List<RuckSack> ruckSacks) {

  public static RuckSackGroup from(List<String> ruckSackStr) {
    return new RuckSackGroup(ruckSackStr.stream().map(RuckSack::from).toList());
  }

  public  Item duplicateItem() {
    final Compartment compartment1 = ruckSacks.get(0).compartments().get(0);
    final Compartment compartment2 = ruckSacks.get(1).compartments().get(0);
    final Compartment compartment3 = ruckSacks.get(2).compartments().get(0);
    List<Item> duplicatedItems = compartment1.items().stream().distinct()
        .filter(compartment2.items()::contains).filter(compartment3.items()::contains).toList();
    return duplicatedItems.get(0);
  }

}

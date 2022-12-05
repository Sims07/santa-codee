package com.santa.puzlle.y2022.daythree;

import java.util.ArrayList;
import java.util.List;

public record RuckSack(List<Compartment> compartments) {

  public static RuckSack from(String strRep) {
    List<Compartment> compartments1=new ArrayList<>();
    final int half = strRep.length() / 2;
    compartments1.add(Compartment.from(strRep.substring(0, half)));
    compartments1.add(Compartment.from(strRep.substring(half,strRep.length())));
    return new RuckSack(compartments1);
  }

  public Item duplicateItem() {
    final Compartment compartment1 = compartments.get(0);
    final Compartment compartment2 = compartments.get(1);
    List<Item> duplicatedItems = compartment1.items().stream().distinct()
        .filter(compartment2.items()::contains).toList();
    return duplicatedItems.get(0);
  }
}

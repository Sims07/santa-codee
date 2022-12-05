package com.santa.puzlle.y2022.daythree.partTwo;

import java.util.List;

public record Compartment(List<Item> items) {

  public static Compartment from(String strRep) {
    return new Compartment(strRep.chars().mapToObj(Item::new).toList());
  }

  @Override
  public String toString() {
    return items.toString();
  }
}

package com.santa.puzlle.y2022.daythree.partTwo;

import java.util.List;

public record RuckSack(List<Compartment> compartments) {

  public static RuckSack from(String strRep) {
    return new RuckSack(List.of(Compartment.from(strRep)));
  }


}

package com.santa.puzlle.y2022.dayFour;

import java.util.List;

public record Pairs(List<Pair> pairs) {

  public long nbOverlapped() {
    return pairs.stream().mapToLong(p->p.isOverlapped()?1:0).sum();
  }
}

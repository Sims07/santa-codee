package com.santa.puzlle.y2022.dayFive;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Slots(List<Slot> slots) {

  public static Slots of(List<Slot> slotList) {
    return new Slots(slotList);
  }
  public Slots move(Move move) {
    move(move.nbCrates(),move.fromIndex(),move.toIndex());
    return this;
  }
  public Slots move(int nbCrateToMove, int fromIndex, int toSlotIndex) {
    final Slot fromSlot = slots.get(fromIndex - 1);
    final Slot toSlot = slots.get(toSlotIndex - 1);
    IntStream.range(0,nbCrateToMove).forEach(i->{
      toSlot.push(fromSlot.pop());
    });
    return this;
  }

  public String configuration() {
    return slots.stream().map(Slot::peek).map(Crate::value).collect(Collectors.joining());
  }

  public Slots playMoves(List<Move> moves) {
    moves.forEach(this::move);
    return this;
  }
}

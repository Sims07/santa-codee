package com.santa.puzlle.y2022.dayFive;

import java.util.List;
import java.util.Stack;

public record Slot(int position, Stack<Crate> crates) {

  public static Slot of(int position, List<String> crateValues) {
    final Stack<Crate> cratesStack = new Stack<>();
    crateValues.stream().map(Crate::new).forEach(cratesStack::push);
    return new Slot(position, cratesStack);
  }

  public Crate pop() {
    return crates.pop();
  }

  public Crate peek() {
    return crates.peek();
  }

  public Crate push(Crate push) {
    return crates.push(push);
  }
}

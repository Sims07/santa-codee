package com.santa.puzlle.y2022.daythree;

public record Item(int charValue) {

  @Override
  public String toString() {
    return "Item{" +
        "charValue=" + (char)charValue +
        '}';
  }

  int value(){
    if(charValue<97){
      return charValue-'A'+27;
    }
    return charValue-'a'+1;
  }
}

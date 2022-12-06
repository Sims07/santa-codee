package com.santa.puzlle.y2022.dayFive.partTwo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Move(int nbCrates, int fromIndex, int toIndex) {

  public static final Pattern PATTERN = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

  public static Move from(String str) {
    Matcher matcher = PATTERN.matcher(str);
    if(matcher.find()){
      return new Move(Integer.valueOf(matcher.group(1)),
          Integer.valueOf(matcher.group(2)),Integer.valueOf(matcher.group(3)));
    }else{
      throw new IllegalArgumentException(str);
    }

  }

}

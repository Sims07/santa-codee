package com.santa.puzlle.y2022.dayFour.partTwo;

public record CleaningSection(int from, int to) {

  public static CleaningSection from(int from, int to) {
    return new CleaningSection(from,to);
  }

  public boolean in(CleaningSection cleaningSection) {
    if(allInclude(cleaningSection)){
      return true;
    }else if(notInside(cleaningSection)||cleaningSection.notInside(this)){
      return false;
    } else if(atLeastOneOverlap(cleaningSection)||cleaningSection.atLeastOneOverlap(this)){
      return true;
    }
    return true;
  }

  private boolean atLeastOneOverlap(CleaningSection cleaningSection) {
    return from <cleaningSection.to || to> cleaningSection.to;
  }

  private boolean notInside(CleaningSection cleaningSection) {
    return from < cleaningSection.from && to < cleaningSection.from;
  }

  private boolean allInclude(CleaningSection cleaningSection) {
    return from>=cleaningSection.from && to<=cleaningSection.to;
  }
}

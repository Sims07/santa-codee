package com.santa.puzlle.y2021.daythree;

import java.util.List;

@FunctionalInterface
public interface FilterDiagData {

  List<BinaryString> filter(List<BinaryString> toFilter, int index);
}

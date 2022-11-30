package com.santa.puzlle.daythree;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OxygenDiagnostic {
  private List<BinaryString> diagnosticRaw;
  private int oxygenGeneratorRating;
  private int co2ScrubberRating;

  public OxygenDiagnostic load(List<String> diagnosticRawData) {
    diagnosticRaw = diagnosticRawData.stream().map(BinaryString::new).toList();
    this.oxygenGeneratorRating = computeOxygenGeneratorRating();
    this.co2ScrubberRating = computeCo2GeneratorRating();
    return this;
  }

  private int computeOxygenGeneratorRating() {
    return computeGeneric(
        diagnosticRaw,
        (diagRawCurrent, index) -> diagRawCurrent.stream().filter(equalToOne(index)).toList(),
        (diagRawCurrent, index) -> diagRawCurrent.stream().filter(equalToZero(index)).toList(),
        0);
  }

  private int computeCo2GeneratorRating() {
    return computeGeneric(
        diagnosticRaw,
        (diagRawCurrent, index) -> diagRawCurrent.stream().filter(equalToZero(index)).toList(),
        (diagRawCurrent, index) -> diagRawCurrent.stream().filter(equalToOne(index)).toList(),
        0);
  }

  private Predicate<BinaryString> equalToZero(int index) {
    return raw -> raw.bytes().get(index) == 0;
  }

  private int computeGeneric(
      List<BinaryString> diagnosticRaw,
      FilterDiagData filterMoreONeThanZeroDiagDataFilter,
      FilterDiagData filterLessONeThanZeroDiagDataFilter,
      int index) {
    if (diagnosticRaw.size() == 1) {
      return diagnosticRaw.get(0).value();
    } else {
      final Map<Integer, Long> nbOccurrences = mapNumberOfZeroAndOneFor(index, diagnosticRaw);
      if (numberOccurrencesOfOneGreaterThanNumberOfZero(nbOccurrences)) {
        return computeGeneric(
            filterMoreONeThanZeroDiagDataFilter.filter(diagnosticRaw, index),
            filterMoreONeThanZeroDiagDataFilter,
            filterLessONeThanZeroDiagDataFilter,
            index + 1);
      } else {
        return computeGeneric(
            filterLessONeThanZeroDiagDataFilter.filter(diagnosticRaw, index),
            filterMoreONeThanZeroDiagDataFilter,
            filterLessONeThanZeroDiagDataFilter,
            index + 1);
      }
    }
  }

  private boolean numberOccurrencesOfOneGreaterThanNumberOfZero(Map<Integer, Long> nbOccurrences) {
    return nbOccurrencesOfOne(nbOccurrences) >= nbOccurrencesOfZero(nbOccurrences);
  }

  private Predicate<BinaryString> equalToOne(int index) {
    return raw -> raw.bytes().get(index) == 1;
  }

  private Map<Integer, Long> mapNumberOfZeroAndOneFor(int index, List<BinaryString> diagnosticRaw) {
    return diagnosticRaw.stream()
        .map(binaryString -> binaryString.bytes().get(index))
        .collect(Collectors.groupingBy(bit -> bit, Collectors.counting()));
  }

  private Long nbOccurrencesOfZero(Map<Integer, Long> nbOccurrences) {
    return Optional.ofNullable(nbOccurrences.get(0)).orElse(0L);
  }

  private Long nbOccurrencesOfOne(Map<Integer, Long> nbOccurrences) {
    return Optional.ofNullable(nbOccurrences.get(1)).orElse(0L);
  }

  public int oxygenGeneratorRating() {
    return this.oxygenGeneratorRating;
  }

  public int co2ScrubberRating() {
    return this.co2ScrubberRating;
  }

  public int lifeSupport() {
    return this.co2ScrubberRating * this.oxygenGeneratorRating;
  }
}

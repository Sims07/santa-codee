package com.santa.puzlle.daythree;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Diagnostic {
  private List<BinaryString> diagnosticRaw;
  private BinaryString gamma;
  private BinaryString epsilon;

  public Diagnostic load(List<String> diagnosticRawData) {
    diagnosticRaw = diagnosticRawData.stream().map(BinaryString::new).toList();
    computeGammaAndEpsilon(diagnosticRawData);
    return this;
  }

  private void computeGammaAndEpsilon(List<String> diagnosticRawData) {
    AtomicReference<String> gammaTmp = new AtomicReference<>("");
    AtomicReference<String> epsilonTmp = new AtomicReference<>("");
    IntStream.range(0, diagnosticRawData.get(0).length())
        .forEach(
            index -> {
              final Map<Integer, Long> nbOccurrences = mapNumberOfZeroAndOneFor(index);
              if (nbOccurrencesOfOne(nbOccurrences) > nbOccurrencesOfZero(nbOccurrences)) {
                appendWhenMoreOccurrencesOfOne(gammaTmp, epsilonTmp);
              } else {
                appendLessOccurrencesOfOne(gammaTmp, epsilonTmp);
              }
            });
    this.gamma = new BinaryString(gammaTmp.get());
    this.epsilon = new BinaryString(epsilonTmp.get());
  }

  private Map<Integer, Long> mapNumberOfZeroAndOneFor(int index) {
    return diagnosticRaw.stream()
        .map(binaryString -> binaryString.bytes().get(index))
        .collect(Collectors.groupingBy(bit -> bit, Collectors.counting()));
  }

  private void appendLessOccurrencesOfOne(
      AtomicReference<String> gammaTmp, AtomicReference<String> epsilonTmp) {
    gammaTmp.set(gammaTmp + "0");
    epsilonTmp.set(epsilonTmp + "1");
  }

  private void appendWhenMoreOccurrencesOfOne(
      AtomicReference<String> gammaTmp, AtomicReference<String> epsilonTmp) {
    gammaTmp.set(gammaTmp + "1");
    epsilonTmp.set(epsilonTmp + "0");
  }

  private Long nbOccurrencesOfZero(Map<Integer, Long> nbOccurrences) {
    return Optional.ofNullable(nbOccurrences.get(0)).orElse(0l);
  }

  private Long nbOccurrencesOfOne(Map<Integer, Long> nbOccurrences) {
    return Optional.ofNullable(nbOccurrences.get(1)).orElse(0l);
  }

  public int gamma() {
    return gamma.value();
  }

  public int epsilon() {
    return epsilon.value();
  }

  public int powerConsumption() {
    return epsilon() * gamma();
  }
}

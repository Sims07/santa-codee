package fees;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class VisaFees {
  private VisaFeeSaved activeFee;
  private final List<VisaFeeSaved> visaFees;
  private final Country country;

  public VisaFees(List<VisaFeeSaved> visaFees, Country country) {
    this.visaFees = new ArrayList<>(visaFees);
    this.activeFee = visaFees.stream().filter(VisaFeeSaved::active).findAny().orElse(null);
    this.country = country;
  }

  void add(VisaFeeSaved visaFeeSaved) {
    if (activeFee == null) {
      this.activeFee = visaFeeSaved;
      this.activeFee.activate();
    } else {
      activateNewFee(visaFeeSaved);
    }
    this.visaFees.add(visaFeeSaved);
  }

  private void activateNewFee(VisaFeeSaved visaFeeSaved) {
    final boolean newActiveFee = isNewActiveFee(visaFeeSaved);
    if (newActiveFee) {
      this.activeFee.active(false);
      this.activeFee.endDate(Instant.now());
      this.activeFee = visaFeeSaved;
      this.activeFee.activate();
    }
  }

  private boolean isNewActiveFee(VisaFeeSaved visaFeeSaved) {
    return isEffectiveDateAfterCurrentEffectiveDate(visaFeeSaved)
        && isEffectiveDateInThePast(visaFeeSaved);
  }

  private boolean isEffectiveDateInThePast(VisaFeeSaved visaFeeSaved) {
    return visaFeeSaved
        .visaFeeChange()
        .effectiveDate()
        .value()
        .isBefore(now(visaFeeSaved.visaFeeChange().effectiveDate().value().getZone()));
  }

  protected ZonedDateTime now(ZoneId zoneId) {
    return ZonedDateTime.now(zoneId);
  }

  private boolean isEffectiveDateAfterCurrentEffectiveDate(VisaFeeSaved visaFeeSaved) {
    return visaFeeSaved
        .visaFeeChange()
        .effectiveDate()
        .value()
        .isAfter(activeFee.visaFeeChange().effectiveDate().value());
  }

  public VisaFeeSaved active() {
    // TODO recompute to see there is a passed fee change with an effective date after the current
    // active one
    final VisaFeeSaved feeSaved =
        passed().stream()
            .filter(
                visaFeeSaved ->
                    visaFeeSaved
                            .visaFeeChange()
                            .effectiveDate()
                            .value()
                            .isBefore(
                                now(activeFee.visaFeeChange().effectiveDate().value().getZone()))
                        && visaFeeSaved
                            .visaFeeChange()
                            .effectiveDate()
                            .value()
                            .isAfter(activeFee.visaFeeChange().effectiveDate().value()))
            .findAny()
            .orElse(activeFee);
    if (!feeSaved.equals(activeFee)) {
      activateNewFee(feeSaved);
    }
    return activeFee;
  }

  public List<VisaFeeSaved> passed() {
    return visaFees.stream()
        .filter(visaFeeSaved -> !visaFeeSaved.active())
        .filter(visaFeeSaved -> visaFeeSaved.visaFeeChange().country().equals(this.country))
        .filter(
            visaFeeSaved ->
                visaFeeSaved
                    .visaFeeChange()
                    .effectiveDate()
                    .value()
                    .isBefore(now(visaFeeSaved.visaFeeChange().effectiveDate().value().getZone())))
        .toList();
  }

  public List<VisaFeeSaved> visaFees() {
    return new ArrayList<>(visaFees);
  }
}

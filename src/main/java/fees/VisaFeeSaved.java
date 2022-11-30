package fees;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class VisaFeeSaved {
  private final VisaFeeId uuid;
  private final VisaFeeChange visaFeeChange;
  private final Instant saveDate;
  private Instant applyingDate;
  private EndDate endDate;
  private boolean active;

  public VisaFeeSaved(VisaFeeChange visaFeeChange, Instant saveDate) {
    this.visaFeeChange = visaFeeChange;
    this.saveDate = saveDate;
    this.uuid = new VisaFeeId(UUID.randomUUID());
  }

  public void active(boolean active) {
    this.active = active;
  }

  public void activate() {
    active(true);
    applyingDate(Instant.now());
  }

  public Optional<Instant> applyingDate() {
    return Optional.ofNullable(applyingDate);
  }

  public VisaFeeSaved applyingDate(Instant applyingDate) {
    this.applyingDate = applyingDate;
    return this;
  }

  public VisaFeeChange visaFeeChange() {
    return visaFeeChange;
  }

  public VisaFeeSaved endDate(Instant endDate) {
    this.endDate = new EndDate(endDate);
    return this;
  }

  public Optional<EndDate> endDate() {
    return Optional.ofNullable(this.endDate);
  }

  public boolean active() {
    return this.active;
  }

  public Instant saveDate() {
    return this.saveDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisaFeeSaved that = (VisaFeeSaved) o;
    return Objects.equals(uuid, that.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }
}

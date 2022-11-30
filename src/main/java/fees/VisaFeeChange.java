package fees;

import java.time.Instant;
import java.util.List;

public record VisaFeeChange (Country country, User simon, EffectiveDate effectiveDate,
                             List<VisaFee> visaFees, ExchangeRate exchangeRate){

  public VisaFeeSaved save() {
    return new VisaFeeSaved(this, Instant.now());
  }
}

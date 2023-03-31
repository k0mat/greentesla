package pl.k0mat.greentesla.atm;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@EqualsAndHashCode
public class AtmJob {
    int region;
    int atmId;
}

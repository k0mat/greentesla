package pl.k0mat.greentesla.atm;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class AtmJob {
    int region;
    int atmId;
}

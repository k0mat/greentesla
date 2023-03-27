package pl.k0mat.greentesla.atm;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class AtmRequest {
    int region;
    RequestType requestType;
    int atmId;
}

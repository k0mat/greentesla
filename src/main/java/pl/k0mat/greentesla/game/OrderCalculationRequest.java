package pl.k0mat.greentesla.game;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class OrderCalculationRequest {
    int groupCount;
    List<Clan> clans;
}

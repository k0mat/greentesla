package pl.k0mat.greentesla.game;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Clan {
    int numberOfPlayers;
    int points;


}

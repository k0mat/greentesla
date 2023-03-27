package pl.k0mat.greentesla.game;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private static final Comparator<Clan> CLAN_COMPARATOR = Comparator.comparing(Clan::getPoints)
            .thenComparing(
                    Comparator.comparing(Clan::getNumberOfPlayers)
                            .reversed()
            ).reversed();

    public List<List<Clan>> calculateOrder(OrderCalculationRequest calculationRequest) {
        int maxGroupSize = calculationRequest.getGroupCount();
        List<Clan> sortedClans = calculationRequest.getClans().stream().sorted(CLAN_COMPARATOR)
                .collect(Collectors.toList());
        List<List<Clan>> entryGroups = new ArrayList<>();

        while (!sortedClans.isEmpty()) {
            List<Clan> entryGroup = new ArrayList<>();
            Clan bestClan = sortedClans.get(0);
            entryGroup.add(bestClan);
            int availableSpace = maxGroupSize - bestClan.getNumberOfPlayers();
            for (int j = 1; availableSpace > 0 && j < sortedClans.size(); j++) {
                Clan canditateClan = sortedClans.get(j);
                if (canditateClan.getNumberOfPlayers() <= availableSpace) {
                    entryGroup.add(canditateClan);
                    availableSpace -= canditateClan.getNumberOfPlayers();
                }
            }
            entryGroups.add(entryGroup);
            sortedClans.removeAll(entryGroup);
        }

        return entryGroups;
    }
}

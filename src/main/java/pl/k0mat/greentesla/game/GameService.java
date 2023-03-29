package pl.k0mat.greentesla.game;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    public List<List<Clan>> calculateOrder(OrderCalculationRequest calculationRequest) {
        int maxGroupSize = calculationRequest.getGroupCount();
        Comparator<Clan> scoreComparator = Comparator.comparingInt(Clan::getPoints).reversed();
        ArrayList<TreeSet<Clan>> clansBySize = new ArrayList<>(maxGroupSize);
        for (int i = 0; i < maxGroupSize; i++) {
            clansBySize.add(new TreeSet<>(scoreComparator));
        }
        for (Clan clan : calculationRequest.getClans()) {
            clansBySize.get(clan.getNumberOfPlayers() - 1).add(clan);
        }
        int clansLeft = calculationRequest.getClans().size();

        List<List<Clan>> entryGroups = new ArrayList<>();
        int availableSpace = maxGroupSize;
        Clan nextClan = getNextClanAndRemove(clansBySize, availableSpace);
        List<Clan> nextEntryGroup = new ArrayList<>();
        while (clansLeft > 0) {
            if (nextClan == null) {
                availableSpace = maxGroupSize;
                entryGroups.add(nextEntryGroup);
                nextEntryGroup = new ArrayList<>();
            } else {
                nextEntryGroup.add(nextClan);
                availableSpace -= nextClan.getNumberOfPlayers();
                clansLeft--;
            }
            nextClan = getNextClanAndRemove(clansBySize, availableSpace);
        }
        if (!nextEntryGroup.isEmpty()) {
            entryGroups.add(nextEntryGroup);
        }
        return entryGroups;
    }

    private Clan getNextClanAndRemove(ArrayList<TreeSet<Clan>> clansBySize, int availableSpace) {
        Optional<Clan> max = clansBySize.subList(0, availableSpace).stream()
                .filter(clans -> !clans.isEmpty())
                .map(TreeSet::first)
                .max(Comparator.comparingInt(Clan::getPoints));

        if (max.isPresent()) {
            Clan nextClan = max.get();
            clansBySize.get(nextClan.getNumberOfPlayers() - 1).remove(nextClan);
            return nextClan;
        } else {
            return null;
        }
    }
}

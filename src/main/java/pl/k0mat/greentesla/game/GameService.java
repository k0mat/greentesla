package pl.k0mat.greentesla.game;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    public List<List<Clan>> calculateOrder(OrderCalculationRequest calculationRequest) {
        int maxGroupSize = calculationRequest.getGroupCount();
        Comparator<Clan> scoreComparator = Comparator.comparingInt(Clan::getPoints);
        List<List<Clan>> clansBySize
                = groupClansBySizeAndSort(calculationRequest.getClans(), maxGroupSize, scoreComparator);

        long clansToTake = clansBySize.stream().mapToLong(Collection::size).sum();
        return calculateEntryGroups(maxGroupSize, clansBySize, clansToTake);
    }

    private List<List<Clan>> groupClansBySizeAndSort(List<Clan> allClans,
                                                     int maxGroupSize,
                                                     Comparator<Clan> scoreComparator) {
        List<List<Clan>> clansBySize = new ArrayList<>(maxGroupSize);
        int initialSize = (allClans.size() / maxGroupSize) + 5;
        for (int i = 0; i < maxGroupSize; i++) {
            clansBySize.add(new ArrayList<>(initialSize));
        }
        for (Clan clan : allClans) {
            clansBySize.get(clan.getNumberOfPlayers() - 1).add(clan);
        }
        for (List<Clan> clans : clansBySize) {
            clans.sort(scoreComparator);
        }
        return clansBySize;
    }

    private List<List<Clan>> calculateEntryGroups(int maxGroupSize, List<List<Clan>> clansBySize, long clansToTake) {
        long clansLeft = clansToTake;

        int availableSpace = maxGroupSize;
        List<List<Clan>> entryGroups = new ArrayList<>();
        List<Clan> nextEntryGroup = new ArrayList<>();
        Clan nextClan = getNextClanAndRemove(clansBySize, availableSpace);

        while (clansLeft > 0) {
            if (nextClan == null) {
                availableSpace = maxGroupSize;
                entryGroups.add(nextEntryGroup);
                nextEntryGroup = new ArrayList<>(nextEntryGroup.size() + 5);
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

    private Clan getNextClanAndRemove(List<List<Clan>> clansBySize, int availableSpace) {
        Optional<Clan> max = clansBySize.subList(0, availableSpace).stream()
                .filter(clans -> !clans.isEmpty())
                .map(clans -> clans.get(clans.size() - 1))
                .max(Comparator.comparingInt(Clan::getPoints));

        if (max.isPresent()) {
            Clan nextClan = max.get();
            List<Clan> clans = clansBySize.get(nextClan.getNumberOfPlayers() - 1);
            clans.remove(clans.size() - 1);
            return nextClan;
        } else {
            return null;
        }
    }
}

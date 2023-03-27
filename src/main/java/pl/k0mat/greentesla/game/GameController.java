package pl.k0mat.greentesla.game;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@RestController
@RequestMapping("/onlinegame")
public class GameController {
    private final GameService gameService;

    @PostMapping("/calculate")
    public List<List<Clan>> calculateOrder(
            @RequestBody OrderCalculationRequest calculationRequest
    ) {
        return gameService.calculateOrder(calculationRequest);
    }

    @GetMapping("/randomQueue")
    public OrderCalculationRequest randomQueue(
            @RequestParam int dataSize
    ) {
        Random random = new Random();
        int maxGroupSize = random.nextInt(1, 1001);

        List<Clan> clans = new ArrayList<>(dataSize);
        for (int i = 0; i < dataSize; i++) {
            Clan clan = new Clan(
                    random.nextInt(1, maxGroupSize + 1),
                    random.nextInt(1, 1000001)
            );
            clans.add(clan);
        }

        return new OrderCalculationRequest(maxGroupSize, clans);
    }
}

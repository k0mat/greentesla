package pl.k0mat.greentesla.atm;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@RestController
@RequestMapping("/atms")
public class AtmController {
    private final AtmService atmService;

    @PostMapping("/calculateOrder")
    public List<AtmJob> calculateOrder(
            @RequestBody List<AtmRequest> requests
    ) {
        return atmService.calculateOrder(requests);
    }

    @GetMapping("/randomOrders")
    public List<AtmRequest> randomData(
            @RequestParam int dataSize
    ) {
        Random random = new Random();
        List<AtmRequest> data = new ArrayList<>(dataSize);
        for (int i = 0; i < dataSize; i++) {
            AtmRequest randomRequest = new AtmRequest(
                    random.nextInt(1, (dataSize / 10) + 1),
                    RequestType.values()[random.nextInt(0, RequestType.values().length)],
                    random.nextInt(1, 21)
            );
            data.add(randomRequest);
        }
        return data;
    }
}

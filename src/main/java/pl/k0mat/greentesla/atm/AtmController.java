package pl.k0mat.greentesla.atm;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}

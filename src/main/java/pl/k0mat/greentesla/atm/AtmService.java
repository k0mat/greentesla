package pl.k0mat.greentesla.atm;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtmService {

    private static final Comparator<AtmRequest> REQUEST_COMPARATOR = Comparator.comparing(AtmRequest::getRegion)
            .thenComparing(AtmRequest::getRequestType);

    public List<AtmJob> calculateOrder(List<AtmRequest> requests) {
        return requests.stream()
                .sorted(REQUEST_COMPARATOR)
                .map(atmRequest -> new AtmJob(atmRequest.getRegion(), atmRequest.getAtmId()))
                .collect(Collectors.toList());
    }
}

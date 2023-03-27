package pl.k0mat.greentesla.transactions;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @PostMapping("/report")
    public List<String> generateReport() {
        return Collections.singletonList("todo");
    }
}

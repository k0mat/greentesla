package pl.k0mat.greentesla.transactions;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    private final TransactionService transactionService;

    @PostMapping("/report")
    public List<AccountReport> generateReport(
            @RequestBody List<Transaction> transactions
    ) {
        return transactionService.generateReport(transactions);
    }

    @GetMapping("/randomTransactions")
    public List<Transaction> randomData(
            @RequestParam int dataSize
    ) {
        Random random = new Random();
        List<Transaction> data = new ArrayList<>(dataSize);
        for (int i = 0; i < dataSize; i++) {
            BigDecimal amount = BigDecimal.valueOf(random.nextDouble(0.5, 1000.1)).setScale(2, RoundingMode.HALF_EVEN);
            data.add(new Transaction(randomAccountNumber(random), randomAccountNumber(random), amount));
        }
        return data;
    }

    private String randomAccountNumber(Random random) {
        return random.ints(26, 0, 10)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}

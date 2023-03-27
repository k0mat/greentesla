package pl.k0mat.greentesla.transactions;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @GetMapping("/random-transactions")
    public List<Transaction> randomData(
            @RequestParam int dataSize
    ) {
        Random random = new Random();
        List<Transaction> data = new ArrayList<>(dataSize);
        for (int i = 0; i < dataSize; i++) {
            String creditAccount = String.valueOf(random.nextLong(dataSize, dataSize * 10L));
            String debitAccount = String.valueOf(random.nextLong(dataSize, dataSize * 10L));
            BigDecimal amount = BigDecimal.valueOf(random.nextDouble(0.5, 1000.1)).setScale(2, RoundingMode.HALF_EVEN);
            data.add(new Transaction(debitAccount, creditAccount, amount));
        }
        return data;
    }
}

package pl.k0mat.greentesla.transactions;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class TransactionService {
    public List<AccountReport> generateReport(List<Transaction> transactions) {
        Map<String, AccountReport> accountNumberToReport = new TreeMap<>();
        transactions.forEach(transaction -> {
            AccountReport creditAccount
                    = accountNumberToReport.computeIfAbsent(transaction.getCreditAccount(), AccountReport::new);
            creditAccount.increaseBalance(transaction.getAmount());
            AccountReport debitAccount
                    = accountNumberToReport.computeIfAbsent(transaction.getDebitAccount(), AccountReport::new);
            debitAccount.decreaseBalance(transaction.getAmount());
        });
        return accountNumberToReport.values()
                .stream()
                .toList();
    }
}

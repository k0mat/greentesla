package pl.k0mat.greentesla.transactions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class AccountReport {
    private final String account;
    private int debitCount;
    private int creditCount;
    private BigDecimal balance = BigDecimal.ZERO;

    public void increaseBalance(BigDecimal transactionAmount) {
        creditCount++;
        balance = balance.add(transactionAmount);
    }

    public void decreaseBalance(BigDecimal transactionAmount) {
        debitCount++;
        balance = balance.subtract(transactionAmount);
    }
}

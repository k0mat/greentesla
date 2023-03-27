package pl.k0mat.greentesla.transactions;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Builder
@Jacksonized
public class Transaction {
    String debitAccount;
    String creditAccount;
    BigDecimal amount;
}

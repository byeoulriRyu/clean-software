package org.example.transaction.change;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.method.DirectMethod;
import org.example.method.PaymentMethod;
import org.example.transaction.Transaction;

@NoArgsConstructor
public class ChangeDirectTransaction extends ChangeMethodTransaction {

    private String bank;
    private String account;

    public ChangeDirectTransaction(int empId, String bank, String account) {
        super(empId);
        this.bank = bank;
        this.account = account;
    }

    @Override
    PaymentMethod getMethod() {
        return DirectMethod.builder().bank(bank).account(account).build();
    }
}

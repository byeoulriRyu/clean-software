package org.example.transaction.change;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.method.MailMethod;
import org.example.method.PaymentMethod;
import org.example.transaction.Transaction;

@NoArgsConstructor
public class ChangeMailTransaction extends ChangeMethodTransaction {

    private String address;

    public ChangeMailTransaction(int empId, String address) {
        super(empId);
        this.address = address;
    }

    @Override
    PaymentMethod getMethod() {
        return MailMethod.builder().address(address).build();
    }
}

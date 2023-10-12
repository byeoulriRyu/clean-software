package org.example.transaction.change;

import lombok.NoArgsConstructor;
import org.example.method.HoldMethod;
import org.example.method.PaymentMethod;

@NoArgsConstructor
public class ChangeHoldTransaction extends ChangeMethodTransaction {

    public ChangeHoldTransaction(int empId) {
        super(empId);
    }

    @Override
    PaymentMethod getMethod() {
        return HoldMethod.builder().build();
    }
}

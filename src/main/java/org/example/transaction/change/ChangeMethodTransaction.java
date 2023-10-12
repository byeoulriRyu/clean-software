package org.example.transaction.change;

import lombok.NoArgsConstructor;
import org.example.entity.Employee;
import org.example.method.PaymentMethod;

@NoArgsConstructor
public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

    public ChangeMethodTransaction(int empId) {
        super(empId);
    }

    @Override
    public void change(Employee e) {
        e.setMethod(e.getMethod());
    }

    abstract PaymentMethod getMethod();
}

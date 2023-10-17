package org.example.transaction.change;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.PayrollDatabase;
import org.example.entity.Employee;
import org.example.transaction.Transaction;

@NoArgsConstructor
@AllArgsConstructor
public abstract class ChangeEmployeeTransaction implements Transaction {

    private int empId;

    @Override
    public void execute() throws Exception {
        Employee e = PayrollDatabase.getEmployee(empId);

        if (e != null) {
            change(e);
        }

    }

    public abstract void change(Employee e);
}

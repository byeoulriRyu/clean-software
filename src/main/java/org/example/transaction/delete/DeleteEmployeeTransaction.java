package org.example.transaction.delete;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.PayrollDatabase;
import org.example.transaction.Transaction;

@NoArgsConstructor
@AllArgsConstructor
public class DeleteEmployeeTransaction implements Transaction {

    private int empId;

    @Override
    public void excute() throws Exception {
        PayrollDatabase.deleteEmployee(empId);
    }

}

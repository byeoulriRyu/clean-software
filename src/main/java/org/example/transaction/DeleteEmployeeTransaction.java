package org.example.transaction;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.PayrollDatabase;

@NoArgsConstructor
@AllArgsConstructor
public class DeleteEmployeeTransaction implements Transaction{

    private int empId;

    @Override
    public void excute() throws Exception {
        PayrollDatabase.deleteEmployee(empId);
    }

}

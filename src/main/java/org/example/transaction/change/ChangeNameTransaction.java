package org.example.transaction.change;

import lombok.NoArgsConstructor;
import org.example.entity.Employee;

@NoArgsConstructor
public class ChangeNameTransaction extends ChangeEmployeeTransaction {

    private String name;

    public ChangeNameTransaction(int empId, String name) {
        super(empId);
        this.name = name;
    }

    @Override
    public void change(Employee e) {
        e.setName(name);
    }
}

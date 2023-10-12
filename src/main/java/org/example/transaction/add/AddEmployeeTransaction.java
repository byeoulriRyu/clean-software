package org.example.transaction.add;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.PayrollDatabase;
import org.example.classification.PaymentClassification;
import org.example.entity.Employee;
import org.example.method.HoldMethod;
import org.example.method.PaymentMethod;
import org.example.schedule.PaymentSchedule;
import org.example.transaction.Transaction;

@NoArgsConstructor
@AllArgsConstructor
public abstract class AddEmployeeTransaction implements Transaction {
    private int empId;
    private String name;
    private String address;

    public abstract PaymentClassification getClassification();
    public abstract PaymentSchedule getSchedule();

    @Override
    public void excute() throws Exception {
        PaymentClassification pc = getClassification();
        PaymentSchedule ps = getSchedule();
        PaymentMethod pm = new HoldMethod();

        Employee employee = Employee.builder()
                .empId(empId)
                .name(name)
                .address(address)
                .classification(pc)
                .schedule(ps)
                .method(pm)
                .build();

        PayrollDatabase.addEmployee(empId, employee);
    }

}

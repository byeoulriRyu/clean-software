package org.example.transaction.add;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.PayrollDatabase;
import org.example.affiliation.Affiliation;
import org.example.affiliation.NoAffiliation;
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
    public void execute() throws Exception {
        PaymentClassification pc = getClassification();
        PaymentSchedule ps = getSchedule();
        PaymentMethod pm = HoldMethod.builder().build();
        Affiliation af = NoAffiliation.builder().build();

        Employee employee = Employee.builder()
                .empId(empId)
                .name(name)
                .address(address)
                .classification(pc)
                .schedule(ps)
                .method(pm)
                .affiliation(af)
                .build();

        PayrollDatabase.addEmployee(empId, employee);
    }

}

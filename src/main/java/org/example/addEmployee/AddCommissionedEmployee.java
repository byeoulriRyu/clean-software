package org.example.addEmployee;

import lombok.NoArgsConstructor;
import org.example.classification.CommissionedClassification;
import org.example.classification.PaymentClassification;
import org.example.schedule.BiweeklySchedule;
import org.example.schedule.PaymentSchedule;
import org.example.transaction.AddEmployeeTransaction;

@NoArgsConstructor
public class AddCommissionedEmployee extends AddEmployeeTransaction {

    private double salary;
    private double commissionRate;

    public AddCommissionedEmployee(int empId, String address, String name, double salary, double commissionRate) {
        super(empId, address, name);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}

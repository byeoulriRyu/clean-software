package org.example.addEmployee;

import org.example.classification.PaymentClassification;
import org.example.classification.SalariedClassification;
import org.example.schedule.MonthlySchedule;
import org.example.schedule.PaymentSchedule;
import org.example.transaction.AddEmployeeTransaction;

public class AddSalariedEmployee extends AddEmployeeTransaction {

    private double salary;

    public AddSalariedEmployee(int empId, String name, String address, double salary) {
        super(empId, name, address);
        this.salary = salary;
    }

    @Override
    public PaymentClassification getClassification() {
        return SalariedClassification.builder()
                .salary(salary)
                .build();
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}

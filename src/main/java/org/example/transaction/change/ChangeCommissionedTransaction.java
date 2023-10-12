package org.example.transaction.change;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.PayrollDatabase;
import org.example.classification.CommissionedClassification;
import org.example.classification.PaymentClassification;
import org.example.entity.Employee;
import org.example.method.HoldMethod;
import org.example.method.PaymentMethod;
import org.example.schedule.BiweeklySchedule;
import org.example.schedule.PaymentSchedule;
import org.example.transaction.Transaction;

@NoArgsConstructor
public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {

    private double salary;
    private double commissionRate;

    public ChangeCommissionedTransaction(int empId, double salary, double commissionRate) {
        super(empId);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    PaymentClassification getClassification() {
        return CommissionedClassification.builder().salary(salary).commissionRate(commissionRate).build();
    }

    @Override
    PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}

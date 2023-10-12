package org.example.transaction.change;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.PayrollDatabase;
import org.example.classification.HourlyClassification;
import org.example.classification.PaymentClassification;
import org.example.classification.SalariedClassification;
import org.example.entity.Employee;
import org.example.method.HoldMethod;
import org.example.method.PaymentMethod;
import org.example.schedule.MonthlySchedule;
import org.example.schedule.PaymentSchedule;
import org.example.schedule.WeeklySchedule;
import org.example.transaction.Transaction;

@NoArgsConstructor
public class ChangeSalariedTransaction extends ChangeClassificationTransaction {

    private double salary;

    public ChangeSalariedTransaction(int empId, double salary) {
        super(empId);
        this.salary = salary;
    }

    @Override
    public PaymentClassification getClassification() {
        return SalariedClassification.builder().salary(salary).build();
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

}

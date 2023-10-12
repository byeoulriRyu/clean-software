package org.example.transaction.change;

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
public abstract class ChangeCommissionedTransaction implements Transaction {

    private double salary;
    private double commissionRate;

}

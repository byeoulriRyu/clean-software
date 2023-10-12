package org.example.transaction;

import lombok.Builder;
import org.example.PayrollDatabase;
import org.example.affiliation.Affiliation;
import org.example.affiliation.UnionAffiliation;
import org.example.classification.CommissionedClassification;
import org.example.classification.PaymentClassification;
import org.example.entity.Employee;
import org.example.entity.SalesReceipt;
import org.example.entity.ServiceCharge;

@Builder
public class ServiceChargeTransaction implements Transaction{
    private int memberId;
    private long date;
    private double amount;

    @Override
    public void excute() throws Exception {

        Employee emp = PayrollDatabase.getUnionMember(memberId);

        if (emp == null)
            throw new Exception("No such employee.");

        Affiliation a = emp.getAffiliation();

        try {
            UnionAffiliation ua = (UnionAffiliation) a;
            ua.addServiceCharge(
                    ServiceCharge.builder()
                            .date(date)
                            .amount(amount)
                            .build()
            );
        } catch (Exception e) {
            throw new Exception("Tried to add timecard to non-hourly employee");
        }

    }

}

package org.example.transaction.change;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.PayrollDatabase;
import org.example.affiliation.Affiliation;
import org.example.affiliation.NoAffiliation;
import org.example.affiliation.UnionAffiliation;
import org.example.entity.Employee;
import org.example.transaction.Transaction;

@NoArgsConstructor
public class ChangeUnaffiliationTransaction extends ChangeAffiliationTransaction {

    public ChangeUnaffiliationTransaction(int empId) {
        super(empId);
    }

    @Override
    Affiliation getAffiliation() {
        return NoAffiliation.builder().build();
    }

    @Override
    void recordMembership(Employee e) {
        Affiliation af = e.getAffiliation();
        UnionAffiliation uf = (UnionAffiliation) af;
        PayrollDatabase.removeUnionMember(uf.getMemberId());
    }
}

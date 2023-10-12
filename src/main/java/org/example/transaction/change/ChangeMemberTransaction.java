package org.example.transaction.change;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.PayrollDatabase;
import org.example.affiliation.Affiliation;
import org.example.affiliation.UnionAffiliation;
import org.example.entity.Employee;
import org.example.transaction.Transaction;

@Getter
@NoArgsConstructor
public class ChangeMemberTransaction extends ChangeAffiliationTransaction {

    private int memberId;
    private double dues;

    public ChangeMemberTransaction(int empId, int memberId, double dues) {
        super(empId);
        this.memberId = memberId;
        this.dues = dues;
    }

    @Override
    Affiliation getAffiliation() {
        return UnionAffiliation.builder().memberId(memberId).dues(dues).build();
    }

    @Override
    void recordMembership(Employee e) {
        PayrollDatabase.addUnionMember(memberId, e);
    }
}

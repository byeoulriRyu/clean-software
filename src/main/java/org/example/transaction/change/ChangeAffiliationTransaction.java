package org.example.transaction.change;

import lombok.NoArgsConstructor;
import org.example.affiliation.Affiliation;
import org.example.entity.Employee;

@NoArgsConstructor
public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {

    public ChangeAffiliationTransaction(int empId) {
        super(empId);
    }

    @Override
    public void change(Employee e) {
        recordMembership(e);
        e.setAffiliation(getAffiliation());
    }

    abstract Affiliation getAffiliation();
    abstract void recordMembership(Employee e);
}

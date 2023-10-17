package org.example.transaction.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.PayrollDatabase;
import org.example.entity.Employee;
import org.example.transaction.Transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
public class PaydayTransaction implements Transaction {

    private Date payDate;

    @Builder.Default
    private Map<Integer, PayCheck> itsPayChecks = new HashMap<Integer, PayCheck>();

    @Override
    public void execute() throws Exception {
        PayrollDatabase.getAllEmployeeIds().forEach(empId -> {
            Employee e = PayrollDatabase.getEmployee(empId);
            if (e != null && e.isPayDate(payDate)) {
                PayCheck pc = PayCheck.builder().payDate(payDate).build();
                itsPayChecks.put(empId, pc);
                e.payDay(pc);
            }
        });


    }

    public PayCheck getPayCheck(int empId) {
        return itsPayChecks.get(empId);
    }

}

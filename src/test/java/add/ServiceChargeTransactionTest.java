package add;

import org.example.PayrollDatabase;
import org.example.addEmployee.AddHourlyEmployee;
import org.example.affiliation.UnionAffiliation;
import org.example.entity.Employee;
import org.example.entity.ServiceCharge;
import org.example.transaction.add.ServiceChargeTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class ServiceChargeTransactionTest {

    @BeforeAll
    public static void before() {
        PayrollDatabase.clear();
    }

    @Test
    public void testServiceChargeTransaction() throws Exception {
        int empId = 2;
        String name = "Bill";
        String address = "Home";
        double hourlyRate = 15.25;

        double amount = 12.95;
    
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.NOVEMBER, 01);
        Date payDate = calendar.getTime();

        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, name, address, hourlyRate);
        addHourlyEmployee.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        UnionAffiliation af = UnionAffiliation.builder()
                .dues(amount)
                .build();

        e.setAffiliation(af);

        int memberId = 86;

        PayrollDatabase.addUnionMember(memberId, e);

        ServiceChargeTransaction sct = ServiceChargeTransaction.builder()
                .memberId(memberId)
                .date(payDate)
                .amount(amount)
                .build();
        sct.execute();

        ServiceCharge sc = af.getServiceCharge(payDate);
        Assertions.assertNotNull(sc);
        Assertions.assertEquals(amount, sc.getAmount(), .001);
    }

}

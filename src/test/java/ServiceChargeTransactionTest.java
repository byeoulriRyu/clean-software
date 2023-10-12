import org.example.PayrollDatabase;
import org.example.addEmployee.AddHourlyEmployee;
import org.example.affiliation.UnionAffiliation;
import org.example.classification.HourlyClassification;
import org.example.classification.PaymentClassification;
import org.example.entity.Employee;
import org.example.entity.ServiceCharge;
import org.example.entity.TimeCard;
import org.example.transaction.ServiceChargeTransaction;
import org.example.transaction.TimeCardTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

        long date = 20011101;
        double amount = 12.95;

        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, name, address, hourlyRate);
        addHourlyEmployee.excute();

        Employee e = PayrollDatabase.getEmployee(empId);
        Assertions.assertNotNull(e);

        UnionAffiliation af = UnionAffiliation.builder()
                .amount(amount)
                .build();

        e.setAffiliation(af);

        int memberId = 86;

        PayrollDatabase.addUnionMember(memberId, e);

        ServiceChargeTransaction sct = ServiceChargeTransaction.builder()
                .memberId(memberId)
                .date(date)
                .amount(amount)
                .build();
        sct.excute();

        ServiceCharge sc = af.getServiceCharge(date);
        Assertions.assertNotNull(sc);
        Assertions.assertEquals(amount, sc.getAmount(), .001);
    }

}

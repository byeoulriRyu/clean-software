package pay;

import org.example.PayrollDatabase;
import org.example.addEmployee.AddHourlyEmployee;
import org.example.addEmployee.AddSalariedEmployee;
import org.example.entity.Employee;
import org.example.transaction.change.ChangeAddressTransaction;
import org.example.transaction.change.ChangeNameTransaction;
import org.example.transaction.pay.PayCheck;
import org.example.transaction.pay.PaydayTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class PayEmployeeTest {

    private static final int empId = 1;
    private static final String name = "Bob";
    private static final String address = "Home";

    @BeforeAll
    public static void before() throws Exception {
        PayrollDatabase.clear();
    }

    @Test
    @Order(1)
    public void testPaySingleSalariedEmployee() throws Exception {

        AddSalariedEmployee t = new AddSalariedEmployee(empId, name, address, 1000.00);
        t.excute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, 10, 30);
        Date payDate = calendar.getTime();

        PaydayTransaction pt = PaydayTransaction.builder()
                .payDate(payDate)
                .build();
        pt.excute();

        PayCheck pc = pt.getPayCheck(empId);
        Assertions.assertNotNull(pc);
        Assertions.assertEquals(pc.getPayDate(), payDate);
        //Assertions.assertEquals("Hold", pc.getDisposition());
        Assertions.assertEquals(0.0, pc.getDeductions(), .001);
        Assertions.assertEquals(1000.00, pc.getNetPay(), .001);

    }

    @Test
    @Order(2)
    public void testPaySingleHourlyEmployeeNoTimeCards() throws Exception {


    }

    @Test
    @Order(3)
    public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {


    }

    @Test
    @Order(4)
    public void testPaySingleHourlyEmployeeOnWrongDate() throws Exception {


    }

    @Test
    @Order(5)
    public void testPaySingleHourlyEmployeeTwoTimeCards() throws Exception {


    }

    @Test
    @Order(6)
    public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws Exception {


    }

    @Test
    @Order(7)
    public void testSalariedUnionMemberDues() throws Exception {


    }

    @Test
    @Order(8)
    public void testHourlyUnionMemberServiceCharge() throws Exception {


    }

    @Test
    @Order(9)
    public void testServiceChargesSpanningMultiplePayPeriods() throws Exception {


    }

}

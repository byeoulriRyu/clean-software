package pay;

import org.example.PayrollDatabase;
import org.example.addEmployee.AddHourlyEmployee;
import org.example.addEmployee.AddSalariedEmployee;
import org.example.transaction.add.ServiceChargeTransaction;
import org.example.transaction.add.TimeCardTransaction;
import org.example.transaction.change.ChangeMemberTransaction;
import org.example.transaction.pay.PayCheck;
import org.example.transaction.pay.PaydayTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PayEmployeeTest {

    @BeforeEach
    public void before() throws Exception {
        PayrollDatabase.clear();
    }

    @Test
    @Order(1)
    public void testPaySingleSalariedEmployee() throws Exception {

        int empId = 1;
        String name = "Bob";
        String address = "Home";

        AddSalariedEmployee t = new AddSalariedEmployee(empId, name, address, 1000.00);
        t.execute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.NOVEMBER, 30);
        Date payDate = calendar.getTime();

        PaydayTransaction pt = PaydayTransaction.builder()
                .payDate(payDate)
                .build();
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertNotNull(pc);
        assertEquals(pc.getPayDate(), payDate);
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(0.0, pc.getDeductions(), .001);
        assertEquals(1000.00, pc.getNetPay(), .001);

    }

    @Test
    @Order(2)
    public void testPaySingleHourlyEmployeeNoTimeCards() throws Exception {
        int empId = 1;
        String name = "Bob";
        String address = "Home";

        AddSalariedEmployee t = new AddSalariedEmployee(empId, name, address, 1000.00);
        t.execute();
    
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.NOVEMBER, 30);
        Date payDate = calendar.getTime();
    
        PaydayTransaction pt = PaydayTransaction.builder()
                .payDate(payDate)
                .build();
        pt.execute();
        
        PayCheck pc = pt.getPayCheck(empId);
        assertNull(pc);
    }

    @Test
    @Order(3)
    public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();
    
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.NOVEMBER, 9);
        Date payDate = calendar.getTime();
    
        //TODO: HourlyClassification 내 long date 처리 수정 필요
        TimeCardTransaction tc = TimeCardTransaction.builder()
                .empId(empId)
                .date(payDate.getTime())
                .hours(2.0)
                .build();
        tc.execute();
        
        PaydayTransaction pt = PaydayTransaction.builder()
                .payDate(payDate)
                .build();
        pt.execute();
    
        PayCheck pc = pt.getPayCheck(empId);
        assertNull(pc);
    
        validateHourlyPaycheck(pt, empId, payDate, (8 + 1.5) * 15.25);
    }
    
    private void validateHourlyPaycheck(PaydayTransaction paydayTransaction, int empId, Date payDate, double pay) {
        PayCheck payCheck = paydayTransaction.getPayCheck(empId);
        assertEquals(payDate, payCheck.getPayDate());
        assertEquals(pay, payCheck.getCrossPay(), .001);
        assertEquals("Hold", payCheck.getField("Disposition"));
        assertEquals(0.0, payCheck.getDeductions(), .001);
        assertEquals(1000.00, payCheck.getNetPay(), .001);
    }

    @Test
    @Order(4)
    public void TestPaySingleHourlyEmployeeOvertimeOneTimeCard() throws Exception {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();
    
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.NOVEMBER, 9);
        Date payDate = calendar.getTime();
        
        TimeCardTransaction timeCardTransaction = TimeCardTransaction.builder()
                .empId(empId)
                .date(payDate.getTime())
                .hours(9.0)
                .build();
        timeCardTransaction.execute();
        
        PaydayTransaction paydayTransaction = PaydayTransaction.builder()
                .payDate(payDate)
                .build();
        paydayTransaction.execute();
        
        validateHourlyPaycheck(paydayTransaction, empId, payDate, (8 + 1.5) * 15.25);
    }
    
    

    @Test
    @Order(5)
    public void testPaySingleHourlyEmployeeOnWrongDate() throws Exception {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();
    
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.NOVEMBER, 8);
        Date payDate = calendar.getTime();
    
        TimeCardTransaction timeCardTransaction = TimeCardTransaction.builder()
                .empId(empId)
                .date(payDate.getTime())
                .hours(9.0)
                .build();
        timeCardTransaction.execute();
    
        PaydayTransaction paydayTransaction = PaydayTransaction.builder()
                .payDate(payDate)
                .build();
        paydayTransaction.execute();
    
        PayCheck payCheck = paydayTransaction.getPayCheck(empId);
        assertNull(payCheck);

    }

    @Test
    @Order(6)
    public void testPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();
    
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.NOVEMBER, 9);
        Date payDate = calendar.getTime();
    
        TimeCardTransaction timeCardTransaction = TimeCardTransaction.builder()
                .empId(empId)
                .date(payDate.getTime())
                .hours(2.0)
                .build();
        timeCardTransaction.execute();
    
        calendar.set(2001, Calendar.NOVEMBER, 8);
        Date payDate2 = calendar.getTime();
    
        TimeCardTransaction timeCardTransaction2 = TimeCardTransaction.builder()
                .empId(empId)
                .date(payDate2.getTime())
                .hours(5.0)
                .build();
        timeCardTransaction2.execute();
    
        PaydayTransaction paydayTransaction = PaydayTransaction.builder()
                .payDate(payDate)
                .build();
        paydayTransaction.execute();
    
        PayCheck pc = paydayTransaction.getPayCheck(empId);
        validateHourlyPaycheck(paydayTransaction, empId, payDate, 7 * 15.25);
    }
    
    

    @Test
    @Order(7)
    public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws Exception {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();
    
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.NOVEMBER, 9);
        Date payDate = calendar.getTime();
    
        TimeCardTransaction timeCardTransaction = TimeCardTransaction.builder()
                .empId(empId)
                .date(payDate.getTime())
                .hours(2.0)
                .build();
        timeCardTransaction.execute();
        
        calendar.set(2001, Calendar.NOVEMBER, 2);
        Date dateInPreviousPayPeriod = calendar.getTime();
    
        TimeCardTransaction timeCardTransaction2 = TimeCardTransaction.builder()
                .empId(empId)
                .date(dateInPreviousPayPeriod.getTime())
                .hours(5.0)
                .build();
        timeCardTransaction2.execute();
    
        PaydayTransaction paydayTransaction = PaydayTransaction.builder()
                .payDate(payDate)
                .build();
        paydayTransaction.execute();
    
        validateHourlyPaycheck(paydayTransaction, empId, payDate, 2 * 15.25);

    }

    @Test
    @Order(8)
    public void testSalariedUnionMemberDues() throws Exception {
        int empId = 1;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        addSalariedEmployee.execute();
    
        int memberId = 7734;
        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(empId, memberId, 9.42);
        changeMemberTransaction.execute();
    
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.NOVEMBER, 30);
        Date payDate = calendar.getTime();
    
        PaydayTransaction paydayTransaction = PaydayTransaction.builder()
                .payDate(payDate)
                .build();
        paydayTransaction.execute();

        double consumerValue = 2.0;
        validatePayCheck(paydayTransaction, empId, payDate, 1000.0 - consumerValue);
    }
    
    private void validatePayCheck(PaydayTransaction paydayTransaction, int empId, Date payDate, double pay) {
        PayCheck payCheck = paydayTransaction.getPayCheck(empId);
        assertEquals(payDate, payCheck.getPayDate());
        assertEquals(pay, payCheck.getCrossPay(), .001);
        assertEquals("Hold", payCheck.getField("Disposition"));
        assertEquals(0.0, payCheck.getDeductions(), .001);
        assertEquals(1000.00, payCheck.getNetPay(), .001);
    }
    
    
    @Test
    @Order(9)
    public void testHourlyUnionMemberServiceCharge() throws Exception {
        int empId = 1;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        addSalariedEmployee.execute();
    
        int memberId = 7734;
        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(empId, memberId, 9.42);
        changeMemberTransaction.execute();
    
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.NOVEMBER, 9);
        Date payDate = calendar.getTime();
    
        ServiceChargeTransaction serviceChargeTransaction = ServiceChargeTransaction.builder()
                .memberId(memberId)
                .date(payDate.getTime())
                .amount(19.42)
                .build();
        serviceChargeTransaction.execute();
    
        TimeCardTransaction timeCardTransaction = TimeCardTransaction.builder()
                .empId(empId)
                .date(payDate.getTime())
                .hours(8.0)
                .build();
        timeCardTransaction.execute();
    
        PaydayTransaction paydayTransaction = PaydayTransaction.builder()
                .payDate(payDate)
                .build();
        paydayTransaction.execute();
    
        PayCheck payCheck = paydayTransaction.getPayCheck(empId);
        assertEquals(payDate, payCheck.getPayPeriodEndDate());
        assertEquals(8 * 15.24, payCheck.getCrossPay(), .001);
        assertEquals("Hold", payCheck.getField("Disposition"));
        assertEquals(9.42 + 19.42, payCheck.getDeductions(), .001);
        assertEquals((8 * 15.24) - (9.42 + 19.42), payCheck.getNetPay(), .001);

    }

    @Test
    @Order(10)
    public void testServiceChargesSpanningMultiplePayPeriods() throws Exception {
        int empId = 1;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        addSalariedEmployee.execute();
    
        int memberId = 7734;
        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(empId, memberId, 9.42);
        changeMemberTransaction.execute();
    
        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, Calendar.NOVEMBER, 2);
        Date earlyDate = payCalendar.getTime();
        payCalendar.set(2001, Calendar.NOVEMBER, 9);
        Date payDate = payCalendar.getTime();
        payCalendar.set(2001, Calendar.NOVEMBER, 16);
        Date lateDate = payCalendar.getTime();
    
        ServiceChargeTransaction serviceChargeTransaction = ServiceChargeTransaction.builder()
                .memberId(memberId)
                .date(payDate.getTime())
                .amount(19.42)
                .build();
        serviceChargeTransaction.execute();
    
        ServiceChargeTransaction serviceEarlyChargeTransaction = ServiceChargeTransaction.builder()
                .memberId(memberId)
                .date(earlyDate.getTime())
                .amount(100.00)
                .build();
        serviceEarlyChargeTransaction.execute();
    
        ServiceChargeTransaction serviceLateChargeTransaction = ServiceChargeTransaction.builder()
                .memberId(memberId)
                .date(lateDate.getTime())
                .amount(200.00)
                .build();
        serviceLateChargeTransaction.execute();
    
        TimeCardTransaction timeCardTransaction = TimeCardTransaction.builder()
                .empId(empId)
                .date(payDate.getTime())
                .hours(8.0)
                .build();
        timeCardTransaction.execute();
    
        PaydayTransaction paydayTransaction = PaydayTransaction.builder()
                .payDate(payDate)
                .build();
        paydayTransaction.execute();
    
        PayCheck payCheck  = paydayTransaction.getPayCheck(empId);
        assertEquals(payDate, payCheck.getPayPeriodEndDate());
        assertEquals(8 * 15.24, payCheck.getCrossPay(), .001);
        assertEquals("Hold", payCheck.getField("Disposition"));
        assertEquals(9.42 + 19.42, payCheck.getDeductions(), .001);
        assertEquals((8 * 15.24) - (9.42 + 19.42), payCheck.getNetPay(), .001);

    }

}

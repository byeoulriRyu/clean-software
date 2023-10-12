package org.example.schedule;

import java.util.Calendar;
import java.util.Date;

public class MonthlySchedule implements PaymentSchedule{

    @Override
    public boolean isPayDate(Date payDate) {
        Calendar payDateCalendar = Calendar.getInstance();
        payDateCalendar.setTime(payDate);
        return payDateCalendar.get(Calendar.DATE) == payDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return null;
    }
}

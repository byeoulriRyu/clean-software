package org.example.schedule;

import java.util.Calendar;
import java.util.Date;

public class WeeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(Date payDate) {
        Calendar payDateCalendar = Calendar.getInstance();
        payDateCalendar.setTime(payDate);
        int m1 = payDateCalendar.get(Calendar.DATE);

        payDateCalendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        int m2 = payDateCalendar.get(Calendar.DATE);
        return m1 == m2;
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return null;
    }
}

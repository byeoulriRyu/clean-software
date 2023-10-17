package org.example.classification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.TimeCard;
import org.example.transaction.pay.PayCheck;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
@Builder
public class HourlyClassification implements PaymentClassification {

    private double hourlyRate;

    @Builder.Default
    private Map<Long, TimeCard> timeCardMap = new LinkedHashMap<>();

    public void addTimeCard(TimeCard timeCard) {
        timeCardMap.put(timeCard.getDate(), timeCard);
    }

    public TimeCard getTimeCard(long date) {
        return timeCardMap.get(date);
    }

    @Override
    public double calculatePay(PayCheck pc) {
        AtomicReference<Double> totalPay = new AtomicReference<>((double) 0);
        Date payPeriod = pc.getPayDate();
        timeCardMap.keySet().forEach(key -> {
            TimeCard tc = timeCardMap.get(key);
            try {
                if(isInPayPeriod(tc, payPeriod)) {
                    totalPay.updateAndGet(v -> new Double((double) (v + calculatePayForTimeCard(tc))));
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
        return totalPay.get();
    }

    private boolean isInPayPeriod(TimeCard tc, Date payPeriod) throws ParseException {
        Date payPeriodEndDate = payPeriod;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payPeriod);
        calendar.add(Calendar.DATE, -5);

        Date payPeriodStartDate = calendar.getTime();

        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date timeCardDate = sdf.parse(String.valueOf(tc.getDate()));

        return (( timeCardDate.compareTo(payPeriodStartDate) >= 0 ) && ( timeCardDate.compareTo(payPeriodEndDate) <= 0 ));
    }

    private double calculatePayForTimeCard(TimeCard tc) {
        double hours = tc.getHours();
        double overtime = Math.max(0.0, hours - 8.0);
        double straightTime = hours - overtime;

        return straightTime * hourlyRate + overtime * hourlyRate * 1.5;
    }
}

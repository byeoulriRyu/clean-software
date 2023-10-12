package org.example.classification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.TimeCard;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class HourlyClassification extends PaymentClassification {

    private double hourlyRate;

    @Builder.Default
    private Map<Long, TimeCard> timeCardMap = new LinkedHashMap<>();

    public void addTimeCard(TimeCard timeCard) {
        timeCardMap.put(timeCard.getDate(), timeCard);
    }

    public TimeCard getTimeCard(long date) {
        return timeCardMap.get(date);
    }
}

package org.example.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.affiliation.Affiliation;
import org.example.classification.PaymentClassification;
import org.example.method.PaymentMethod;
import org.example.schedule.PaymentSchedule;

import java.util.Date;


@Getter
@Builder
public class TimeCard {

    private long date;
    private double hours;

}

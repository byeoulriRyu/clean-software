package org.example.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.affiliation.Affiliation;
import org.example.classification.PaymentClassification;
import org.example.method.PaymentMethod;
import org.example.schedule.PaymentSchedule;


@Getter
@Setter
@Builder
public class Employee {

    private int empId;
    private String name;
    private String address;
    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod method;
    private Affiliation affiliation;

}

package org.example.classification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommissionedClassification extends PaymentClassification {
    private double salary;
    private double commissionRate;
}

package org.example.classification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommissionedClassification extends PaymentClassification {
    private double salary;
    private double commissionRate;
}

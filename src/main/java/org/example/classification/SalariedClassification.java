package org.example.classification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SalariedClassification extends PaymentClassification {

    private double salary;

}

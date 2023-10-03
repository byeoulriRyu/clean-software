package org.example.classification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HourlyClassification extends PaymentClassification {

    private double hourlyRate;

}

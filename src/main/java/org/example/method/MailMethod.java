package org.example.method;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MailMethod implements PaymentMethod {

    private String address;
    
}

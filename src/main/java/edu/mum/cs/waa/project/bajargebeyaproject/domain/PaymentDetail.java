package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
public class PaymentDetail {
    String paymentId;
    String payerId;
    String payerName;
    String payerEmail;
    String shipAddLine1;
    String shipAddCity;
    String shipAddState;
    String shipAddPostalCode;
    String shipAddCountryCode;
    LocalDate payDate;
    Double amount;
    String remarks;
    String seller;
}
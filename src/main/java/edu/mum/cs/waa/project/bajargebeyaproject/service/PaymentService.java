package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.PaymentDetail;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Receipt;
import org.json.JSONException;

public interface PaymentService {

    public PaymentDetail logTransaction(String transactionId, String token, String payerId) throws JSONException;
    public String makePayment(Double amnt, String products);
}

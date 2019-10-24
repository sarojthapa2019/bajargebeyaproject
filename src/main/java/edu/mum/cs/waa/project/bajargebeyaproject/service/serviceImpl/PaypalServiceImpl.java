package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import com.paypal.api.payments.*;
import com.paypal.base.codec.binary.Base64;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import edu.mum.cs.waa.project.bajargebeyaproject.service.PaymentService;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.AccessToken;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.PaymentDetail;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
* Author:Sujiv Shrestha
*/

@Service
public class PaypalServiceImpl implements PaymentService {
    @Autowired
    private RestTemplate restTemplate;

    private final String payUrl = "http://localhost:8080";
    private final String clientId = "AQd7rwjYpHxq5omYKUZ8JaegD8mWNiY01ZFpZax1Zv-cp7frOLdGJmTjt1Zzp7uiIJ8w9LKQDV5gvYR-";
    private final String clientSecret = "EGUbTSqeJxRYEgUWLRqp7uKhMj3Y6yOmfz-ZUw14ipsRe1512miyf8AFTo_gfPe3M1UDFZl_I5I3_LbK";
    private final String authUrl = "https://api.sandbox.paypal.com/v1/oauth2/token";
    private final String mode = "sandbox";//"paypal";
    private APIContext apiContext;

    public PaypalServiceImpl() {
        apiContext = new APIContext(clientId, clientSecret, mode);
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecret(){
        return clientSecret;
    }

    public HttpHeaders createAuthHeaders(){
        String username = clientId;
        String password = clientSecret;
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

    public String getToken(){
        HttpHeaders headers = createAuthHeaders();
        MultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","client_credentials");
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body,headers);
        AccessToken authObj = restTemplate.exchange(authUrl, HttpMethod.POST,httpEntity,AccessToken.class).getBody();
        System.out.println("Auth:"+authObj.getAccess_token());
        return authObj.getAccess_token();
    }

    @Override
    public PaymentDetail logTransaction(String transactionId, String token, String payerId) throws JSONException {
        String payUrl = "https://api.sandbox.paypal.com/v1/payments/payment/"+transactionId;
        System.out.println("trans:"+payUrl);
        HttpHeaders header = createAuthHeaders();
        header.add("Content-Type","application/json");
        header.add("Authorization", getToken());
        HttpEntity<?> httpEntity = new HttpEntity<Object>(null,header);
        ResponseEntity<String> responseEntity = restTemplate.exchange(payUrl,HttpMethod.GET,httpEntity,String.class);//.postForLocation(payUrl,json);
        if(responseEntity!=null){
            System.out.println("Rest response:"+ responseEntity.getBody());
            System.out.println("Response Status:"+responseEntity.getStatusCode());
            String resStr = responseEntity.getBody();
            JSONObject js = new JSONObject(resStr);
            System.out.println(js.get("cart"));

            PaymentDetail pd = new PaymentDetail();
            JSONObject tran = (JSONObject) js.getJSONArray("transactions").get(0);

            JSONObject payerInfo = js.getJSONObject("payer").getJSONObject("payer_info");
            String am = tran.getJSONObject("amount").get("total").toString();
            pd.setAmount(Double.parseDouble(am));
            pd.setPayDate(LocalDate.now());
            pd.setPayerEmail(payerInfo.get("email").toString());
            pd.setPayerId(payerInfo.get("payer_id").toString());
            pd.setPayerName(payerInfo.get("first_name").toString()+" "+payerInfo.get("last_name").toString());
            pd.setPaymentId(js.get("id").toString());
            pd.setRemarks(tran.get("description").toString());

            JSONObject shipAd = payerInfo.getJSONObject("shipping_address");
            pd.setShipAddCity(shipAd.get("city").toString());
            pd.setShipAddCountryCode(shipAd.get("country_code").toString());
            pd.setShipAddLine1(shipAd.get("line1").toString());
            pd.setShipAddPostalCode(shipAd.get("postal_code").toString());
            pd.setShipAddState(shipAd.get("state").toString());

            return pd;
        }

        return null;
    }

    @Override
    public String makePayment(Double amnt, String orderId) {
        String paymentStatus="/cancel";

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(Double.toString(amnt));

        //setShipping and taxes here...
        Details detail = new Details();

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(orderId);
        transaction.setSoftDescriptor("Total charged ammount:"+Double.toString(amnt));
        transaction.setNoteToPayee("Thanks for shopping at BajarGebeya");
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
//        payer.getPayerInfo().setEmail("badboy2@mum.com");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(payUrl+"/payment/cancel/"+orderId);
        redirectUrls.setReturnUrl(payUrl+"/order/list");
        payment.setRedirectUrls(redirectUrls);

        // Create payment
        try {
            Payment createdPayment = payment.create(apiContext);

             System.out.println(createdPayment);

            Iterator<Links> links = createdPayment.getLinks().iterator();
            while (links.hasNext()) {
                Links link = links.next();
                String payMethod = "approval_url";//"execute";//
                if (link.getRel().equalsIgnoreCase(payMethod)){
                    // REDIRECT USER TO link.getHref()
                    return link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }

        return paymentStatus;
    }
}

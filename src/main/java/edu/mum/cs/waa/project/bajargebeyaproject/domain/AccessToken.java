package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccessToken {
    String scope;           //: "https://uri.paypal.com/services/invoicing https://uri.paypal.com/services/disputes/read-buyer https://uri.paypal.com/services/payments/realtimepayment https://uri.paypal.com/services/disputes/update-seller https://uri.paypal.com/services/payments/payment/authcapture openid https://uri.paypal.com/services/disputes/read-seller https://uri.paypal.com/services/payments/refund https://api.paypal.com/v1/vault/credit-card https://api.paypal.com/v1/payments/.* https://uri.paypal.com/payments/payouts https://api.paypal.com/v1/vault/credit-card/.* https://uri.paypal.com/services/subscriptions https://uri.paypal.com/services/applications/webhooks",
    String access_token;    //: "A21AAHHd4z3kEmha0jdkGgsFLthiemzKX6d5gW7woKDuZeZh7io8rsp1Tm6NgElViI8AjqinS3MJuhrdLVyz5TrC8KpahqIOQ",
    String token_type;      //: "Bearer",
    String app_id;          //: "APP-80W284485P519543T",
    Long expires_in = 0L;        //: 31996,
    String nonce;           //: "2019-07-17T20:06:16ZKMqiXfbA8UxqR3Ir067v4U-2Kq_nreYnWqVBzR-BWec"
}

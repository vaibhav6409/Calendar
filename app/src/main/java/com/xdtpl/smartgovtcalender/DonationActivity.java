package com.xdtpl.smartgovtcalender;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.xdtpl.Model.Ip;
import com.xdtpl.Remote.IpService;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import java.util.HashMap;
import java.util.Map;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationActivity extends AppCompatActivity {

    IpService mService;
    EditText amount;
    Button btnGetIP;
    AlertDialog dialog;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        mService = Common.getIpService();

        Button donation = (Button) findViewById(R.id.donation);
        amount = (EditText) findViewById(R.id.amount);
        dialog = new SpotsDialog(DonationActivity.this);

        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isError = false;
                if (amount.length() == 0) {
                    amount.requestFocus();
                    amount.setError("Field cannot be empty");
                    isError = true;
                }
                if(isError)
                    return;

                getData();
                //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://paytm.com/"));
                //startActivity(browserIntent);
            }
        });
    }

    private void getData() {
        try {
            final String txamount = amount.getText().toString();
            final long time = System.currentTimeMillis();

            final Map<String, String> paramMap = new HashMap<String,String>();
            paramMap.put( "MID" , "EiiHhf00580411774533");
            // Key in your staging and production MID available in your dashboard
            paramMap.put( "ORDER_ID" , String.valueOf(time));
            paramMap.put( "CUST_ID" , String.valueOf(time));
            //paramMap.put( "MOBILE_NO" , "9975538344");
            //paramMap.put( "EMAIL" , "nilesh.bhagate@outlook.com");
            paramMap.put( "CHANNEL_ID" , "WAP");
            paramMap.put( "TXN_AMOUNT" , txamount);
            paramMap.put( "WEBSITE" , "DEFAULT");
            // This is the staging value. Production value is available in your dashboard
            paramMap.put( "INDUSTRY_TYPE_ID" , "Retail");
            // This is the staging value. Production value is available in your dashboard
            paramMap.put( "CALLBACK_URL", "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID="+time);

            loading = ProgressDialog.show(DonationActivity.this, "Loading" , "Wait while Loading");

            mService.getIp(paramMap).enqueue(new Callback<Ip>() {
                @Override
                public void onResponse(Call<Ip> call, Response<Ip> response) {
                    //PaytmPGService Service = PaytmPGService.getStagingService();
                    PaytmPGService Service = PaytmPGService.getProductionService();
                    //dialog.dismiss();
                    //txtIP.setText(response.body().getchecksum());
                    //paramMap.put( "CHECKSUMHASH" , "tqMojYANnL5rFXqa3x3XedNi7pMfryfD1ehgyZpytxPCnSUUmMu96rXijfXQE3kyLprwLxbwH9WQQb0kRWifk7C0rZhRdVdGfAHrQ0BYApM=");
                    paramMap.put( "CHECKSUMHASH" , response.body().getchecksum());

                    PaytmOrder Order = new PaytmOrder((HashMap<String, String>) paramMap);
                    Service.initialize(Order, null);

                    Service.startPaymentTransaction(DonationActivity.this, true, true, new PaytmPaymentTransactionCallback() {
                        /*Call Backs*/
                        public void someUIErrorOccurred(String inErrorMessage) {

                            Toast.makeText(getApplicationContext(), "Payment Transaction response ", Toast.LENGTH_LONG).show();
                        }
                        public void onTransactionResponse(Bundle inResponse) {
                            Toast.makeText(getApplicationContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();

                        }
                        public void networkNotAvailable() {
                            Toast.makeText(getApplicationContext(), "Payment Transaction response ", Toast.LENGTH_LONG).show();

                        }
                        public void clientAuthenticationFailed(String inErrorMessage) {
                            Toast.makeText(getApplicationContext(), "Payment Transaction response ", Toast.LENGTH_LONG).show();
                        }
                        public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
                            Toast.makeText(getApplicationContext(), "Payment Transaction response ", Toast.LENGTH_LONG).show();
                        }
                        public void onBackPressedCancelTransaction() {
                            Toast.makeText(getApplicationContext(), "Payment Transaction response ", Toast.LENGTH_LONG).show();
                        }
                        public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                            Toast.makeText(getApplicationContext(), "Payment Transaction response ", Toast.LENGTH_LONG).show();
                        }
                    });

                    loading.hide();
                }

                @Override
                public void onFailure(Call<Ip> call, Throwable t) {
                    Log.e("Error", t.getMessage());
                    loading.hide();
                    //dialog.dismiss();
                }
            });
        }catch (Exception e){
            Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}

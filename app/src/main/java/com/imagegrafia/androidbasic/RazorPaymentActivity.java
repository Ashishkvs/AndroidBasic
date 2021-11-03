package com.imagegrafia.androidbasic;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class RazorPaymentActivity extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = RazorPaymentActivity.class.getSimpleName();
    private static String KEY_ID = "rzp_test_jTgNz1Aqjz7YTc";
    private static String KEY_SECRET = "UKjvA8e2wxjFMkjSpEqf00Ag";

    private EditText editTextPaymentAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razor_payment);

        /**
         * Preload payment resources
         */
        Checkout.preload(getApplicationContext());
        editTextPaymentAmount = findViewById(R.id.editTextPaymentAmount);
        Button btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(v -> startPayment(editTextPaymentAmount.getText().toString()));
    }

    //    Order createOrderWithRazorPayGetOrderId() {
//        try {
//            JSONObject orderRequest = new JSONObject();
//            orderRequest.put("amount", 50000); // amount in the smallest currency unit
//            orderRequest.put("currency", "INR");
//            orderRequest.put("receipt", "order_rcptid_11");
//
//            Order order = razorpay.Orders.create(orderRequest);
//        } catch (RazorpayException e) {
//            // Handle Exception
//            System.out.println(e.getMessage());
//        }
//    }
    public void startPayment(String amount) {
        Log.i(TAG, "Amount passed {} " + amount);
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID(KEY_ID);
        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.omsent_logo);

        /**
         * Reference to current activity
         */
        final Activity activity = RazorPaymentActivity.this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Omsent Androd app");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //BEFORE ACTUAL CHECKOUT GET ORDER_ID GENERATED FROM BACKEND AND PASS IT HERE TO COMPLETE PAYMENT PROCESS
//            options.put("order_id", "order_od123");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", String.valueOf(Float.parseFloat(amount) * 100));//pass amount in currency subunits
            options.put("prefill.email", "ashishkvs@gmail.com");
            options.put("prefill.contact", "8599894881");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch (Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Log.i(TAG, "razorpayPaymentID : " + razorpayPaymentID);
            capturePaymentViaBackend(razorpayPaymentID, editTextPaymentAmount.getText().toString());
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
    void capturePaymentViaBackend(String razorpayPaymentID , String amount) {
        Toast.makeText(this, amount+" Payment Successful: " + razorpayPaymentID, Toast.LENGTH_LONG).show();
    }
}
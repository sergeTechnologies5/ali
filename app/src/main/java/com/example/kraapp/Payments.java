package com.example.kraapp;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import models.Payment;
import models.PaymentResponse;
import models.Services;
import retrofit.ApiClient;
import retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payments extends AppCompatActivity {

    Button mpesa;
    Button back;
    EditText number;
    public ApiService apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        mpesa = findViewById(R.id.mpesa);
        apiInterface = ApiClient.getService();
        number = findViewById(R.id.number);

        back = findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ServicesActivity.class));
            }
        });

        mpesa.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user_id = String.valueOf(MainActivity.user_id);
                        String service_id = String.valueOf(ServicesActivity.service_id);
                        String fee = String.valueOf(ServicesActivity.fee);
                        String numberr = number.getText().toString();

                        Call<PaymentResponse> call = apiInterface.payForservices( new Payment(user_id,service_id,numberr,fee));
                        call.enqueue(new Callback<PaymentResponse>() {
                            @Override
                            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {

                                Toast.makeText(getApplication(), response.message(),Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                               // Toast.makeText(getApplication(), t.getMessage(),Toast.LENGTH_LONG).show();
                            }

                        });

                        number.setText("");

                    }
                }
        );
    }
}

package com.example.kraapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Payment;
import models.PaymentResponse;
import models.User;
import repositories.UserRepo;
import retrofit.ApiClient;
import retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    @BindView(R.id.input_firstname)
    EditText _firtstnameText;

    @BindView(R.id.input_lastname) EditText _lastnameText;


    @BindView(R.id.input_name) EditText _nameText;

    @BindView(R.id.input_email) EditText _emailText;

    @BindView(R.id.input_password) EditText _passwordText;

    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;

    public ApiService apiInterface;
    UserRepo userRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        ButterKnife.bind(this);

        apiInterface = ApiClient.getService();

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = _emailText.getText().toString();
                String firstname = _firtstnameText.getText().toString();
                String lastname = _lastnameText.getText().toString();
                String password = _passwordText.getText().toString();
                String username = _nameText.getText().toString();

                signup(username,email,  firstname,lastname,password );
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    private void signup(String username, String email, String firstname, String lastname, String password) {

        userRepo = new UserRepo(getApplication());
        userRepo.createAccount(new User(email,firstname,lastname,username,password));
        _emailText.setText("");
        _firtstnameText.setText("");
        _lastnameText.setText("");
        _passwordText.setText("");
        _nameText.setText("");

        //Toast.makeText(this,username+email+firstname+lastname+password,Toast.LENGTH_LONG).show();

    }
}

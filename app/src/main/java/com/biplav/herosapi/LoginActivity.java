package com.biplav.herosapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.biplav.herosapi.Api.UserApi;
import com.biplav.herosapi.Url.Url;
import com.biplav.herosapi.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError("please enter name");
                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("please enter age");
                } else {

                    Retrofit retrofit = Url.getInstance();
                    User user = new User(username.getText().toString().trim(), password.getText().toString().trim());
                    UserApi userApi = retrofit.create(UserApi.class);
                    Call<Void> voidCall = userApi.loginUser(user);

                    voidCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (!response.isSuccessful()) {
                                Log.d("error", response.toString());
                                Toast.makeText(LoginActivity.this, ""+ response.message(), Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("error", t.getLocalizedMessage());
                        }
                    });

                }

            }
        });
    }
}

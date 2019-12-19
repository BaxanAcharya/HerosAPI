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

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.usernameR);
        password = findViewById(R.id.passwordR);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError("please enter name");
                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("please enter age");
                } else {
                    Retrofit retrofit = Url.getInstance();
                    User user = new User(username.getText().toString(), password.getText().toString());
                    UserApi userApi = retrofit.create(UserApi.class);
                    Call<Void> voidCall = userApi.registerUser(user);

                    voidCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (!response.isSuccessful()) {
                                Log.d("error", response.toString());
                                Toast.makeText(MainActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "User Created!!!!!!!!", Toast.LENGTH_SHORT).show();
                                username.setText("");
                                password.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("error  m", t.getLocalizedMessage());
                        }
                    });
                }
            }
        });
    }
}

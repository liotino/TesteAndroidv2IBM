package com.leonardoliotino.testeibm.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.leonardoliotino.testeibm.R;
import com.leonardoliotino.testeibm.api.ApiMethodRetrofit;
import com.leonardoliotino.testeibm.controller.validar.CPF;
import com.leonardoliotino.testeibm.domain.LoginResponse;
import com.leonardoliotino.testeibm.domain.User;
import com.leonardoliotino.testeibm.domain.UserAccount;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText inputUsername;
    EditText inputSenha;
    SharedPreferences pref;
    SharedPreferences.Editor prefEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputSenha = (EditText) findViewById(R.id.inputSenha);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                attemptLogin();

                Log.d("TAG-LOGIN","ENVIALOGIN");
                Log.d("TAG-USERNAME",inputUsername.getText().toString());
                Log.d("TAG-SENHA",inputSenha.getText().toString());

           }

        });

    }


    public void attemptLogin() {

        inputUsername.setError(null);
        inputSenha.setError(null);

        String username = inputUsername.getText().toString();
        String senha = inputSenha.getText().toString();

        Boolean flagCancel = false;


        if(!TextUtils.isEmpty(username)) {

            //checa se o usuario e um email
            if(isEmailValid(username)) {

                flagCancel = false;

             //checa se o usuario um cpf valido
            }else if(CPF.isCPF(username)) {

                flagCancel = false;

                Log.d("TAG-CPF","ESSE E UM CPF");


            }else{

                flagCancel = true;


                inputUsername.setError(getString(R.string.error_cpf_email_required));

            }


        }else{

            flagCancel = true;

           inputUsername.setError(getString(R.string.error_field_required));


        }


        if(TextUtils.isEmpty(senha)) {

           flagCancel = true;

           inputSenha.setError(getString(R.string.error_field_required));

        }


        if(!senha.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{3,}$")) {

          flagCancel = true;

          inputSenha.setError(getString(R.string.error_caracteres_required));


        }


        if(flagCancel == false) {

            User user = new User();

            user.setUsername(username);
            user.setPassword(senha);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            Retrofit retrofit = new Retrofit.Builder().baseUrl(getString(R.string.webservice))
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            ApiMethodRetrofit apiMethodsRetrofit2 = retrofit.create(ApiMethodRetrofit.class);

            Call<LoginResponse> callbackUser =  apiMethodsRetrofit2.login("test_user","Test@1");

            callbackUser.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if (response.isSuccessful()) {

                        if(response.code() == 200) {

                            //Log.d("tag-retro", call.request().body().toString());

                            LoginResponse loginResponse = (LoginResponse) response.body();

                            Log.d("TAG-RETROFIT-SUCESS", loginResponse.userAcount.toString());

                            UserAccount userAccount = (UserAccount) loginResponse.userAcount;


                            Gson gson = new Gson();

                            String jsonUser = gson.toJson(userAccount);

                            Log.d("TAG-OBJ-USERACOUNT-JSON",jsonUser);

                            pref = getSharedPreferences("objUser", Context.MODE_PRIVATE);

                            prefEditor = pref.edit();

                            prefEditor.putString("objUser",jsonUser);

                            if(prefEditor.commit()) {

                                Log.d("TAG-ACTIVI-SALVADO","SALVOU COM SUCESSO");
                                Log.d("TAG-ACTIVI-SALVADO-JSON",jsonUser.toString());

                            }


                        }

                   }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    Log.d("TAG-RETROFIT-ERRO", t.getMessage());


                }
            });

            inputUsername.setText(" ");
            inputSenha.setText(" ");


        }

    }

    private boolean isEmailValid(String email) {

        return email.contains("@") && email.contains(".");

        //return email.matches("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})");

    }



}

package com.leonardoliotino.testeibm.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.leonardoliotino.testeibm.R;
import com.leonardoliotino.testeibm.controller.validar.CPF;
import com.leonardoliotino.testeibm.controller.validar.ValidaCPF;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText inputUsername;
    EditText inputSenha;

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

                //Log.d("TAG-CPF","ESSE NAO E UM CPF");

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


    }

    private boolean isEmailValid(String email) {

        //return email.contains("@") && email.contains(".");

        return email.matches("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})");

    }




}

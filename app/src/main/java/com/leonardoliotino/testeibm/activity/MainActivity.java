package com.leonardoliotino.testeibm.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.leonardoliotino.testeibm.R;

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

                Log.d("TAG-LOGIN","ENVIALOGIN");
                Log.d("TAG-USERNAME",inputUsername.getText().toString());
                Log.d("TAG-SENHA",inputSenha.getText().toString());

           }

        });



    }


}

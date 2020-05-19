package com.leonardoliotino.testeibm.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.leonardoliotino.testeibm.R;
import com.leonardoliotino.testeibm.adapter.StatementAdapter;
import com.leonardoliotino.testeibm.api.ApiMethodRetrofit;
import com.leonardoliotino.testeibm.domain.StatementResponse;
import com.leonardoliotino.testeibm.domain.UserAccount;

import java.text.NumberFormat;
import java.util.Locale;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityContaSelecionada extends AppCompatActivity {

 TextView txtContaNome;
 TextView txtConta;
 TextView txtContaNumero;
 TextView txtSaldo;
 TextView txtSaldoQuantia;
 Context context;

 ImageView btnLogOut;
 SharedPreferences pref;
 SharedPreferences.Editor prefEditor;

 RecyclerView recyclerView;
 LinearLayoutManager linearLayoutManager;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_conta_selecionada);

      context = this;

      txtContaNome = (TextView) findViewById(R.id.txtNome);
      txtConta = (TextView) findViewById(R.id.txtConta);
      txtContaNumero = (TextView) findViewById(R.id.txtNumeroConta);
      txtSaldo = (TextView) findViewById(R.id.txtSaldo);
      txtSaldoQuantia = (TextView) findViewById(R.id.txtSaldoQuantia);
      btnLogOut = (ImageView) findViewById(R.id.btnLogOut);

      pref = getSharedPreferences("objUser", Context.MODE_PRIVATE);

      if(!pref.getString("objUser", "").isEmpty()) {

          Gson gson = new Gson();

          String jsonRecupera = pref.getString("objUser", "");

          UserAccount userAccount = gson.fromJson(jsonRecupera,UserAccount.class);

          Log.d("TAG-EXSITE-PREFERENCES",userAccount.toString());

          txtContaNome.setText(userAccount.getName());
          txtContaNumero.setText(userAccount.getAgency());

          NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
          txtSaldoQuantia.setText(numberFormat.format(userAccount.getBalance()));

          //API
          OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

          Retrofit retrofit = new Retrofit.Builder().baseUrl(getString(R.string.webservice))
                  .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                  .client(okHttpClient)
                  .build();


          ApiMethodRetrofit apiMethodsRetrofit2 = retrofit.create(ApiMethodRetrofit.class);

          Call<StatementResponse> callBackStatements = apiMethodsRetrofit2.listContaSelecionada(userAccount.getUserId());

           callBackStatements.enqueue(new Callback<StatementResponse>() {

               @Override
               public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {

                   if (response.isSuccessful()) {

                       if(response.code() == 200) {

                           StatementResponse statementResponse = (StatementResponse) response.body();

                           Log.d("TAG-RETROFIT-SUCESS", statementResponse.statementListList.toString());

                           recyclerView = (RecyclerView) findViewById(R.id.ReciclerView);
                           StatementAdapter statementAdapter = new StatementAdapter(statementResponse.statementListList,context);
                           linearLayoutManager = new LinearLayoutManager(context);
                           recyclerView.setLayoutManager(linearLayoutManager);
                           recyclerView.setAdapter(statementAdapter);

                       }

                   }

               }

               @Override
               public void onFailure(Call<StatementResponse> call, Throwable t) {

                   Log.d("TAG-RETROFIT-ERRO", t.getMessage());

               }

           });


           btnLogOut.setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View view) {

                   /*
                   prefEditor = pref.edit();
                   prefEditor.remove("objUser");
                   prefEditor.clear();
                   prefEditor.commit();
                   */

                   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                   startActivity(intent);

               }

           });



      }

  }

}

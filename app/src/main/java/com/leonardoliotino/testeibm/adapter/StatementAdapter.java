package com.leonardoliotino.testeibm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leonardoliotino.testeibm.R;
import com.leonardoliotino.testeibm.domain.StatementList;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ViewHolder> {

   List<StatementList> statementLists;
   Context context;


    public StatementAdapter(List<StatementList> statementLists, Context context) {

     this.statementLists = statementLists;
     this.context = context;

    }


    @Override
    public void onBindViewHolder(@NonNull StatementAdapter.ViewHolder holder, int position) {

     StatementList statement = statementLists.get(position);

        //Exception devido a data

        try {

            holder.bindView(statement);

        }catch(ParseException e) {
            e.printStackTrace();
        }

    }


    @NonNull
    @Override
    public StatementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View view = LayoutInflater.from(context).inflate(R.layout.item_conta_selecionada,parent,false);

      ViewHolder viewHolder = new ViewHolder(view);

      return viewHolder;

    }



    @Override
    public int getItemCount() {

     return this.statementLists.size();


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //construtora
        ViewHolder(View itemView) {
         super(itemView);
        }


      void bindView(StatementList statementList) throws ParseException {

          TextView txtTitle = itemView.findViewById(R.id.txtTitle);
          TextView txtDesc = itemView.findViewById(R.id.txtDesc);
          TextView txtDate = itemView.findViewById(R.id.txtDate);
          TextView txtValue = itemView.findViewById(R.id.txtValue);

          txtTitle.setText(statementList.getTitle());
          txtDesc.setText(statementList.getDesc());

          SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
          Date dataConvertida = new SimpleDateFormat("yyyy-MM-dd").parse(statementList.getDate());
          txtDate.setText(formatoData.format(dataConvertida));

          NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
          txtValue.setText(numberFormat.format(statementList.getValue()));

      }

    }



}

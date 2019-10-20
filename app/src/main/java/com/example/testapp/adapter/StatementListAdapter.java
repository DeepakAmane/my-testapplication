package com.example.testapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.network.response.Statement;
import com.example.testapp.network.response.StatementResponse;
import com.example.testapp.viewmodel.StatementsListViewModel;

import java.util.ArrayList;
import java.util.List;

public class StatementListAdapter extends RecyclerView.Adapter<StatementListAdapter.StatementHolder> {


    private List<Statement> statementList = new ArrayList<>();

    @NonNull
    @Override
    public StatementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_statement_item, parent, false);
        return new StatementHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementHolder holder, int position) {
        Statement statement = statementList.get(position);
        holder.title.setText(statement.getTitle());
        holder.description.setText(statement.getDesc());
        holder.date.setText(statement.getDate());
        holder.value.setText(String.valueOf(statement.getValue()));

    }

    @Override
    public int getItemCount() {
        return statementList.size();
    }


    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
        notifyDataSetChanged();
    }

    class StatementHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView date;
        private TextView value;

        public StatementHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_textView);
            description = itemView.findViewById(R.id.description_textView);
            date = itemView.findViewById(R.id.date_textView);
            value = itemView.findViewById(R.id.value_textView);

        }
    }


}

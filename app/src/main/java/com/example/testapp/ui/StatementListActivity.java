package com.example.testapp.ui;

import android.os.Bundle;

import com.example.testapp.adapter.StatementListAdapter;
import com.example.testapp.network.response.StatementResponse;
import com.example.testapp.network.response.UserAccount;
import com.example.testapp.utils.Utils;
import com.example.testapp.viewmodel.StatementsListViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.R;

/**
 * Statement details List is displayed for authenticated User
 **/
public class StatementListActivity extends AppCompatActivity {

    // UI references
    private StatementsListViewModel mViewModel;
    private RecyclerView mStatementListRecyclerView;
    private StatementListAdapter adapter;
    private ProgressBar mProgressBar;

    private TextView nameTextView, accountTextView, balanceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_list);
        iniUIControls();
        initRecyclerView();
        bindValuesTOUI();
        mViewModel = ViewModelProviders.of(this).get(StatementsListViewModel.class);
        handleNetworkConnection();
        pullStatementListOnLoad();
    }

    private void handleNetworkConnection() {
        if (Utils.isNetworkConnected(StatementListActivity.this)) {
            observeToFetchStatementsList();
        } else {
            Toast.makeText(StatementListActivity.this, R.string.no_net_connection, Toast.LENGTH_LONG).show();
        }
    }

    private void bindValuesTOUI() {
        if (getIntent().getSerializableExtra("MyClass") != null) {
            UserAccount account = (UserAccount) getIntent().getSerializableExtra("MyClass");
            nameTextView.setText(account.getName());
            accountTextView.setText(account.getBankAccount());
            balanceTextView.setText(String.valueOf(account.getBalance()));
        }
    }

    /**
     * Inialization of the Ui controls on the screen
     **/
    private void iniUIControls() {
        nameTextView = (TextView) findViewById(R.id.name_textview);
        accountTextView = (TextView) findViewById(R.id.account_value_textView);
        balanceTextView = (TextView) findViewById(R.id.balance_value_textView);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Call the StatementList API on launch of the Screen
     ***/
    private void pullStatementListOnLoad() {
        if (getIntent().getSerializableExtra("MyClass") != null) {
            mViewModel.onLaunchPullStatementList((UserAccount) getIntent().getSerializableExtra("MyClass"));
        }
    }

    /**
     * Renders RecyclerView with statement list
     */
    private void initRecyclerView() {
        mStatementListRecyclerView = (RecyclerView) findViewById(R.id.statementListRecylerview);
        mStatementListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStatementListRecyclerView.setHasFixedSize(true);
        adapter = new StatementListAdapter();
        mStatementListRecyclerView.setAdapter(adapter);
    }

    /*
     * Observe the Statement List and set to the adapter
     * ***/
    private void observeToFetchStatementsList() {
        mViewModel.getStatementList().observe(this, new Observer<StatementResponse>() {
            @Override
            public void onChanged(StatementResponse statementResponse) {
                mProgressBar.setVisibility(View.GONE);
                if (statementResponse.getStatementList().size() == 0) {
                    Toast.makeText(StatementListActivity.this, "Unbale to get Statement List", Toast.LENGTH_LONG).show();
                } else {

                    adapter.setStatementList(statementResponse.getStatementList());
                }
            }
        });
    }
}

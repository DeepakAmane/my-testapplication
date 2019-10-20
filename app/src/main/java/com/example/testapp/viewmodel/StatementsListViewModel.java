package com.example.testapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testapp.model.StatementsRepository;
import com.example.testapp.network.response.StatementResponse;
import com.example.testapp.network.response.UserAccount;

/**
 * StatementList UI holder for
 * StatementListActivity
 **/
public class StatementsListViewModel extends AndroidViewModel {

    // Create a LiveData
    private LiveData<StatementResponse> statementListResponse;
    private StatementsRepository statementsRepository;

    public StatementsListViewModel(Application application) {
        super(application);
        statementsRepository = new StatementsRepository();
        statementListResponse = statementsRepository.getStatementList();
    }

    public LiveData<StatementResponse> getStatementList() {
        return statementListResponse;
    }

    public void onLaunchPullStatementList(UserAccount userAccount) {
        if (userAccount != null) {
            statementsRepository.fetchStatementList(userAccount);
        }
    }
}

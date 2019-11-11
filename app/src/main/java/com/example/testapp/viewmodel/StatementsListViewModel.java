package com.example.testapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testapp.model.UserRepository;
import com.example.testapp.network.response.StatementResponse;
import com.example.testapp.network.response.UserAccount;

/**
 * StatementList UI holder for
 * StatementListActivity
 **/
public class StatementsListViewModel extends AndroidViewModel {

    // Create a LiveData
    private LiveData<StatementResponse> statementListResponse;
    private UserRepository mUserRepository;

    public StatementsListViewModel(Application application, UserRepository userRepository) {
        super(application);
        this.mUserRepository = userRepository;
        statementListResponse = userRepository.getStatementList();
    }

    public LiveData<StatementResponse> getStatementList() {
        return statementListResponse;
    }

    public void onLaunchPullStatementList(UserAccount userAccount) {
        if (userAccount != null) {
            mUserRepository.fetchStatementList(userAccount);
        }
    }
}

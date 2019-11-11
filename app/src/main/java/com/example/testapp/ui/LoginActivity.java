package com.example.testapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.testapp.R;
import com.example.testapp.databinding.ActivityLoginBinding;
import com.example.testapp.model.UserViewModelFactory;
import com.example.testapp.viewmodel.LoginViewModel;

/*****
 * Performs Authentication of the User
 */

public class LoginActivity extends AppCompatActivity {

    // UI references
    private LoginViewModel mViewModel;
    private ActivityLoginBinding activityLoginBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_login);

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        // Get the ViewModel.
        //mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        UserViewModelFactory factory = UserViewModelFactory.getInstance(this.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);


        activityLoginBinding.setViewModel(mViewModel);
        activityLoginBinding.setLifecycleOwner(this);

        obeserveLoginAuthentication();
        handleLoginSubmit();
        observeToastMessages();
    }

    public LoginViewModel getmViewModel() {
        if (mViewModel != null) {
            return mViewModel;
        } else
            return null;
    }

    /**
     * Show the Toast Messages as per the user invalid inputs
     **/
    private void observeToastMessages() {
        mViewModel.getToastMessage().observe(this, message -> {
            if (!message.isEmpty()) {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    /***
     * Handle Login Submit listener
     */
    private void handleLoginSubmit() {
        activityLoginBinding.loginButton.setOnClickListener(
                (View view) -> {
                    mViewModel.onBtnLoginClick();
                });
    }

    /***
     * On Successful authentication navigate  the Statement List Screen via Intent
     */
    private void obeserveLoginAuthentication() {
        mViewModel.getLoginUser().observe(this, userResponse -> {
            if (userResponse != null) {
                Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, StatementListActivity.class);
                intent.putExtra("MyClass", userResponse.getUserAccount());
                startActivity(intent);
                finish();
                activityLoginBinding.progressBar.setVisibility(View.GONE);

            } else {
                Log.d("LoginActivity", "value user is null");
                // Show ERROR
            }
        });
    }
}

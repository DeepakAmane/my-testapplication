package com.example.testapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testapp.model.UserDetailsRepository;
import com.example.testapp.network.response.LoginResponse;
import com.example.testapp.utils.Utils;

/****
 * The LoginViewmodel class holds the ui data for LoginActivity
 */
public class LoginViewModel extends AndroidViewModel {

    // Create a LiveData
    private LiveData<LoginResponse> userLoginResponse;

    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public MutableLiveData<Integer> visibility;
    private MutableLiveData<String> toastMessage;
    private UserDetailsRepository userDetailsRepository;
    private Context context;


    public LoginViewModel(Application application) {
        super(application);
        this.context = application.getApplicationContext();
        userDetailsRepository = new UserDetailsRepository();
        toastMessage = new MutableLiveData<>();
        toastMessage.setValue("");
        userLoginResponse = userDetailsRepository.getLoginUser();
    }

    public MutableLiveData<Integer> getVisibility() {
        if (visibility == null) {
            visibility = new MutableLiveData<>();
            visibility.setValue(8);
        }
        return visibility;
    }



    public LiveData<LoginResponse> getLoginUser() {
        return userLoginResponse;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    /***
     * Handle the Login button listener via LoginActivity.java
     */
    public void onBtnLoginClick() {
        if (validateInputs()) {
            if (Utils.isNetworkConnected(context)) {
                getVisibility().setValue(0); // show Progress Dialog
                userDetailsRepository.loginUser(email.get(), password.get());
            } else {
                toastMessage.setValue("No Network Connection");
            }
        }
    }

    public boolean validateInputs() {
        boolean isValid = true;

        if (email.get() == null) {
            isValid = false;
            toastMessage.setValue("Enter Email id");

        } else if (!Utils.isEmailValid(email.get())) {
            isValid = false;
            toastMessage.setValue("Invalid Email");

        } else if (password.get() == null) {
            isValid = false;
            toastMessage.setValue("Enter Password");

        } else if (!Utils.isValidPassword(password.get())) {
            isValid = false;
            toastMessage.setValue("Password does not Match");
        }
        return isValid;
    }

}

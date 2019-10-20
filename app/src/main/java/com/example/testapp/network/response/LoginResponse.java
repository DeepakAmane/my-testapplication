package com.example.testapp.network.response;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private  UserAccount userAccount;
    private   Error error;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}

package com.example.testapp;

import com.example.testapp.network.response.UserAccount;

public class InjectUserAccountDetails {

    public static UserAccount injectUserAccount() {
        return new UserAccount(1, "Jose da Silva Teste", "2050", "012314564", 3.3445);
    }
}

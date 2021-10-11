/*
 *  Srinivas Bharadwaj Chintalapati
 *  Test Data: username: a@a.com, pwd: test123
 */

package com.example.userloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginAction, RegisterFragment.RegisterInterface, AccountFragment.AccountInterface, UpdateFragment.MInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onLoginSuccess(DataServices.Account account) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, AccountFragment.newInstance(account)).addToBackStack(null).commit();

    }

    @Override
    public void goToRegister() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new RegisterFragment()).addToBackStack(null).commit();
    }

    @Override
    public void goToAccount(DataServices.Account account) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, AccountFragment.newInstance(account))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToBackFragment() {

        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToEdit(DataServices.Account account) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, UpdateFragment.newInstance(account),"Update Fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToMain() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new LoginFragment()).addToBackStack(null).commit();
    }
}
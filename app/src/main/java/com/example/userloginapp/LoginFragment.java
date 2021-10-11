/**
 *  Assignment - InClass04
 *  File - Group10_InClass04.zip
 *  Group Members Anurag Chitnis & Srinivas Bharadwaj Chintalapati
 */
package com.example.userloginapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class LoginFragment extends Fragment {




    public LoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Login");
        EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        EditText editTextPassword = view.findViewById(R.id.editTextPassword);
        view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    DataServices.AccountRequestTask task = DataServices.login(editTextEmail.getText().toString(),
                            editTextPassword.getText().toString());
                    if (task.isSuccessful()) {
                        loginAction.onLoginSuccess(task.getAccount());
                    } else {
                        Toast.makeText(getActivity(), task.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }

                }


        });

        view.findViewById(R.id.textViewCreateAcc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAction.goToRegister();
            }
        });



    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof LoginAction){
            loginAction = (LoginAction) context ;
        }
    }
   LoginAction loginAction;
    public interface  LoginAction{
        void onLoginSuccess(DataServices.Account account);
        void goToRegister();
    }
}
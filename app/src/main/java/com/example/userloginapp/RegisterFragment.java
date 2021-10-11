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


public class RegisterFragment extends Fragment {


    public RegisterFragment() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Register Account");

        EditText editTextName = view.findViewById(R.id.editTextName);
        EditText editTextEmail = view.findViewById(R.id.editTextEmailId);
        EditText editTextPassword = view.findViewById(R.id.editTextPasswordId);
        view.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    DataServices.AccountRequestTask accountRequestTask = DataServices.register(editTextName.getText().toString(),
                            editTextEmail.getText().toString(),editTextPassword.getText().toString());
                    if(accountRequestTask.isSuccessful()) {
                        registerInterface.goToAccount(accountRequestTask.getAccount());
                    }else{
                        Toast.makeText(getActivity(), accountRequestTask.getErrorMessage(),Toast.LENGTH_SHORT).show();
                    }
                }


        });
        view.findViewById(R.id.textViewCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerInterface.goToMain();
            }
        });


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof RegisterInterface){
            registerInterface =  (RegisterInterface)context;
        }
    }

    RegisterInterface registerInterface;
    public interface RegisterInterface{
        void goToAccount(DataServices.Account account);
        void goToMain();
    }
}
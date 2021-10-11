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
import android.widget.TextView;
import android.widget.Toast;


public class UpdateFragment extends Fragment {


    private static final String ARG_ACCOUNT = "Account";

    private DataServices.Account account;

    public UpdateFragment() {
        // Required empty public constructor
    }

    public static UpdateFragment newInstance(DataServices.Account account) {
        UpdateFragment fragment = new UpdateFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ACCOUNT, account);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            account = (DataServices.Account)getArguments().getSerializable(ARG_ACCOUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Update Account");

        TextView email = view.findViewById(R.id.emailId);
        email.setText(account.getEmail());

        EditText name = view.findViewById(R.id.editTextEditName);
        name.setText(account.getName());

        EditText passWord = view.findViewById(R.id.editTextUpdatePassword);
        passWord.setText(account.getPassword());

        view.findViewById(R.id.btnUpdateSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataServices.AccountRequestTask accountRequestTask = DataServices.update(account, name.getText().toString(),
                        passWord.getText().toString());
                if(!accountRequestTask.isSuccessful()){
                    Toast.makeText(getActivity(), accountRequestTask.getErrorMessage(),Toast.LENGTH_SHORT).show();
                }else{
                    mInterface.goToAccount(accountRequestTask.getAccount());
            }
        }
        });

        view.findViewById(R.id.textViewCancelUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.goToBackFragment();
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof MInterface ){
            mInterface = (MInterface)context;
        }
    }

    MInterface mInterface;
    public interface MInterface{
      void  goToAccount(DataServices.Account account);
      void  goToBackFragment();
    }
}


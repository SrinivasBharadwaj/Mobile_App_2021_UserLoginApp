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
import android.widget.TextView;


public class AccountFragment extends Fragment {



    private static final String ACCOUNT_TEMP = "Account";
    private DataServices.Account account;


    public static AccountFragment  newInstance( DataServices.Account account) {

        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putSerializable(ACCOUNT_TEMP, account);
        fragment.setArguments(args);
        return fragment;
    }

    public AccountFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            account = (DataServices.Account)getArguments().getSerializable(ACCOUNT_TEMP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       return inflater.inflate(R.layout.fragment_account, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Account");
        TextView displayName = view.findViewById(R.id.textViewNameDisplay);
        displayName.setText(account.getName());

        view.findViewById(R.id.btnEditProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountInterface.goToEdit(account);
            }
        });

        view.findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountInterface.goToMain();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AccountInterface){
            accountInterface = (AccountInterface)context;
        }

    }

    AccountInterface accountInterface;
    public interface AccountInterface{
        void goToEdit(DataServices.Account account);
        void goToMain();
    }

}
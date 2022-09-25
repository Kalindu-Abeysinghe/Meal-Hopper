package com.example.foodapp.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.Common.CheckLoginVM;
import com.example.foodapp.Interfaces.OnActionBarListener;
import com.example.foodapp.MainActivity;
import com.example.foodapp.Models.User;
import com.example.foodapp.R;

public class UserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CheckLoginVM checkLoginVM;
    private User loggedInUser;
    private TextView namesView, emailView, addressView, telephoneView;
    private Button logoutButton;
    private OnActionBarListener onActionBarListener;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity)
            onActionBarListener=(OnActionBarListener)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user, container, false);

        namesView=view.findViewById(R.id.name_textView);
        emailView=view.findViewById(R.id.email_field);
        addressView=view.findViewById(R.id.address_field);
        telephoneView=view.findViewById(R.id.telephone_field);
        logoutButton=view.findViewById(R.id.logout_button);

        //Getting the logged in user from the ViewModel
        checkLoginVM=new ViewModelProvider(getActivity(), (ViewModelProvider.Factory)new ViewModelProvider.NewInstanceFactory()).get(CheckLoginVM.class);

        if(checkLoginVM.getIsLoggedIn().getValue()){
            loggedInUser=checkLoginVM.getLoggedInUser().getValue();

            namesView.setText(loggedInUser.getFirstName()+" "+loggedInUser.getLastName());
            emailView.setText(loggedInUser.getEmail());
            Log.i("email", loggedInUser.getEmail());
            addressView.setText(loggedInUser.getAddress());
            telephoneView.setText(String.valueOf(loggedInUser.getTeleNumber()));
        }

        if(onActionBarListener!=null){
            onActionBarListener.onEnableFloatingCartButton(false);
            onActionBarListener.onSetActionBarText("My Profile");
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onActionBarListener.onPopBackStack();
                checkLoginVM.setIsLoggedIn(false);
                checkLoginVM.setLoggedInUser(null);
            }
        });
        return view;
    }
}
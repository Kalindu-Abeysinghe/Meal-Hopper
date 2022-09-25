package com.example.foodapp.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.Common.CheckLoginVM;
import com.example.foodapp.Database.FoodAppDBModel;
import com.example.foodapp.Interfaces.OnActionBarListener;
import com.example.foodapp.MainActivity;
import com.example.foodapp.Models.User;
import com.example.foodapp.R;

public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button loginButton, signUpButton;
    private EditText emailField, passwordField;
    private OnActionBarListener onActionBarListener;
    private FoodAppDBModel foodAppDBModel;
    private CheckLoginVM checkLoginVM;

    public LoginFragment() {
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
            onActionBarListener=(OnActionBarListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        foodAppDBModel=new FoodAppDBModel();
        foodAppDBModel.load(getContext());

        checkLoginVM=new ViewModelProvider(getActivity(), (ViewModelProvider.Factory)new ViewModelProvider.NewInstanceFactory()).get(CheckLoginVM.class);
        loginButton=view.findViewById(R.id.login_button);
        signUpButton=view.findViewById(R.id.sign_up_button);
        emailField=view.findViewById(R.id.email_login);
        passwordField=view.findViewById(R.id.password_login);

        onActionBarListener.onSetActionBarText("User Log In");
        onActionBarListener.onAddNavigationIcon();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailField.getText().toString();
                String password=passwordField.getText().toString();

                if(foodAppDBModel.isUserValid(email,password)){
                    onActionBarListener.onPopBackStack();
                    onActionBarListener.onPopBackStack();
                    onActionBarListener.onReplaceFragment(new UserFragment());

                    checkLoginVM.setLoggedInUser(foodAppDBModel.getUserByEmail(email));
                    checkLoginVM.setIsLoggedIn(true);
                }
                else{
                    Toast toast=Toast.makeText(getContext(),"Invalid User", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onActionBarListener.onReplaceFragment(new SignupFragment());

            }
        });
        return view;
    }
}
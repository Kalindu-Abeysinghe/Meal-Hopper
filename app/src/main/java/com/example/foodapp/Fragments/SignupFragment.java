package com.example.foodapp.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.Database.FoodAppDBModel;
import com.example.foodapp.Interfaces.OnActionBarListener;
import com.example.foodapp.MainActivity;
import com.example.foodapp.Models.User;
import com.example.foodapp.R;


public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnActionBarListener onActionBarListener;
    private EditText emailField, passwordField, firstNameField, lastNameField, addressField, telephoneField;
    private Button registerButton;

    public SignupFragment() {
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
        View view= inflater.inflate(R.layout.fragment_signup, container, false);
        registerButton =view.findViewById(R.id.register_button);
        emailField=view.findViewById(R.id.email_signup);
        passwordField=view.findViewById(R.id.password_signup);
        firstNameField=view.findViewById(R.id.first_name);
        lastNameField=view.findViewById(R.id.last_name);
        addressField=view.findViewById(R.id.address_signup);
        telephoneField=view.findViewById(R.id.telephone_signup);


        if(onActionBarListener!=null){
            onActionBarListener.onSetActionBarText("User Registration");
            onActionBarListener.onAddBackButton();
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              addNewUser();
            }
        });
        return view;
    }

    public void addNewUser(){
        FoodAppDBModel foodAppDBModel=new FoodAppDBModel();
        foodAppDBModel.load(getContext());

        try {
            String email=emailField.getText().toString();
            String password=passwordField.getText().toString();
            String address=addressField.getText().toString();
            String firstName=firstNameField.getText().toString();
            String lastName=lastNameField.getText().toString();
            int telephone =Integer.parseInt(telephoneField.getText().toString());

            foodAppDBModel.addUser(new User(
                    email,
                    firstName,
                    lastName,
                    password,
                    address,
                    telephone
            ));

            onActionBarListener.onReplaceFragment(new LoginFragment());
            Toast toast=Toast.makeText(getContext(), "Success! You can now Log in.", Toast.LENGTH_LONG);
            toast.show();

        }
        catch (Exception e){
            Toast toast=Toast.makeText(getContext(), "Unsuccessful Registration"+e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
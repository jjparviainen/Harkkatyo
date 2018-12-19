package com.example.ett15084.harkkatyo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MyInfoFragment extends Fragment {

    String name = "";
    String email = "";
    String phoneNumber = "";
    NodeList nList;

    EditText defaultName;
    EditText defaultEmail;
    EditText defaultPhoneNumber;
    Button saveMyInfo;
    Button checkBookings;
    MyInfoHandler myInfoHandler = MyInfoHandler.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.myinfo_fragment, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        defaultName = getView().findViewById(R.id.defaultName);
        defaultEmail = getView().findViewById(R.id.defaultEmail);
        defaultPhoneNumber = getView().findViewById(R.id.defaultPhoneNumber);
        saveMyInfo = getView().findViewById(R.id.saveButton);
        checkBookings = getView().findViewById(R.id.checkBookings);

        getDefaultName();
        getEmail();
        getPhoneNumber();

        defaultName = getView().findViewById(R.id.defaultName);
        if(myInfoHandler.getDefaultName() != null){
            defaultName.setText(myInfoHandler.getDefaultName());
        }
        else {
        }

        defaultEmail = getView().findViewById(R.id.defaultEmail);
        if(myInfoHandler.getDefaultEmail() != null){
            defaultEmail.setText(myInfoHandler.getDefaultEmail());
        }
        else {
        }

        defaultPhoneNumber = getView().findViewById(R.id.defaultPhoneNumber);
        if(myInfoHandler.getDefaultPhoneNumber() != null){
            defaultPhoneNumber.setText(myInfoHandler.getDefaultPhoneNumber());
        }
        else {
        }

        saveMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInfoHandler.setMyInfoHandler(name, email, phoneNumber);
                System.out.println(name + email + phoneNumber);
                replaceFragment(new FloorballFragment());
            }
        });

        checkBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CheckBookings());
            }
        });
    }

    public void getDefaultName() {
        defaultName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getEmail() {
        defaultEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getPhoneNumber() {
        defaultPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneNumber = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void replaceFragment(Fragment selectedFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, selectedFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}


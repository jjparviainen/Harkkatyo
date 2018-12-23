package com.example.ett15084.harkkatyo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import static java.lang.Math.toIntExact;

public class BadmintonFragment extends Fragment implements View.OnClickListener {

    Button button800;
    Button button900;
    Button button1000;
    Button button1100;
    Button button1200;
    Button button1300;

    Spinner spinner;
    Badminton badminton = Badminton.getInstance();
    DateHandler dateHandler = DateHandler.getInstance();
    String sport;
    int laskuri = 0;
    int datePicked;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.badminton_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        button800 = getView().findViewById(R.id.button800);
        button900 = getView().findViewById(R.id.button900);
        button1000 = getView().findViewById(R.id.button1000);
        button1100 = getView().findViewById(R.id.button1100);
        button1200 = getView().findViewById(R.id.button1200);
        button1300 = getView().findViewById(R.id.button1300);

        badminton.addButtonColor();

        button800.setOnClickListener(this);
        button900.setOnClickListener(this);
        button1000.setOnClickListener(this);
        button1100.setOnClickListener(this);
        button1200.setOnClickListener(this);
        button1300.setOnClickListener(this);
        sport = "Badminton";
        setSpinner();
    }

    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.button800:
                String time800 = "8:00"; // This is sent to BookingFragment
                fragment = new BookingFragment();

                // Info of the button pressed sent to BookingsFragment
                Bundle bundle = new Bundle();
                bundle.putString("button", time800);
                bundle.putInt("selectedDate", datePicked);
                bundle.putString("sport", sport);
                fragment.setArguments(bundle);

                replaceFragment(fragment);
                break;

            case R.id.button900:
                String time900 = "9:00";
                fragment = new BookingFragment();

                bundle = new Bundle();
                bundle.putString("button", time900);
                bundle.putInt("selectedDate", datePicked);
                bundle.putString("sport", sport);
                fragment.setArguments(bundle);

                replaceFragment(fragment);
                break;

            case R.id.button1000:
                String time1000 = "10:00";

                fragment = new BookingFragment();

                bundle = new Bundle();
                bundle.putString("button", time1000);
                bundle.putInt("selectedDate", datePicked);
                bundle.putString("sport", sport);
                fragment.setArguments(bundle);

                replaceFragment(fragment);
                break;

            case R.id.button1100:
                String time1100 = "11:00";
                fragment = new BookingFragment();

                bundle = new Bundle();
                bundle.putString("button", time1100);
                bundle.putInt("selectedDate", datePicked);
                bundle.putString("sport", sport);
                fragment.setArguments(bundle);

                replaceFragment(fragment);
                break;

            case R.id.button1200:
                String time1200 = "12:00";

                fragment = new BookingFragment();

                bundle = new Bundle();
                bundle.putString("button", time1200);
                bundle.putInt("selectedDate", datePicked);
                bundle.putString("sport", sport);
                fragment.setArguments(bundle);

                replaceFragment(fragment);
                break;

            case R.id.button1300:
                String time1300 = "13:00";
                fragment = new BookingFragment();

                bundle = new Bundle();
                bundle.putString("button", time1300);
                bundle.putInt("selectedDate", datePicked);
                bundle.putString("sport", sport);
                fragment.setArguments(bundle);

                replaceFragment(fragment);
                break;
        }
    }

    public void setColor(Button button, int selectedDate){
        System.out.println("Selected date pitäisi olla 1 ja sehän on: " + selectedDate);
        System.out.println("Laskurina arvo on: " + laskuri);

        if(badminton.getButtonColor(selectedDate).get(laskuri) == "Green"){
            button.setBackgroundColor(Color.GREEN);
            laskuri++;
            System.out.println("Asetetaan väri vihreäksi " + laskuri);
        }
        else{
            button.setBackgroundColor(Color.RED);
            laskuri++;
            System.out.println("Asetetaan väri punaiseksi " + laskuri);
        }
    }

    public void setSpinner(){

        spinner = getView().findViewById(R.id.dateSpinner);
        //ArrayAdapter<String> adp = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_item, dateHandler.setDatesArray());
        ArrayAdapter adp = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, dateHandler.setDatesArray());
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);

        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                datePicked = toIntExact(id);
                System.out.println("Päivämäärän id valittuna: " + datePicked);

                setColor(button800, datePicked);
                setColor(button900, datePicked);
                setColor(button1000, datePicked);
                setColor(button1100, datePicked);
                setColor(button1200, datePicked);
                setColor(button1300, datePicked);
                laskuri = 0;

            } // to close the onItemSelected

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

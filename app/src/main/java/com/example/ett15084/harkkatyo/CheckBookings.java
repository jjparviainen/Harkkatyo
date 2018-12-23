package com.example.ett15084.harkkatyo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CheckBookings extends Fragment {

    Floorball floorball;
    Squash squash;
    Badminton badminton;

    TextView listBookings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.check_bookings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        floorball = Floorball.getInstance();
        squash = Squash.getInstance();
        badminton = Badminton.getInstance();

        listBookings = getView().findViewById(R.id.listBookings);

        listBookingsToTextViewFloorball();
        listBookingsToTextViewSquash();
        listBookingsToTextViewBadminton();
    }


    // The following three methods are used to write the bookings from all sports to the textview in this fragment
    public void listBookingsToTextViewFloorball(){
        for(int i=0; i<floorball.bookings.size();i++ ){
            listBookings.append(floorball.bookings.get(i).getDate()+ "\n");
            listBookings.append(floorball.bookings.get(i).getTimeBooked() + "  " + floorball.bookings.get(i).getSport() + "\n");
            listBookings.append(floorball.bookings.get(i).getName()+ "\n");
            listBookings.append(floorball.bookings.get(i).getEmail()+ "\n");
            listBookings.append(floorball.bookings.get(i).getPhoneNumber()+ "\n");
            listBookings.append("\n");
        }
    }
    public void listBookingsToTextViewSquash(){
        for(int i=0; i<squash.bookings.size();i++ ){
            listBookings.append(squash.bookings.get(i).getDate()+ "\n");
            listBookings.append(squash.bookings.get(i).getTimeBooked() + "  " + squash.bookings.get(i).getSport() + "\n");
            listBookings.append(squash.bookings.get(i).getName()+ "\n");
            listBookings.append(squash.bookings.get(i).getEmail()+ "\n");
            listBookings.append(squash.bookings.get(i).getPhoneNumber()+ "\n");
            listBookings.append("\n");
        }
    }

    public void listBookingsToTextViewBadminton(){
        for(int i=0; i<badminton.bookings.size();i++ ){
            listBookings.append(badminton.bookings.get(i).getDate()+ "\n");
            listBookings.append(badminton.bookings.get(i).getTimeBooked() + "  " + badminton.bookings.get(i).getSport() + "\n");
            listBookings.append(badminton.bookings.get(i).getName()+ "\n");
            listBookings.append(badminton.bookings.get(i).getEmail()+ "\n");
            listBookings.append(badminton.bookings.get(i).getPhoneNumber()+ "\n");
            listBookings.append("\n");
        }
    }
}

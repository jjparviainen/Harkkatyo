package com.example.ett15084.harkkatyo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BookingFragment extends Fragment {

    TextView bookingTime;

    EditText name;
    EditText email;
    EditText phoneNumber;

    String nameWritten;
    String emailWritten;
    String numberWritten;
    String sport;
    String selectedTimeButton;
    String selectedDateString;

    Button bookButton;
    Button removeBookingButton;

    Floorball floorball = Floorball.getInstance();
    Squash squash = Squash.getInstance();
    Badminton badminton = Badminton.getInstance();

    DateHandler dateHandler = DateHandler.getInstance();
    MyInfoHandler myInfoHandler = MyInfoHandler.getInstance();
    int selectedDateInSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.booking_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        removeBookingButton = getView().findViewById(R.id.removeBookingButton);

        // Info on the button pressed and date is received fomr the sport fragments
        selectedTimeButton = getArguments().getString("button");
        selectedDateInSpinner = getArguments().getInt("selectedDate");
        sport = getArguments().getString("sport");
        System.out.println("############################################################################################# Laji on: " + sport);
        selectedDateString = dateHandler.dates.get(selectedDateInSpinner).toString();
        System.out.println("#################################################### Button pressed: " + selectedTimeButton);
        System.out.println("#################################################### Päivä valittu " + selectedDateInSpinner);

        bookingTime = getView().findViewById(R.id.time);
        bookButton = getView().findViewById(R.id.saveButton);

        bookingTime.setText("Booking for time slot: " + dateHandler.dates.get(selectedDateInSpinner).toString() + " at " + selectedTimeButton);

        name = getView().findViewById(R.id.name);
        if(myInfoHandler.getDefaultName() != null){
            name.setText(myInfoHandler.getDefaultName());
            nameWritten = myInfoHandler.getDefaultName();
        }

        email = getView().findViewById(R.id.email);
        if(myInfoHandler.getDefaultEmail() != null){
            email.setText(myInfoHandler.getDefaultEmail());
            emailWritten = myInfoHandler.getDefaultEmail();
        }

        phoneNumber = getView().findViewById(R.id.phone);
        if(myInfoHandler.getDefaultPhoneNumber() != null){
            phoneNumber.setText(myInfoHandler.getDefaultPhoneNumber());
            numberWritten = myInfoHandler.getDefaultPhoneNumber();
        }

        if(sport.equals("Floorball")){
            setRemoveButtonEnabledFloorball();
        }
        else if(sport.equals("Squash")){
            setRemoveButtonEnabledSquash();
        }
        else if(sport.equals("Badminton")){
            setRemoveButtonEnabledBadminton();//
        }

        getEmail();
        getName();
        getPhoneNumber();


        // Activates when "BOOK!" button is pressed.
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bookings bookingObject = new Bookings(nameWritten, emailWritten, numberWritten, selectedTimeButton, selectedDateString, sport);
                if(sport.equals("Floorball")){
                    floorball.addToList(bookingObject);
                    floorball.writeXML3(nameWritten, emailWritten, numberWritten, selectedTimeButton, selectedDateString);
                    changeColorFloorball("Red");
                    replaceFragment(new FloorballFragment());
                }
                else if(sport.equals("Badminton")){ //
                    badminton.addToList(bookingObject);
                    badminton.writeXML3(nameWritten, emailWritten, numberWritten, selectedTimeButton, selectedDateString);
                    changeColorBadminton("Red");
                    replaceFragment(new BadmintonFragment());
                }
                else if(sport.equals("Squash")){
                    squash.addToList(bookingObject);
                    squash.writeXML3(nameWritten, emailWritten, numberWritten, selectedTimeButton, selectedDateString);
                    changeColorSquash("Red");
                    replaceFragment(new SquashFragment());
                }


            }
        });

        // Activates when "REMOVE BOOKING" button is pressed
        removeBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sport.equals("Floorball")){
                    changeColorFloorball("Green");
                    replaceFragment(new FloorballFragment());
                    for(int i=0; i<floorball.bookings.size();i++){
                        if(floorball.bookings.get(i).getDate().equals(selectedDateString) && floorball.bookings.get(i).getTimeBooked().equals(selectedTimeButton)){
                            floorball.bookings.remove(i);
                        }
                    }
                }
                else if(sport.equals("Badminton")){ //TODO Lajin vaihto
                    changeColorBadminton("Green"); // TODO tästä kanssa
                    replaceFragment(new BadmintonFragment());
                    for(int i=0; i<badminton.bookings.size();i++){
                        if(badminton.bookings.get(i).getDate().equals(selectedDateString) && badminton.bookings.get(i).getTimeBooked().equals(selectedTimeButton)){
                            badminton.bookings.remove(i);
                        }
                    }
                }
                else if (sport.equals("Squash")){
                    changeColorSquash("Green");
                    replaceFragment(new SquashFragment());
                    for(int i=0; i<squash.bookings.size();i++){
                        if(squash.bookings.get(i).getDate().equals(selectedDateString) && squash.bookings.get(i).getTimeBooked().equals(selectedTimeButton)){
                            squash.bookings.remove(i);
                        }
                    }
                }
            }
        });
    }

    // Checks whether a certain time is booked or not and based on that info disables/enables the remove booking button
    public void setRemoveButtonEnabledFloorball(){
        removeBookingButton.setEnabled(false);
        if(selectedDateInSpinner == 0){
            if(selectedTimeButton.equals("8:00") && floorball.ButtonsDay0.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);

            }
            else if(selectedTimeButton.equals("9:00")&& floorball.ButtonsDay0.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00") && floorball.ButtonsDay0.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);

            }
            else if(selectedTimeButton.equals("11:00")&& floorball.ButtonsDay0.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00") && floorball.ButtonsDay0.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);

            }
            else if(selectedTimeButton.equals("13:00")&& floorball.ButtonsDay0.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 1){
            if(selectedTimeButton.equals("8:00")&& floorball.ButtonsDay1.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& floorball.ButtonsDay1.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& floorball.ButtonsDay1.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& floorball.ButtonsDay1.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& floorball.ButtonsDay1.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& floorball.ButtonsDay1.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 2){
            if(selectedTimeButton.equals("8:00")&& floorball.ButtonsDay2.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& floorball.ButtonsDay2.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& floorball.ButtonsDay2.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& floorball.ButtonsDay2.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& floorball.ButtonsDay2.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& floorball.ButtonsDay2.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 3){
            if(selectedTimeButton.equals("8:00")&& floorball.ButtonsDay3.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& floorball.ButtonsDay3.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& floorball.ButtonsDay3.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& floorball.ButtonsDay3.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& floorball.ButtonsDay3.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& floorball.ButtonsDay3.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 4){
            if(selectedTimeButton.equals("8:00")&& floorball.ButtonsDay4.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& floorball.ButtonsDay4.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& floorball.ButtonsDay4.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& floorball.ButtonsDay4.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& floorball.ButtonsDay4.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& floorball.ButtonsDay4.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }

    }
    public void setRemoveButtonEnabledBadminton(){
        removeBookingButton.setEnabled(false);
        if(selectedDateInSpinner == 0){
            if(selectedTimeButton.equals("8:00") && badminton.ButtonsDay0.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);

            }
            else if(selectedTimeButton.equals("9:00")&& badminton.ButtonsDay0.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00") && badminton.ButtonsDay0.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);

            }
            else if(selectedTimeButton.equals("11:00")&& badminton.ButtonsDay0.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00") && badminton.ButtonsDay0.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);

            }
            else if(selectedTimeButton.equals("13:00")&& badminton.ButtonsDay0.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 1){
            if(selectedTimeButton.equals("8:00")&& badminton.ButtonsDay1.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& badminton.ButtonsDay1.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& badminton.ButtonsDay1.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& badminton.ButtonsDay1.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& badminton.ButtonsDay1.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& badminton.ButtonsDay1.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 2){
            if(selectedTimeButton.equals("8:00")&& badminton.ButtonsDay2.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& badminton.ButtonsDay2.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& badminton.ButtonsDay2.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& badminton.ButtonsDay2.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& badminton.ButtonsDay2.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& badminton.ButtonsDay2.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 3){
            if(selectedTimeButton.equals("8:00")&& badminton.ButtonsDay3.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& badminton.ButtonsDay3.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& badminton.ButtonsDay3.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& badminton.ButtonsDay3.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& badminton.ButtonsDay3.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& badminton.ButtonsDay3.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 4){
            if(selectedTimeButton.equals("8:00")&& badminton.ButtonsDay4.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& badminton.ButtonsDay4.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& badminton.ButtonsDay4.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& badminton.ButtonsDay4.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& badminton.ButtonsDay4.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& badminton.ButtonsDay4.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }

    }

    public void setRemoveButtonEnabledSquash(){
        removeBookingButton.setEnabled(false);
        if(selectedDateInSpinner == 0){
            if(selectedTimeButton.equals("8:00") && squash.ButtonsDay0.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);

            }
            else if(selectedTimeButton.equals("9:00")&& squash.ButtonsDay0.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00") && squash.ButtonsDay0.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);

            }
            else if(selectedTimeButton.equals("11:00")&& squash.ButtonsDay0.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00") && squash.ButtonsDay0.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);

            }
            else if(selectedTimeButton.equals("13:00")&& squash.ButtonsDay0.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 1){
            if(selectedTimeButton.equals("8:00")&& squash.ButtonsDay1.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& squash.ButtonsDay1.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& squash.ButtonsDay1.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& squash.ButtonsDay1.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& squash.ButtonsDay1.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& squash.ButtonsDay1.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 2){
            if(selectedTimeButton.equals("8:00")&& squash.ButtonsDay2.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& squash.ButtonsDay2.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& squash.ButtonsDay2.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& squash.ButtonsDay2.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& squash.ButtonsDay2.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& squash.ButtonsDay2.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 3){
            if(selectedTimeButton.equals("8:00")&& squash.ButtonsDay3.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& squash.ButtonsDay3.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& squash.ButtonsDay3.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& squash.ButtonsDay3.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& squash.ButtonsDay3.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& squash.ButtonsDay3.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }
        else if(selectedDateInSpinner == 4){
            if(selectedTimeButton.equals("8:00")&& squash.ButtonsDay4.get(0).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("9:00")&& squash.ButtonsDay4.get(1).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("10:00")&& squash.ButtonsDay4.get(2).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("11:00")&& squash.ButtonsDay4.get(3).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("12:00")&& squash.ButtonsDay4.get(4).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
            else if(selectedTimeButton.equals("13:00")&& squash.ButtonsDay4.get(5).equals("Red")){
                removeBookingButton.setEnabled(true);
            }
        }

    }

    // Sets the colors of the buttons based on their reservations every time floorball fragment is started
    public void changeColorFloorball(String color){
        if(selectedDateInSpinner == 0){
            if(selectedTimeButton.equals("8:00")){
                floorball.ButtonsDay0.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                floorball.ButtonsDay0.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                floorball.ButtonsDay0.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                floorball.ButtonsDay0.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                floorball.ButtonsDay0.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                floorball.ButtonsDay0.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 1){
            if(selectedTimeButton.equals("8:00")){
                floorball.ButtonsDay1.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                floorball.ButtonsDay1.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                floorball.ButtonsDay1.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                floorball.ButtonsDay1.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                floorball.ButtonsDay1.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                floorball.ButtonsDay1.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 2){
            if(selectedTimeButton.equals("8:00")){
                floorball.ButtonsDay2.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                floorball.ButtonsDay2.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                floorball.ButtonsDay2.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                floorball.ButtonsDay2.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                floorball.ButtonsDay2.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                floorball.ButtonsDay2.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 3){
            if(selectedTimeButton.equals("8:00")){
                floorball.ButtonsDay3.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                floorball.ButtonsDay3.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                floorball.ButtonsDay3.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                floorball.ButtonsDay3.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                floorball.ButtonsDay3.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                floorball.ButtonsDay3.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 4){
            if(selectedTimeButton.equals("8:00")){
                floorball.ButtonsDay4.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                floorball.ButtonsDay4.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                floorball.ButtonsDay4.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                floorball.ButtonsDay4.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                floorball.ButtonsDay4.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                floorball.ButtonsDay4.set(5, color);
            }
        }
    }

    // Sets the colors of the buttons based on their reservations every time badminton fragment is started
    public void changeColorBadminton(String color){
        if(selectedDateInSpinner == 0){
            if(selectedTimeButton.equals("8:00")){
                badminton.ButtonsDay0.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                badminton.ButtonsDay0.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                badminton.ButtonsDay0.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                badminton.ButtonsDay0.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                badminton.ButtonsDay0.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                badminton.ButtonsDay0.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 1){
            if(selectedTimeButton.equals("8:00")){
                badminton.ButtonsDay1.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                badminton.ButtonsDay1.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                badminton.ButtonsDay1.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                badminton.ButtonsDay1.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                badminton.ButtonsDay1.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                badminton.ButtonsDay1.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 2){
            if(selectedTimeButton.equals("8:00")){
                badminton.ButtonsDay2.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                badminton.ButtonsDay2.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                badminton.ButtonsDay2.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                badminton.ButtonsDay2.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                badminton.ButtonsDay2.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                badminton.ButtonsDay2.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 3){
            if(selectedTimeButton.equals("8:00")){
                badminton.ButtonsDay3.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                badminton.ButtonsDay3.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                badminton.ButtonsDay3.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                badminton.ButtonsDay3.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                badminton.ButtonsDay3.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                badminton.ButtonsDay3.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 4){
            if(selectedTimeButton.equals("8:00")){
                badminton.ButtonsDay4.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                badminton.ButtonsDay4.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                badminton.ButtonsDay4.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                badminton.ButtonsDay4.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                badminton.ButtonsDay4.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                badminton.ButtonsDay4.set(5, color);
            }
        }
    }

    // Sets the colors of the buttons based on their reservations every time squash fragment is started
    public void changeColorSquash(String color){
        if(selectedDateInSpinner == 0){
            if(selectedTimeButton.equals("8:00")){
                squash.ButtonsDay0.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                squash.ButtonsDay0.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                squash.ButtonsDay0.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                squash.ButtonsDay0.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                squash.ButtonsDay0.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                squash.ButtonsDay0.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 1){
            if(selectedTimeButton.equals("8:00")){
                squash.ButtonsDay1.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                squash.ButtonsDay1.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                squash.ButtonsDay1.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                squash.ButtonsDay1.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                squash.ButtonsDay1.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                squash.ButtonsDay1.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 2){
            if(selectedTimeButton.equals("8:00")){
                squash.ButtonsDay2.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                squash.ButtonsDay2.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                squash.ButtonsDay2.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                squash.ButtonsDay2.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                squash.ButtonsDay2.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                squash.ButtonsDay2.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 3){
            if(selectedTimeButton.equals("8:00")){
                squash.ButtonsDay3.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                squash.ButtonsDay3.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                squash.ButtonsDay3.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                squash.ButtonsDay3.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                squash.ButtonsDay3.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                squash.ButtonsDay3.set(5, color);
            }
        }
        else if(selectedDateInSpinner == 4){
            if(selectedTimeButton.equals("8:00")){
                squash.ButtonsDay4.set(0, color);
            }
            else if(selectedTimeButton.equals("9:00")){
                squash.ButtonsDay4.set(1, color);
            }
            else if(selectedTimeButton.equals("10:00")){
                squash.ButtonsDay4.set(2, color);
            }
            else if(selectedTimeButton.equals("11:00")){
                squash.ButtonsDay4.set(3, color);
            }
            else if(selectedTimeButton.equals("12:00")){
                squash.ButtonsDay4.set(4, color);
            }
            else if(selectedTimeButton.equals("13:00")){
                squash.ButtonsDay4.set(5, color);
            }
        }
    }

    public void getName(){
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nameWritten = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getEmail(){
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailWritten = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getPhoneNumber(){
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                numberWritten = s.toString();
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






























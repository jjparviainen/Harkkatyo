package com.example.ett15084.harkkatyo;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class Badminton {

    private static Badminton badminton = new Badminton();

    // List for all the bookings-objects
    ArrayList<Bookings> bookings = new ArrayList();

    Context context;
    int laskuri = 0;

    // ArrayLists for the button colors
    ArrayList <String> ButtonsDay0 = new ArrayList();
    ArrayList <String> ButtonsDay1 = new ArrayList();
    ArrayList <String> ButtonsDay2 = new ArrayList();
    ArrayList <String> ButtonsDay3 = new ArrayList();
    ArrayList <String> ButtonsDay4 = new ArrayList();

    public void addToList(Bookings bookingObject){
        bookings.add(bookingObject);
        System.out.println(bookingObject.getName() + bookingObject.getEmail() + bookingObject.getPhoneNumber() + bookingObject.getDate() + bookingObject.getTimeBooked());
    }

    // Writes the info of the bookings to an XML-file
    public void writeXML3(String nameWritten, String emailWritten, String numberWritten, String selectedTime, String selectedDate){
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        StringWriter writer = new StringWriter();
        Context context = MainActivity.getInstance();

        try {

            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "bookings");

            for(int i=0; i<bookings.size();i++){
                serializer.startTag("", "date");
                serializer.text(bookings.get(i).getDate());

                serializer.startTag("", "time");
                serializer.text(bookings.get(i).getTimeBooked());

                serializer.startTag("", "sport");
                serializer.text(bookings.get(i).getSport());
                serializer.endTag("", "sport");

                serializer.startTag("", "name");
                serializer.text(bookings.get(i).getName());
                serializer.endTag("", "name");


                serializer.startTag("","email");
                serializer.text(bookings.get(i).getEmail());
                serializer.endTag("", "email");

                serializer.startTag("","phonenumber");
                serializer.text(bookings.get(i).getPhoneNumber());
                serializer.endTag("", "phonenumber");
                serializer.endTag("", "time");
                serializer.endTag("", "date");
            }

            serializer.endTag("", "bookings");
            serializer.endDocument();
            String results = writer.toString();
            writeToFile(context, "bookings.xml", results);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(Context context, String fileName, String str){
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(str.getBytes(), 0, str.length());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // ArrayLists keeping track of the color of the button for all the days. Initiated green as initially all times are available
    public void addButtonColor(){
        ButtonsDay0.add("Green");
        ButtonsDay0.add("Green");
        ButtonsDay0.add("Green");
        ButtonsDay0.add("Green");
        ButtonsDay0.add("Green");
        ButtonsDay0.add("Green");


        ButtonsDay1.add("Green");
        ButtonsDay1.add("Green");
        ButtonsDay1.add("Green");
        ButtonsDay1.add("Green");
        ButtonsDay1.add("Green");
        ButtonsDay1.add("Green");

        ButtonsDay2.add("Green");
        ButtonsDay2.add("Green");
        ButtonsDay2.add("Green");
        ButtonsDay2.add("Green");
        ButtonsDay2.add("Green");
        ButtonsDay2.add("Green");

        ButtonsDay3.add("Green");
        ButtonsDay3.add("Green");
        ButtonsDay3.add("Green");
        ButtonsDay3.add("Green");
        ButtonsDay3.add("Green");
        ButtonsDay3.add("Green");

        ButtonsDay4.add("Green");
        ButtonsDay4.add("Green");
        ButtonsDay4.add("Green");
        ButtonsDay4.add("Green");
        ButtonsDay4.add("Green");
        ButtonsDay4.add("Green");
    }

    // Method recieves the id of the chosen date and based on that return the ArrayList of the correct day
    public ArrayList getButtonColor(int selectedItemInSpinner){
        if (selectedItemInSpinner == 0){
            return ButtonsDay0;
        }
        else if(selectedItemInSpinner ==1){
            return ButtonsDay1;
        }
        else if(selectedItemInSpinner ==2){
            return ButtonsDay2;
        }
        else if(selectedItemInSpinner ==3){
            return ButtonsDay3;
        }
        else if(selectedItemInSpinner ==4){
            return ButtonsDay4;
        }
        else {
            return ButtonsDay0;
        }
    }

    public static Badminton getInstance(){
        return badminton;
    }
}

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

    // Tämä lista pitää kirjaa kaikista varauksista squash-saliin
    // Näitäkin tarvitaan luultavasti oma lista jokaiselle päivälle

    ArrayList<Bookings> bookings = new ArrayList();

    Context context;
    int laskuri = 0;

    // Tämä lista pitää kirjaa nappuloiden väristä, eli onko varattu
    // Näitäkin yksi lista per päivä
    ArrayList <String> ButtonsDay0 = new ArrayList();
    ArrayList <String> ButtonsDay1 = new ArrayList();
    ArrayList <String> ButtonsDay2 = new ArrayList();
    ArrayList <String> ButtonsDay3 = new ArrayList();
    ArrayList <String> ButtonsDay4 = new ArrayList();

    public void addToList(Bookings bookingObject){
        bookings.add(bookingObject);
        System.out.println(bookingObject.getName() + bookingObject.getEmail() + bookingObject.getPhoneNumber() + bookingObject.getDate() + bookingObject.getTimeBooked());
    }

    // Nyt on ihan android write to xml ohjeet käytössä
    public void writeXML3(String nameWritten, String emailWritten, String numberWritten, String selectedTime, String selectedDate){
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        StringWriter writer = new StringWriter();
        Context context = MainActivity.getInstance();

        try {

            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "bookings");
            //serializer.attribute("", "number", "4");

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

    // Muodostetaan ArrayListit jokaisen päivän nappuloiden väreistä. Alustetaan vihreiksi koska aluksi tietenkin kaikki ajat vapaat alussa.
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

    // Tämä metodi saa valitun päivämäärän id:n floorballFragmentistä ja sen perusteella palauttaa oikean päivän ArrayListin (eli täyteen if-lauseita tämä)
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
        /*for (int i = 0; i < floorballButtonsDay0.size(); i++) {
            System.out.println(floorballButtonsDay0.get(i));
        }*/
        else {
            return ButtonsDay0;
        }
    }

    public static Badminton getInstance(){
        return badminton;
    }
}

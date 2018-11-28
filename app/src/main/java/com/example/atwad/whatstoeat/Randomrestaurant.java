package com.example.atwad.whatstoeat;

import android.nfc.Tag;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Randomrestaurant extends Activity {

   //Random number generator

    //array list to contain previous random selected Restaurants
    ArrayList<Integer> choices = new ArrayList<Integer>();

// array to contain all resources to set two text views and image view
int [][] restaurantInfo ={{R.string.villageGrill,R.string.villageGrillAddress,R.drawable.villagegrill},
        {R.string.grillWorx,R.string.grillWorxAddress,R.drawable.grillworxpic},{R.string.daVinci,R.string.daVinciAddress,R.drawable.davincistable}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_randomrestaurant);

        int randint =randomNum(restaurantInfo.length);
        // Create Text views and image views  based on id
        TextView restaurantName = findViewById(R.id.randomName);
        TextView restaurantLocation = findViewById(R.id.randomAddress);
        ImageView restaurantPicture =findViewById(R.id.randomPicture);
        //Rows contain information about the same restaurant, so switching rows switches restaurants
        restaurantName.setText(restaurantInfo[randint][0]);
        restaurantLocation.setText(restaurantInfo[randint][1]);
        restaurantPicture.setImageResource(restaurantInfo[randint][2]);
        choices.add(randint);

    }
    //onclick on button caused new random numbers to generate and set activity only if not used before if used before set to ran out page
    public void nextRandom(View view)
    {

        TextView restaurantName = findViewById(R.id.randomName);
        TextView restaurantLocation = findViewById(R.id.randomAddress);
        ImageView restaurantPicture =findViewById(R.id.randomPicture);
        Button buttonTry = findViewById(R.id.buttonTryAgain);
        int randint =randomNum(restaurantInfo.length);
        // Makes sure that the choice is not already in the array list choices, also makes sure that array list does not get bigger than array
        while(!checkArrayList(choices,randint) && choices.size()<=restaurantInfo.length)
        {
            randint =randomNum(restaurantInfo.length);
        }
        // display a random page with information from restaurant table
       if(checkArrayList(choices,randint)) {

           restaurantName.setText(restaurantInfo[randint][0]);
           restaurantLocation.setText(restaurantInfo[randint][1]);
           restaurantPicture.setImageResource(restaurantInfo[randint][2]);
           choices.add(randint);
       }

       // Once the app is out of restaurants in the array restaurantsinfo it displays a default page
       else
       {

           restaurantName.setText(R.string.outOfPlaces);
           restaurantLocation.setPadding(20,20,20,20);
           restaurantLocation.setText(R.string.outOfPlaces2);
           restaurantPicture.setImageResource(R.drawable.x_sign);
           buttonTry.setVisibility(View.GONE);
       }
        choices.add(randint);
    }

     //Checks array list for choice appearing already. this prevents pages being repeated.
    public boolean checkArrayList(ArrayList<Integer> pastChoices, int num)
    {
        boolean check=true;
        for(int i =0; i<pastChoices.size();i++)
        {
            if (pastChoices.get(i)==num)
            {
               check=false;
            }

        }
        return check;
    }

    // Method returns random number between zero and parameter
    public int randomNum(int arraySize)
    {
        Random r = new Random();
        return  Math.abs(r.nextInt()) % arraySize;

    }

}

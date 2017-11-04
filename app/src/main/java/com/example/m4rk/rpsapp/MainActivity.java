package com.example.m4rk.rpsapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int STONE = 0;
    private static final int PAPER = 1;
    private static final int SCISSORS = 2;

    private int userChoice = STONE;
    private int cpuChoice;

    private ImageView img;

    private void changeChoice()
    {
        if(userChoice == STONE)
        {
            userChoice = PAPER;
        } else if(userChoice == PAPER) {
            userChoice = SCISSORS;
        } else {
            userChoice = STONE;
        }

        changeImage();

    }

    private void changeImage() {
        switch (userChoice)
        {
            case STONE:
                img.setImageDrawable(getResources().getDrawable(R.drawable.stone));
                break;
            case PAPER:
                img.setImageDrawable(getResources().getDrawable(R.drawable.paper));
                break;
            case SCISSORS:
                img.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
                break;
        }

    }

    private void writeOutCPUChoice()
    {
        TextView cpuChoiceBox = (TextView) findViewById(R.id.cpuChoice);

        if(cpuChoice == STONE)
        {
            cpuChoiceBox.setText(R.string.cpu_choice_stone);
        } else if(cpuChoice == PAPER) {
            cpuChoiceBox.setText(R.string.cpu_choice_paper);
        } else {
            cpuChoiceBox.setText(R.string.cpu_choice_scissors);
        }
    }

    private boolean isUserWin()
    {
        Random rm = new Random();
        boolean isUserWin = false;

        do{
            cpuChoice = rm.nextInt(3);
        } while (cpuChoice == userChoice);

        /*
        * Átláthatóság miatt bontottam az elágazáson belül elágazást
        */
        if(userChoice == STONE)
        {
            if(cpuChoice == SCISSORS)
            {
                isUserWin = true;
            }
        } else if(userChoice == PAPER)
        {

            if(cpuChoice == STONE)
            {
                isUserWin = true;
            }
        } else {

            if(cpuChoice == PAPER)
            {
                isUserWin = true;
            }
        }

        return isUserWin;

    }

    private void toastAlert(String message)
    {
        Context context = getApplicationContext();
        int length = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, length);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.userChoice);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeChoice();
            }
        });

        Button play = (Button) findViewById(R.id.playButton);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isUserWin()) {
                    toastAlert("Congrats, you won!");
                } else {
                    toastAlert("You lost! Try again! :/ ");
                }
                writeOutCPUChoice();
            }
        });

    }

}

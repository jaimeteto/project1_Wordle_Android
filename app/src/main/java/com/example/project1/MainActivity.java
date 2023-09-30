package com.example.project1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String word = "apple";

    public static int rightLetters;
    private static String currentWord ;
    private Button aButton2;
    public GridView inputGrid;
    public GridView buttonGrid;
    private static Context context;
    char inputs[]={'1','2','3','4','5','1','2','3','4','5','1','2','3','4','5','1','2','3','4','5','1','2','3','4','5','1','2','3','4','5'};
    char[] letters={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    EditText focusedEditText;

    public int currentRow =0;
    public static void stringConcat(String newString){
        String currentWord1 = MainActivity.currentWord;

        //Toast.makeText(MainActivity.context, "checking new word : "+ currentWord1, Toast.LENGTH_SHORT).show();
        MainActivity.currentWord= currentWord1.concat(newString);;


    }
    public void checkInputsWithWord(String chosenWord,String input,GridView buttons,GridView textEdits,int row){
            //if input is less than 5 ask user to input a 5 letter word
            if(input.length()<5){
                Toast.makeText(getApplicationContext(), "enter a 5 letter word : "+ input, Toast.LENGTH_SHORT).show();
                return;

            }

            //check the input letter with the chosen word (each character)

            for(int i=0;i<chosenWord.length();i++){

                EditText current = ((LinearLayout) textEdits.getChildAt(row+i)).findViewById(R.id.input);

                if(Character.compare(chosenWord.charAt(i),input.charAt(i))==0){
                    //change color
                    current.setTextColor(Color.GREEN);
                    rightLetters ++;

                } else if (chosenWord.indexOf(input.charAt(i))>0) {//if input char is in the chosen word
                    current.setTextColor(Color.YELLOW);

                } else{
                    current.setTextColor(Color.RED);

                }
                if(rightLetters==5){
                    alertView("You have won");
                }

            }

    }
    private void alertView(String message ) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle( message )
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("do you want to restart the game?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                        finish();
                    }})
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
//                        restart activity
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }).show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //aButton2 = findViewById(R.id.aButton);
        //aButton2.setOnClickListener(this);
        currentWord ="";
        rightLetters=0;
        MainActivity.context=getApplicationContext();
        inputGrid = (GridView) findViewById(R.id.grid1); // init GridView
        buttonGrid = (GridView) findViewById(R.id.grid2); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), inputs, 1,inputGrid);
        CustomAdapter customAdapter2 = new CustomAdapter(getApplicationContext(), letters, 2,inputGrid);
        inputGrid.setAdapter(customAdapter);
        buttonGrid.setAdapter(customAdapter2);
        //create a onclick listener for enter button
        Button enterButton = (Button) findViewById(R.id.enter);
        enterButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //when clicked check if input word is 5 characters

                // then check if if eac editText is in the same place as the selected word(from java class)
                //request focus to the next row
                EditText nextRowFirst = ((LinearLayout) inputGrid.getChildAt(currentRow+5)).findViewById(R.id.input);
                EditText current = ((LinearLayout) inputGrid.getChildAt(currentRow+4)).findViewById(R.id.input);
                nextRowFirst.setFocusableInTouchMode(true);
                nextRowFirst.requestFocus();
                Toast.makeText(getApplicationContext(), "checking current word : "+ currentWord, Toast.LENGTH_SHORT).show();

                checkInputsWithWord("APPLE",currentWord,buttonGrid,inputGrid,currentRow);
                currentWord="";//clearing word
                currentRow+=5;


            }

        });


    }

}
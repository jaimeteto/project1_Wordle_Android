package com.example.project1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    public static String word =Words.get_word();

    public static int rightLetters;
    private static String currentWord ;
    private Button aButton2;
    public GridView inputGrid;
    public GridView buttonGrid;
    private static Context context;
    char inputs[]={'1','2','3','4','5','1','2','3','4','5','1','2','3','4','5','1','2','3','4','5','1','2','3','4','5','1','2','3','4','5'};
    private static char[] letters={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    EditText focusedEditText;

    public int currentRow =0;

    public static int findButton( String letter){
        int index=-1;
        for (int i=0;i< letters.length;i++){
            if (letter.charAt(0)==letters[i])
               index= i;
        }
        return index;

    }
    public static void stringConcat(String newString){
        String currentWord1 = MainActivity.currentWord;

        //Toast.makeText(MainActivity.context, "checking new word : "+ currentWord1, Toast.LENGTH_SHORT).show();
        MainActivity.currentWord= currentWord1.concat(newString);;


    }
    public boolean checkInputsWithWord(String chosenWord,String input,GridView buttons,GridView textEdits,int row){
            //if input is less than 5 ask user to input a 5 letter word

            if(input.length()<5){
                Toast.makeText(getApplicationContext(), "enter a 5 letter word : "+ input, Toast.LENGTH_SHORT).show();
                return false;

            }

            //check the input letter with the chosen word (each character)

            for(int i=0;i<chosenWord.length();i++){

                EditText current = ((LinearLayout) textEdits.getChildAt(row+i)).findViewById(R.id.input);
                String letter = current.getText().toString();
                int index = findButton(letter);
                Button currentButton = ((LinearLayout) buttons.getChildAt(index)).findViewById(R.id.myButton);

                if(Character.compare(chosenWord.charAt(i),input.charAt(i))==0){

                    ColorDrawable buttonColor =(ColorDrawable)currentButton.getBackground();
                    //change color
                    int colorId = buttonColor.getColor();
                    if(colorId != Color.GREEN){
                        rightLetters ++;
                        currentButton.setBackgroundColor(Color.GREEN);
                    }
                    current.setTextColor(Color.GREEN);

                    //add only if this letter has not been picked before


                } else if (chosenWord.indexOf(input.charAt(i))>0) {//if input char is in the chosen word
                    current.setTextColor(Color.YELLOW);
                    currentButton.setBackgroundColor(Color.YELLOW);

                } else{
                    current.setTextColor(Color.RED);
                    currentButton.setBackgroundColor(Color.RED);

                }
                if(rightLetters==5){
                    alertView("You have won");
                }

            }
        return true;
    }
    private void alertView(String message ) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle( message )
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("do you want to restart the game?")
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), Menu.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT",true);
                        startActivity(intent);
                    }})
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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
        Button deleteButton = (Button) findViewById(R.id.delete);
        //Toast.makeText(getApplicationContext(), "word : "+ word, Toast.LENGTH_SHORT).show();


        deleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                LinearLayout child = (LinearLayout) inputGrid.getFocusedChild(); // get the text edit that currently holds the focus
                EditText current;
                EditText previousE;
                int tag1;
                int previous;


                if(child ==null){//if there is no focus
                    current = ((LinearLayout) inputGrid.getChildAt(0)).findViewById(R.id.input);
                    current.getText().clear();// if there is no focus then give it to the first edittext
                }
                else{
                    current = ((LinearLayout) inputGrid.getFocusedChild()).findViewById(R.id.input); //current child holding the focus

                    //if focused box is empty jump to prevoius
                    if(current.getText().toString().trim().length() ==0){
                        char char1 = (Character) current.getTag();
                        tag1=currentRow+Character.getNumericValue(char1);
                        previous = tag1-2;
                        //jump to prevoius
                        if(previous<0 || previous<currentRow){
                            ;
                        }
                        else {
                            previousE = ((LinearLayout) inputGrid.getChildAt(previous)).findViewById(R.id.input);
                            previousE.requestFocus();
                            currentWord= currentWord.substring(0,currentWord.length()-1);
                        }
                    }
                    else{
                        current.getText().clear();
                        current.requestFocus();
                    }
                }


            }



        });
        enterButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //when clicked check if input word is 5 characters
                //Toast.makeText(getApplicationContext(), "checking current Row : "+ currentRow, Toast.LENGTH_SHORT).show();
                if(currentRow ==25){
                    alertView("YOU LOST\n Correct word:"+word);
                }

                // then check if if eac editText is in the same place as the selected word(from java class)
                //request focus to the next row
                else {

                    if(currentWord.length()<5){
                        Toast.makeText(getApplicationContext(), "Enter a 5 letter word ", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        EditText nextRowFirst = ((LinearLayout) inputGrid.getChildAt(currentRow + 5)).findViewById(R.id.input);
                        EditText current = ((LinearLayout) inputGrid.getChildAt(currentRow + 4)).findViewById(R.id.input);
                        nextRowFirst.setFocusableInTouchMode(true);
                        nextRowFirst.requestFocus();
                        //Toast.makeText(getApplicationContext(), "checking current word : "+ currentWord, Toast.LENGTH_SHORT).show();

                        checkInputsWithWord(word, currentWord, buttonGrid, inputGrid, currentRow);
                        currentWord = "";//clearing word
                        currentRow += 5;
                    }
                }


            }

        });


    }

}
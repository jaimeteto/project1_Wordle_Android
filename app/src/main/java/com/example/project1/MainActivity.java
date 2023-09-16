package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button aButton2;
    GridView inputGrid;
    GridView buttonGrid;
    char inputs[]={'1','2','3','4','5','1','2','3','4','5','1','2','3','4','5'};
    char[] letters={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    EditText focusedEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //aButton2 = findViewById(R.id.aButton);
        //aButton2.setOnClickListener(this);

        inputGrid = (GridView) findViewById(R.id.grid1); // init GridView
        buttonGrid = (GridView) findViewById(R.id.grid2); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),inputs,1);
        CustomAdapter customAdapter2 = new CustomAdapter(getApplicationContext(),letters,2);
        inputGrid.setAdapter(customAdapter);
        buttonGrid.setAdapter(customAdapter2);
    }
    @Override
    public void onClick(View view){

        EditText nextView;
        EditText currentView;


    }
}